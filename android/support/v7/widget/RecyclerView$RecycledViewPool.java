// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;
import android.util.SparseArray;
import android.util.SparseIntArray;

public class RecyclerView$RecycledViewPool
{
    private int mAttachCount;
    private SparseIntArray mMaxScrap;
    private SparseArray<ArrayList<RecyclerView$ViewHolder>> mScrap;
    
    public RecyclerView$RecycledViewPool() {
        this.mScrap = (SparseArray<ArrayList<RecyclerView$ViewHolder>>)new SparseArray();
        this.mMaxScrap = new SparseIntArray();
        this.mAttachCount = 0;
    }
    
    private ArrayList<RecyclerView$ViewHolder> getScrapHeapForType(final int n) {
        ArrayList<RecyclerView$ViewHolder> list;
        if ((list = (ArrayList<RecyclerView$ViewHolder>)this.mScrap.get(n)) == null) {
            final ArrayList<RecyclerView$ViewHolder> list2 = new ArrayList<RecyclerView$ViewHolder>();
            this.mScrap.put(n, (Object)list2);
            list = list2;
            if (this.mMaxScrap.indexOfKey(n) < 0) {
                this.mMaxScrap.put(n, 5);
                list = list2;
            }
        }
        return list;
    }
    
    void attach(final RecyclerView$Adapter recyclerView$Adapter) {
        ++this.mAttachCount;
    }
    
    public void clear() {
        this.mScrap.clear();
    }
    
    void detach() {
        --this.mAttachCount;
    }
    
    public RecyclerView$ViewHolder getRecycledView(int n) {
        final ArrayList list = (ArrayList)this.mScrap.get(n);
        if (list != null && !list.isEmpty()) {
            n = list.size() - 1;
            final RecyclerView$ViewHolder recyclerView$ViewHolder = list.get(n);
            list.remove(n);
            return recyclerView$ViewHolder;
        }
        return null;
    }
    
    void onAdapterChanged(final RecyclerView$Adapter recyclerView$Adapter, final RecyclerView$Adapter recyclerView$Adapter2, final boolean b) {
        if (recyclerView$Adapter != null) {
            this.detach();
        }
        if (!b && this.mAttachCount == 0) {
            this.clear();
        }
        if (recyclerView$Adapter2 != null) {
            this.attach(recyclerView$Adapter2);
        }
    }
    
    public void putRecycledView(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        final int itemViewType = recyclerView$ViewHolder.getItemViewType();
        final ArrayList<RecyclerView$ViewHolder> scrapHeapForType = this.getScrapHeapForType(itemViewType);
        if (this.mMaxScrap.get(itemViewType) <= scrapHeapForType.size()) {
            return;
        }
        recyclerView$ViewHolder.resetInternal();
        scrapHeapForType.add(recyclerView$ViewHolder);
    }
}
