// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Interpolator;

class ValueAnimatorCompat
{
    private final ValueAnimatorCompat$Impl mImpl;
    
    ValueAnimatorCompat(final ValueAnimatorCompat$Impl mImpl) {
        this.mImpl = mImpl;
    }
    
    public void cancel() {
        this.mImpl.cancel();
    }
    
    public float getAnimatedFraction() {
        return this.mImpl.getAnimatedFraction();
    }
    
    public int getAnimatedIntValue() {
        return this.mImpl.getAnimatedIntValue();
    }
    
    public boolean isRunning() {
        return this.mImpl.isRunning();
    }
    
    public void setDuration(final int duration) {
        this.mImpl.setDuration(duration);
    }
    
    public void setFloatValues(final float n, final float n2) {
        this.mImpl.setFloatValues(n, n2);
    }
    
    public void setIntValues(final int n, final int n2) {
        this.mImpl.setIntValues(n, n2);
    }
    
    public void setInterpolator(final Interpolator interpolator) {
        this.mImpl.setInterpolator(interpolator);
    }
    
    public void setListener(final ValueAnimatorCompat$AnimatorListener valueAnimatorCompat$AnimatorListener) {
        if (valueAnimatorCompat$AnimatorListener != null) {
            this.mImpl.setListener(new ValueAnimatorCompat$2(this, valueAnimatorCompat$AnimatorListener));
            return;
        }
        this.mImpl.setListener(null);
    }
    
    public void setUpdateListener(final ValueAnimatorCompat$AnimatorUpdateListener valueAnimatorCompat$AnimatorUpdateListener) {
        if (valueAnimatorCompat$AnimatorUpdateListener != null) {
            this.mImpl.setUpdateListener(new ValueAnimatorCompat$1(this, valueAnimatorCompat$AnimatorUpdateListener));
            return;
        }
        this.mImpl.setUpdateListener(null);
    }
    
    public void start() {
        this.mImpl.start();
    }
}
