// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.view.animation.Interpolator;
import android.content.Context;

class ScrollerCompat$ScrollerCompatImplGingerbread implements ScrollerCompat$ScrollerCompatImpl
{
    @Override
    public void abortAnimation(final Object o) {
        ScrollerCompatGingerbread.abortAnimation(o);
    }
    
    @Override
    public boolean computeScrollOffset(final Object o) {
        return ScrollerCompatGingerbread.computeScrollOffset(o);
    }
    
    @Override
    public Object createScroller(final Context context, final Interpolator interpolator) {
        return ScrollerCompatGingerbread.createScroller(context, interpolator);
    }
    
    @Override
    public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        ScrollerCompatGingerbread.fling(o, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    @Override
    public void fling(final Object o, final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        ScrollerCompatGingerbread.fling(o, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    @Override
    public float getCurrVelocity(final Object o) {
        return 0.0f;
    }
    
    @Override
    public int getCurrX(final Object o) {
        return ScrollerCompatGingerbread.getCurrX(o);
    }
    
    @Override
    public int getCurrY(final Object o) {
        return ScrollerCompatGingerbread.getCurrY(o);
    }
    
    @Override
    public int getFinalX(final Object o) {
        return ScrollerCompatGingerbread.getFinalX(o);
    }
    
    @Override
    public int getFinalY(final Object o) {
        return ScrollerCompatGingerbread.getFinalY(o);
    }
    
    @Override
    public boolean isFinished(final Object o) {
        return ScrollerCompatGingerbread.isFinished(o);
    }
    
    @Override
    public boolean isOverScrolled(final Object o) {
        return ScrollerCompatGingerbread.isOverScrolled(o);
    }
    
    @Override
    public void notifyHorizontalEdgeReached(final Object o, final int n, final int n2, final int n3) {
        ScrollerCompatGingerbread.notifyHorizontalEdgeReached(o, n, n2, n3);
    }
    
    @Override
    public void notifyVerticalEdgeReached(final Object o, final int n, final int n2, final int n3) {
        ScrollerCompatGingerbread.notifyVerticalEdgeReached(o, n, n2, n3);
    }
    
    @Override
    public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4) {
        ScrollerCompatGingerbread.startScroll(o, n, n2, n3, n4);
    }
    
    @Override
    public void startScroll(final Object o, final int n, final int n2, final int n3, final int n4, final int n5) {
        ScrollerCompatGingerbread.startScroll(o, n, n2, n3, n4, n5);
    }
}
