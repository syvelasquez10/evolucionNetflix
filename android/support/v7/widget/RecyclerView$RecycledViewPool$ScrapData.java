// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.ArrayList;

class RecyclerView$RecycledViewPool$ScrapData
{
    long mBindRunningAverageNs;
    long mCreateRunningAverageNs;
    int mMaxScrap;
    ArrayList<RecyclerView$ViewHolder> mScrapHeap;
    
    RecyclerView$RecycledViewPool$ScrapData() {
        this.mScrapHeap = new ArrayList<RecyclerView$ViewHolder>();
        this.mMaxScrap = 5;
        this.mCreateRunningAverageNs = 0L;
        this.mBindRunningAverageNs = 0L;
    }
}
