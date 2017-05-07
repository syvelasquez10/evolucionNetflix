// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.mdx;

import android.support.v4.content.LocalBroadcastManager;
import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

public class RemoteControlClientManager$MediaButtonIntentHandlerProxy extends BroadcastReceiver
{
    public static final String RESEND_MEDIA_BUTTON_ACTION = "com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy";
    
    public void onReceive(final Context context, final Intent intent) {
        Log.d("RemoteControlClientManager", "Re-sending media button event as local broadcast...");
        intent.setAction("com.netflix.mediaclient.service.mdx.MediaButtonIntentHandlerProxy");
        LocalBroadcastManager.getInstance(context).sendBroadcast(intent);
    }
}
