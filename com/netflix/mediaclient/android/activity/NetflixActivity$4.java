// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixActivity$4 extends BroadcastReceiver
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$4(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (Log.isLoggable("NetflixActivity", 2)) {
            Log.v("NetflixActivity", "Finishing activity " + this.this$0.getClass().getSimpleName() + " from intent: " + intent.getAction());
        }
        this.this$0.finish();
    }
}
