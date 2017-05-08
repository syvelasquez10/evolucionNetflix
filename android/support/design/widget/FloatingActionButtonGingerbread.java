// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.PorterDuff$Mode;
import android.view.animation.Animation;
import android.view.animation.Animation$AnimationListener;
import android.view.animation.AnimationUtils;
import android.support.design.R$anim;
import android.graphics.Rect;
import android.content.res.ColorStateList;

class FloatingActionButtonGingerbread extends FloatingActionButtonImpl
{
    ShadowDrawableWrapper mShadowDrawable;
    private final StateListAnimator mStateListAnimator;
    
    FloatingActionButtonGingerbread(final VisibilityAwareImageButton visibilityAwareImageButton, final ShadowViewDelegate shadowViewDelegate, final ValueAnimatorCompat$Creator valueAnimatorCompat$Creator) {
        super(visibilityAwareImageButton, shadowViewDelegate, valueAnimatorCompat$Creator);
        (this.mStateListAnimator = new StateListAnimator()).addState(FloatingActionButtonGingerbread.PRESSED_ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonGingerbread$ElevateToTranslationZAnimation(this)));
        this.mStateListAnimator.addState(FloatingActionButtonGingerbread.FOCUSED_ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonGingerbread$ElevateToTranslationZAnimation(this)));
        this.mStateListAnimator.addState(FloatingActionButtonGingerbread.ENABLED_STATE_SET, this.createAnimator(new FloatingActionButtonGingerbread$ResetElevationAnimation(this)));
        this.mStateListAnimator.addState(FloatingActionButtonGingerbread.EMPTY_STATE_SET, this.createAnimator(new FloatingActionButtonGingerbread$DisabledElevationAnimation(this)));
    }
    
    private ValueAnimatorCompat createAnimator(final FloatingActionButtonGingerbread$ShadowAnimatorImpl floatingActionButtonGingerbread$ShadowAnimatorImpl) {
        final ValueAnimatorCompat animator = this.mAnimatorCreator.createAnimator();
        animator.setInterpolator(FloatingActionButtonGingerbread.ANIM_INTERPOLATOR);
        animator.setDuration(100L);
        animator.addListener(floatingActionButtonGingerbread$ShadowAnimatorImpl);
        animator.addUpdateListener(floatingActionButtonGingerbread$ShadowAnimatorImpl);
        animator.setFloatValues(0.0f, 1.0f);
        return animator;
    }
    
    private static ColorStateList createColorStateList(final int n) {
        return new ColorStateList(new int[][] { FloatingActionButtonGingerbread.FOCUSED_ENABLED_STATE_SET, FloatingActionButtonGingerbread.PRESSED_ENABLED_STATE_SET, new int[0] }, new int[] { n, n, 0 });
    }
    
    @Override
    float getElevation() {
        return this.mElevation;
    }
    
    @Override
    void getPadding(final Rect rect) {
        this.mShadowDrawable.getPadding(rect);
    }
    
    @Override
    void hide(final FloatingActionButtonImpl$InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener, final boolean b) {
        if (this.isOrWillBeHidden()) {
            return;
        }
        this.mAnimState = 1;
        final Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_fab_out);
        loadAnimation.setInterpolator(android.support.design.widget.AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR);
        loadAnimation.setDuration(200L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new FloatingActionButtonGingerbread$1(this, b, floatingActionButtonImpl$InternalVisibilityChangedListener));
        this.mView.startAnimation(loadAnimation);
    }
    
    @Override
    void jumpDrawableToCurrentState() {
        this.mStateListAnimator.jumpToCurrentState();
    }
    
    @Override
    void onCompatShadowChanged() {
    }
    
    @Override
    void onDrawableStateChanged(final int[] state) {
        this.mStateListAnimator.setState(state);
    }
    
    @Override
    void onElevationsChanged(final float n, final float n2) {
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setShadowSize(n, this.mPressedTranslationZ + n);
            this.updatePadding();
        }
    }
    
    @Override
    void setBackgroundDrawable(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int n, final int n2) {
        DrawableCompat.setTintList(this.mShapeDrawable = DrawableCompat.wrap((Drawable)this.createShapeDrawable()), list);
        if (porterDuff$Mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
        DrawableCompat.setTintList(this.mRippleDrawable = DrawableCompat.wrap((Drawable)this.createShapeDrawable()), createColorStateList(n));
        Drawable[] array;
        if (n2 > 0) {
            this.mBorderDrawable = this.createBorderDrawable(n2, list);
            array = new Drawable[] { this.mBorderDrawable, this.mShapeDrawable, this.mRippleDrawable };
        }
        else {
            this.mBorderDrawable = null;
            array = new Drawable[] { this.mShapeDrawable, this.mRippleDrawable };
        }
        this.mContentBackground = (Drawable)new LayerDrawable(array);
        (this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getResources(), this.mContentBackground, this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ)).setAddPaddingForCorners(false);
        this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
    }
    
    @Override
    void setBackgroundTintList(final ColorStateList borderTint) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintList(this.mShapeDrawable, borderTint);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setBorderTint(borderTint);
        }
    }
    
    @Override
    void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        if (this.mShapeDrawable != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
    }
    
    @Override
    void setRippleColor(final int n) {
        if (this.mRippleDrawable != null) {
            DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(n));
        }
    }
    
    @Override
    void show(final FloatingActionButtonImpl$InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener, final boolean b) {
        if (this.isOrWillBeShown()) {
            return;
        }
        this.mAnimState = 2;
        this.mView.internalSetVisibility(0, b);
        final Animation loadAnimation = AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_fab_in);
        loadAnimation.setDuration(200L);
        loadAnimation.setInterpolator(android.support.design.widget.AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new FloatingActionButtonGingerbread$2(this, floatingActionButtonImpl$InternalVisibilityChangedListener));
        this.mView.startAnimation(loadAnimation);
    }
}
