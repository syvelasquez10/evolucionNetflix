// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.view;

import android.view.animation.Interpolator;
import android.view.View;

interface ViewPropertyAnimatorCompat$ViewPropertyAnimatorCompatImpl
{
    void alpha(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
    
    void cancel(final ViewPropertyAnimatorCompat p0, final View p1);
    
    void scaleY(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
    
    void setDuration(final ViewPropertyAnimatorCompat p0, final View p1, final long p2);
    
    void setInterpolator(final ViewPropertyAnimatorCompat p0, final View p1, final Interpolator p2);
    
    void setListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorListener p2);
    
    void setUpdateListener(final ViewPropertyAnimatorCompat p0, final View p1, final ViewPropertyAnimatorUpdateListener p2);
    
    void start(final ViewPropertyAnimatorCompat p0, final View p1);
    
    void translationX(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
    
    void translationY(final ViewPropertyAnimatorCompat p0, final View p1, final float p2);
}
