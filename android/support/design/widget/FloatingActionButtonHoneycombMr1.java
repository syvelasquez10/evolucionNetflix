// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.View;

class FloatingActionButtonHoneycombMr1 extends FloatingActionButtonEclairMr1
{
    private boolean mIsHiding;
    
    FloatingActionButtonHoneycombMr1(final View view, final ShadowViewDelegate shadowViewDelegate) {
        super(view, shadowViewDelegate);
    }
    
    @Override
    void hide() {
        if (this.mIsHiding) {
            return;
        }
        this.mView.animate().scaleX(0.0f).scaleY(0.0f).alpha(0.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)new FloatingActionButtonHoneycombMr1$1(this));
    }
    
    @Override
    void show() {
        this.mView.animate().scaleX(1.0f).scaleY(1.0f).alpha(1.0f).setDuration(200L).setInterpolator((TimeInterpolator)AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR).setListener((Animator$AnimatorListener)null);
    }
}
