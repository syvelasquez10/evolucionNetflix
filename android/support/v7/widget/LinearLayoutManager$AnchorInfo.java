// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

class LinearLayoutManager$AnchorInfo
{
    int mCoordinate;
    boolean mLayoutFromEnd;
    int mPosition;
    final /* synthetic */ LinearLayoutManager this$0;
    
    LinearLayoutManager$AnchorInfo(final LinearLayoutManager this$0) {
        this.this$0 = this$0;
    }
    
    private boolean isViewValidAsAnchor(final View view, final RecyclerView$State recyclerView$State) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        return !recyclerView$LayoutParams.isItemRemoved() && recyclerView$LayoutParams.getViewLayoutPosition() >= 0 && recyclerView$LayoutParams.getViewLayoutPosition() < recyclerView$State.getItemCount();
    }
    
    void assignCoordinateFromPadding() {
        int mCoordinate;
        if (this.mLayoutFromEnd) {
            mCoordinate = this.this$0.mOrientationHelper.getEndAfterPadding();
        }
        else {
            mCoordinate = this.this$0.mOrientationHelper.getStartAfterPadding();
        }
        this.mCoordinate = mCoordinate;
    }
    
    public void assignFromView(final View view) {
        if (this.mLayoutFromEnd) {
            this.mCoordinate = this.this$0.mOrientationHelper.getDecoratedEnd(view) + this.this$0.mOrientationHelper.getTotalSpaceChange();
        }
        else {
            this.mCoordinate = this.this$0.mOrientationHelper.getDecoratedStart(view);
        }
        this.mPosition = this.this$0.getPosition(view);
    }
    
    public void assignFromViewAndKeepVisibleRect(final View view) {
        final int totalSpaceChange = this.this$0.mOrientationHelper.getTotalSpaceChange();
        if (totalSpaceChange >= 0) {
            this.assignFromView(view);
        }
        else {
            this.mPosition = this.this$0.getPosition(view);
            if (this.mLayoutFromEnd) {
                final int n = this.this$0.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - this.this$0.mOrientationHelper.getDecoratedEnd(view);
                this.mCoordinate = this.this$0.mOrientationHelper.getEndAfterPadding() - n;
                if (n > 0) {
                    final int decoratedMeasurement = this.this$0.mOrientationHelper.getDecoratedMeasurement(view);
                    final int mCoordinate = this.mCoordinate;
                    final int startAfterPadding = this.this$0.mOrientationHelper.getStartAfterPadding();
                    final int n2 = mCoordinate - decoratedMeasurement - (startAfterPadding + Math.min(this.this$0.mOrientationHelper.getDecoratedStart(view) - startAfterPadding, 0));
                    if (n2 < 0) {
                        this.mCoordinate += Math.min(n, -n2);
                    }
                }
            }
            else {
                final int decoratedStart = this.this$0.mOrientationHelper.getDecoratedStart(view);
                final int n3 = decoratedStart - this.this$0.mOrientationHelper.getStartAfterPadding();
                this.mCoordinate = decoratedStart;
                if (n3 > 0) {
                    final int n4 = this.this$0.mOrientationHelper.getEndAfterPadding() - Math.min(0, this.this$0.mOrientationHelper.getEndAfterPadding() - totalSpaceChange - this.this$0.mOrientationHelper.getDecoratedEnd(view)) - (decoratedStart + this.this$0.mOrientationHelper.getDecoratedMeasurement(view));
                    if (n4 < 0) {
                        this.mCoordinate -= Math.min(n3, -n4);
                    }
                }
            }
        }
    }
    
    void reset() {
        this.mPosition = -1;
        this.mCoordinate = Integer.MIN_VALUE;
        this.mLayoutFromEnd = false;
    }
    
    @Override
    public String toString() {
        return "AnchorInfo{mPosition=" + this.mPosition + ", mCoordinate=" + this.mCoordinate + ", mLayoutFromEnd=" + this.mLayoutFromEnd + '}';
    }
}
