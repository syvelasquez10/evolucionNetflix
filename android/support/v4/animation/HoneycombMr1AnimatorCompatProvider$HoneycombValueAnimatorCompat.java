// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.animation;

import android.view.View;
import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.ValueAnimator;
import android.animation.Animator$AnimatorListener;
import android.animation.Animator;

class HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat implements ValueAnimatorCompat
{
    final Animator mWrapped;
    
    public HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat(final Animator mWrapped) {
        this.mWrapped = mWrapped;
    }
    
    @Override
    public void addListener(final AnimatorListenerCompat animatorListenerCompat) {
        this.mWrapped.addListener((Animator$AnimatorListener)new HoneycombMr1AnimatorCompatProvider$AnimatorListenerCompatWrapper(animatorListenerCompat, this));
    }
    
    @Override
    public void addUpdateListener(final AnimatorUpdateListenerCompat animatorUpdateListenerCompat) {
        if (this.mWrapped instanceof ValueAnimator) {
            ((ValueAnimator)this.mWrapped).addUpdateListener((ValueAnimator$AnimatorUpdateListener)new HoneycombMr1AnimatorCompatProvider$HoneycombValueAnimatorCompat$1(this, animatorUpdateListenerCompat));
        }
    }
    
    @Override
    public void cancel() {
        this.mWrapped.cancel();
    }
    
    @Override
    public float getAnimatedFraction() {
        return ((ValueAnimator)this.mWrapped).getAnimatedFraction();
    }
    
    @Override
    public void setDuration(final long duration) {
        this.mWrapped.setDuration(duration);
    }
    
    @Override
    public void setTarget(final View target) {
        this.mWrapped.setTarget((Object)target);
    }
    
    @Override
    public void start() {
        this.mWrapped.start();
    }
}
