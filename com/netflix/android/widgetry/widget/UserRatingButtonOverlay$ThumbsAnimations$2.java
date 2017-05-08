// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
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
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;

class UserRatingButtonOverlay$ThumbsAnimations$2 extends AnimatorListenerAdapter
{
    final /* synthetic */ UserRatingButtonOverlay$ThumbsAnimations this$1;
    final /* synthetic */ UserRatingButtonOverlay val$this$0;
    
    UserRatingButtonOverlay$ThumbsAnimations$2(final UserRatingButtonOverlay$ThumbsAnimations this$1, final UserRatingButtonOverlay val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    public void onAnimationEnd(final Animator animator) {
        final boolean b = true;
        boolean selected = true;
        final boolean b2 = false;
        int access$1100 = 0;
        this.this$1.mClickAnimatorFeedbackFadeOut.start();
        if (this.this$1.this$0.mTarget != null) {
            this.this$1.mClickedDrawable.setApplyAlphaAlsoToIcon(false);
            if (this.this$1.mClickedDrawable != this.this$1.this$0.mLeftDrawable) {
                this.this$1.this$0.mLeftDrawable.setApplyAlphaAlsoToIcon(true);
                this.this$1.this$0.mRightDrawable.setSelected(!this.this$1.this$0.mRightDrawable.isSelected() && b);
                final UserRatingButton access$1101 = this.this$1.this$0.mTarget;
                int access$1102 = b2 ? 1 : 0;
                if (this.this$1.this$0.mRightDrawable.isSelected()) {
                    access$1102 = this.this$1.this$0.getRightValue();
                }
                access$1101.setRating(access$1102);
                ViewCompat.setElevation((View)this.this$1.this$0.mThumbLeft, 0.0f);
                ViewCompat.setElevation((View)this.this$1.this$0.mThumbRight, 1.0f);
                return;
            }
            this.this$1.this$0.mRightDrawable.setApplyAlphaAlsoToIcon(true);
            final UserRatingButtonOverlay$PastilleDrawable access$1103 = this.this$1.this$0.mLeftDrawable;
            if (this.this$1.this$0.mLeftDrawable.isSelected()) {
                selected = false;
            }
            access$1103.setSelected(selected);
            final UserRatingButton access$1104 = this.this$1.this$0.mTarget;
            if (this.this$1.this$0.mLeftDrawable.isSelected()) {
                access$1100 = this.this$1.this$0.getLeftValue();
            }
            access$1104.setRating(access$1100);
            ViewCompat.setElevation((View)this.this$1.this$0.mThumbLeft, 1.0f);
            ViewCompat.setElevation((View)this.this$1.this$0.mThumbRight, 0.0f);
        }
    }
}
