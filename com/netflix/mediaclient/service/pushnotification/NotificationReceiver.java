// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pushnotification;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.NetflixService;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public final class NotificationReceiver extends BroadcastReceiver
{
    private static final String TAG = "nf_push";
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("nf_push", 3)) {
            Log.d("nf_push", "Received an action: " + intent.getAction());
        }
        if (!"com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED".equals(intent.getAction())) {
            Log.d("nf_push", "Not supported!");
            return;
        }
        Log.d("nf_push", "received notification canceled");
        final Intent intent2 = new Intent("com.netflix.mediaclient.intent.action.NOTIFICATION_CANCELED");
        intent2.setClass(context, (Class)NetflixService.class);
        intent2.addCategory("com.netflix.mediaclient.intent.category.PUSH");
        final String stringExtra = intent.getStringExtra("guid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received cancel notification WITHOUT Event GUID! Do nothing!");
            return;
        }
        intent2.putExtra("guid", stringExtra);
        final String stringExtra2 = intent.getStringExtra("messageGuid");
        if (StringUtils.isEmpty(stringExtra)) {
            Log.e("nf_push", "Received cancel notification WITHOUT GUID! Do nothing!");
            return;
        }
        intent2.putExtra("messageGuid", stringExtra2);
        context.startService(intent2);
    }
}
