// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.Log;
import java.util.Locale;
import com.netflix.mediaclient.util.log.ConsolidatedLoggingUtils;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.android.volley.VolleyError;

public final class VolleyUtils
{
    private static final String TAG = "nf_volley";
    
    public static NetflixStatus getStatus(final VolleyError volleyError, final ErrorLogging errorLogging) {
        final StatusCode internal_ERROR = StatusCode.INTERNAL_ERROR;
        StatusCode statusCode;
        if (volleyError instanceof FalcorParseException) {
            statusCode = FalcorParseException.getErrorCode(((FalcorParseException)volleyError).getMessage());
        }
        else if (volleyError instanceof FalcorServerException) {
            statusCode = FalcorServerException.getErrorCode(((FalcorServerException)volleyError).getMessage(), errorLogging);
        }
        else if (volleyError instanceof ServerError) {
            statusCode = StatusCode.SERVER_ERROR;
        }
        else if (volleyError instanceof TimeoutError) {
            statusCode = getStatusCodeFromVolleyNetworkError(volleyError);
        }
        else {
            statusCode = internal_ERROR;
            if (volleyError instanceof NetworkError) {
                statusCode = getStatusCodeFromVolleyNetworkError(volleyError);
            }
        }
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
            if (Log.isLoggable("nf_volley", 5)) {
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
}
