// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.Log;

class KongPowerUpScreen$4$1 implements Runnable
{
    final /* synthetic */ KongPowerUpScreen$4 this$1;
    
    KongPowerUpScreen$4$1(final KongPowerUpScreen$4 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.this$0.postPlayManager.isPostPlayPaused()) {
            Log.d("KongPowerUpScreen", "Post play is in paused state. Ignoring request to start battle intro animation.");
            return;
        }
        this.this$1.this$0.playBattleTitleSound();
        this.this$1.this$0.postPlayManager.showBattleIntro();
    }
}
