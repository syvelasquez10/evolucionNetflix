// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.player;

import android.animation.ValueAnimator;
import android.view.ViewGroup$MarginLayoutParams;
import android.view.View;
import android.animation.ValueAnimator$AnimatorUpdateListener;

final class BottomPanel$2 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ View val$extraSeekbarHandler;
    final /* synthetic */ ViewGroup$MarginLayoutParams val$params;
    
    BottomPanel$2(final ViewGroup$MarginLayoutParams val$params, final View val$extraSeekbarHandler) {
        this.val$params = val$params;
        this.val$extraSeekbarHandler = val$extraSeekbarHandler;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$params.leftMargin = (int)valueAnimator.getAnimatedValue();
        this.val$extraSeekbarHandler.requestLayout();
    }
}
