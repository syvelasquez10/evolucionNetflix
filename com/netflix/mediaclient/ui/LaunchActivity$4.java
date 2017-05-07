// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class LaunchActivity$4 extends BroadcastReceiver
{
    final /* synthetic */ LaunchActivity this$0;
    
    LaunchActivity$4(final LaunchActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("LaunchActivity", 2)) {
            Log.v("LaunchActivity", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.HANDLER_RESULT".equals(action)) {
            Log.d("LaunchActivity", "Delayed nflx action completed, finish activity");
            this.this$0.finish();
        }
        else if (Log.isLoggable("LaunchActivity", 3)) {
            Log.d("LaunchActivity", "We do not support action " + action);
        }
    }
}
