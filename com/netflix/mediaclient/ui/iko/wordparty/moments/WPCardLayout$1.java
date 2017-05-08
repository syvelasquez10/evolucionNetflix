// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.iko.wordparty.moments;

import android.animation.ValueAnimator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class WPCardLayout$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ WPCardLayout this$0;
    final /* synthetic */ ObjectAnimator val$cardAnimator;
    
    WPCardLayout$1(final WPCardLayout this$0, final ObjectAnimator val$cardAnimator) {
        this.this$0 = this$0;
        this.val$cardAnimator = val$cardAnimator;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        if (valueAnimator.getAnimatedFraction() >= 0.5) {
            this.this$0.imageView.toggleCardClosed();
            this.this$0.imageView.updateDrawableBitmap();
            this.val$cardAnimator.removeUpdateListener((ValueAnimator$AnimatorUpdateListener)this);
        }
    }
}
