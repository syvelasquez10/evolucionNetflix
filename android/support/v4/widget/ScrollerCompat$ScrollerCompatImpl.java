// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.Interpolator;
import android.content.Context;

interface ScrollerCompat$ScrollerCompatImpl
{
    void abortAnimation(final Object p0);
    
    boolean computeScrollOffset(final Object p0);
    
    Object createScroller(final Context p0, final Interpolator p1);
    
    void fling(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8);
    
    void fling(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5, final int p6, final int p7, final int p8, final int p9, final int p10);
    
    float getCurrVelocity(final Object p0);
    
    int getCurrX(final Object p0);
    
    int getCurrY(final Object p0);
    
    int getFinalX(final Object p0);
    
    int getFinalY(final Object p0);
    
    boolean isFinished(final Object p0);
    
    boolean isOverScrolled(final Object p0);
    
    void notifyHorizontalEdgeReached(final Object p0, final int p1, final int p2, final int p3);
    
    void notifyVerticalEdgeReached(final Object p0, final int p1, final int p2, final int p3);
    
    void startScroll(final Object p0, final int p1, final int p2, final int p3, final int p4);
    
    void startScroll(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5);
}
