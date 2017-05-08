// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.util.SparseArray;

public class RecyclerView$State
{
    private SparseArray<Object> mData;
    int mDeletedInvisibleItemCountSincePreviousLayout;
    long mFocusedItemId;
    int mFocusedItemPosition;
    int mFocusedSubChildId;
    boolean mInPreLayout;
    boolean mIsMeasuring;
    int mItemCount;
    int mLayoutStep;
    int mPreviousLayoutItemCount;
    boolean mRunPredictiveAnimations;
    boolean mRunSimpleAnimations;
    boolean mStructureChanged;
    private int mTargetPosition;
    boolean mTrackOldChangeHolders;
    
    public RecyclerView$State() {
        this.mTargetPosition = -1;
        this.mPreviousLayoutItemCount = 0;
        this.mDeletedInvisibleItemCountSincePreviousLayout = 0;
        this.mLayoutStep = 1;
        this.mItemCount = 0;
        this.mStructureChanged = false;
        this.mInPreLayout = false;
        this.mTrackOldChangeHolders = false;
        this.mIsMeasuring = false;
        this.mRunSimpleAnimations = false;
        this.mRunPredictiveAnimations = false;
    }
    
    void assertLayoutStep(final int n) {
        if ((this.mLayoutStep & n) == 0x0) {
            throw new IllegalStateException("Layout state should be one of " + Integer.toBinaryString(n) + " but it is " + Integer.toBinaryString(this.mLayoutStep));
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
    
    void prepareForNestedPrefetch(final RecyclerView$Adapter recyclerView$Adapter) {
        this.mLayoutStep = 1;
        this.mItemCount = recyclerView$Adapter.getItemCount();
        this.mStructureChanged = false;
        this.mInPreLayout = false;
        this.mTrackOldChangeHolders = false;
        this.mIsMeasuring = false;
    }
    
    @Override
    public String toString() {
        return "State{mTargetPosition=" + this.mTargetPosition + ", mData=" + this.mData + ", mItemCount=" + this.mItemCount + ", mPreviousLayoutItemCount=" + this.mPreviousLayoutItemCount + ", mDeletedInvisibleItemCountSincePreviousLayout=" + this.mDeletedInvisibleItemCountSincePreviousLayout + ", mStructureChanged=" + this.mStructureChanged + ", mInPreLayout=" + this.mInPreLayout + ", mRunSimpleAnimations=" + this.mRunSimpleAnimations + ", mRunPredictiveAnimations=" + this.mRunPredictiveAnimations + '}';
    }
    
    public boolean willRunPredictiveAnimations() {
        return this.mRunPredictiveAnimations;
    }
}
