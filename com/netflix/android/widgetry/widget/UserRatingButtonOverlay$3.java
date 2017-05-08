// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class UserRatingButtonOverlay$3 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ UserRatingButtonOverlay this$0;
    final /* synthetic */ UserRatingButton$OnRateListener val$onRateListener;
    
    UserRatingButtonOverlay$3(final UserRatingButtonOverlay this$0, final UserRatingButton$OnRateListener val$onRateListener) {
        this.this$0 = this$0;
        this.val$onRateListener = val$onRateListener;
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        this.val$onRateListener.onAlphaAnimate((float)valueAnimator.getAnimatedValue());
    }
}
