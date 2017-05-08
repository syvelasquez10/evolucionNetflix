// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import com.netflix.mediaclient.Log;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class WPMomentScreen$4 extends AnimatorListenerAdapter
{
    final /* synthetic */ WPMomentScreen this$0;
    final /* synthetic */ WPCardView val$v;
    
    WPMomentScreen$4(final WPMomentScreen this$0, final WPCardView val$v) {
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
        this.this$0.onCardClickEnd(this.val$v);
    }
}
