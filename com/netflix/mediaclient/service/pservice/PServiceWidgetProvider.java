// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.service.pservice.logging.PServiceWidgetLogEvent$WidgetAction;
import android.content.ComponentName;
import android.appwidget.AppWidgetManager;
import android.os.Bundle;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.annotation.TargetApi;
import android.appwidget.AppWidgetProvider;

@TargetApi(16)
public class PServiceWidgetProvider extends AppWidgetProvider
{
    private static final String TAG = "nf_preapp_appwidgetprovider";
    
    private static Intent createNotifyPServiceIntent(final Context context, final String s) {
        Log.d("nf_preapp_appwidgetprovider", String.format("Sending command to PService to start...action:%s", s));
        final Intent intent = new Intent(s);
        intent.addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET");
        intent.setClass(context, (Class)PService.class);
        return intent;
    }
    
    public static int getWidgetHeightInDp(final Bundle bundle, final boolean b) {
        if (b) {
            return bundle.getInt("appWidgetMinHeight");
        }
        return bundle.getInt("appWidgetMaxHeight");
    }
    
    public static int getWidgetWidthInDp(final Bundle bundle, final boolean b) {
        if (b) {
            return bundle.getInt("appWidgetMaxWidth");
        }
        return bundle.getInt("appWidgetMinWidth");
    }
    
    private static void notifyPServiceOnWidgetAction(final Context context, final String s, final int n) {
        Log.d("nf_preapp_appwidgetprovider", String.format("Sending command to PService to start...widgetId:%d, action:%s", n, s));
        final Intent notifyPServiceIntent = createNotifyPServiceIntent(context, s);
        notifyPServiceIntent.putExtra("widgetId", n);
        context.startService(notifyPServiceIntent);
    }
    
    public static void notifyWidgetsOnConfigurationChange(final Context context) {
        int n = 0;
        Log.d("nf_preapp_appwidgetprovider", "onConfigurationChange");
        final int[] appWidgetIds = AppWidgetManager.getInstance(context).getAppWidgetIds(new ComponentName(context, (Class)PServiceWidgetProvider.class));
        if (appWidgetIds.length > 0) {
            n = appWidgetIds[0];
        }
        notifyPServiceOnWidgetAction(context, "com.netflix.mediaclient.intent.action.ACTION_RESIZED_FROM_PREAPP_WIDGET", n);
    }
    
    public void onAppWidgetOptionsChanged(final Context context, final AppWidgetManager appWidgetManager, final int n, final Bundle bundle) {
        if (Log.isLoggable()) {
            final int widgetWidthInDp = getWidgetWidthInDp(bundle, false);
            final int widgetHeightInDp = getWidgetHeightInDp(bundle, false);
            final int widgetWidthInDp2 = getWidgetWidthInDp(bundle, true);
            final int widgetHeightInDp2 = getWidgetHeightInDp(bundle, true);
            Log.d("nf_preapp_appwidgetprovider", String.format("widget resizing, Landscape(WxH):[%d-%d](%d-%d), Portrait(WxH):[%d-%d](%d-%d)", widgetWidthInDp2, widgetHeightInDp2, PServiceWidgetAgent.cellFromDp(widgetWidthInDp2), PServiceWidgetAgent.cellFromDp(widgetHeightInDp2), widgetWidthInDp, widgetHeightInDp, PServiceWidgetAgent.cellFromDp(widgetWidthInDp), PServiceWidgetAgent.cellFromDp(widgetHeightInDp)));
        }
        notifyPServiceOnWidgetAction(context, "com.netflix.mediaclient.intent.action.ACTION_RESIZED_FROM_PREAPP_WIDGET", n);
    }
    
    public void onDisabled(final Context context) {
        Log.d("nf_preapp_appwidgetprovider", "onDisabled");
        PServiceLogging.storeLogEvent(context, PServiceWidgetLogEvent$WidgetAction.DELETE);
        context.startService(createNotifyPServiceIntent(context, "com.netflix.mediaclient.intent.action.UNINSTALL_FROM_PREAPP_WIDGET"));
    }
    
    public void onEnabled(final Context context) {
        Log.d("nf_preapp_appwidgetprovider", "onEnabled");
    }
    
    public void onUpdate(final Context context, final AppWidgetManager appWidgetManager, final int[] array) {
        Log.d("nf_preapp_appwidgetprovider", String.format("onUpdate appWidgetIds.length= %d", array.length));
        int intValue = PService.INVALID_WIDGET_ID;
        if (array.length == 1) {
            intValue = array[0];
        }
        PServiceLogging.storeLogEvent(context, PServiceWidgetLogEvent$WidgetAction.INSTALL, intValue);
        notifyPServiceOnWidgetAction(context, "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET", intValue);
    }
}
