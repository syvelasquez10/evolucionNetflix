// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.Log;

class KongPowerUpScreen$1 implements Runnable
{
    final /* synthetic */ KongPowerUpScreen this$0;
    
    KongPowerUpScreen$1(final KongPowerUpScreen this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.postPlayManager.isPostPlayPaused()) {
            Log.d("KongPowerUpScreen", "Post play is in paused state. Ignoring request to start power up animation.");
            return;
        }
        this.this$0.startAnimation();
    }
}
