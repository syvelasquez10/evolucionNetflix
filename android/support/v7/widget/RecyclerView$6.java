// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RecyclerView$6 implements AdapterHelper$Callback
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$6(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    void dispatchUpdate(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        switch (adapterHelper$UpdateOp.cmd) {
            default: {}
            case 1: {
                this.this$0.mLayout.onItemsAdded(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 2: {
                this.this$0.mLayout.onItemsRemoved(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount);
            }
            case 4: {
                this.this$0.mLayout.onItemsUpdated(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, adapterHelper$UpdateOp.payload);
            }
            case 8: {
                this.this$0.mLayout.onItemsMoved(this.this$0, adapterHelper$UpdateOp.positionStart, adapterHelper$UpdateOp.itemCount, 1);
            }
        }
    }
    
    @Override
    public RecyclerView$ViewHolder findViewHolder(final int n) {
        final RecyclerView$ViewHolder viewHolderForPosition = this.this$0.findViewHolderForPosition(n, true);
        if (viewHolderForPosition != null && !this.this$0.mChildHelper.isHidden(viewHolderForPosition.itemView)) {
            return viewHolderForPosition;
        }
        return null;
    }
    
    @Override
    public void markViewHoldersUpdated(final int n, final int n2, final Object o) {
        this.this$0.viewRangeUpdate(n, n2, o);
        this.this$0.mItemsChanged = true;
    }
    
    @Override
    public void offsetPositionsForAdd(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForInsert(n, n2);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForMove(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForMove(n, n2);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void offsetPositionsForRemovingInvisible(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForRemove(n, n2, true);
        this.this$0.mItemsAddedOrRemoved = true;
        final RecyclerView$State mState = this.this$0.mState;
        mState.mDeletedInvisibleItemCountSincePreviousLayout += n2;
    }
    
    @Override
    public void offsetPositionsForRemovingLaidOutOrNewView(final int n, final int n2) {
        this.this$0.offsetPositionRecordsForRemove(n, n2, false);
        this.this$0.mItemsAddedOrRemoved = true;
    }
    
    @Override
    public void onDispatchFirstPass(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.dispatchUpdate(adapterHelper$UpdateOp);
    }
    
    @Override
    public void onDispatchSecondPass(final AdapterHelper$UpdateOp adapterHelper$UpdateOp) {
        this.dispatchUpdate(adapterHelper$UpdateOp);
    }
}
