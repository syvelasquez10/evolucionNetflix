// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Context;

public final class OfflineLogUtils extends ConsolidatedLoggingUtils
{
    public static void reportAddCachedVideoEnded(final Context context, final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!") || ConsolidatedLoggingUtils.isNull(s, "OXID can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("oxid", s);
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        while (true) {
            if (error == null) {
                break Label_0105;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportAddCachedVideoStart(final Context context, final String s) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_ADD_CACHED_VIDEO_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("oxid", s);
        intent.putExtra("cmd", UserActionLogging$CommandName.AddCachedVideoCommand.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportCachedPlayEnded(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        while (true) {
            if (error == null) {
                break Label_0085;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportCachedPlayStart(final Context context, final String s, final String s2, final int n, final int n2, final int n3) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_CACHED_PLAY_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("oxid", s);
        intent.putExtra("videoid", s2);
        intent.putExtra("runtime", n);
        intent.putExtra("logicalStart", n2);
        intent.putExtra("logicalEnd", n3);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportDownloadEnded(final Context context, final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("dxid", s);
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        while (true) {
            if (error == null) {
                break Label_0096;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportDownloadStart(final Context context, final String s, final boolean b) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_DOWNLOAD_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("dxid", s);
        if (b) {
            intent.putExtra("cmd", UserActionLogging$CommandName.RetryDownloadCommand.name());
        }
        else {
            intent.putExtra("cmd", UserActionLogging$CommandName.ResumeDownloadCommand.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportRemoveCachedVideoEnded(final Context context, final String s, final IClientLogging$ModalView clientLogging$ModalView, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("oxid", s);
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        while (true) {
            if (error == null) {
                break Label_0096;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to get JSON string from UIError", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportRemoveCachedVideoStart(final Context context, final String s) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_OFFLINE_REMOVE_CACHED_VIDEO_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("oxid", s);
        intent.putExtra("cmd", UserActionLogging$CommandName.RemoveCachedVideoCommand.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
