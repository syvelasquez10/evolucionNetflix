// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import com.netflix.mediaclient.Log;
import android.content.Intent;
import android.content.Context;
import android.content.BroadcastReceiver;

class NetflixActivity$5 extends BroadcastReceiver
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$5(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    public void onReceive(final Context context, final Intent intent) {
        if (this.this$0.destroyed()) {
            return;
        }
        if (intent == null || !"com.netflix.mediaclient.service.ACTION_EXPAND_MDX_MINI_PLAYER".equals(intent.getAction())) {
            Log.d("NetflixActivity", "Invalid intent: ", intent);
            return;
        }
        this.this$0.expandMiniPlayerIfVisible();
    }
}
