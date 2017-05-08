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
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import com.netflix.android.widgetry.utils.FloatUtils;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class UserRatingButtonOverlay$ThumbsAnimations$4 extends AnimatorListenerAdapter
{
    private UserRatingButton targetWhenStarted;
    final /* synthetic */ UserRatingButtonOverlay$ThumbsAnimations this$1;
    final /* synthetic */ UserRatingButtonOverlay val$this$0;
    
    UserRatingButtonOverlay$ThumbsAnimations$4(final UserRatingButtonOverlay$ThumbsAnimations this$1, final UserRatingButtonOverlay val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
        this.targetWhenStarted = null;
    }
    
    public void onAnimationEnd(final Animator animator) {
        if (this.targetWhenStarted != null && FloatUtils.floatsEqual((float)this.this$1.mFadeButtonsAnimator.getAnimatedValue(), 0.0f)) {
            if (this.this$1.mOnDismissed != null) {
                this.this$1.mOnDismissed.onAnimationEnded();
            }
            this.this$1.this$0.mOnRateListener.onDismissed(this.targetWhenStarted);
        }
    }
    
    public void onAnimationStart(final Animator animator) {
        this.targetWhenStarted = this.this$1.this$0.mTarget;
    }
}
