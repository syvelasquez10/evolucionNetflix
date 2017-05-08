// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.View;
import android.animation.TimeInterpolator;
import android.annotation.TargetApi;

@TargetApi(12)
class HoneycombMr1AnimatorCompatProvider implements AnimatorProvider
{
    private TimeInterpolator mDefaultInterpolator;
    
    @Override
    public void clearInterpolator(final View view) {
        if (this.mDefaultInterpolator == null) {
            this.mDefaultInterpolator = new ValueAnimator().getInterpolator();
        }
        view.animate().setInterpolator(this.mDefaultInterpolator);
    }
    
    @Override
    public ValueAnimatorCompat emptyValueAnimator() {
        return new HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat((Animator)ValueAnimator.ofFloat(new float[] { 0.0f, 1.0f }));
    }
}
