// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

public abstract class OrientationHelper
{
    private int mLastTotalSpace;
    protected final RecyclerView$LayoutManager mLayoutManager;
    
    private OrientationHelper(final RecyclerView$LayoutManager mLayoutManager) {
        this.mLastTotalSpace = Integer.MIN_VALUE;
        this.mLayoutManager = mLayoutManager;
    }
    
    public static OrientationHelper createHorizontalHelper(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        return new OrientationHelper$1(recyclerView$LayoutManager);
    }
    
    public static OrientationHelper createOrientationHelper(final RecyclerView$LayoutManager recyclerView$LayoutManager, final int n) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("invalid orientation");
            }
            case 0: {
                return createHorizontalHelper(recyclerView$LayoutManager);
            }
            case 1: {
                return createVerticalHelper(recyclerView$LayoutManager);
            }
        }
    }
    
    public static OrientationHelper createVerticalHelper(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        return new OrientationHelper$2(recyclerView$LayoutManager);
    }
    
    public abstract int getDecoratedEnd(final View p0);
    
    public abstract int getDecoratedMeasurement(final View p0);
    
    public abstract int getDecoratedMeasurementInOther(final View p0);
    
    public abstract int getDecoratedStart(final View p0);
    
    public abstract int getEnd();
    
    public abstract int getEndAfterPadding();
    
    public abstract int getEndPadding();
    
    public abstract int getStartAfterPadding();
    
    public abstract int getTotalSpace();
    
    public int getTotalSpaceChange() {
        if (Integer.MIN_VALUE == this.mLastTotalSpace) {
            return 0;
        }
        return this.getTotalSpace() - this.mLastTotalSpace;
    }
    
    public abstract void offsetChildren(final int p0);
    
    public void onLayoutComplete() {
        this.mLastTotalSpace = this.getTotalSpace();
    }
}
