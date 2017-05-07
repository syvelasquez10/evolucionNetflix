// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.widget;

import android.os.Build$VERSION;
import android.view.animation.Interpolator;
import android.content.Context;

public class ScrollerCompat
{
    static final int CHASE_FRAME_TIME = 16;
    private static final String TAG = "ScrollerCompat";
    ScrollerCompat$ScrollerCompatImpl mImpl;
    Object mScroller;
    
    private ScrollerCompat(final int n, final Context context, final Interpolator interpolator) {
        if (n >= 14) {
            this.mImpl = new ScrollerCompat$ScrollerCompatImplIcs();
        }
        else if (n >= 9) {
            this.mImpl = new ScrollerCompat$ScrollerCompatImplGingerbread();
        }
        else {
            this.mImpl = new ScrollerCompat$ScrollerCompatImplBase();
        }
        this.mScroller = this.mImpl.createScroller(context, interpolator);
    }
    
    ScrollerCompat(final Context context, final Interpolator interpolator) {
        this(Build$VERSION.SDK_INT, context, interpolator);
    }
    
    public static ScrollerCompat create(final Context context) {
        return create(context, null);
    }
    
    public static ScrollerCompat create(final Context context, final Interpolator interpolator) {
        return new ScrollerCompat(context, interpolator);
    }
    
    public void abortAnimation() {
        this.mImpl.abortAnimation(this.mScroller);
    }
    
    public boolean computeScrollOffset() {
        return this.mImpl.computeScrollOffset(this.mScroller);
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8) {
        this.mImpl.fling(this.mScroller, n, n2, n3, n4, n5, n6, n7, n8);
    }
    
    public void fling(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7, final int n8, final int n9, final int n10) {
        this.mImpl.fling(this.mScroller, n, n2, n3, n4, n5, n6, n7, n8, n9, n10);
    }
    
    public float getCurrVelocity() {
        return this.mImpl.getCurrVelocity(this.mScroller);
    }
    
    public int getCurrX() {
        return this.mImpl.getCurrX(this.mScroller);
    }
    
    public int getCurrY() {
        return this.mImpl.getCurrY(this.mScroller);
    }
    
    public int getFinalX() {
        return this.mImpl.getFinalX(this.mScroller);
    }
    
    public int getFinalY() {
        return this.mImpl.getFinalY(this.mScroller);
    }
    
    public boolean isFinished() {
        return this.mImpl.isFinished(this.mScroller);
    }
    
    public boolean isOverScrolled() {
        return this.mImpl.isOverScrolled(this.mScroller);
    }
    
    public void notifyHorizontalEdgeReached(final int n, final int n2, final int n3) {
        this.mImpl.notifyHorizontalEdgeReached(this.mScroller, n, n2, n3);
    }
    
    public void notifyVerticalEdgeReached(final int n, final int n2, final int n3) {
        this.mImpl.notifyVerticalEdgeReached(this.mScroller, n, n2, n3);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4) {
        this.mImpl.startScroll(this.mScroller, n, n2, n3, n4);
    }
    
    public void startScroll(final int n, final int n2, final int n3, final int n4, final int n5) {
        this.mImpl.startScroll(this.mScroller, n, n2, n3, n4, n5);
    }
}
