// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

final class OrientationHelper$1 extends OrientationHelper
{
    OrientationHelper$1(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        super(recyclerView$LayoutManager, null);
    }
    
    @Override
    public int getDecoratedEnd(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).rightMargin + this.mLayoutManager.getDecoratedRight(view);
    }
    
    @Override
    public int getDecoratedMeasurement(final View view) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        return recyclerView$LayoutParams.rightMargin + (this.mLayoutManager.getDecoratedMeasuredWidth(view) + recyclerView$LayoutParams.leftMargin);
    }
    
    @Override
    public int getDecoratedMeasurementInOther(final View view) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        return recyclerView$LayoutParams.bottomMargin + (this.mLayoutManager.getDecoratedMeasuredHeight(view) + recyclerView$LayoutParams.topMargin);
    }
    
    @Override
    public int getDecoratedStart(final View view) {
        return this.mLayoutManager.getDecoratedLeft(view) - ((RecyclerView$LayoutParams)view.getLayoutParams()).leftMargin;
    }
    
    @Override
    public int getEnd() {
        return this.mLayoutManager.getWidth();
    }
    
    @Override
    public int getEndAfterPadding() {
        return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public int getEndPadding() {
        return this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingLeft();
    }
    
    @Override
    public int getTotalSpace() {
        return this.mLayoutManager.getWidth() - this.mLayoutManager.getPaddingLeft() - this.mLayoutManager.getPaddingRight();
    }
    
    @Override
    public void offsetChildren(final int n) {
        this.mLayoutManager.offsetChildrenHorizontal(n);
    }
}
