// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import java.util.Iterator;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.NetworkError;
import com.netflix.mediaclient.service.webclient.volley.FalkorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.logging.client.model.Error;
import android.support.v4.content.LocalBroadcastManager;
import java.util.Locale;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.service.logging.apm.model.Display$ScanMode;
import com.netflix.mediaclient.service.logging.apm.model.Display$AspectRatio;
import com.netflix.mediaclient.service.logging.apm.model.Display$Connector;
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
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement$Debug;
import org.json.JSONObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.FalkorPathResult;
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
    
    protected static void addToIntent(final Intent intent, final String s, final List<FalkorPathResult> list) {
        try {
            final String jsonArray = FalkorPathResult.createJSONArray(list);
            if (jsonArray != null) {
                intent.putExtra(s, jsonArray);
            }
        }
        catch (Throwable t) {
            Log.e("nf_log", "addToIntent:: Failed to create JSON string for list of FalkorPathResult ", t);
        }
    }
    
    public static DeepErrorElement$Debug createDebug(final String stackTrace, final JSONObject message) {
        if (stackTrace == null && message == null) {
            return null;
        }
        final DeepErrorElement$Debug deepErrorElement$Debug = new DeepErrorElement$Debug();
        deepErrorElement$Debug.setMessage(message);
        deepErrorElement$Debug.setStackTrace(stackTrace);
        return deepErrorElement$Debug;
    }
    
    protected static List<DeepErrorElement> createDeepErrorList(final StatusCode statusCode, final String stackTrace) {
        final ArrayList<DeepErrorElement> list = new ArrayList<DeepErrorElement>();
        final DeepErrorElement deepErrorElement = new DeepErrorElement();
        deepErrorElement.setErrorCode(String.valueOf(statusCode.getValue()));
        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
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
            if (Log.isLoggable()) {
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
        switch (ConsolidatedLoggingUtils$1.$SwitchMap$com$netflix$mediaclient$StatusCode[statusCode.ordinal()]) {
            default: {
                rootCause = RootCause.unknownFailure;
                break;
            }
            case 10:
            case 11: {
                rootCause = RootCause.clientFailure;
                break;
            }
            case 12: {
                rootCause = RootCause.serverResponseBad;
                break;
            }
            case 13:
            case 14: {
                rootCause = RootCause.clientRequestBad;
                break;
            }
            case 8:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 20:
            case 21:
            case 22:
            case 23:
            case 24: {
                rootCause = RootCause.serverFailure;
                break;
            }
            case 3:
            case 4: {
                rootCause = RootCause.networkFailure;
                break;
            }
            case 6: {
                rootCause = RootCause.sslExpiredCert;
                break;
            }
            case 7: {
                rootCause = RootCause.sslHandshakeFailure;
                break;
            }
            case 5: {
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
                float refreshRate = defaultDisplay.getRefreshRate();
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Refresh rate: " + refreshRate);
                }
                if (refreshRate < 10.0f) {
                    refreshRate = 60.0f;
                }
                final Point point = new Point();
                defaultDisplay.getSize(point);
                return new Display(Display$Connector.internal, null, point.x, point.y, (int)refreshRate, null);
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
            if (Log.isLoggable()) {
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
        if (Log.isLoggable()) {
            Log.w("nf_log", "VolleyError: " + volleyError.getMessage());
        }
        if (volleyError.networkResponse != null && Log.isLoggable()) {
            Log.d("nf_log", "Error on response:" + new String(volleyError.networkResponse.data));
        }
    Label_0156:
        while (true) {
            while (true) {
                Label_0109: {
                    if (volleyError instanceof FalkorParseException) {
                        error.setRootCause(RootCause.serverResponseBad);
                        break Label_0109;
                    }
                    Label_0241: {
                        break Label_0241;
                        while (true) {
                            final JSONObject message = new JSONObject();
                            while (true) {
                                DeepErrorElement deepErrorElement = null;
                                Label_0324: {
                                    try {
                                        message.put("bodyResponse", (Object)new String(volleyError.networkResponse.data));
                                        deepErrorElement = new DeepErrorElement();
                                        final DeepErrorElement$Debug debug = new DeepErrorElement$Debug();
                                        debug.setStackTrace(android.util.Log.getStackTraceString((Throwable)volleyError));
                                        debug.setMessage(message);
                                        deepErrorElement.setDebug(debug);
                                        if (volleyError != null && volleyError.networkResponse != null) {
                                            deepErrorElement.setErrorCode("" + volleyError.networkResponse.statusCode);
                                            error.addDeepError(deepErrorElement);
                                            return error;
                                        }
                                        break Label_0324;
                                        // iftrue(Label_0258:, !volleyError instanceof FalkorServerException)
                                        // iftrue(Label_0109:, !volleyError instanceof NetworkError)
                                        // iftrue(Label_0292:, !volleyError instanceof TimeoutError)
                                        while (true) {
                                            Block_10: {
                                                break Block_10;
                                                error.setRootCause(RootCause.serverFailure);
                                                break;
                                            Block_13_Outer:
                                                while (true) {
                                                    error.setRootCause(RootCause.tcpConnectionTimeout);
                                                    break;
                                                    while (true) {
                                                        error.setRootCause(getRootCauseFromVolleyNetworkError(volleyError));
                                                        break;
                                                        Label_0292: {
                                                            continue;
                                                        }
                                                    }
                                                    Label_0275: {
                                                        continue Block_13_Outer;
                                                    }
                                                }
                                            }
                                            error.setRootCause(RootCause.serverFailure);
                                            break;
                                            Label_0258: {
                                                continue;
                                            }
                                        }
                                    }
                                    // iftrue(Label_0275:, !volleyError instanceof ServerError)
                                    catch (Throwable t) {
                                        Log.e("nf_log", "Failed to add body response to JSON object", t);
                                        continue Label_0156;
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
            continue Label_0156;
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
        switch (ConsolidatedLoggingUtils$1.$SwitchMap$com$netflix$mediaclient$StatusCode[statusCode.ordinal()]) {
            default: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report  generic failure for " + s + ", status code not found " + statusCode);
                }
                return new Error(RootCause.unknownFailure, null);
            }
            case 1:
            case 2: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report success for " + s);
                }
                return null;
            }
            case 3: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report network error for " + s);
                }
                return new Error(RootCause.networkFailure, null);
            }
            case 4: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report no connectivity for " + s);
                }
                return new Error(RootCause.tcpNoRouteToHost, null);
            }
            case 5: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for " + s);
                }
                return new Error(RootCause.sslExpiredCert, null);
            }
            case 6: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, date time error for " + s);
                }
                return new Error(RootCause.sslUntrustedCert, null);
            }
            case 7: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, generic for " + s);
                }
                return new Error(RootCause.sslUntrustedCert, null);
            }
            case 8: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report server error, generic for " + s);
                }
                return new Error(RootCause.serverFailure, null);
            }
            case 9: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report uknown error for " + s);
                }
                return new Error(RootCause.unknownFailure, null);
            }
        }
    }
    
    public static List<FalkorPathResult> toFalkorPathResultList(final List<String> list) {
        int size;
        if (list != null) {
            size = list.size();
        }
        else {
            size = 0;
        }
        final ArrayList list2 = new ArrayList<FalkorPathResult>(size);
        if (list != null) {
            final Iterator<String> iterator = list.iterator();
            while (iterator.hasNext()) {
                list2.add(new FalkorPathResult(iterator.next(), false, null));
            }
        }
        return (List<FalkorPathResult>)list2;
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
        switch (ConsolidatedLoggingUtils$1.$SwitchMap$com$netflix$mediaclient$StatusCode[unknown.ordinal()]) {
            default: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report  generic failure for, status code not found " + unknown);
                }
                return new UIError(RootCause.unknownFailure, null, null, null);
            }
            case 1:
            case 2: {
                Log.d("nf_log", "Report success");
                return null;
            }
            case 3: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report network error for");
                }
                return new UIError(RootCause.networkFailure, null, null, null);
            }
            case 4: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report no connectivity for");
                }
                return new UIError(RootCause.tcpNoRouteToHost, null, null, null);
            }
            case 5: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for");
                }
                return new UIError(RootCause.sslExpiredCert, null, null, null);
            }
            case 6: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, date time error for");
                }
                return new UIError(RootCause.sslUntrustedCert, null, null, null);
            }
            case 7: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report SSL error, generic for");
                }
                return new UIError(RootCause.sslUntrustedCert, null, null, null);
            }
            case 8: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report server error, generic for");
                }
                return new UIError(RootCause.serverFailure, null, null, null);
            }
            case 9: {
                if (Log.isLoggable()) {
                    Log.d("nf_log", "Report uknown error for");
                }
                return new UIError(RootCause.unknownFailure, null, null, null);
            }
        }
    }
    
    protected static void validateArgument(final Object o, final String s) {
        if (o == null) {
            Log.e("nf_log", s);
            throw new IllegalArgumentException(s);
        }
    }
}
