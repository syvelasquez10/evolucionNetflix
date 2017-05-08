// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;
import android.util.SparseArray;

public class RecyclerView$RecycledViewPool
{
    private int mAttachCount;
    SparseArray<RecyclerView$RecycledViewPool$ScrapData> mScrap;
    
    public RecyclerView$RecycledViewPool() {
        this.mScrap = (SparseArray<RecyclerView$RecycledViewPool$ScrapData>)new SparseArray();
        this.mAttachCount = 0;
    }
    
    private RecyclerView$RecycledViewPool$ScrapData getScrapDataForType(final int n) {
        RecyclerView$RecycledViewPool$ScrapData recyclerView$RecycledViewPool$ScrapData;
        if ((recyclerView$RecycledViewPool$ScrapData = (RecyclerView$RecycledViewPool$ScrapData)this.mScrap.get(n)) == null) {
            recyclerView$RecycledViewPool$ScrapData = new RecyclerView$RecycledViewPool$ScrapData();
            this.mScrap.put(n, (Object)recyclerView$RecycledViewPool$ScrapData);
        }
        return recyclerView$RecycledViewPool$ScrapData;
    }
    
    void attach(final RecyclerView$Adapter recyclerView$Adapter) {
        ++this.mAttachCount;
    }
    
    public void clear() {
        for (int i = 0; i < this.mScrap.size(); ++i) {
            ((RecyclerView$RecycledViewPool$ScrapData)this.mScrap.valueAt(i)).mScrapHeap.clear();
        }
    }
    
    void detach() {
        --this.mAttachCount;
    }
    
    void factorInBindTime(final int n, final long n2) {
        final RecyclerView$RecycledViewPool$ScrapData scrapDataForType = this.getScrapDataForType(n);
        scrapDataForType.mBindRunningAverageNs = this.runningAverage(scrapDataForType.mBindRunningAverageNs, n2);
    }
    
    void factorInCreateTime(final int n, final long n2) {
        final RecyclerView$RecycledViewPool$ScrapData scrapDataForType = this.getScrapDataForType(n);
        scrapDataForType.mCreateRunningAverageNs = this.runningAverage(scrapDataForType.mCreateRunningAverageNs, n2);
    }
    
    public RecyclerView$ViewHolder getRecycledView(final int n) {
        final RecyclerView$RecycledViewPool$ScrapData recyclerView$RecycledViewPool$ScrapData = (RecyclerView$RecycledViewPool$ScrapData)this.mScrap.get(n);
        if (recyclerView$RecycledViewPool$ScrapData != null && !recyclerView$RecycledViewPool$ScrapData.mScrapHeap.isEmpty()) {
            final ArrayList<RecyclerView$ViewHolder> mScrapHeap = recyclerView$RecycledViewPool$ScrapData.mScrapHeap;
            return mScrapHeap.remove(mScrapHeap.size() - 1);
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
        final ArrayList<RecyclerView$ViewHolder> mScrapHeap = this.getScrapDataForType(itemViewType).mScrapHeap;
        if (((RecyclerView$RecycledViewPool$ScrapData)this.mScrap.get(itemViewType)).mMaxScrap <= mScrapHeap.size()) {
            return;
        }
        recyclerView$ViewHolder.resetInternal();
        mScrapHeap.add(recyclerView$ViewHolder);
    }
    
    long runningAverage(final long n, final long n2) {
        if (n == 0L) {
            return n2;
        }
        return n / 4L * 3L + n2 / 4L;
    }
    
    boolean willBindInTime(final int n, final long n2, final long n3) {
        final long mBindRunningAverageNs = this.getScrapDataForType(n).mBindRunningAverageNs;
        return mBindRunningAverageNs == 0L || mBindRunningAverageNs + n2 < n3;
    }
    
    boolean willCreateInTime(final int n, final long n2, final long n3) {
        final long mCreateRunningAverageNs = this.getScrapDataForType(n).mCreateRunningAverageNs;
        return mCreateRunningAverageNs == 0L || mCreateRunningAverageNs + n2 < n3;
    }
}
