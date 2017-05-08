// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.drawable.LayerDrawable;
import android.graphics.drawable.GradientDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.PorterDuff$Mode;
import android.view.animation.Animation$AnimationListener;
import android.support.design.R$anim;
import android.graphics.Rect;
import android.content.res.ColorStateList;
import android.view.animation.Animation;
import android.view.View;
import android.graphics.drawable.Drawable;

class FloatingActionButtonEclairMr1 extends FloatingActionButtonImpl
{
    private int mAnimationDuration;
    private Drawable mBorderDrawable;
    private float mElevation;
    private boolean mIsHiding;
    private float mPressedTranslationZ;
    private Drawable mRippleDrawable;
    ShadowDrawableWrapper mShadowDrawable;
    private Drawable mShapeDrawable;
    private StateListAnimator mStateListAnimator;
    
    FloatingActionButtonEclairMr1(final View target, final ShadowViewDelegate shadowViewDelegate) {
        super(target, shadowViewDelegate);
        this.mAnimationDuration = target.getResources().getInteger(17694720);
        (this.mStateListAnimator = new StateListAnimator()).setTarget(target);
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.PRESSED_ENABLED_STATE_SET, this.setupAnimation(new FloatingActionButtonEclairMr1$ElevateToTranslationZAnimation(this, null)));
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.FOCUSED_ENABLED_STATE_SET, this.setupAnimation(new FloatingActionButtonEclairMr1$ElevateToTranslationZAnimation(this, null)));
        this.mStateListAnimator.addState(FloatingActionButtonEclairMr1.EMPTY_STATE_SET, this.setupAnimation(new FloatingActionButtonEclairMr1$ResetElevationAnimation(this, null)));
    }
    
    private static ColorStateList createColorStateList(final int n) {
        return new ColorStateList(new int[][] { FloatingActionButtonEclairMr1.FOCUSED_ENABLED_STATE_SET, FloatingActionButtonEclairMr1.PRESSED_ENABLED_STATE_SET, new int[0] }, new int[] { n, n, 0 });
    }
    
    private Animation setupAnimation(final Animation animation) {
        animation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        animation.setDuration((long)this.mAnimationDuration);
        return animation;
    }
    
    private void updatePadding() {
        final Rect rect = new Rect();
        this.mShadowDrawable.getPadding(rect);
        this.mShadowViewDelegate.setShadowPadding(rect.left, rect.top, rect.right, rect.bottom);
    }
    
    @Override
    void hide() {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            return;
        }
        final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_fab_out);
        loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
        loadAnimation.setDuration(200L);
        loadAnimation.setAnimationListener((Animation$AnimationListener)new FloatingActionButtonEclairMr1$1(this));
        this.mView.startAnimation(loadAnimation);
    }
    
    @Override
    void jumpDrawableToCurrentState() {
        this.mStateListAnimator.jumpToCurrentState();
    }
    
    @Override
    void onDrawableStateChanged(final int[] state) {
        this.mStateListAnimator.setState(state);
    }
    
    @Override
    void setBackgroundDrawable(final Drawable drawable, final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int n, final int n2) {
        DrawableCompat.setTintList(this.mShapeDrawable = DrawableCompat.wrap(drawable.mutate()), list);
        if (porterDuff$Mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
        final GradientDrawable gradientDrawable = new GradientDrawable();
        gradientDrawable.setShape(1);
        gradientDrawable.setColor(-1);
        gradientDrawable.setCornerRadius(this.mShadowViewDelegate.getRadius());
        DrawableCompat.setTintList(this.mRippleDrawable = DrawableCompat.wrap((Drawable)gradientDrawable), createColorStateList(n));
        DrawableCompat.setTintMode(this.mRippleDrawable, PorterDuff$Mode.MULTIPLY);
        Drawable[] array;
        if (n2 > 0) {
            this.mBorderDrawable = this.createBorderDrawable(n2, list);
            array = new Drawable[] { this.mBorderDrawable, this.mShapeDrawable, this.mRippleDrawable };
        }
        else {
            this.mBorderDrawable = null;
            array = new Drawable[] { this.mShapeDrawable, this.mRippleDrawable };
        }
        (this.mShadowDrawable = new ShadowDrawableWrapper(this.mView.getResources(), (Drawable)new LayerDrawable(array), this.mShadowViewDelegate.getRadius(), this.mElevation, this.mElevation + this.mPressedTranslationZ)).setAddPaddingForCorners(false);
        this.mShadowViewDelegate.setBackgroundDrawable(this.mShadowDrawable);
        this.updatePadding();
    }
    
    @Override
    void setBackgroundTintList(final ColorStateList list) {
        DrawableCompat.setTintList(this.mShapeDrawable, list);
        if (this.mBorderDrawable != null) {
            DrawableCompat.setTintList(this.mBorderDrawable, list);
        }
    }
    
    @Override
    void setBackgroundTintMode(final PorterDuff$Mode porterDuff$Mode) {
        DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
    }
    
    @Override
    void setElevation(final float mElevation) {
        if (this.mElevation != mElevation && this.mShadowDrawable != null) {
            this.mShadowDrawable.setShadowSize(mElevation, this.mPressedTranslationZ + mElevation);
            this.mElevation = mElevation;
            this.updatePadding();
        }
    }
    
    @Override
    void setPressedTranslationZ(final float mPressedTranslationZ) {
        if (this.mPressedTranslationZ != mPressedTranslationZ && this.mShadowDrawable != null) {
            this.mPressedTranslationZ = mPressedTranslationZ;
            this.mShadowDrawable.setMaxShadowSize(this.mElevation + mPressedTranslationZ);
            this.updatePadding();
        }
    }
    
    @Override
    void setRippleColor(final int n) {
        DrawableCompat.setTintList(this.mRippleDrawable, createColorStateList(n));
    }
    
    @Override
    void show() {
        if (this.mView.getVisibility() != 0 || this.mIsHiding) {
            this.mView.clearAnimation();
            this.mView.setVisibility(0);
            final Animation loadAnimation = android.view.animation.AnimationUtils.loadAnimation(this.mView.getContext(), R$anim.design_fab_in);
            loadAnimation.setDuration(200L);
            loadAnimation.setInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
            this.mView.startAnimation(loadAnimation);
        }
    }
}
