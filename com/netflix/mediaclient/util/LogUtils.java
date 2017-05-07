// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.service.logging.client.model.ActionOnUIError;
import com.netflix.mediaclient.service.logging.client.model.DeepErrorElement;
import com.netflix.mediaclient.service.logging.client.model.RootCause;
import com.netflix.mediaclient.service.logging.client.model.HttpResponse;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.servicemgr.ApplicationPerformanceMetricsLogging;
import com.netflix.mediaclient.servicemgr.UserActionLogging;
import org.json.JSONException;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Context;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.FalcorPathResult;
import java.util.List;
import com.netflix.mediaclient.service.logging.JsonSerializer;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;

public final class LogUtils
{
    private static final String TAG = "nf_log";
    
    private static void addToIntent(final Intent intent, final String s, final JsonSerializer jsonSerializer) {
        final String stringSafely = toStringSafely(jsonSerializer);
        if (stringSafely != null) {
            intent.putExtra(s, stringSafely);
        }
    }
    
    private static void addToIntent(final Intent intent, final String s, final List<FalcorPathResult> list) {
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
    
    public static String createSessionLookupKey(final String s, final String s2) {
        return "" + s + "." + s2;
    }
    
    public static void pauseReporting(final Context context) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportAddToQueueActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("title_rank", (int)n);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddToQueueActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_ADD_TO_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportAssetRequest(final String s, final IClientLogging.AssetType assetType, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            applicationPerformanceMetricsLogging.startAssetRequestSession(s, assetType);
        }
    }
    
    public static void reportAssetRequestFailure(final String s, final VolleyError volleyError, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, IClientLogging.CompletionReason.failed, null, toError(volleyError, s));
        }
    }
    
    public static void reportAssetRequestResult(final String s, final int n, final ApplicationPerformanceMetricsLogging applicationPerformanceMetricsLogging) {
        if (applicationPerformanceMetricsLogging == null) {
            Log.e("nf_log", "APM is unavailable, can not report asset request result");
        }
        else if (!StringUtils.isEmpty(s)) {
            IClientLogging.CompletionReason completionReason = IClientLogging.CompletionReason.failed;
            final Error error = toError(n, s);
            if (error == null) {
                completionReason = IClientLogging.CompletionReason.success;
            }
            applicationPerformanceMetricsLogging.endAssetRequestSession(s, completionReason, null, error);
        }
    }
    
    public static void reportDataRequestEnded(final Context context, final String s, final IClientLogging.CompletionReason completionReason, final List<FalcorPathResult> list, final Error error, final HttpResponse httpResponse) {
        validateArgument(context, "Context can not be null!");
        validateArgument(s, "Request ID can not be null!");
        validateArgument(completionReason, "Completion reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("reason", completionReason.name());
        addToIntent(intent, "error", error);
        addToIntent(intent, "http_response", httpResponse);
        addToIntent(intent, "falcorPathResults", list);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDataRequestStarted(final Context context, final String s, final String s2) {
        validateArgument(context, "Context can not be null!");
        validateArgument(s, "Request ID can not be null!");
        validateArgument(s2, "Request URL can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_DATA_REQUEST_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("request_id", s);
        intent.putExtra("url", s2);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportLoginActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0058;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportLoginActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_LOGIN_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportNavigationActionEnded(final Context context, final IClientLogging.ModalView modalView, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        if (modalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0079;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportNavigationActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        if (modalView == null) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_NAVIGATION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportPlayActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportPlayActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_START_PLAY_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRateActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError, final Integer n, final Integer n2) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0062;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                if (n != null) {
                    intent.putExtra("rank", (int)n);
                }
                if (n2 != null) {
                    intent.putExtra("rating", (int)n2);
                }
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRateActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_RATE_TITLE_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRemoveFromQueueActionEnded(final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0058;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRemoveFromQueueActionStarted(final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_REMOVE_FROM_PLAYLIST_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportSearchActionEnded(final long n, final Context context, final IClientLogging.CompletionReason completionReason, final UIError uiError) {
        validateArgument(context, "Context can not be null!");
        validateArgument(completionReason, "Reason can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", completionReason.name());
        intent.putExtra("id", n);
        while (true) {
            if (uiError == null) {
                break Label_0075;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportSearchActionStarted(final long n, final Context context, final UserActionLogging.CommandName commandName, final IClientLogging.ModalView modalView, final String s) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIA_SEARCH_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        intent.putExtra("id", n);
        if (commandName != null) {
            intent.putExtra("cmd", commandName.name());
        }
        if (s != null) {
            intent.putExtra("term", s);
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUiModalViewChanged(final Context context, final IClientLogging.ModalView modalView) {
        validateArgument(context, "Context can not be null!");
        validateArgument(modalView, "View can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_APM_UI_MODAL_VIEW_CHANGED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("view", modalView.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void resumeReporting(final Context context, final boolean b) {
        validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_PAUSE_EVENTS_DELIVERY");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("flush", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    private static Error toError(final int n, final String s) {
        Error error = null;
        Error error2 = null;
        switch (n) {
            default: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report  generic failure for " + s + ", status code not found " + n);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
            case 0:
            case 1: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report success for " + s);
                    return null;
                }
                return error;
            }
            case -3: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report network error for " + s);
                }
                error2 = new Error(RootCause.networkFailure, null);
                break;
            }
            case -11: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report no connectivity for " + s);
                }
                error2 = new Error(RootCause.tcpNoRouteToHost, null);
                break;
            }
            case -122: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, no valid certificate for " + s);
                }
                error2 = new Error(RootCause.sslExpiredCert, null);
                break;
            }
            case -121: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, date time error for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case -120: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report SSL error, generic for " + s);
                }
                error2 = new Error(RootCause.sslUntrustedCert, null);
                break;
            }
            case -62: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report server error, generic for " + s);
                }
                error2 = new Error(RootCause.serverFailure, null);
                break;
            }
            case -1: {
                if (Log.isLoggable("nf_log", 3)) {
                    Log.d("nf_log", "Report uknown error for " + s);
                }
                error2 = new Error(RootCause.unknownFailure, null);
                break;
            }
        }
        error = error2;
        return error;
    }
    
    private static Error toError(final VolleyError volleyError, final String s) {
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
    
    private static String toStringSafely(final JsonSerializer jsonSerializer) {
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
    
    private static void validateArgument(final Object o, final String s) {
        if (o == null) {
            Log.e("nf_log", s);
            throw new IllegalArgumentException(s);
        }
    }
    
    public static class LogReportErrorArgs
    {
        private UIError error;
        private IClientLogging.CompletionReason reason;
        private int statusCode;
        
        public LogReportErrorArgs(final int statusCode, final ActionOnUIError actionOnUIError, final String s, final List<DeepErrorElement> list) {
            this.statusCode = statusCode;
            this.populate(actionOnUIError, s, list);
        }
        
        private void populate(final ActionOnUIError actionOnUIError, final String s, final List<DeepErrorElement> list) {
            final Error access$000 = toError(this.statusCode, "na");
            if (access$000 != null) {
                this.error = new UIError(access$000.getRootCause(), actionOnUIError, s, list);
                this.reason = IClientLogging.CompletionReason.failed;
                return;
            }
            this.reason = IClientLogging.CompletionReason.success;
        }
        
        public UIError getError() {
            return this.error;
        }
        
        public IClientLogging.CompletionReason getReason() {
            return this.reason;
        }
        
        public int getStatusCode() {
            return this.statusCode;
        }
    }
}
