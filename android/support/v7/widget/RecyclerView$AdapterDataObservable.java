// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.database.Observable;

class RecyclerView$AdapterDataObservable extends Observable<RecyclerView$AdapterDataObserver>
{
    public boolean hasObservers() {
        return !this.mObservers.isEmpty();
    }
    
    public void notifyChanged() {
        for (int i = this.mObservers.size() - 1; i >= 0; --i) {
            ((RecyclerView$AdapterDataObserver)this.mObservers.get(i)).onChanged();
        }
    }
    
    public void notifyItemMoved(final int n, final int n2) {
        for (int i = this.mObservers.size() - 1; i >= 0; --i) {
            ((RecyclerView$AdapterDataObserver)this.mObservers.get(i)).onItemRangeMoved(n, n2, 1);
        }
    }
    
    public void notifyItemRangeChanged(final int n, final int n2) {
        for (int i = this.mObservers.size() - 1; i >= 0; --i) {
            ((RecyclerView$AdapterDataObserver)this.mObservers.get(i)).onItemRangeChanged(n, n2);
        }
    }
    
    public void notifyItemRangeInserted(final int n, final int n2) {
        for (int i = this.mObservers.size() - 1; i >= 0; --i) {
            ((RecyclerView$AdapterDataObserver)this.mObservers.get(i)).onItemRangeInserted(n, n2);
        }
    }
    
    public void notifyItemRangeRemoved(final int n, final int n2) {
        for (int i = this.mObservers.size() - 1; i >= 0; --i) {
            ((RecyclerView$AdapterDataObserver)this.mObservers.get(i)).onItemRangeRemoved(n, n2);
        }
    }
}
