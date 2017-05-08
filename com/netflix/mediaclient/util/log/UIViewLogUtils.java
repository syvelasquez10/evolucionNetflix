// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.log;

import java.io.Serializable;
import com.netflix.mediaclient.service.logging.client.model.Error;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputMethod;
import com.netflix.mediaclient.service.logging.client.model.DataContext;
import com.netflix.mediaclient.servicemgr.IClientLogging$ModalView;
import com.netflix.mediaclient.servicemgr.UIViewLogging$UIViewCommandName;
import com.netflix.mediaclient.service.logging.uiview.model.CommandEndedEvent$InputValue;
import android.support.v4.content.LocalBroadcastManager;
import android.content.Intent;
import org.json.JSONObject;
import android.content.Context;

public final class UIViewLogUtils extends ConsolidatedLoggingUtils
{
    public static String MISSING_GUID;
    public static int MISSING_TRACK_ID;
    
    static {
        UIViewLogUtils.MISSING_TRACK_ID = 0;
        UIViewLogUtils.MISSING_GUID = null;
    }
    
    public static void reportLeftMenuImpressionEnded(final Context context, final boolean b) {
        sendImpressionBroadcast(context, "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_ENDED", b, null);
    }
    
    public static void reportLeftMenuImpressionStarted(final Context context, final JSONObject jsonObject) {
        sendImpressionBroadcast(context, "com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_IMPRESSION_START", null, jsonObject);
    }
    
    public static void reportLeftMenuUIViewCommandEnded(final Context context) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportLeftMenuUIViewCommandStarted(final Context context, final CommandEndedEvent$InputValue commandEndedEvent$InputValue) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_LEFT_PANEL_VIEW_CMD_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("inputValue", commandEndedEvent$InputValue.name());
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportNotificationImpressionEnded(final Context context, final boolean b) {
        sendImpressionBroadcast(context, "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_ENDED", b, null);
    }
    
    public static void reportNotificationImpressionStarted(final Context context, final JSONObject jsonObject) {
        sendImpressionBroadcast(context, "com.netflix.mediaclient.intent.action.LOG_NOTIFICATION_IMPRESSION_START", null, jsonObject);
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext) {
        reportUIViewCommand(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, dataContext, null);
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext, final String s) {
        reportUIViewCommandStarted(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, dataContext, s);
        reportUIViewCommandEnded(context);
    }
    
    public static void reportUIViewCommand(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final CommandEndedEvent$InputMethod commandEndedEvent$InputMethod, final DataContext dataContext) {
        reportUIViewCommandStarted(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, commandEndedEvent$InputMethod, dataContext, null, null);
        reportUIViewCommandEnded(context);
    }
    
    public static void reportUIViewCommandEnded(final Context context) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUIViewCommandStarted(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext, final String s) {
        reportUIViewCommandStarted(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, dataContext, s, null);
    }
    
    public static void reportUIViewCommandStarted(final Context context, final UIViewLogging$UIViewCommandName uiViewLogging$UIViewCommandName, final IClientLogging$ModalView clientLogging$ModalView, final DataContext dataContext, final String s, final JSONObject jsonObject) {
        reportUIViewCommandStarted(context, uiViewLogging$UIViewCommandName, clientLogging$ModalView, null, dataContext, s, jsonObject);
    }
    
    public static void reportUIViewCommandStarted(final Context context, UIViewLogging$UIViewCommandName string, final IClientLogging$ModalView clientLogging$ModalView, final CommandEndedEvent$InputMethod commandEndedEvent$InputMethod, final DataContext dataContext, final String s, final JSONObject jsonObject) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_CMD_START");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (clientLogging$ModalView != null) {
            intent.putExtra("view", clientLogging$ModalView.name());
        }
        if (commandEndedEvent$InputMethod != null) {
            intent.putExtra("inputMethod", commandEndedEvent$InputMethod.name());
        }
        if (string != null) {
            intent.putExtra("cmd", string.name());
        }
        Label_0104: {
            if (dataContext == null) {
                break Label_0104;
            }
            string = null;
            while (true) {
                try {
                    string = (UIViewLogging$UIViewCommandName)dataContext.toJSONObject().toString();
                    intent.putExtra("dataContext", (String)string);
                    if (StringUtils.isNotEmpty(s)) {
                        intent.putExtra("url", s);
                    }
                    if (jsonObject != null) {
                        intent.putExtra("model", jsonObject.toString());
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
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION_SESSION_ENDED");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("success", b);
        while (true) {
            if (error == null) {
                break Label_0053;
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
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent("com.netflix.mediaclient.intent.action.LOG_UIVIEW_IMPRESSION");
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        intent.putExtra("trackId", n);
        if (uiViewLogging$UIViewCommandName != null) {
            intent.putExtra("cmd", uiViewLogging$UIViewCommandName.name());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
    
    public static void reportUIViewImpressionStarted(final Context context, final IClientLogging$ModalView clientLogging$ModalView, final String s) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
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
    
    private static void sendImpressionBroadcast(final Context context, final String s, final Boolean b, final JSONObject jsonObject) {
        if (ConsolidatedLoggingUtils.isNull(context, "Context can not be null!")) {
            return;
        }
        final Intent intent = new Intent(s);
        intent.addCategory("com.netflix.mediaclient.intent.category.LOGGING");
        if (b != null) {
            intent.putExtra("success", (Serializable)b);
        }
        if (jsonObject != null) {
            intent.putExtra("model", jsonObject.toString());
        }
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
