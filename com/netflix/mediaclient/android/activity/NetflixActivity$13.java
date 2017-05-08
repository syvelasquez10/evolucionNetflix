// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.activity;

import android.content.Context;
import com.netflix.mediaclient.ui.login.LogoutActivity;
import com.netflix.mediaclient.Log;

class NetflixActivity$13 implements Runnable
{
    final /* synthetic */ NetflixActivity this$0;
    
    NetflixActivity$13(final NetflixActivity this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        Log.d("NetflixActivity", "Restarting app, time: " + System.nanoTime());
        this.this$0.finish();
        this.this$0.startActivity(LogoutActivity.create((Context)this.this$0));
    }
}
