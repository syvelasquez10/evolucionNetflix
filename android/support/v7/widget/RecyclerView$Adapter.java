// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.ViewGroup;

public abstract class RecyclerView$Adapter<VH extends RecyclerView$ViewHolder>
{
    private boolean mHasStableIds;
    private final RecyclerView$AdapterDataObservable mObservable;
    
    public RecyclerView$Adapter() {
        this.mObservable = new RecyclerView$AdapterDataObservable();
        this.mHasStableIds = false;
    }
    
    public final void bindViewHolder(final VH vh, final int mPosition) {
        vh.mPosition = mPosition;
        if (this.hasStableIds()) {
            vh.mItemId = this.getItemId(mPosition);
        }
        this.onBindViewHolder(vh, mPosition);
        vh.setFlags(1, 7);
    }
    
    public final VH createViewHolder(final ViewGroup viewGroup, final int mItemViewType) {
        final RecyclerView$ViewHolder onCreateViewHolder = this.onCreateViewHolder(viewGroup, mItemViewType);
        onCreateViewHolder.mItemViewType = mItemViewType;
        return (VH)onCreateViewHolder;
    }
    
    public abstract int getItemCount();
    
    public long getItemId(final int n) {
        return -1L;
    }
    
    public int getItemViewType(final int n) {
        return 0;
    }
    
    public final boolean hasObservers() {
        return this.mObservable.hasObservers();
    }
    
    public final boolean hasStableIds() {
        return this.mHasStableIds;
    }
    
    public final void notifyDataSetChanged() {
        this.mObservable.notifyChanged();
    }
    
    public final void notifyItemChanged(final int n) {
        this.mObservable.notifyItemRangeChanged(n, 1);
    }
    
    public final void notifyItemInserted(final int n) {
        this.mObservable.notifyItemRangeInserted(n, 1);
    }
    
    public final void notifyItemMoved(final int n, final int n2) {
        this.mObservable.notifyItemMoved(n, n2);
    }
    
    public final void notifyItemRangeChanged(final int n, final int n2) {
        this.mObservable.notifyItemRangeChanged(n, n2);
    }
    
    public final void notifyItemRangeInserted(final int n, final int n2) {
        this.mObservable.notifyItemRangeInserted(n, n2);
    }
    
    public final void notifyItemRangeRemoved(final int n, final int n2) {
        this.mObservable.notifyItemRangeRemoved(n, n2);
    }
    
    public final void notifyItemRemoved(final int n) {
        this.mObservable.notifyItemRangeRemoved(n, 1);
    }
    
    public void onAttachedToRecyclerView(final RecyclerView recyclerView) {
    }
    
    public abstract void onBindViewHolder(final VH p0, final int p1);
    
    public abstract VH onCreateViewHolder(final ViewGroup p0, final int p1);
    
    public void onDetachedFromRecyclerView(final RecyclerView recyclerView) {
    }
    
    public void onViewAttachedToWindow(final VH vh) {
    }
    
    public void onViewDetachedFromWindow(final VH vh) {
    }
    
    public void onViewRecycled(final VH vh) {
    }
    
    public void registerAdapterDataObserver(final RecyclerView$AdapterDataObserver recyclerView$AdapterDataObserver) {
        this.mObservable.registerObserver((Object)recyclerView$AdapterDataObserver);
    }
    
    public void setHasStableIds(final boolean mHasStableIds) {
        if (this.hasObservers()) {
            throw new IllegalStateException("Cannot change whether this adapter has stable IDs while the adapter has registered observers.");
        }
        this.mHasStableIds = mHasStableIds;
    }
    
    public void unregisterAdapterDataObserver(final RecyclerView$AdapterDataObserver recyclerView$AdapterDataObserver) {
        this.mObservable.unregisterObserver((Object)recyclerView$AdapterDataObserver);
    }
}
