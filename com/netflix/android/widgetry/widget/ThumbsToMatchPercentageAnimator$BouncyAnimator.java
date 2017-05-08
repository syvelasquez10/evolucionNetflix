// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.android.widgetry.widget;

import android.text.TextPaint;
import android.text.StaticLayout;
import android.text.Layout$Alignment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.SpannableString;
import com.netflix.mediaclient.util.l10n.BidiMarker;
import android.text.style.ImageSpan;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.content.ContextCompat;
import com.netflix.android.widgetry.R$drawable;
import android.support.v4.view.animation.PathInterpolatorCompat;
import com.netflix.android.widgetry.utils.LogUtils;
import android.text.Spannable;
import android.widget.TextView;
import android.view.ViewGroup;
import android.view.animation.Interpolator;
import android.animation.TimeInterpolator;
import android.view.View;
import com.netflix.android.widgetry.utils.UiUtils;
import android.text.TextUtils;
import android.animation.Animator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.Animator$AnimatorListener;

class ThumbsToMatchPercentageAnimator$BouncyAnimator implements Animator$AnimatorListener, ValueAnimator$AnimatorUpdateListener
{
    private static final int DEBUG_MULTIPLIER = 1;
    private static final long LONG_DURATION_MS = 300L;
    private static final long MID_DURATION_MS = 150L;
    private static final long SHORT_DURATION_MS = 100L;
    private static final float VALUE_ONE = 1.0f;
    private static final float VALUE_ZERO = 0.0f;
    private int mCurrentState;
    private float mCurrentTranslation;
    private final ValueAnimator mFirstHalfOutAnimator;
    private int mFutureRating;
    private CharSequence mFutureText;
    private final ValueAnimator mSecondHalfInAnimator;
    private int mTargetTranslation;
    final /* synthetic */ ThumbsToMatchPercentageAnimator this$0;
    
    private ThumbsToMatchPercentageAnimator$BouncyAnimator(final ThumbsToMatchPercentageAnimator this$0) {
        this.this$0 = this$0;
        this.mCurrentState = 0;
        this.mFirstHalfOutAnimator = new ValueAnimator();
        this.mSecondHalfInAnimator = new ValueAnimator();
        this.mFirstHalfOutAnimator.setDuration(300L);
        this.mFirstHalfOutAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)this);
        this.mSecondHalfInAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)this);
        this.mFirstHalfOutAnimator.addListener((Animator$AnimatorListener)this);
        this.mSecondHalfInAnimator.addListener((Animator$AnimatorListener)this);
    }
    
    private void cancel(final ValueAnimator valueAnimator) {
        if (valueAnimator.isRunning()) {
            valueAnimator.cancel();
        }
    }
    
    private void start(final int mCurrentState, final ValueAnimator valueAnimator, final float... floatValues) {
        valueAnimator.setFloatValues(floatValues);
        this.mCurrentState = mCurrentState;
        valueAnimator.start();
    }
    
    private boolean willDisplayText() {
        return this.mFutureRating == 0;
    }
    
    public void onAnimationCancel(final Animator animator) {
    }
    
    public void onAnimationEnd(final Animator animator) {
        switch (this.mCurrentState) {
            default: {}
            case 1: {
                this.this$0.mMatchPercentageTextView.setText(this.mFutureText);
                if (this.willDisplayText()) {
                    if (TextUtils.isEmpty(this.mFutureText)) {
                        this.mCurrentState = 0;
                        return;
                    }
                    this.mSecondHalfInAnimator.setDuration(100L);
                }
                else {
                    this.mSecondHalfInAnimator.setDuration(150L);
                }
                this.mCurrentTranslation = this.this$0.mContainerToAnimate.getTranslationX();
                this.start(2, this.mSecondHalfInAnimator, 0.0f, 1.0f);
            }
            case 2: {
                this.mCurrentState = 0;
                this.this$0.mContainerToAnimate.setTranslationX((float)(this.mTargetTranslation * this.this$0.getTranslationDirection()));
            }
        }
    }
    
    public void onAnimationRepeat(final Animator animator) {
    }
    
    public void onAnimationStart(final Animator animator) {
    }
    
    public void onAnimationUpdate(final ValueAnimator valueAnimator) {
        final float floatValue = (float)valueAnimator.getAnimatedValue();
        final float n = 1.0f - floatValue;
        this.this$0.mMatchPercentageTextView.setAlpha(floatValue);
        if (this.mCurrentState == 1) {
            this.this$0.mContainerToAnimate.setTranslationX(floatValue * this.mCurrentTranslation + n * (this.this$0.mMatchPercentageTextView.getMeasuredWidth() + UiUtils.getMarginEnd((View)this.this$0.mMatchPercentageTextView)) * this.this$0.getTranslationDirection());
        }
        else if (this.mCurrentState == 2) {
            this.this$0.mContainerToAnimate.setTranslationX(floatValue * this.mTargetTranslation * this.this$0.getTranslationDirection() + n * this.mCurrentTranslation);
        }
    }
    
    public void start(final int mFutureRating, final CharSequence mFutureText, final int mTargetTranslation) {
        this.mFutureRating = mFutureRating;
        this.mFutureText = mFutureText;
        this.mTargetTranslation = mTargetTranslation;
        this.mCurrentTranslation = this.this$0.mContainerToAnimate.getTranslationX();
        this.cancel(this.mFirstHalfOutAnimator);
        this.cancel(this.mSecondHalfInAnimator);
        if (this.mFutureRating == 0) {
            this.mFirstHalfOutAnimator.setInterpolator((TimeInterpolator)ThumbsToMatchPercentageAnimator.CUBIC_BEZIER_SELECTION);
        }
        else {
            this.mFirstHalfOutAnimator.setInterpolator((TimeInterpolator)ThumbsToMatchPercentageAnimator.CUBIC_BEZIER_DESELECTION);
        }
        this.mCurrentTranslation = this.this$0.mContainerToAnimate.getTranslationX();
        if (TextUtils.isEmpty(this.this$0.mMatchPercentageTextView.getText()) && this.mFutureRating != 0) {
            this.this$0.mMatchPercentageTextView.setText(this.mFutureText);
            this.start(2, this.mSecondHalfInAnimator, 0.0f, 1.0f);
            return;
        }
        this.start(1, this.mFirstHalfOutAnimator, 1.0f, 0.0f);
    }
}
