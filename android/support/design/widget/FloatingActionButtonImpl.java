// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.PorterDuff$Mode;
import android.graphics.drawable.GradientDrawable;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.design.R$color;
import android.content.res.ColorStateList;
import android.graphics.Rect;
import android.view.ViewTreeObserver$OnPreDrawListener;
import android.graphics.drawable.Drawable;
import android.view.animation.Interpolator;

abstract class FloatingActionButtonImpl
{
    static final Interpolator ANIM_INTERPOLATOR;
    static final int ANIM_STATE_HIDING = 1;
    static final int ANIM_STATE_NONE = 0;
    static final int ANIM_STATE_SHOWING = 2;
    static final int[] EMPTY_STATE_SET;
    static final int[] ENABLED_STATE_SET;
    static final int[] FOCUSED_ENABLED_STATE_SET;
    static final long PRESSED_ANIM_DELAY = 100L;
    static final long PRESSED_ANIM_DURATION = 100L;
    static final int[] PRESSED_ENABLED_STATE_SET;
    static final int SHOW_HIDE_ANIM_DURATION = 200;
    int mAnimState;
    final ValueAnimatorCompat$Creator mAnimatorCreator;
    CircularBorderDrawable mBorderDrawable;
    Drawable mContentBackground;
    float mElevation;
    private ViewTreeObserver$OnPreDrawListener mPreDrawListener;
    float mPressedTranslationZ;
    Drawable mRippleDrawable;
    final ShadowViewDelegate mShadowViewDelegate;
    Drawable mShapeDrawable;
    private final Rect mTmpRect;
    final VisibilityAwareImageButton mView;
    
    static {
        ANIM_INTERPOLATOR = AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR;
        PRESSED_ENABLED_STATE_SET = new int[] { 16842919, 16842910 };
        FOCUSED_ENABLED_STATE_SET = new int[] { 16842908, 16842910 };
        ENABLED_STATE_SET = new int[] { 16842910 };
        EMPTY_STATE_SET = new int[0];
    }
    
    FloatingActionButtonImpl(final VisibilityAwareImageButton mView, final ShadowViewDelegate mShadowViewDelegate, final ValueAnimatorCompat$Creator mAnimatorCreator) {
        this.mAnimState = 0;
        this.mTmpRect = new Rect();
        this.mView = mView;
        this.mShadowViewDelegate = mShadowViewDelegate;
        this.mAnimatorCreator = mAnimatorCreator;
    }
    
    private void ensurePreDrawListener() {
        if (this.mPreDrawListener == null) {
            this.mPreDrawListener = (ViewTreeObserver$OnPreDrawListener)new FloatingActionButtonImpl$1(this);
        }
    }
    
    CircularBorderDrawable createBorderDrawable(final int n, final ColorStateList borderTint) {
        final Context context = this.mView.getContext();
        final CircularBorderDrawable circularDrawable = this.newCircularDrawable();
        circularDrawable.setGradientColors(ContextCompat.getColor(context, R$color.design_fab_stroke_top_outer_color), ContextCompat.getColor(context, R$color.design_fab_stroke_top_inner_color), ContextCompat.getColor(context, R$color.design_fab_stroke_end_inner_color), ContextCompat.getColor(context, R$color.design_fab_stroke_end_outer_color));
        circularDrawable.setBorderWidth(n);
        circularDrawable.setBorderTint(borderTint);
        return circularDrawable;
    }
    
    GradientDrawable createShapeDrawable() {
        final GradientDrawable gradientDrawableForShape = this.newGradientDrawableForShape();
        gradientDrawableForShape.setShape(1);
        gradientDrawableForShape.setColor(-1);
        return gradientDrawableForShape;
    }
    
    final Drawable getContentBackground() {
        return this.mContentBackground;
    }
    
    abstract float getElevation();
    
    abstract void getPadding(final Rect p0);
    
    abstract void hide(final FloatingActionButtonImpl$InternalVisibilityChangedListener p0, final boolean p1);
    
    boolean isOrWillBeHidden() {
        if (this.mView.getVisibility() == 0) {
            if (this.mAnimState != 1) {
                return false;
            }
        }
        else if (this.mAnimState == 2) {
            return false;
        }
        return true;
    }
    
    boolean isOrWillBeShown() {
        if (this.mView.getVisibility() != 0) {
            if (this.mAnimState != 2) {
                return false;
            }
        }
        else if (this.mAnimState == 1) {
            return false;
        }
        return true;
    }
    
    abstract void jumpDrawableToCurrentState();
    
    CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawable();
    }
    
    GradientDrawable newGradientDrawableForShape() {
        return new GradientDrawable();
    }
    
    void onAttachedToWindow() {
        if (this.requirePreDrawListener()) {
            this.ensurePreDrawListener();
            this.mView.getViewTreeObserver().addOnPreDrawListener(this.mPreDrawListener);
        }
    }
    
    abstract void onCompatShadowChanged();
    
    void onDetachedFromWindow() {
        if (this.mPreDrawListener != null) {
            this.mView.getViewTreeObserver().removeOnPreDrawListener(this.mPreDrawListener);
            this.mPreDrawListener = null;
        }
    }
    
    abstract void onDrawableStateChanged(final int[] p0);
    
    abstract void onElevationsChanged(final float p0, final float p1);
    
    void onPaddingUpdated(final Rect rect) {
    }
    
    void onPreDraw() {
    }
    
    boolean requirePreDrawListener() {
        return false;
    }
    
    abstract void setBackgroundDrawable(final ColorStateList p0, final PorterDuff$Mode p1, final int p2, final int p3);
    
    abstract void setBackgroundTintList(final ColorStateList p0);
    
    abstract void setBackgroundTintMode(final PorterDuff$Mode p0);
    
    final void setElevation(final float mElevation) {
        if (this.mElevation != mElevation) {
            this.onElevationsChanged(this.mElevation = mElevation, this.mPressedTranslationZ);
        }
    }
    
    final void setPressedTranslationZ(final float mPressedTranslationZ) {
        if (this.mPressedTranslationZ != mPressedTranslationZ) {
            this.mPressedTranslationZ = mPressedTranslationZ;
            this.onElevationsChanged(this.mElevation, mPressedTranslationZ);
        }
    }
    
    abstract void setRippleColor(final int p0);
    
    abstract void show(final FloatingActionButtonImpl$InternalVisibilityChangedListener p0, final boolean p1);
    
    final void updatePadding() {
        final Rect mTmpRect = this.mTmpRect;
        this.getPadding(mTmpRect);
        this.onPaddingUpdated(mTmpRect);
        this.mShadowViewDelegate.setShadowPadding(mTmpRect.left, mTmpRect.top, mTmpRect.right, mTmpRect.bottom);
    }
}
