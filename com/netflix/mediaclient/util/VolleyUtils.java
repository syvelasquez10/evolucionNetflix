// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.client.model.RootCause;
import java.util.List;
import org.json.JSONObject;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import java.util.ArrayList;
import com.netflix.mediaclient.service.logging.client.model.Error;
import java.util.Locale;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.StatusCodeError;
import com.android.volley.NoConnectionError;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.NetworkResponse;
import java.util.Collections;
import com.android.volley.VolleyError;

public final class VolleyUtils
{
    public static int HTTP_STATUS_REQUEST_TIMEOUT = 0;
    private static final String TAG = "nf_volley";
    public static final VolleyError TIMEOUT_ERROR;
    
    static {
        VolleyUtils.HTTP_STATUS_REQUEST_TIMEOUT = 408;
        TIMEOUT_ERROR = new VolleyError(new NetworkResponse(VolleyUtils.HTTP_STATUS_REQUEST_TIMEOUT, new byte[0], Collections.emptyMap(), false));
    }
    
    public static NetflixStatus getStatus(final VolleyError volleyError, final ErrorLogging errorLogging, StatusCode statusCode) {
        final String message = volleyError.getMessage();
        if (volleyError instanceof FalkorException) {
            statusCode = FalkorException.getErrorCode(message, errorLogging);
        }
        else if (volleyError instanceof ServerError) {
            statusCode = StatusCode.SERVER_ERROR;
        }
        else if (volleyError instanceof TimeoutError) {
            statusCode = getStatusCodeFromVolleyNetworkError(volleyError);
        }
        else if (volleyError instanceof NetworkError) {
            statusCode = getStatusCodeFromVolleyNetworkError(volleyError);
        }
        else if (volleyError instanceof NoConnectionError) {
            statusCode = StatusCode.NO_CONNECTIVITY;
        }
        else if (volleyError instanceof StatusCodeError) {
            statusCode = ((StatusCodeError)volleyError).getStatusCode();
        }
        Log.d("nf_volley", "getStatus statusCode: " + statusCode);
        final NetflixStatus netflixStatus = new NetflixStatus(statusCode);
        netflixStatus.setError(ConsolidatedLoggingUtils.toError(volleyError));
        return netflixStatus;
    }
    
    private static StatusCode getStatusCodeFromVolleyNetworkError(final VolleyError volleyError) {
        final String message = volleyError.getMessage();
        StatusCode statusCode;
        if (message == null) {
            statusCode = StatusCode.NETWORK_ERROR;
        }
        else {
            final String lowerCase = message.toLowerCase(Locale.US);
            if (Log.isLoggable()) {
                Log.d("nf_volley", ".next call failed with error =" + lowerCase);
            }
            statusCode = StatusCode.NETWORK_ERROR;
            if (lowerCase.contains("sslhandshakeexception")) {
                statusCode = StatusCode.HTTP_SSL_ERROR;
                if (lowerCase.contains("current time") && lowerCase.contains("validation time")) {
                    return StatusCode.HTTP_SSL_DATE_TIME_ERROR;
                }
                if (lowerCase.contains("no trusted certificate found")) {
                    return StatusCode.HTTP_SSL_NO_VALID_CERT;
                }
            }
        }
        return statusCode;
    }
    
    private static boolean isNetworkResponseExist(final VolleyError volleyError) {
        return volleyError != null && volleyError.networkResponse != null;
    }
    
    public static Error toError(final VolleyError volleyError) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
        list.add(deepErrorElement);
        deepErrorElement.setDebug(debug);
        deepErrorElement.setFatal(true);
        deepErrorElement.setErrorCode(StatusCode.NETWORK_ERROR.name());
        while (true) {
            if (!isNetworkResponseExist(volleyError)) {
                break Label_0119;
            }
            try {
                final JSONObject message = new JSONObject();
                debug.setMessage(message);
                message.put("statusCode", volleyError.networkResponse.statusCode);
                if (volleyError.networkResponse.data != null) {
                    message.put("response", (Object)new String(volleyError.networkResponse.data, "UTF-8"));
                }
                if (volleyError != null && volleyError.getStackTrace() != null) {
                    debug.setStackTrace(android.util.Log.getStackTraceString((Throwable)volleyError));
                }
                return new Error(toRootCause(volleyError), list);
            }
            catch (Throwable t) {
                continue;
            }
            break;
        }
    }
    
    public static RootCause toRootCause(final VolleyError volleyError) {
        if (!isNetworkResponseExist(volleyError)) {
            return RootCause.networkFailure;
        }
        final int statusCode = volleyError.networkResponse.statusCode;
        if (statusCode >= 400 && statusCode < 500) {
            return RootCause.http4xx;
        }
        if (statusCode >= 500 && statusCode < 600) {
            return RootCause.http5xx;
        }
        final StatusCode statusCodeFromVolleyNetworkError = getStatusCodeFromVolleyNetworkError(volleyError);
        if (statusCodeFromVolleyNetworkError == StatusCode.HTTP_SSL_ERROR) {
            return RootCause.sslHandshakeFailure;
        }
        if (statusCodeFromVolleyNetworkError == StatusCode.HTTP_SSL_DATE_TIME_ERROR) {
            return RootCause.sslExpiredCert;
        }
        if (statusCodeFromVolleyNetworkError == StatusCode.HTTP_SSL_NO_VALID_CERT) {
            return RootCause.sslUntrustedCert;
        }
        return RootCause.networkFailure;
    }
}
