// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.launch;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class LaunchActivity$3 extends BroadcastReceiver
{
    final /* synthetic */ LaunchActivity this$0;
    
    LaunchActivity$3(final LaunchActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable()) {
            Log.v("LaunchActivity", "Received intent " + intent);
        }
        final String action = intent.getAction();
        if ("com.netflix.mediaclient.intent.action.HANDLER_RESULT".equals(action)) {
            Log.d("LaunchActivity", "Delayed nflx action completed, finish activity");
            this.this$0.finish();
        }
        else if (Log.isLoggable()) {
            Log.d("LaunchActivity", "We do not support action " + action);
        }
    }
}
