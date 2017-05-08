// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
import android.view.View;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$dimen;
import com.netflix.android.widgetry.R$drawable;
import android.view.View$OnClickListener;
import com.netflix.android.widgetry.R$id;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$color;
import com.netflix.android.widgetry.R$layout;
import android.content.Context;
import android.support.v4.view.animation.PathInterpolatorCompat;
import android.graphics.Point;
import android.support.design.widget.CoordinatorLayout;
import android.graphics.drawable.Drawable;
import android.graphics.Rect;
import android.widget.ImageView;
import android.view.animation.Interpolator;
import android.annotation.SuppressLint;
import android.view.ViewGroup;
import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;

class UserRatingButtonOverlay$ThumbsAnimations$1 implements ValueAnimator$AnimatorUpdateListener
{
    final /* synthetic */ UserRatingButtonOverlay$ThumbsAnimations this$1;
    
    UserRatingButtonOverlay$ThumbsAnimations$1(final UserRatingButtonOverlay$ThumbsAnimations this$1) {
        this.this$1 = this$1;
    }
    
    private int getAlphaValue(final float n) {
        return Math.max(0, Math.min((int)(255.0f * n), 255));
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float floatValue = (float)valueAnimator.getAnimatedValue();
        final int alphaValue = this.getAlphaValue(floatValue);
        if (valueAnimator == this.this$1.mClickAnimatorFeedbackFadeIn || valueAnimator == this.this$1.mClickAnimatorFeedbackFadeOut) {
            this.this$1.mClickedDrawable.setPressedStateAlpha(alphaValue);
        }
        else {
            if (valueAnimator == this.this$1.mFadeButtonsAnimator) {
                this.this$1.this$0.mLeftDrawable.setAlpha(alphaValue);
                this.this$1.this$0.mRightDrawable.setAlpha(alphaValue);
                return;
            }
            if (valueAnimator == this.this$1.mFadeCloseAnimator) {
                this.this$1.this$0.mCloseDrawable.setAlpha(alphaValue);
                return;
            }
            if (valueAnimator == this.this$1.mScaleAnimator) {
                this.this$1.this$0.mDimBackground.setAlpha(alphaValue);
                final float n = floatValue * 0.32f + 0.68f;
                this.this$1.this$0.mLeftDrawable.setScale(n);
                this.this$1.this$0.mRightDrawable.setScale(n);
                this.this$1.this$0.mClose.setScaleX(n);
                this.this$1.this$0.mClose.setScaleY(n);
                this.this$1.this$0.requestLayout();
            }
        }
    }
}
