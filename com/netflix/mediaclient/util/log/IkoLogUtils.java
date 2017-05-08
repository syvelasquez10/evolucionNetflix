// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import android.content.Context;

public class IkoLogUtils extends ConsolidatedLoggingUtils
{
    public static void reportIkoModeEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_MODE_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0065;
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
    
    public static void reportIkoModeStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") && !ConsolidatedLoggingUtils.isNull(clientLogging$ModalView, "View can not be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_MODE_START");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("view", clientLogging$ModalView.name());
            if (userActionLogging$CommandName != null) {
                intent.putExtra("cmd", userActionLogging$CommandName.name());
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportIkoMomentEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError, String s, final String s2, final int n, final String s3) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0069;
            }
            try {
                intent.putExtra("error", uiError.toJSONObject().toString());
                String s4;
                if ((s4 = s) == null) {
                    s4 = "unknown";
                }
                String s5;
                if ((s5 = s2) == null) {
                    s5 = "unknown";
                }
                if ((s = s3) == null) {
                    s = "unknown";
                }
                intent.putExtra("momentId", s4);
                intent.putExtra("momentType", s5);
                intent.putExtra("expectedVideoOffset", n);
                intent.putExtra("ikoMomentState", s);
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportIkoMomentStarted(final Context context, final UserActionLogging$CommandName userActionLogging$CommandName, final IClientLogging$ModalView clientLogging$ModalView) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") && !ConsolidatedLoggingUtils.isNull(clientLogging$ModalView, "View can not be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_MOMENT_START");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("view", clientLogging$ModalView.name());
            if (userActionLogging$CommandName != null) {
                intent.putExtra("cmd", userActionLogging$CommandName.name());
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportIkoVideoLoadEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final UIError uiError) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context cannot be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason cannot be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_END");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        while (true) {
            if (uiError == null) {
                break Label_0065;
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
    
    public static void reportIkoVideoLoadStarted(final Context context, final String s) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context cannot be null!") && !ConsolidatedLoggingUtils.isNull(s, "Url cannot be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_LOAD_START");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("url", s);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportIkoVideoPlaybackEnded(final Context context, final IClientLogging$CompletionReason clientLogging$CompletionReason, final int n, final int n2) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context cannot be null!") && !ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason cannot be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_END");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("reason", clientLogging$CompletionReason.name());
            intent.putExtra("errorCode", n);
            intent.putExtra("errorSubCode", n2);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportIkoVideoPlaybackStarted(final Context context, final String s) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context cannot be null!") && !ConsolidatedLoggingUtils.isNull(s, "Url cannot be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_IKO_VIDEO_PLAYBACK_START");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("url", s);
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
}
