// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.animation.ValueAnimator;
import android.view.View;
import android.animation.TimeInterpolator;

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
}
