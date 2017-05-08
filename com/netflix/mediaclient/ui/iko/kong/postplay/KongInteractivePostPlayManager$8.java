// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.kong.postplay;

class KongInteractivePostPlayManager$8 implements Runnable
{
    final /* synthetic */ KongInteractivePostPlayManager this$0;
    
    KongInteractivePostPlayManager$8(final KongInteractivePostPlayManager this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.UNLOCK) {
            this.this$0.showUnlockedGear();
        }
        else {
            if (this.this$0.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.BATTLE) {
                this.this$0.showGearSelection();
                return;
            }
            if (this.this$0.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.POWERUP) {
                this.this$0.showPowerUp();
                return;
            }
            if (this.this$0.postPlayState == KongInteractivePostPlayManager$POST_PLAY_STATE.RESULT) {
                this.this$0.showBattleResult();
            }
        }
    }
}
