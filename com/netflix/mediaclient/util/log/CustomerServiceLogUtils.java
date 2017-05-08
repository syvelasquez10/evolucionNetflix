// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$EntryPoint;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$Action;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.logging.client.model.Error;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$TerminationReason;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$CallQuality;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.servicemgr.CustomerServiceLogging$ReturnToDialScreenFrom;
import com.netflix.mediaclient.service.logging.apm.model.Orientation;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import android.content.Context;

public final class CustomerServiceLogUtils extends ConsolidatedLoggingUtils
{
    public static void reportBackToDialScreen(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final Orientation orientation, final CustomerServiceLogging$ReturnToDialScreenFrom customerServiceLogging$ReturnToDialScreenFrom) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") && !ConsolidatedLoggingUtils.isNull(orientation, "Orientation can not be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_BACK_TO");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            if (clientLogging$ModalView != null) {
                intent.putExtra("source", clientLogging$ModalView.name());
            }
            intent.putExtra("orientation", orientation.name());
            if (customerServiceLogging$ReturnToDialScreenFrom != null) {
                intent.putExtra("using", customerServiceLogging$ReturnToDialScreenFrom.name());
            }
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportCallConnected(final Context context, final CustomerServiceLogging$CallQuality customerServiceLogging$CallQuality) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") && !ConsolidatedLoggingUtils.isNull(customerServiceLogging$CallQuality, "Initial call quality can not be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_CONNECTED");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("call_quality", customerServiceLogging$CallQuality.name());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportCallQualityChanged(final Context context, final CustomerServiceLogging$CallQuality customerServiceLogging$CallQuality) {
        if (!ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") && !ConsolidatedLoggingUtils.isNull(customerServiceLogging$CallQuality, "New call quality can not be null!")) {
            final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_QUALITY_CHANGED");
            intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
            intent.putExtra("call_quality", customerServiceLogging$CallQuality.name());
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
        }
    }
    
    public static void reportCallSessionEnded(final Context context, final CustomerServiceLogging$TerminationReason customerServiceLogging$TerminationReason, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!") || ConsolidatedLoggingUtils.isNull(customerServiceLogging$TerminationReason, "Termination reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        intent.putExtra("terminationReason", customerServiceLogging$TerminationReason.name());
        while (true) {
            if (error == null) {
                break Label_0090;
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
    
    public static void reportCallSessionStarted(final Context context, final String s, final boolean b) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_SESSION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("uuid", s);
        intent.putExtra("displayed", b);
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportExitFromDialScreen(final Context context, final CustomerServiceLogging$Action customerServiceLogging$Action) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_CALL_UI_EXIT");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (customerServiceLogging$Action != null) {
            intent.putExtra("action", customerServiceLogging$Action.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportHelpRequestSessionEnded(final Context context, final CustomerServiceLogging$Action customerServiceLogging$Action, final String s, final IClientLogging$CompletionReason clientLogging$CompletionReason, final Error error) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!") || ConsolidatedLoggingUtils.isNull(clientLogging$CompletionReason, "Reason can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("reason", clientLogging$CompletionReason.name());
        if (customerServiceLogging$Action != null) {
            intent.putExtra("action", customerServiceLogging$Action.name());
        }
        if (StringUtils.isNotEmpty(s)) {
            intent.putExtra("url", s);
        }
        while (true) {
            if (error == null) {
                break Label_0103;
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
    
    public static void reportHelpRequestSessionStarted(final Context context, final CustomerServiceLogging$EntryPoint customerServiceLogging$EntryPoint) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_CS_HELP_SESSION_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (customerServiceLogging$EntryPoint != null) {
            intent.putExtra("entry", customerServiceLogging$EntryPoint.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
