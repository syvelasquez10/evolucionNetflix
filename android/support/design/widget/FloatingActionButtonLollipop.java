// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.graphics.PorterDuff$Mode;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.animation.TimeInterpolator;
import android.view.View;
import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.animation.AnimatorSet;
import android.animation.StateListAnimator;
import android.graphics.Rect;
import android.graphics.drawable.InsetDrawable;
import android.annotation.TargetApi;

@TargetApi(21)
class FloatingActionButtonLollipop extends FloatingActionButtonIcs
{
    private InsetDrawable mInsetDrawable;
    
    FloatingActionButtonLollipop(final VisibilityAwareImageButton visibilityAwareImageButton, final ShadowViewDelegate shadowViewDelegate, final ValueAnimatorCompat$Creator valueAnimatorCompat$Creator) {
        super(visibilityAwareImageButton, shadowViewDelegate, valueAnimatorCompat$Creator);
    }
    
    public float getElevation() {
        return this.mView.getElevation();
    }
    
    @Override
    void getPadding(final Rect rect) {
        if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
            final float radius = this.mShadowViewDelegate.getRadius();
            final float n = this.getElevation() + this.mPressedTranslationZ;
            final int n2 = (int)Math.ceil(ShadowDrawableWrapper.calculateHorizontalPadding(n, radius, false));
            final int n3 = (int)Math.ceil(ShadowDrawableWrapper.calculateVerticalPadding(n, radius, false));
            rect.set(n2, n3, n2, n3);
            return;
        }
        rect.set(0, 0, 0, 0);
    }
    
    @Override
    void jumpDrawableToCurrentState() {
    }
    
    @Override
    CircularBorderDrawable newCircularDrawable() {
        return new CircularBorderDrawableLollipop();
    }
    
    @Override
    void onCompatShadowChanged() {
        this.updatePadding();
    }
    
    @Override
    void onDrawableStateChanged(final int[] array) {
    }
    
    @Override
    void onElevationsChanged(final float n, final float n2) {
        final StateListAnimator stateListAnimator = new StateListAnimator();
        final AnimatorSet set = new AnimatorSet();
        set.play((Animator)ObjectAnimator.ofFloat((Object)this.mView, "elevation", new float[] { n }).setDuration(0L)).with((Animator)ObjectAnimator.ofFloat((Object)this.mView, View.TRANSLATION_Z, new float[] { n2 }).setDuration(100L));
        set.setInterpolator((TimeInterpolator)FloatingActionButtonLollipop.ANIM_INTERPOLATOR);
        stateListAnimator.addState(FloatingActionButtonLollipop.PRESSED_ENABLED_STATE_SET, (Animator)set);
        final AnimatorSet set2 = new AnimatorSet();
        set2.play((Animator)ObjectAnimator.ofFloat((Object)this.mView, "elevation", new float[] { n }).setDuration(0L)).with((Animator)ObjectAnimator.ofFloat((Object)this.mView, View.TRANSLATION_Z, new float[] { n2 }).setDuration(100L));
        set2.setInterpolator((TimeInterpolator)FloatingActionButtonLollipop.ANIM_INTERPOLATOR);
        stateListAnimator.addState(FloatingActionButtonLollipop.FOCUSED_ENABLED_STATE_SET, (Animator)set2);
        final AnimatorSet set3 = new AnimatorSet();
        final AnimatorSet set4 = new AnimatorSet();
        set4.play((Animator)ObjectAnimator.ofFloat((Object)this.mView, View.TRANSLATION_Z, new float[] { 0.0f }).setDuration(100L)).after(100L);
        set3.play((Animator)ObjectAnimator.ofFloat((Object)this.mView, "elevation", new float[] { n }).setDuration(0L)).with((Animator)set4);
        set3.setInterpolator((TimeInterpolator)FloatingActionButtonLollipop.ANIM_INTERPOLATOR);
        stateListAnimator.addState(FloatingActionButtonLollipop.ENABLED_STATE_SET, (Animator)set3);
        final AnimatorSet set5 = new AnimatorSet();
        set5.play((Animator)ObjectAnimator.ofFloat((Object)this.mView, "elevation", new float[] { 0.0f }).setDuration(0L)).with((Animator)ObjectAnimator.ofFloat((Object)this.mView, View.TRANSLATION_Z, new float[] { 0.0f }).setDuration(0L));
        set5.setInterpolator((TimeInterpolator)FloatingActionButtonLollipop.ANIM_INTERPOLATOR);
        stateListAnimator.addState(FloatingActionButtonLollipop.EMPTY_STATE_SET, (Animator)set5);
        this.mView.setStateListAnimator(stateListAnimator);
        if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
            this.updatePadding();
        }
    }
    
    @Override
    void onPaddingUpdated(final Rect rect) {
        if (this.mShadowViewDelegate.isCompatPaddingEnabled()) {
            this.mInsetDrawable = new InsetDrawable(this.mRippleDrawable, rect.left, rect.top, rect.right, rect.bottom);
            this.mShadowViewDelegate.setBackgroundDrawable((Drawable)this.mInsetDrawable);
            return;
        }
        this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
    }
    
    @Override
    boolean requirePreDrawListener() {
        return false;
    }
    
    @Override
    void setBackgroundDrawable(final ColorStateList list, final PorterDuff$Mode porterDuff$Mode, final int n, final int n2) {
        DrawableCompat.setTintList(this.mShapeDrawable = DrawableCompat.wrap((Drawable)this.createShapeDrawable()), list);
        if (porterDuff$Mode != null) {
            DrawableCompat.setTintMode(this.mShapeDrawable, porterDuff$Mode);
        }
        Object mShapeDrawable;
        if (n2 > 0) {
            this.mBorderDrawable = this.createBorderDrawable(n2, list);
            mShapeDrawable = new LayerDrawable(new Drawable[] { this.mBorderDrawable, this.mShapeDrawable });
        }
        else {
            this.mBorderDrawable = null;
            mShapeDrawable = this.mShapeDrawable;
        }
        this.mRippleDrawable = (Drawable)new RippleDrawable(ColorStateList.valueOf(n), (Drawable)mShapeDrawable, (Drawable)null);
        this.mContentBackground = this.mRippleDrawable;
        this.mShadowViewDelegate.setBackgroundDrawable(this.mRippleDrawable);
    }
    
    @Override
    void setRippleColor(final int rippleColor) {
        if (this.mRippleDrawable instanceof RippleDrawable) {
            ((RippleDrawable)this.mRippleDrawable).setColor(ColorStateList.valueOf(rippleColor));
            return;
        }
        super.setRippleColor(rippleColor);
    }
}
