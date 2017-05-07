// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import java.util.Iterator;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.ServerError;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Locale;
import com.android.volley.VolleyError;
import android.graphics.Point;
import android.view.WindowManager;
import com.netflix.mediaclient.service.logging.apm.model.Display;
import android.content.Context;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import java.util.UUID;
import java.util.ArrayList;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.JsonSerializer;
import android.content.Intent;

public abstract class ConsolidatedLoggingUtils
{
    private static final String JSON_DEBUG_MESSAGE = "bodyResponse";
    protected static final String TAG = "nf_log";
    
    protected static void addToIntent(final Intent intent, final String s, final JsonSerializer jsonSerializer) {
        final String stringSafely = toStringSafely(jsonSerializer);
        if (stringSafely != null) {
            intent.putExtra(s, stringSafely);
        }
    }
    
    protected static void addToIntent(final Intent intent, final String s, final List<FalcorPathResult> list) {
        try {
            final String jsonArray = FalcorPathResult.createJSONArray(list);
            if (jsonArray != null) {
                intent.putExtra(s, jsonArray);
            }
        }
        catch (Throwable t) {
            Log.e("nf_log", "addToIntent:: Failed to create JSON string for list of FalcorPathResult ", t);
        }
    }
    
    public static DeepErrorElement.Debug createDebug(final String stackTrace, final JSONObject message) {
        if (stackTrace == null && message == null) {
            return null;
        }
        final DeepErrorElement.Debug debug = new DeepErrorElement.Debug();
        debug.setMessage(message);
        debug.setStackTrace(stackTrace);
        return debug;
    }
    
    protected static List<DeepErrorElement> createDeepErrorList(final StatusCode statusCode, final String stackTrace) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setErrorCode(String.valueOf(statusCode.getValue()));
        final DeepErrorElement.Debug debug = new DeepErrorElement.Debug();
        debug.setStackTrace(stackTrace);
        deepErrorElement.setDebug(debug);
        list.add(deepErrorElement);
        return list;
    }
    
    public static String createGUID() {
        return String.valueOf(UUID.randomUUID());
    }
    
    public static HttpResponse createHttpResponse(final long n, final int n2) {
        final HttpResponse httpResponse = new HttpResponse();
        httpResponse.setResponseTime((int)n);
        httpResponse.setMimeType("text/x-json");
        httpResponse.setContentLength(n2);
        return httpResponse;
    }
    
    public static String createSessionLookupKey(final String s, final String s2) {
        return "" + s + "." + s2;
    }
    
    public static UIError createUIError(final Status status, final String s, final ActionOnUIError actionOnUIError) {
        if (status.getError() != null) {
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", "Error message already exist in status object " + status);
            }
            return createUIErrorFromError(status, s, actionOnUIError);
        }
        Log.d("nf_log", "Error message does NOT exist in status object");
        return createUIErrorFromStatus(status, s, actionOnUIError);
    }
    
    private static UIError createUIErrorFromError(final Status status, final String s, final ActionOnUIError actionOnUIError) {
        return new UIError(status.getError(), actionOnUIError, s);
    }
    
    private static UIError createUIErrorFromStatus(final Status status, final String s, final ActionOnUIError actionOnUIError) {
        final RootCause serverFailure = RootCause.serverFailure;
        final StatusCode statusCode = status.getStatusCode();
        final List<DeepErrorElement> deepErrorList = createDeepErrorList(statusCode, s);
        RootCause rootCause = null;
        switch (statusCode) {
            default: {
                rootCause = RootCause.unknownFailure;
                break;
            }
            case NRD_ERROR:
            case INTERNAL_ERROR: {
                rootCause = RootCause.clientFailure;
                break;
            }
            case FALCOR_RESPONSE_PARSE_ERROR: {
                rootCause = RootCause.serverResponseBad;
                break;
            }
            case NRD_LOGIN_ACTIONID_4:
            case NRD_LOGIN_ACTIONID_8: {
                rootCause = RootCause.clientRequestBad;
                break;
            }
            case SERVER_ERROR:
            case NRD_LOGIN_ACTIONID_1:
            case NRD_LOGIN_ACTIONID_2:
            case NRD_LOGIN_ACTIONID_3:
            case NRD_LOGIN_ACTIONID_5:
            case NRD_LOGIN_ACTIONID_6:
            case NRD_LOGIN_ACTIONID_7:
            case NRD_LOGIN_ACTIONID_9:
            case NRD_LOGIN_ACTIONID_10:
            case NRD_LOGIN_ACTIONID_11:
            case NRD_LOGIN_ACTIONID_12: {
                rootCause = RootCause.serverFailure;
                break;
            }
            case NETWORK_ERROR:
            case NO_CONNECTIVITY: {
                rootCause = RootCause.networkFailure;
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                rootCause = RootCause.sslExpiredCert;
                break;
            }
            case HTTP_SSL_ERROR: {
                rootCause = RootCause.sslHandshakeFailure;
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                rootCause = RootCause.sslUntrustedCert;
                break;
            }
        }
        return new UIError(rootCause, actionOnUIError, s, deepErrorList);
    }
    
    public static Display getDisplay(final Context context) {
        if (context != null) {
            final WindowManager windowManager = (WindowManager)context.getSystemService("window");
            if (windowManager != null) {
                final android.view.Display defaultDisplay = windowManager.getDefaultDisplay();
                final float refreshRate = defaultDisplay.getRefreshRate();
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Refresh rate: " + refreshRate);
                }
                float n = refreshRate;
                if (refreshRate < 10.0f) {
                    n = 60.0f;
                }
                final Point point = new Point();
                defaultDisplay.getSize(point);
                return new Display(Display.Connector.internal, null, point.x, point.y, (int)n, null);
            }
        }
        return null;
    }
    
    protected static RootCause getRootCauseFromVolleyNetworkError(final VolleyError volleyError) {
        final String message = volleyError.getMessage();
        RootCause rootCause;
        if (message == null) {
            rootCause = RootCause.networkFailure;
        }
        else {
            final String lowerCase = message.toLowerCase(Locale.US);
            if (Log.isLoggable("nf_log", 3)) {
                Log.d("nf_log", ".next call failed with error =" + lowerCase);
            }
            rootCause = RootCause.networkFailure;
            if (lowerCase.contains("sslhandshakeexception")) {
                rootCause = RootCause.sslHandshakeFailure;
                if (lowerCase.contains("current time") && lowerCase.contains("validation time")) {
                    return RootCause.sslExpiredCert;
                }
                if (lowerCase.contains("no trusted certificate found")) {
                    return RootCause.sslUntrustedCert;
                }
            }
        }
        return rootCause;
    }
    
    public static void pauseReporting(final Context context) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void resumeReporting(final Context context, final boolean b) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("flush", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static Error toError(final VolleyError volleyError) {
        final Error error = new Error();
        if (Log.isLoggable("nf_log", 5)) {
            Log.w("nf_log", "VolleyError: " + volleyError.getMessage());
        }
        if (volleyError.networkResponse != null && Log.isLoggable("nf_log", 3)) {
            Log.d("nf_log", "Error on response:" + new String(volleyError.networkResponse.data));
        }
    Label_0162:
        while (true) {
            while (true) {
                Label_0115: {
                    if (volleyError instanceof FalcorParseException) {
                        error.setRootCause(RootCause.serverResponseBad);
                        break Label_0115;
                    }
                    Label_0247: {
                        break Label_0247;
                        while (true) {
                            final JSONObject message = new JSONObject();
                            while (true) {
                                DeepErrorElement deepErrorElement = null;
                                Label_0330: {
                                    try {
                                        message.put("bodyResponse", (Object)new String(volleyError.networkResponse.data));
                                        deepErrorElement = new DeepErrorElement();
                                        final DeepErrorElement.Debug debug = new DeepErrorElement.Debug();
                                        debug.setStackTrace(android.util.Log.getStackTraceString((Throwable)volleyError));
                                        debug.setMessage(message);
                                        deepErrorElement.setDebug(debug);
                                        if (volleyError != null && volleyError.networkResponse != null) {
                                            deepErrorElement.setErrorCode("" + volleyError.networkResponse.statusCode);
                                            error.addDeepError(deepErrorElement);
                                            return error;
                                        }
                                        break Label_0330;
                                        // iftrue(Label_0281:, !volleyError instanceof ServerError)
                                        // iftrue(Label_0298:, !volleyError instanceof TimeoutError)
                                        // iftrue(Label_0115:, !volleyError instanceof NetworkError)
                                        // iftrue(Label_0264:, !volleyError instanceof FalcorServerException)
                                    Block_12:
                                        while (true) {
                                            error.setRootCause(RootCause.serverFailure);
                                            break;
                                            Block_13: {
                                                Block_11: {
                                                    break Block_11;
                                                    Label_0281: {
                                                        break Block_12;
                                                    }
                                                    Label_0298:
                                                    break Block_13;
                                                }
                                                error.setRootCause(RootCause.serverFailure);
                                                break;
                                            }
                                            error.setRootCause(getRootCauseFromVolleyNetworkError(volleyError));
                                            break;
                                            continue;
                                        }
                                        error.setRootCause(RootCause.tcpConnectionTimeout);
                                        break;
                                    }
                                    catch (Throwable t) {
                                        Log.e("nf_log", "Failed to add body response to JSON object", t);
                                        continue Label_0162;
                                    }
                                }
                                Log.e("nf_log", "Network response is not set!");
                                deepErrorElement.setErrorCode("504");
                                continue;
                            }
                        }
                    }
                }
                Log.d("nf_log", "Report data request failed");
                final JSONObject message = null;
                if (volleyError.networkResponse != null) {
                    continue;
                }
                break;
            }
            continue Label_0162;
        }
    }
    
    protected static Error toError(final VolleyError volleyError, final String s) {
        if (volleyError == null || volleyError.networkResponse == null) {
            return new Error(RootCause.unknownFailure, null);
        }
        final int statusCode = volleyError.networkResponse.statusCode;
        if (statusCode >= 400 && statusCode < 500) {
            return new Error(RootCause.http4xx, null);
        }
        if (statusCode == 500) {
            new Error(RootCause.serverFailure, null);
            return new Error(RootCause.serverFailure, null);
        }
        if (statusCode > 500 && statusCode < 600) {
            return new Error(RootCause.http5xx, null);
        }
        return new Error(RootCause.unknownFailure, null);
    }
    
    protected static Error toError(final StatusCode statusCode, final String s) {
        final Error error = null;
        Error error2 = null;
        switch (statusCode) {
            default: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report  generic failure for " + s + ", status code not found " + statusCode);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
            case OK:
            case NON_RECOMMENDED_APP_VERSION: {
                error2 = error;
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report success for " + s);
                    return null;
                }
                break;
            }
            case NETWORK_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report network error for " + s);
                }
                error2 = new Error(RootCause.networkFailure, null);
                break;
            }
            case NO_CONNECTIVITY: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report no connectivity for " + s);
                }
                error2 = new Error(RootCause.tcpNoRouteToHost, null);
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for " + s);
                }
                error2 = new Error(RootCause.sslExpiredCert, null);
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, date time error for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case HTTP_SSL_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, generic for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case SERVER_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report server error, generic for " + s);
                }
                error2 = new Error(RootCause.serverFailure, null);
                break;
            }
            case UNKNOWN: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report uknown error for " + s);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
        }
        return error2;
    }
    
    public static List<FalcorPathResult> toFalcorPathResultList(final List<String> list) {
        int size = 0;
        if (list != null) {
            size = list.size();
        }
        final ArrayList list2 = new ArrayList<FalcorPathResult>(size);
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(new FalcorPathResult(iterator.next(), false, null));
            }
        }
        return (List<FalcorPathResult>)list2;
    }
    
    protected static String toStringSafely(final JsonSerializer jsonSerializer) {
        if (jsonSerializer == null) {
            return null;
        }
        try {
            return jsonSerializer.toJSONObject().toString();
        }
        catch (Throwable t) {
            Log.e("nf_log", "toStringSafely:: Failed to create JSON string for event " + jsonSerializer, t);
            return null;
        }
    }
    
    public static UIError toUIError(final StatusCode statusCode) {
        StatusCode unknown = statusCode;
        if (statusCode == null) {
            unknown = StatusCode.UNKNOWN;
        }
        UIError uiError = null;
        switch (unknown) {
            default: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report  generic failure for, status code not found " + unknown);
                }
                uiError = new UIError(RootCause.unknownFailure, null, null, null);
                break;
            }
            case OK:
            case NON_RECOMMENDED_APP_VERSION: {
                Log.d("nf_log", "Report success");
                return null;
            }
            case NETWORK_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report network error for");
                }
                uiError = new UIError(RootCause.networkFailure, null, null, null);
                break;
            }
            case NO_CONNECTIVITY: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report no connectivity for");
                }
                uiError = new UIError(RootCause.tcpNoRouteToHost, null, null, null);
                break;
            }
            case HTTP_SSL_NO_VALID_CERT: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for");
                }
                uiError = new UIError(RootCause.sslExpiredCert, null, null, null);
                break;
            }
            case HTTP_SSL_DATE_TIME_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, date time error for");
                }
                uiError = new UIError(RootCause.sslUntrustedCert, null, null, null);
                break;
            }
            case HTTP_SSL_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, generic for");
                }
                uiError = new UIError(RootCause.sslUntrustedCert, null, null, null);
                break;
            }
            case SERVER_ERROR: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report server error, generic for");
                }
                uiError = new UIError(RootCause.serverFailure, null, null, null);
                break;
            }
            case UNKNOWN: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report uknown error for");
                }
                uiError = new UIError(RootCause.unknownFailure, null, null, null);
                break;
            }
        }
        return uiError;
    }
    
    protected static void validateArgument(final Object o, final String s) {
        if (o == null) {
            Log.e("nf_log", s);
            throw new IllegalArgumentException(s);
        }
    }
}
