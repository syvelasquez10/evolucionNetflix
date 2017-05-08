// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import android.support.v4.view.ViewCompat;

class RecyclerView$RecyclerViewDataObserver extends RecyclerView$AdapterDataObserver
{
    final /* synthetic */ RecyclerView this$0;
    
    RecyclerView$RecyclerViewDataObserver(final RecyclerView this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void onChanged() {
        this.this$0.assertNotInLayoutOrScroll(null);
        this.this$0.mState.mStructureChanged = true;
        this.this$0.setDataSetChangedAfterLayout();
        if (!this.this$0.mAdapterHelper.hasPendingUpdates()) {
            this.this$0.requestLayout();
        }
    }
    
    @Override
    public void onItemRangeChanged(final int n, final int n2, final Object o) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeChanged(n, n2, o)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeInserted(final int n, final int n2) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeInserted(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeMoved(final int n, final int n2, final int n3) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeMoved(n, n2, n3)) {
            this.triggerUpdateProcessor();
        }
    }
    
    @Override
    public void onItemRangeRemoved(final int n, final int n2) {
        this.this$0.assertNotInLayoutOrScroll(null);
        if (this.this$0.mAdapterHelper.onItemRangeRemoved(n, n2)) {
            this.triggerUpdateProcessor();
        }
    }
    
    void triggerUpdateProcessor() {
        if (RecyclerView.POST_UPDATES_ON_ANIMATION && this.this$0.mHasFixedSize && this.this$0.mIsAttached) {
            ViewCompat.postOnAnimation((View)this.this$0, this.this$0.mUpdateChildViewsRunnable);
            return;
        }
        this.this$0.mAdapterUpdateDuringMeasure = true;
        this.this$0.requestLayout();
    }
}
