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
import android.view.View;
import android.support.v4.view.ViewCompat;
import com.netflix.android.widgetry.R$dimen;
import android.animation.ValueAnimator$AnimatorUpdateListener;
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

@SuppressLint({ "ViewConstructor" })
public class UserRatingButtonOverlay extends ViewGroup
{
    private static final Interpolator CUBIC_BEZIER;
    private static final int DEBUG_MULTIPLIER = 1;
    private static final long LONG_DURATION_MS = 300L;
    private static final long MID_DURATION_MS = 250L;
    private static final int NOT_SELECTED_ELEVATION = 0;
    private static final float SCALE_CLOSED = 0.68f;
    private static final int SELECTED_ELEVATION = 1;
    private static final long SHORT_DURATION_MS = 150L;
    private static final float STATE_CLOSED = 0.0f;
    private static final float STATE_OPEN = 1.0f;
    private static final long TINY_DURATION_MS = 50L;
    private final ImageView mClose;
    private final Rect mCloseBounds;
    private final UserRatingButtonOverlay$PastilleDrawable mCloseDrawable;
    private final boolean mDark;
    private Drawable mDimBackground;
    private final Rect mDragOverBoundHelper;
    private final int[] mDragOverLocationHelper;
    private ImageView mDragOverView;
    private final boolean mInvertLayoutForRtl;
    private final UserRatingButtonOverlay$PastilleDrawable mLeftDrawable;
    private final int[] mLocationHelper;
    private final int[] mLocationHelperParent;
    private UserRatingButton$OnRateListener mOnRateListener;
    private CoordinatorLayout mParent;
    private final UserRatingButtonOverlay$PastilleDrawable mRightDrawable;
    private boolean mShouldLayout;
    private UserRatingButton mTarget;
    private final Rect mTargetBounds;
    private final ImageView mThumbLeft;
    private final Rect mThumbLeftBounds;
    private final ImageView mThumbRight;
    private final Rect mThumbRightBounds;
    private final UserRatingButtonOverlay$ThumbsAnimations mThumbsAnimations;
    private final Point mTranslateLeft;
    private final Point mTranslateRight;
    
    static {
        CUBIC_BEZIER = PathInterpolatorCompat.create(0.23f, 1.0f, 0.32f, 1.0f);
    }
    
    UserRatingButtonOverlay(Context context, final UserRatingButton$OnRateListener mOnRateListener, final boolean mDark, final boolean b, int n) {
        final boolean b2 = true;
        super(context);
        this.mShouldLayout = false;
        this.mDragOverView = null;
        this.mLocationHelper = new int[2];
        this.mLocationHelperParent = new int[2];
        this.mTargetBounds = new Rect();
        this.mCloseBounds = new Rect();
        this.mThumbLeftBounds = new Rect();
        this.mThumbRightBounds = new Rect();
        this.mTranslateLeft = new Point();
        this.mTranslateRight = new Point();
        this.mDragOverLocationHelper = new int[2];
        this.mDragOverBoundHelper = new Rect();
        inflate(this.getContext(), R$layout.user_rating_overlay, (ViewGroup)this);
        this.mInvertLayoutForRtl = (b && n == 1 && b2);
        this.mDark = mDark;
        context = this.getContext();
        if (this.mDark) {
            n = R$color.thumb_button_dark_dim;
        }
        else {
            n = R$color.thumb_button_light_dim;
        }
        this.setBackground(this.mDimBackground = (Drawable)new ColorDrawable(ContextCompat.getColor(context, n)));
        this.mThumbLeft = (ImageView)this.findViewById(R$id.user_rating_overlay_up);
        this.mThumbRight = (ImageView)this.findViewById(R$id.user_rating_overlay_down);
        (this.mClose = (ImageView)this.findViewById(R$id.user_rating_overlay_close)).setOnClickListener((View$OnClickListener)new UserRatingButtonOverlay$1(this));
        this.mLeftDrawable = this.buildPastilleDrawable(this.getLeftValue());
        this.mRightDrawable = this.buildPastilleDrawable(this.getRightValue());
        this.mCloseDrawable = new UserRatingButtonOverlay$PastilleDrawable(this, R$drawable.ic_close_black_24dp, R$drawable.ic_close_black_24dp, false, null);
        this.mThumbLeft.setImageDrawable((Drawable)this.mLeftDrawable);
        this.mThumbRight.setImageDrawable((Drawable)this.mRightDrawable);
        this.mClose.setImageDrawable((Drawable)this.mCloseDrawable);
        this.mOnRateListener = mOnRateListener;
        final UserRatingButtonOverlay$2 userRatingButtonOverlay$2 = new UserRatingButtonOverlay$2(this);
        this.mThumbLeft.setOnClickListener((View$OnClickListener)userRatingButtonOverlay$2);
        this.mThumbRight.setOnClickListener((View$OnClickListener)userRatingButtonOverlay$2);
        this.mClose.setOnClickListener((View$OnClickListener)userRatingButtonOverlay$2);
        this.setOnClickListener((View$OnClickListener)userRatingButtonOverlay$2);
        this.mThumbsAnimations = new UserRatingButtonOverlay$ThumbsAnimations(this, null);
        this.mThumbsAnimations.mFadeExternalAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new UserRatingButtonOverlay$3(this, mOnRateListener));
        this.mTranslateLeft.x = this.getResources().getDimensionPixelSize(R$dimen.thumbs_motion_translation_x);
        this.mTranslateLeft.y = this.getResources().getDimensionPixelSize(R$dimen.thumbs_motion_translation_y);
        this.mTranslateRight.x = this.getResources().getDimensionPixelSize(R$dimen.thumbs_motion_translation_x);
        this.mTranslateRight.y = this.getResources().getDimensionPixelSize(R$dimen.thumbs_motion_translation_y);
        ViewCompat.setElevation((View)this, this.getResources().getDimensionPixelSize(R$dimen.thumbs_elevation));
    }
    
    private void adjustButtonsGravity() {
        this.mTranslateLeft.x = Math.abs(this.mTranslateRight.x);
        this.mTranslateLeft.y = Math.abs(this.mTranslateRight.y);
        this.mTranslateRight.x = Math.abs(this.mTranslateRight.x);
        this.mTranslateRight.y = Math.abs(this.mTranslateRight.y);
        if (this.mTarget != null && this.mParent != null) {
            this.mTarget.getLocationInWindow(this.mLocationHelper);
            this.mParent.getLocationInWindow(this.mLocationHelperParent);
            final int[] mLocationHelper = this.mLocationHelper;
            mLocationHelper[0] -= this.mLocationHelperParent[0];
            final int[] mLocationHelper2 = this.mLocationHelper;
            mLocationHelper2[1] -= this.mLocationHelperParent[1];
            final int dimensionPixelSize = this.getResources().getDimensionPixelSize(R$dimen.thumbs_overlay_button_size);
            final int n = dimensionPixelSize / 2;
            final int n2 = this.mLocationHelper[0];
            final int x = this.mTranslateLeft.x;
            final int n3 = this.mLocationHelper[0];
            final int x2 = this.mTranslateRight.x;
            if (n2 - x - n < 0) {
                final Point mTranslateLeft = this.mTranslateLeft;
                mTranslateLeft.x *= -1;
                final Point mTranslateLeft2 = this.mTranslateLeft;
                mTranslateLeft2.y *= -1;
                if (this.getLeftValue() == 2) {
                    swapY(this.mTranslateLeft, this.mTranslateRight);
                }
            }
            else if (dimensionPixelSize + (n3 + x2 + n) > this.mParent.getMeasuredWidth()) {
                final Point mTranslateRight = this.mTranslateRight;
                mTranslateRight.x *= -1;
                final Point mTranslateRight2 = this.mTranslateRight;
                mTranslateRight2.y *= -1;
                if (this.getRightValue() == 2) {
                    swapY(this.mTranslateLeft, this.mTranslateRight);
                }
            }
            else if (this.mLocationHelper[1] - this.mTranslateRight.y - n < 0) {
                this.mTranslateRight.y *= -1;
                this.mTranslateLeft.y *= -1;
            }
        }
    }
    
    private UserRatingButtonOverlay$PastilleDrawable buildPastilleDrawable(final int n) {
        if (n == 2) {
            return new UserRatingButtonOverlay$PastilleDrawable(this, R$drawable.ic_thumbs_up_outline, R$drawable.ic_thumbs_up, true, null);
        }
        return new UserRatingButtonOverlay$PastilleDrawable(this, R$drawable.ic_thumbs_down_outline, R$drawable.ic_thumbs_down, true, null);
    }
    
    private void dismiss() {
        if (this.mParent != null) {
            this.mThumbsAnimations.close(new UserRatingButtonOverlay$4(this));
        }
    }
    
    private int getLeftValue() {
        if (this.mInvertLayoutForRtl) {
            return 1;
        }
        return 2;
    }
    
    private int getRightValue() {
        if (this.mInvertLayoutForRtl) {
            return 2;
        }
        return 1;
    }
    
    private boolean hit(final ImageView mDragOverView, final MotionEvent motionEvent) {
        mDragOverView.getLocationInWindow(this.mDragOverLocationHelper);
        this.mDragOverBoundHelper.left = this.mDragOverLocationHelper[0];
        this.mDragOverBoundHelper.top = this.mDragOverLocationHelper[1];
        this.mDragOverBoundHelper.right = this.mDragOverBoundHelper.left + mDragOverView.getMeasuredWidth();
        this.mDragOverBoundHelper.bottom = this.mDragOverBoundHelper.top + mDragOverView.getMeasuredHeight();
        if (motionEvent.getRawX() >= this.mDragOverBoundHelper.left && motionEvent.getRawX() <= this.mDragOverBoundHelper.right && motionEvent.getRawY() >= this.mDragOverBoundHelper.top && motionEvent.getRawY() <= this.mDragOverBoundHelper.bottom) {
            if (this.mDragOverView == null) {
                mDragOverView.performHapticFeedback(1);
                this.mDragOverView = mDragOverView;
            }
            return true;
        }
        return false;
    }
    
    private static int interpolate(final float n, final float n2, final float n3) {
        return (int)(n3 * n2 + n);
    }
    
    private static void swapY(final Point point, final Point point2) {
        point.y ^= point2.y;
        point2.y ^= point.y;
        point.y ^= point2.y;
    }
    
    private static void updateBounds(final Rect rect, final int n, final Rect rect2) {
        rect.left = rect2.centerX() - n / 2;
        rect.top = rect2.centerY() - n / 2;
        rect.right = rect.left + n;
        rect.bottom = rect.top + n;
    }
    
    public boolean isShowing() {
        return this.mParent != null;
    }
    
    void onDragTouchEvent(final MotionEvent motionEvent) {
        if (motionEvent.getAction() == 2) {
            if (this.mThumbsAnimations.getScaleValue() >= 0.8f && !this.hit(this.mThumbLeft, motionEvent) && !this.hit(this.mThumbRight, motionEvent)) {
                this.mDragOverView = null;
            }
        }
        else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            if (this.mDragOverView != null && motionEvent.getAction() == 1) {
                this.mDragOverView.performClick();
                this.mDragOverView = null;
                return;
            }
            this.dismiss();
        }
    }
    
    protected void onLayout(final boolean b, int measuredWidth, int measuredWidth2, final int n, final int n2) {
        if ((b || this.mShouldLayout || this.mThumbsAnimations.isRunning()) && this.mTarget != null && this.mParent != null) {
            this.mTarget.getIconView().getLocationInWindow(this.mLocationHelper);
            this.mParent.getLocationInWindow(this.mLocationHelperParent);
            final int[] mLocationHelper = this.mLocationHelper;
            mLocationHelper[0] -= this.mLocationHelperParent[0];
            final int[] mLocationHelper2 = this.mLocationHelper;
            mLocationHelper2[1] -= this.mLocationHelperParent[1];
            this.mTargetBounds.left = this.mLocationHelper[0];
            this.mTargetBounds.top = this.mLocationHelper[1];
            this.mTargetBounds.right = this.mLocationHelper[0] + this.mTarget.getIconView().getMeasuredWidth();
            this.mTargetBounds.bottom = this.mLocationHelper[1] + this.mTarget.getIconView().getMeasuredHeight();
            measuredWidth = this.mClose.getMeasuredWidth();
            measuredWidth2 = this.mThumbRight.getMeasuredWidth();
            updateBounds(this.mCloseBounds, measuredWidth, this.mTargetBounds);
            updateBounds(this.mThumbLeftBounds, measuredWidth2, this.mTargetBounds);
            updateBounds(this.mThumbRightBounds, measuredWidth2, this.mTargetBounds);
            this.mClose.layout(this.mCloseBounds.left, this.mCloseBounds.top, this.mCloseBounds.right, this.mCloseBounds.bottom);
            this.mThumbLeft.layout(interpolate(this.mThumbLeftBounds.left, this.mThumbsAnimations.getScaleValue(), -this.mTranslateLeft.x), interpolate(this.mThumbLeftBounds.top, this.mThumbsAnimations.getScaleValue(), -this.mTranslateLeft.y), interpolate(this.mThumbLeftBounds.right, this.mThumbsAnimations.getScaleValue(), -this.mTranslateLeft.x), interpolate(this.mThumbLeftBounds.bottom, this.mThumbsAnimations.getScaleValue(), -this.mTranslateLeft.y));
            this.mThumbRight.layout(interpolate(this.mThumbRightBounds.left, this.mThumbsAnimations.getScaleValue(), this.mTranslateRight.x), interpolate(this.mThumbRightBounds.top, this.mThumbsAnimations.getScaleValue(), -this.mTranslateRight.y), interpolate(this.mThumbRightBounds.right, this.mThumbsAnimations.getScaleValue(), this.mTranslateRight.x), interpolate(this.mThumbRightBounds.bottom, this.mThumbsAnimations.getScaleValue(), -this.mTranslateRight.y));
            this.mShouldLayout = false;
        }
    }
    
    protected void onMeasure(final int n, final int n2) {
        this.measureChild((View)this.mThumbRight, n, n2);
        this.measureChild((View)this.mThumbLeft, n, n2);
        this.measureChild((View)this.mClose, n, n2);
        super.onMeasure(n, n2);
    }
    
    void show(final CoordinatorLayout mParent, final UserRatingButton mTarget, final int n) {
        boolean applyAlphaAlsoToIcon = true;
        if (this.mParent == null) {
            this.mTarget = mTarget;
            this.mTarget.getIconView().setVisibility(4);
            (this.mParent = mParent).addView((View)this, (ViewGroup$LayoutParams)new CoordinatorLayout$LayoutParams(-1, -1));
            this.mShouldLayout = true;
            this.mLeftDrawable.setApplyAlphaAlsoToIcon(this.mInvertLayoutForRtl);
            final UserRatingButtonOverlay$PastilleDrawable mRightDrawable = this.mRightDrawable;
            if (this.mInvertLayoutForRtl) {
                applyAlphaAlsoToIcon = false;
            }
            mRightDrawable.setApplyAlphaAlsoToIcon(applyAlphaAlsoToIcon);
            this.mLeftDrawable.setSelected(false);
            this.mRightDrawable.setSelected(false);
            ViewCompat.setElevation((View)this.mThumbLeft, 0.0f);
            ViewCompat.setElevation((View)this.mThumbRight, 0.0f);
            this.adjustButtonsGravity();
            this.mThumbsAnimations.show(n);
        }
    }
}
