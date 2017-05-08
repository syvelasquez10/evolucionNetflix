// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

public abstract class SimpleItemAnimator extends RecyclerView$ItemAnimator
{
    boolean mSupportsChangeAnimations;
    
    public SimpleItemAnimator() {
        this.mSupportsChangeAnimations = true;
    }
    
    public abstract boolean animateAdd(final RecyclerView$ViewHolder p0);
    
    @Override
    public boolean animateAppearance(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        if (recyclerView$ItemAnimator$ItemHolderInfo != null && (recyclerView$ItemAnimator$ItemHolderInfo.left != recyclerView$ItemAnimator$ItemHolderInfo2.left || recyclerView$ItemAnimator$ItemHolderInfo.top != recyclerView$ItemAnimator$ItemHolderInfo2.top)) {
            return this.animateMove(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo.left, recyclerView$ItemAnimator$ItemHolderInfo.top, recyclerView$ItemAnimator$ItemHolderInfo2.left, recyclerView$ItemAnimator$ItemHolderInfo2.top);
        }
        return this.animateAdd(recyclerView$ViewHolder);
    }
    
    public abstract boolean animateChange(final RecyclerView$ViewHolder p0, final RecyclerView$ViewHolder p1, final int p2, final int p3, final int p4, final int p5);
    
    @Override
    public boolean animateChange(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ViewHolder recyclerView$ViewHolder2, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        final int left = recyclerView$ItemAnimator$ItemHolderInfo.left;
        final int top = recyclerView$ItemAnimator$ItemHolderInfo.top;
        int n;
        int n2;
        if (recyclerView$ViewHolder2.shouldIgnore()) {
            n = recyclerView$ItemAnimator$ItemHolderInfo.left;
            n2 = recyclerView$ItemAnimator$ItemHolderInfo.top;
        }
        else {
            n = recyclerView$ItemAnimator$ItemHolderInfo2.left;
            n2 = recyclerView$ItemAnimator$ItemHolderInfo2.top;
        }
        return this.animateChange(recyclerView$ViewHolder, recyclerView$ViewHolder2, left, top, n, n2);
    }
    
    @Override
    public boolean animateDisappearance(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        final int left = recyclerView$ItemAnimator$ItemHolderInfo.left;
        final int top = recyclerView$ItemAnimator$ItemHolderInfo.top;
        final View itemView = recyclerView$ViewHolder.itemView;
        int n;
        if (recyclerView$ItemAnimator$ItemHolderInfo2 == null) {
            n = itemView.getLeft();
        }
        else {
            n = recyclerView$ItemAnimator$ItemHolderInfo2.left;
        }
        int n2;
        if (recyclerView$ItemAnimator$ItemHolderInfo2 == null) {
            n2 = itemView.getTop();
        }
        else {
            n2 = recyclerView$ItemAnimator$ItemHolderInfo2.top;
        }
        if (!recyclerView$ViewHolder.isRemoved() && (left != n || top != n2)) {
            itemView.layout(n, n2, itemView.getWidth() + n, itemView.getHeight() + n2);
            return this.animateMove(recyclerView$ViewHolder, left, top, n, n2);
        }
        return this.animateRemove(recyclerView$ViewHolder);
    }
    
    public abstract boolean animateMove(final RecyclerView$ViewHolder p0, final int p1, final int p2, final int p3, final int p4);
    
    @Override
    public boolean animatePersistence(final RecyclerView$ViewHolder recyclerView$ViewHolder, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo, final RecyclerView$ItemAnimator$ItemHolderInfo recyclerView$ItemAnimator$ItemHolderInfo2) {
        if (recyclerView$ItemAnimator$ItemHolderInfo.left != recyclerView$ItemAnimator$ItemHolderInfo2.left || recyclerView$ItemAnimator$ItemHolderInfo.top != recyclerView$ItemAnimator$ItemHolderInfo2.top) {
            return this.animateMove(recyclerView$ViewHolder, recyclerView$ItemAnimator$ItemHolderInfo.left, recyclerView$ItemAnimator$ItemHolderInfo.top, recyclerView$ItemAnimator$ItemHolderInfo2.left, recyclerView$ItemAnimator$ItemHolderInfo2.top);
        }
        this.dispatchMoveFinished(recyclerView$ViewHolder);
        return false;
    }
    
    public abstract boolean animateRemove(final RecyclerView$ViewHolder p0);
    
    @Override
    public boolean canReuseUpdatedViewHolder(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        return !this.mSupportsChangeAnimations || recyclerView$ViewHolder.isInvalid();
    }
    
    public final void dispatchAddFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onAddFinished(recyclerView$ViewHolder);
        this.dispatchAnimationFinished(recyclerView$ViewHolder);
    }
    
    public final void dispatchAddStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onAddStarting(recyclerView$ViewHolder);
    }
    
    public final void dispatchChangeFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
        this.onChangeFinished(recyclerView$ViewHolder, b);
        this.dispatchAnimationFinished(recyclerView$ViewHolder);
    }
    
    public final void dispatchChangeStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
        this.onChangeStarting(recyclerView$ViewHolder, b);
    }
    
    public final void dispatchMoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onMoveFinished(recyclerView$ViewHolder);
        this.dispatchAnimationFinished(recyclerView$ViewHolder);
    }
    
    public final void dispatchMoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onMoveStarting(recyclerView$ViewHolder);
    }
    
    public final void dispatchRemoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onRemoveFinished(recyclerView$ViewHolder);
        this.dispatchAnimationFinished(recyclerView$ViewHolder);
    }
    
    public final void dispatchRemoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onRemoveStarting(recyclerView$ViewHolder);
    }
    
    public void onAddFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onAddStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onChangeFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
    }
    
    public void onChangeStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder, final boolean b) {
    }
    
    public void onMoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onMoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onRemoveFinished(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
    
    public void onRemoveStarting(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
    }
}
