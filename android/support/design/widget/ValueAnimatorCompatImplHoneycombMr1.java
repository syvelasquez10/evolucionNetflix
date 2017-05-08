// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.animation.ValueAnimator$AnimatorUpdateListener;
import android.animation.Animator$AnimatorListener;
import android.animation.TimeInterpolator;
import android.view.animation.Interpolator;
import android.animation.ValueAnimator;

class ValueAnimatorCompatImplHoneycombMr1 extends ValueAnimatorCompat$Impl
{
    final ValueAnimator mValueAnimator;
    
    ValueAnimatorCompatImplHoneycombMr1() {
        this.mValueAnimator = new ValueAnimator();
    }
    
    public void cancel() {
        this.mValueAnimator.cancel();
    }
    
    public float getAnimatedFraction() {
        return this.mValueAnimator.getAnimatedFraction();
    }
    
    public int getAnimatedIntValue() {
        return (int)this.mValueAnimator.getAnimatedValue();
    }
    
    public boolean isRunning() {
        return this.mValueAnimator.isRunning();
    }
    
    public void setDuration(final int n) {
        this.mValueAnimator.setDuration((long)n);
    }
    
    public void setFloatValues(final float n, final float n2) {
        this.mValueAnimator.setFloatValues(new float[] { n, n2 });
    }
    
    public void setIntValues(final int n, final int n2) {
        this.mValueAnimator.setIntValues(new int[] { n, n2 });
    }
    
    public void setInterpolator(final Interpolator interpolator) {
        this.mValueAnimator.setInterpolator((TimeInterpolator)interpolator);
    }
    
    public void setListener(final ValueAnimatorCompat$Impl$AnimatorListenerProxy valueAnimatorCompat$Impl$AnimatorListenerProxy) {
        this.mValueAnimator.addListener((Animator$AnimatorListener)new ValueAnimatorCompatImplHoneycombMr1$2(this, valueAnimatorCompat$Impl$AnimatorListenerProxy));
    }
    
    public void setUpdateListener(final ValueAnimatorCompat$Impl$AnimatorUpdateListenerProxy valueAnimatorCompat$Impl$AnimatorUpdateListenerProxy) {
        this.mValueAnimator.addUpdateListener((ValueAnimator$AnimatorUpdateListener)new ValueAnimatorCompatImplHoneycombMr1$1(this, valueAnimatorCompat$Impl$AnimatorUpdateListenerProxy));
    }
    
    public void start() {
        this.mValueAnimator.start();
    }
}
