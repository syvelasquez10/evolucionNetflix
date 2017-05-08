// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

class RecyclerView$4 implements ViewInfoStore$ProcessCallback
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$4(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void processAppeared(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        this.this$0.animateAppearance(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2);
    }
    
    @Override
    public void processDisappeared(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        this.this$0.mRecycler.unscrapView(recyclerView$ViewHolder);
        this.this$0.animateDisappearance(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2);
    }
    
    @Override
    public void processPersistent(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        recyclerView$ViewHolder.setIsRecyclable(false);
        if (this.this$0.mDataSetHasChangedAfterLayout) {
            if (this.this$0.mItemAnimator.animateChange(recyclerView$ViewHolder, recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2)) {
                this.this$0.postAnimationRunner();
            }
        }
        else if (this.this$0.mItemAnimator.animatePersistence(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo, recyclerView$ItemAnimator$ItemHolderInfo2)) {
            this.this$0.postAnimationRunner();
        }
    }
    
    @Override
    public void unused(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.this$0.mLayout.removeAndRecycleView(recyclerView$ViewHolder.itemView, this.this$0.mRecycler);
    }
}
