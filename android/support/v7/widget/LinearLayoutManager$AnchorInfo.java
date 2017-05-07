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
    
    public boolean assignFromViewIfValid(final View view, final RecyclerView$State recyclerView$State) {
        final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)view.getLayoutParams();
        if (!recyclerView$LayoutParams.isItemRemoved() && recyclerView$LayoutParams.getViewPosition() >= 0 && recyclerView$LayoutParams.getViewPosition() < recyclerView$State.getItemCount()) {
            this.assignFromView(view);
            return true;
        }
        return false;
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
