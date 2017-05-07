// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.support.v4.util.ArrayMap;
import android.util.SparseArray;

public class RecyclerView$State
{
    private SparseArray<Object> mData;
    private int mDeletedInvisibleItemCountSincePreviousLayout;
    private boolean mInPreLayout;
    int mItemCount;
    ArrayMap<Long, RecyclerView$ViewHolder> mOldChangedHolders;
    ArrayMap<RecyclerView$ViewHolder, RecyclerView$ItemHolderInfo> mPostLayoutHolderMap;
    ArrayMap<RecyclerView$ViewHolder, RecyclerView$ItemHolderInfo> mPreLayoutHolderMap;
    private int mPreviousLayoutItemCount;
    private boolean mRunPredictiveAnimations;
    private boolean mRunSimpleAnimations;
    private boolean mStructureChanged;
    private int mTargetPosition;
    
    public RecyclerView$State() {
        this.mTargetPosition = -1;
        this.mPreLayoutHolderMap = new ArrayMap<RecyclerView$ViewHolder, RecyclerView$ItemHolderInfo>();
        this.mPostLayoutHolderMap = new ArrayMap<RecyclerView$ViewHolder, RecyclerView$ItemHolderInfo>();
        this.mOldChangedHolders = new ArrayMap<Long, RecyclerView$ViewHolder>();
        this.mItemCount = 0;
        this.mPreviousLayoutItemCount = 0;
        this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mStructureChanged = false;
        this.mInPreLayout = false;
        this.mRunSimpleAnimations = false;
        this.mRunPredictiveAnimations = false;
    }
    
    private void removeFrom(final ArrayMap<Long, RecyclerView$ViewHolder> arrayMap, final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        for (int i = arrayMap.size() - 1; i >= 0; --i) {
            if (recyclerView$ViewHolder == arrayMap.valueAt(i)) {
                arrayMap.removeAt(i);
                break;
            }
        }
    }
    
    public int getItemCount() {
        if (this.mInPreLayout) {
            return this.mPreviousLayoutItemCount - this.mDeletedInvisibleItemCountSincePreviousLayout;
        }
        return this.mItemCount;
    }
    
    public int getTargetScrollPosition() {
        return this.mTargetPosition;
    }
    
    public boolean hasTargetScrollPosition() {
        return this.mTargetPosition != -1;
    }
    
    public boolean isPreLayout() {
        return this.mInPreLayout;
    }
    
    public void onViewIgnored(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.onViewRecycled(recyclerView$ViewHolder);
    }
    
    public void onViewRecycled(final RecyclerView$ViewHolder recyclerView$ViewHolder) {
        this.mPreLayoutHolderMap.remove(recyclerView$ViewHolder);
        this.mPostLayoutHolderMap.remove(recyclerView$ViewHolder);
        if (this.mOldChangedHolders != null) {
            this.removeFrom(this.mOldChangedHolders, recyclerView$ViewHolder);
        }
    }
    
    @Override
    public String toString() {
        return "State{mTargetPosition=" + this.mTargetPosition + ", mPreLayoutHolderMap=" + this.mPreLayoutHolderMap + ", mPostLayoutHolderMap=" + this.mPostLayoutHolderMap + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
    }
    
    public boolean willRunPredictiveAnimations() {
        return this.mRunPredictiveAnimations;
    }
}
