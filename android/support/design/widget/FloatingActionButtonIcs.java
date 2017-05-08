// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.graphics.Paint;
import android.os.Build$VERSION;
import android.view.View;
import android.support.v4.view.ViewCompat;
import android.annotation.TargetApi;

@TargetApi(14)
class FloatingActionButtonIcs extends FloatingActionButtonGingerbread
{
    private float mRotation;
    
    FloatingActionButtonIcs(final VisibilityAwareImageButton visibilityAwareImageButton, final ShadowViewDelegate shadowViewDelegate, final ValueAnimatorCompat$Creator valueAnimatorCompat$Creator) {
        super(visibilityAwareImageButton, shadowViewDelegate, valueAnimatorCompat$Creator);
        this.mRotation = this.mView.getRotation();
    }
    
    private boolean shouldAnimateVisibilityChange() {
        return ViewCompat.isLaidOut((View)this.mView) && !this.mView.isInEditMode();
    }
    
    private void updateFromViewRotation() {
        if (Build$VERSION.SDK_INT == 19) {
            if (this.mRotation % 90.0f != 0.0f) {
                if (this.mView.getLayerType() != 1) {
                    this.mView.setLayerType(1, (Paint)null);
                }
            }
            else if (this.mView.getLayerType() != 0) {
                this.mView.setLayerType(0, (Paint)null);
            }
        }
        if (this.mShadowDrawable != null) {
            this.mShadowDrawable.setRotation(-this.mRotation);
        }
        if (this.mBorderDrawable != null) {
            this.mBorderDrawable.setRotation(-this.mRotation);
        }
    }
    
    @Override
    void hide(final FloatingActionButtonImpl$InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener, final boolean b) {
        if (!this.isOrWillBeHidden()) {
            this.mView.animate().cancel();
            if (this.shouldAnimateVisibilityChange()) {
                this.mAnimState = 1;
                this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new FloatingActionButtonIcs$1(this, b, floatingActionButtonImpl$InternalVisibilityChangedListener));
                return;
            }
            final VisibilityAwareImageButton mView = this.mView;
            int n;
            if (b) {
                n = 8;
            }
            else {
                n = 4;
            }
            mView.internalSetVisibility(n, b);
            if (floatingActionButtonImpl$InternalVisibilityChangedListener != null) {
                floatingActionButtonImpl$InternalVisibilityChangedListener.onHidden();
            }
        }
    }
    
    @Override
    void onPreDraw() {
        final float rotation = this.mView.getRotation();
        if (this.mRotation != rotation) {
            this.mRotation = rotation;
            this.updateFromViewRotation();
        }
    }
    
    @Override
    boolean requirePreDrawListener() {
        return true;
    }
    
    @Override
    void show(final FloatingActionButtonImpl$InternalVisibilityChangedListener floatingActionButtonImpl$InternalVisibilityChangedListener, final boolean b) {
        if (!this.isOrWillBeShown()) {
            this.mView.animate().cancel();
            if (this.shouldAnimateVisibilityChange()) {
                this.mAnimState = 2;
                if (this.mView.getVisibility() != 0) {
                    this.mView.setAlpha(0.0f);
                    this.mView.setScaleY(0.0f);
                    this.mView.setScaleX(0.0f);
                }
                this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new FloatingActionButtonIcs$2(this, b, floatingActionButtonImpl$InternalVisibilityChangedListener));
                return;
            }
            this.mView.internalSetVisibility(0, b);
            this.mView.setAlpha(1.0f);
            this.mView.setScaleY(1.0f);
            this.mView.setScaleX(1.0f);
            if (floatingActionButtonImpl$InternalVisibilityChangedListener != null) {
                floatingActionButtonImpl$InternalVisibilityChangedListener.onShown();
            }
        }
    }
}
