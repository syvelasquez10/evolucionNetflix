// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.animation.TimeInterpolator;
import android.animation.Animator$AnimatorListener;
import android.animation.ValueAnimator;
import android.view.ViewGroup$LayoutParams;
import android.support.design.widget.CoordinatorLayout$LayoutParams;
import android.view.MotionEvent;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$dimen;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import com.netflix.android.widgetry.R$drawable;
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
import android.view.View;
import android.view.View$OnClickListener;

class UserRatingButtonOverlay$2 implements View$OnClickListener
{
    final /* synthetic */ UserRatingButtonOverlay this$0;
    
    UserRatingButtonOverlay$2(final UserRatingButtonOverlay this$0) {
        this.this$0 = this$0;
    }
    
    public void onClick(final View view) {
        if (!this.this$0.mThumbsAnimations.isClosing() && this.this$0.mTarget != null) {
            if (view == this.this$0 || view == this.this$0.mClose) {
                this.this$0.dismiss();
                return;
            }
            if (view == this.this$0.mThumbRight) {
                this.this$0.mOnRateListener.onRate(this.this$0.mTarget, this.this$0.getRightValue());
                this.this$0.mThumbsAnimations.click(this.this$0.mRightDrawable, new UserRatingButtonOverlay$2$1(this));
                return;
            }
            if (view == this.this$0.mThumbLeft) {
                this.this$0.mOnRateListener.onRate(this.this$0.mTarget, this.this$0.getLeftValue());
                this.this$0.mThumbsAnimations.click(this.this$0.mLeftDrawable, new UserRatingButtonOverlay$2$2(this));
            }
        }
    }
}
