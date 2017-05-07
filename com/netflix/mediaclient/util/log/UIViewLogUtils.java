// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import android.content.Context;

public final class UIViewLogUtils extends ConsolidatedLoggingUtils
{
    public static String MISSING_GUID;
    public static int MISSING_TRACK_ID;
    
    static {
        UIViewLogUtils.MISSING_TRACK_ID = 0;
        UIViewLogUtils.MISSING_GUID = null;
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext) {
        reportUIViewCommand(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, dataContext, null);
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext, final String s) {
        reportUIViewCommandStarted(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, dataContext, s);
        reportUIViewCommandEnded(context);
    }
    
    public static void reportUIViewCommandEnded(final Context context) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUIViewCommandStarted(final Context context, UIViewLogging$UIViewCommandName string, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext, final String s) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        if (string != null) {
            intent.putExtra("cmd", string.name());
        }
        Label_0082: {
            if (dataContext == null) {
                break Label_0082;
            }
            string = null;
            while (true) {
                try {
                    string = (UIViewLogging$UIViewCommandName)dataContext.toJSONObject().toString();
                    intent.putExtra("dataContext", (String)string);
                    if (StringUtils.isNotEmpty(s)) {
                        intent.putExtra("url", s);
                    }
                    LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
                }
                catch (JSONException ex) {
                    Log.w("nf_log", "failed to create dataContext: " + dataContext.toString());
                    continue;
                }
                break;
            }
        }
    }
    
    public static void reportUIViewImpressionEnded(final Context context, final boolean b, final Error error) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("success", b);
        while (true) {
            if (error == null) {
                break Label_0049;
            }
            try {
                intent.putExtra("error", error.toJSONObject().toString());
                LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
            }
            catch (JSONException ex) {
                Log.e("nf_log", "Failed to put an errror", (Throwable)ex);
                continue;
            }
            break;
        }
    }
    
    public static void reportUIViewImpressionEvent(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final int n) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("trackId", n);
        if (uiViewLogging$UIViewCommandName != null) {
            intent.putExtra("cmd", uiViewLogging$UIViewCommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUIViewImpressionStarted(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        ConsolidatedLoggingUtils.validateArgument(context, "Context can not be null!");
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_STARTED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (s != null) {
            intent.putExtra("guid", s);
        }
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
