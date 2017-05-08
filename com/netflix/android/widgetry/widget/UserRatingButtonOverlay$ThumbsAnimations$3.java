// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class UserRatingButtonOverlay$ThumbsAnimations$3 extends AnimatorListenerAdapter
{
    final /* synthetic */ UserRatingButtonOverlay$ThumbsAnimations this$1;
    final /* synthetic */ UserRatingButtonOverlay val$this$0;
    
    UserRatingButtonOverlay$ThumbsAnimations$3(final UserRatingButtonOverlay$ThumbsAnimations this$1, final UserRatingButtonOverlay val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.this$1.mClickCallback != null) {
            this.this$1.mClickCallback.onAnimationEnded();
        }
    }
}
