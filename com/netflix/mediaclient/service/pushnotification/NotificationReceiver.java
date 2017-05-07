// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.AndroidUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class NotificationReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_push";
    
    private Intent createIntent(final Context context, final Intent intent, final String s) {
        final Intent intent2 = new Intent(s);
        intent2.setClass(context, (Class)NetflixService.class);
        intent2.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received notification WITHOUT Event GUID! Do nothing!");
            return null;
        }
        intent2.putExtra("guid", stringExtra);
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received notification WITHOUT GUID! Do nothing!");
            return null;
        }
        intent2.putExtra("messageGuid", stringExtra2);
        final String stringExtra3 = intent.getStringExtra("originator");
        if (StringUtils.isEmpty(stringExtra3)) {
            Log.w("nf_push", "Received notification WITHOUT oorigin!");
            return intent2;
        }
        intent2.putExtra("originator", stringExtra3);
        return intent2;
    }
    
    private void handleBrowserRedirectNotification(final Context context, final Intent intent) {
        Log.d("nf_push", "received notification browser redirect");
        final Intent intent2 = this.createIntent(context, intent, "com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT");
        if (intent2 != null) {
            final String stringExtra = intent.getStringExtra("target_url");
            if (stringExtra != null) {
                intent2.putExtra("target_url", stringExtra);
            }
            context.startService(intent2);
        }
    }
    
    private void handleCanceledNotification(final Context context, Intent intent) {
        Log.d("nf_push", "received notification canceled");
        intent = this.createIntent(context, intent, "com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
        if (intent != null) {
            context.startService(intent);
        }
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("nf_push", 3)) {
            AndroidUtils.logIntent("nf_push", intent);
            Log.d("nf_push", "Received an action: " + intent.getAction());
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED".equals(intent.getAction())) {
            this.handleCanceledNotification(context, intent);
            return;
        }
        if ("com.netflix.mediaclient.intent.action.NOTIFICATION_BROWSER_REDIRECT".equals(intent.getAction())) {
            this.handleBrowserRedirectNotification(context, intent);
            return;
        }
        Log.d("nf_push", "Not supported!");
    }
}
