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
    
    int getCurrX(final Object p0);
    
    int getCurrY(final Object p0);
    
    int getFinalX(final Object p0);
    
    int getFinalY(final Object p0);
    
    void startScroll(final Object p0, final int p1, final int p2, final int p3, final int p4, final int p5);
}
