// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice.logging;

import java.util.Iterator;
import com.netflix.mediaclient.service.logging.client.model.UIError;
import com.netflix.mediaclient.servicemgr.IClientLogging$CompletionReason;
import com.netflix.mediaclient.util.log.UserActionLogUtils;
import com.netflix.mediaclient.servicemgr.UserActionLogging$CommandName;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import android.os.Bundle;
import android.appwidget.AppWidgetManager;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import android.annotation.TargetApi;

@TargetApi(16)
public final class PServiceLogging
{
    public static final String EXTRA_PREAPP_EVENT_TIME = "eventTime";
    public static final String EXTRA_PREAPP_WIDGET_DATA = "widgetData";
    private static final Object PServiceLoggingLock;
    
    static {
        PServiceLoggingLock = new Object();
    }
    
    private static String getStoredLogEventsString(final Context context) {
        return PreferenceUtils.getStringPref(context, "pservice_actions_to_log", null);
    }
    
    public static PreAppWidgetSize getWidgetSize(final Context context, final int n) {
        final Bundle appWidgetOptions = AppWidgetManager.getInstance(context.getApplicationContext()).getAppWidgetOptions(n);
        return new PreAppWidgetSize(appWidgetOptions.getInt("appWidgetMinWidth"), appWidgetOptions.getInt("appWidgetMaxWidth"), appWidgetOptions.getInt("appWidgetMinHeight"), appWidgetOptions.getInt("appWidgetMaxHeight"));
    }
    
    private static void putLogEvents(final Context context, final String s) {
        PreferenceUtils.putStringPref(context, "pservice_actions_to_log", s);
    }
    
    private static String readAndClearStoredLog(final Context context) {
        synchronized (PServiceLogging.PServiceLoggingLock) {
            final String storedLogEventsString = getStoredLogEventsString(context);
            putLogEvents(context, null);
            return storedLogEventsString;
        }
    }
    
    public static void reportStoredLogEvents(final Context context, final boolean b) {
        for (final PServiceWidgetLogEvent pServiceWidgetLogEvent : PServiceLogEvents.createFromJsonString(readAndClearStoredLog(context)).getWidgetEvents()) {
            switch (PServiceLogging$1.$SwitchMap$com$netflix$mediaclient$service$pservice$logging$PServiceWidgetLogEvent$WidgetAction[pServiceWidgetLogEvent.action.ordinal()]) {
                default: {
                    continue;
                }
                case 1: {
                    ApmLogUtils.reportPreappAddWidget(context, PreAppWidgetLogData.createInstance(context, pServiceWidgetLogEvent.widgetSize, b).toJsonString(), pServiceWidgetLogEvent.timeInMs);
                    continue;
                }
                case 2: {
                    ApmLogUtils.reportPreappDeleteWidget(context, PreAppWidgetLogData.createInstance(context, pServiceWidgetLogEvent.widgetSize, b).toJsonString(), pServiceWidgetLogEvent.timeInMs);
                    continue;
                }
                case 3: {
                    UserActionLogUtils.reportPreAppWidgetActionStarted(context, UserActionLogging$CommandName.androidWidgetCommand, PreAppWidgetLogData.createInstance(context, pServiceWidgetLogEvent.widgetSize, b), new PreAppWidgetLogActionData(PreAppWidgetLogActionData$PreAppWidgetActionName.GO_TO_NEXT));
                    UserActionLogUtils.reportPreAppWidgetActionEnded(context, IClientLogging$CompletionReason.success, null);
                    continue;
                }
            }
        }
    }
    
    public static void storeLogEvent(final Context context, final PServiceWidgetLogEvent$WidgetAction pServiceWidgetLogEvent$WidgetAction) {
        storeLogEvent(context, pServiceWidgetLogEvent$WidgetAction, new PreAppWidgetSize(0, 0, 0, 0));
    }
    
    public static void storeLogEvent(final Context context, final PServiceWidgetLogEvent$WidgetAction pServiceWidgetLogEvent$WidgetAction, final int n) {
        storeLogEvent(context, pServiceWidgetLogEvent$WidgetAction, getWidgetSize(context, n));
    }
    
    private static void storeLogEvent(final Context context, final PServiceWidgetLogEvent$WidgetAction pServiceWidgetLogEvent$WidgetAction, final PreAppWidgetSize preAppWidgetSize) {
        final PServiceWidgetLogEvent pServiceWidgetLogEvent = new PServiceWidgetLogEvent(pServiceWidgetLogEvent$WidgetAction, preAppWidgetSize);
        synchronized (PServiceLogging.PServiceLoggingLock) {
            final PServiceLogEvents fromJsonString = PServiceLogEvents.createFromJsonString(getStoredLogEventsString(context));
            fromJsonString.addWidgetEvent(pServiceWidgetLogEvent);
            putLogEvents(context, fromJsonString.toJsonString());
        }
    }
}
