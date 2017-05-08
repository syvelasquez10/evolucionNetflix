// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.animation.ValueAnimator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class WPCardView$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ WPCardView this$0;
    final /* synthetic */ ObjectAnimator val$cardAnimator;
    
    WPCardView$1(final WPCardView this$0, final ObjectAnimator val$cardAnimator) {
        this.this$0 = this$0;
        this.val$cardAnimator = val$cardAnimator;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        if (valueAnimator.getAnimatedFraction() >= 0.5) {
            this.this$0.toggleCardClosed();
            this.this$0.updateDrawableBitmap();
            this.val$cardAnimator.removeUpdateListener((ValueAnimator$AnimatorUpdateListener)this);
        }
    }
}
