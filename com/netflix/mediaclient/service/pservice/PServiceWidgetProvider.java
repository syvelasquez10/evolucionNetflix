// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.service.pservice.logging.PServiceLogging;
import com.netflix.mediaclient.service.pservice.logging.PServiceWidgetLogEvent$WidgetAction;
import android.os.Bundle;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import android.annotation.TargetApi;
import android.appwidget.AppWidgetProvider;

@TargetApi(16)
public class PServiceWidgetProvider extends AppWidgetProvider
{
    private static final String TAG = "nf_preapp_appwidgetprovider";
    
    private void updateWidget(final Context context, final String s, final int n) {
        Log.d("nf_preapp_appwidgetprovider", "Sending command to PService to start...");
        final Intent intent = new Intent(s);
        intent.addCategory("com.netflix.mediaclient.intent.category.CATEGORY_FROM_PREAPP_WIDGET");
        intent.putExtra("widgetId", n);
        intent.setClass(context, (Class)PService.class);
        context.startService(intent);
    }
    
    public void onAppWidgetOptionsChanged(final Context context, final AppWidgetManager appWidgetManager, final int n, final Bundle bundle) {
        if (Log.isLoggable()) {
            Log.d("nf_preapp_appwidgetprovider", String.format("widget resizing, width[%d-%d], height[%d-%d]", bundle.getInt("appWidgetMinWidth"), bundle.getInt("appWidgetMaxWidth"), bundle.getInt("appWidgetMinHeight"), bundle.getInt("appWidgetMaxHeight")));
        }
        this.updateWidget(context, "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET", n);
    }
    
    public void onDisabled(final Context context) {
        Log.d("nf_preapp_appwidgetprovider", "onDisabled");
        PServiceLogging.storeLogEvent(context, PServiceWidgetLogEvent$WidgetAction.DELETE);
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
        this.updateWidget(context, "com.netflix.mediaclient.intent.action.INSTALLED_FROM_PREAPP_WIDGET", intValue);
    }
}
