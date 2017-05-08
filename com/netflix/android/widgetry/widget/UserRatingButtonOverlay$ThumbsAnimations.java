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

class UserRatingButtonOverlay$ThumbsAnimations
{
    private final ValueAnimator mClickAnimatorFeedbackFadeIn;
    private final ValueAnimator mClickAnimatorFeedbackFadeOut;
    private UserRatingButtonOverlay$AnimationEndedCallback mClickCallback;
    private UserRatingButtonOverlay$PastilleDrawable mClickedDrawable;
    private boolean mClosing;
    private final ValueAnimator mFadeButtonsAnimator;
    private final ValueAnimator mFadeCloseAnimator;
    private final ValueAnimator mFadeExternalAnimator;
    private long mLongDuration;
    private long mMidDuration;
    private UserRatingButtonOverlay$AnimationEndedCallback mOnDismissed;
    private final ValueAnimator mScaleAnimator;
    private long mShortDuration;
    private final ValueAnimator$AnimatorUpdateListener mUpdateListener;
    final /* synthetic */ UserRatingButtonOverlay this$0;
    
    private UserRatingButtonOverlay$ThumbsAnimations(final UserRatingButtonOverlay this$0) {
        this.this$0 = this$0;
        this.mScaleAnimator = new ValueAnimator();
        this.mFadeExternalAnimator = new ValueAnimator();
        this.mFadeButtonsAnimator = new ValueAnimator();
        this.mFadeCloseAnimator = new ValueAnimator();
        this.mClickAnimatorFeedbackFadeIn = new ValueAnimator();
        this.mClickAnimatorFeedbackFadeOut = new ValueAnimator();
        this.mUpdateListener = (ValueAnimator$AnimatorUpdateListener)new UserRatingButtonOverlay$ThumbsAnimations$1(this);
        this.mFadeCloseAnimator.addUpdateListener(this.mUpdateListener);
        this.mFadeButtonsAnimator.addUpdateListener(this.mUpdateListener);
        this.mClickAnimatorFeedbackFadeIn.addUpdateListener(this.mUpdateListener);
        this.mClickAnimatorFeedbackFadeIn.setFloatValues(new float[] { 0.0f, 0.5f });
        this.mClickAnimatorFeedbackFadeIn.addListener((Animator$AnimatorListener)new UserRatingButtonOverlay$ThumbsAnimations$2(this, this$0));
        this.mClickAnimatorFeedbackFadeOut.addUpdateListener(this.mUpdateListener);
        this.mClickAnimatorFeedbackFadeOut.setFloatValues(new float[] { 0.5f, 0.0f });
        this.mClickAnimatorFeedbackFadeOut.addListener((Animator$AnimatorListener)new UserRatingButtonOverlay$ThumbsAnimations$3(this, this$0));
        this.mScaleAnimator.setInterpolator((TimeInterpolator)UserRatingButtonOverlay.CUBIC_BEZIER);
        this.mScaleAnimator.addUpdateListener(this.mUpdateListener);
        this.mScaleAnimator.addListener((Animator$AnimatorListener)new UserRatingButtonOverlay$ThumbsAnimations$4(this, this$0));
        this.mFadeCloseAnimator.setFloatValues(new float[] { 0.0f });
        this.mFadeButtonsAnimator.setFloatValues(new float[] { 0.0f });
        this.mScaleAnimator.setFloatValues(new float[] { 0.0f });
        this.mFadeExternalAnimator.setFloatValues(new float[] { 0.0f });
    }
    
    private void cancel(final ValueAnimator... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            final ValueAnimator valueAnimator = array[i];
            if (valueAnimator.isRunning()) {
                valueAnimator.cancel();
            }
        }
    }
    
    private void click(final UserRatingButtonOverlay$PastilleDrawable mClickedDrawable, final UserRatingButtonOverlay$AnimationEndedCallback mClickCallback) {
        this.mClickedDrawable = mClickedDrawable;
        if (this.mClickAnimatorFeedbackFadeIn.isRunning()) {
            this.mClickAnimatorFeedbackFadeIn.cancel();
        }
        else if (this.mClickAnimatorFeedbackFadeOut.isRunning()) {
            this.mClickAnimatorFeedbackFadeOut.cancel();
        }
        this.cancel(this.mFadeExternalAnimator, this.mFadeCloseAnimator, this.mFadeButtonsAnimator, this.mScaleAnimator);
        this.mClickedDrawable.setAlpha(255);
        this.mClickCallback = mClickCallback;
        this.mClickAnimatorFeedbackFadeIn.start();
    }
    
    private float getScaleValue() {
        return (float)this.mScaleAnimator.getAnimatedValue();
    }
    
    private void show(final int n) {
        this.mOnDismissed = null;
        this.mClickCallback = null;
        this.mMidDuration = n * 250L;
        this.mShortDuration = 150L * n;
        this.mLongDuration = 300L * n;
        this.mClickAnimatorFeedbackFadeIn.setDuration(50L * n);
        this.mClickAnimatorFeedbackFadeOut.setDuration(n * 250L);
        this.mFadeExternalAnimator.setDuration(this.mMidDuration);
        this.mFadeCloseAnimator.setDuration(this.mShortDuration);
        this.mFadeButtonsAnimator.setDuration(this.mLongDuration);
        this.mScaleAnimator.setDuration(this.mLongDuration);
        this.this$0.mDimBackground.setAlpha(0);
        this.this$0.mCloseDrawable.setAlpha(0);
        this.this$0.mLeftDrawable.setAlpha(0);
        this.this$0.mRightDrawable.setAlpha(0);
        this.mFadeExternalAnimator.setStartDelay(0L);
        this.mFadeCloseAnimator.setStartDelay(this.mLongDuration - this.mShortDuration);
        this.start(1.0f, this.mFadeExternalAnimator, this.mFadeCloseAnimator, this.mFadeButtonsAnimator, this.mScaleAnimator);
        this.mClosing = false;
    }
    
    private void start(final float n, final ValueAnimator... array) {
        this.cancel(array);
        for (int length = array.length, i = 0; i < length; ++i) {
            final ValueAnimator valueAnimator = array[i];
            valueAnimator.setFloatValues(new float[] { (float)valueAnimator.getAnimatedValue(), n });
            valueAnimator.start();
        }
    }
    
    public void close(final UserRatingButtonOverlay$AnimationEndedCallback mOnDismissed) {
        this.mFadeExternalAnimator.setStartDelay(this.mLongDuration - this.mMidDuration * 2L);
        this.mFadeCloseAnimator.setStartDelay(0L);
        this.start(0.0f, this.mFadeExternalAnimator, this.mFadeCloseAnimator, this.mFadeButtonsAnimator, this.mScaleAnimator);
        this.mOnDismissed = mOnDismissed;
        this.mClosing = true;
    }
    
    public boolean isClosing() {
        return this.mClosing && this.mScaleAnimator.isRunning();
    }
    
    boolean isRunning() {
        return this.mScaleAnimator.isRunning();
    }
}
