// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

import com.netflix.mediaclient.Log;

class KongBattleResultScreen$1$1 implements Runnable
{
    final /* synthetic */ KongBattleResultScreen$1 this$1;
    
    KongBattleResultScreen$1$1(final KongBattleResultScreen$1 this$1) {
        this.this$1 = this$1;
    }
    
    @Override
    public void run() {
        if (this.this$1.this$0.postPlayManager.isPostPlayPaused()) {
            Log.d("KongBattleResultScreen", "Post play is in paused state. Ignoring request to start battle intro animation after result.");
            return;
        }
        this.this$1.this$0.playBattleAgainSound();
        this.this$1.this$0.postPlayManager.showBattleIntro();
    }
}
