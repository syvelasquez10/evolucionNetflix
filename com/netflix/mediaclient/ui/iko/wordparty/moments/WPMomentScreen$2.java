// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPMomentScreen$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ WPCardLayout val$card;
    
    WPMomentScreen$2(final WPMomentScreen this$0, final WPCardLayout val$card) {
        this.this$0 = this$0;
        this.val$card = val$card;
    }
    
    public void onAnimationEnd(final Animator animator) {
        super.onAnimationEnd(animator);
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "scaleUpCard onAnimationEnd");
        }
        if (this.this$0.isMomentClosed()) {
            return;
        }
        this.val$card.revealCard();
    }
}
