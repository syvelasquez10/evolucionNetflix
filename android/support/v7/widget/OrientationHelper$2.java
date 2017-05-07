// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

final class OrientationHelper$2 extends OrientationHelper
{
    OrientationHelper$2(final RecyclerView$LayoutManager recyclerView$LayoutManager) {
        super(recyclerView$LayoutManager, null);
    }
    
    @Override
    public int getDecoratedEnd(final View view) {
        return ((RecyclerView$LayoutParams)view.getLayoutParams()).bottomMargin + this.mLayoutManager.getDecoratedBottom(view);
    }
    
    @Override
    public int getDecoratedMeasurement(final View view) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        return recyclerView$LayoutParams.bottomMargin + (this.mLayoutManager.getDecoratedMeasuredHeight(view) + recyclerView$LayoutParams.topMargin);
    }
    
    @Override
    public int getDecoratedMeasurementInOther(final View view) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        return recyclerView$LayoutParams.rightMargin + (this.mLayoutManager.getDecoratedMeasuredWidth(view) + recyclerView$LayoutParams.leftMargin);
    }
    
    @Override
    public int getDecoratedStart(final View view) {
        return this.mLayoutManager.getDecoratedTop(view) - ((RecyclerView$LayoutParams)view.getLayoutParams()).topMargin;
    }
    
    @Override
    public int getEnd() {
        return this.mLayoutManager.getHeight();
    }
    
    @Override
    public int getEndAfterPadding() {
        return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public int getEndPadding() {
        return this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public int getStartAfterPadding() {
        return this.mLayoutManager.getPaddingTop();
    }
    
    @Override
    public int getTotalSpace() {
        return this.mLayoutManager.getHeight() - this.mLayoutManager.getPaddingTop() - this.mLayoutManager.getPaddingBottom();
    }
    
    @Override
    public void offsetChildren(final int n) {
        this.mLayoutManager.offsetChildrenVertical(n);
    }
}
