// 
// Decompiled by Procyon v0.5.30
// 

package android.support.design.widget;

import android.view.animation.Interpolator;

abstract class ValueAnimatorCompat$Impl
{
    abstract void addListener(final ValueAnimatorCompat$Impl$AnimatorListenerProxy p0);
    
    abstract void addUpdateListener(final ValueAnimatorCompat$Impl$AnimatorUpdateListenerProxy p0);
    
    abstract void cancel();
    
    abstract void end();
    
    abstract float getAnimatedFloatValue();
    
    abstract float getAnimatedFraction();
    
    abstract int getAnimatedIntValue();
    
    abstract long getDuration();
    
    abstract boolean isRunning();
    
    abstract void setDuration(final long p0);
    
    abstract void setFloatValues(final float p0, final float p1);
    
    abstract void setIntValues(final int p0, final int p1);
    
    abstract void setInterpolator(final Interpolator p0);
    
    abstract void start();
}
