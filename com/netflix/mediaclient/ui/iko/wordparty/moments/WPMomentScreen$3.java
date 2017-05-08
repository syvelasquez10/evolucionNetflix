// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPMomentScreen$3 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ WPCardLayout val$v;
    
    WPMomentScreen$3(final WPMomentScreen this$0, final WPCardLayout val$v) {
        this.this$0 = this$0;
        this.val$v = val$v;
    }
    
    public void onAnimationEnd(final Animator animator) {
        super.onAnimationEnd(animator);
        if (Log.isLoggable()) {
            Log.d("WPMomentScreen", "zoomInCard onAnimationEnd");
        }
        if (this.this$0.isMomentClosed()) {
            return;
        }
        this.val$v.revealCard();
    }
}
