// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.support.v4.view.ViewCompat;
import android.view.View;

class FloatingActionButtonHoneycombMr1 extends FloatingActionButtonEclairMr1
{
    private boolean mIsHiding;
    
    FloatingActionButtonHoneycombMr1(final View view, final ShadowViewDelegate shadowViewDelegate) {
        super(view, shadowViewDelegate);
    }
    
    @Override
    void hide() {
        if (this.mIsHiding || this.mView.getVisibility() != 0) {
            return;
        }
        if (!ViewCompat.isLaidOut(this.mView) || this.mView.isInEditMode()) {
            this.mView.setVisibility(8);
            return;
        }
        this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new FloatingActionButtonHoneycombMr1$1(this));
    }
    
    @Override
    void show() {
        if (this.mView.getVisibility() != 0) {
            if (!ViewCompat.isLaidOut(this.mView) || this.mView.isInEditMode()) {
                this.mView.setVisibility(0);
                this.mView.setAlpha(1.0f);
                this.mView.setScaleY(1.0f);
                this.mView.setScaleX(1.0f);
                return;
            }
            this.mView.setAlpha(0.0f);
            this.mView.setScaleY(0.0f);
            this.mView.setScaleX(0.0f);
            this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new FloatingActionButtonHoneycombMr1$2(this));
        }
    }
}
