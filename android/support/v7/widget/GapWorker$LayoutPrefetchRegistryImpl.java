// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import java.util.Arrays;

class GapWorker$LayoutPrefetchRegistryImpl implements RecyclerView$LayoutManager$LayoutPrefetchRegistry
{
    int mCount;
    int[] mPrefetchArray;
    int mPrefetchDx;
    int mPrefetchDy;
    
    @Override
    public void addPosition(final int n, final int n2) {
        if (n2 < 0) {
            throw new IllegalArgumentException("Pixel distance must be non-negative");
        }
        final int n3 = this.mCount * 2;
        if (this.mPrefetchArray == null) {
            Arrays.fill(this.mPrefetchArray = new int[4], -1);
        }
        else if (n3 >= this.mPrefetchArray.length) {
            final int[] mPrefetchArray = this.mPrefetchArray;
            System.arraycopy(mPrefetchArray, 0, this.mPrefetchArray = new int[n3 * 2], 0, mPrefetchArray.length);
        }
        this.mPrefetchArray[n3] = n;
        this.mPrefetchArray[n3 + 1] = n2;
        ++this.mCount;
    }
    
    void clearPrefetchPositions() {
        if (this.mPrefetchArray != null) {
            Arrays.fill(this.mPrefetchArray, -1);
        }
    }
    
    void collectPrefetchPositionsFromView(final RecyclerView recyclerView, final boolean mPrefetchMaxObservedInInitialPrefetch) {
        this.mCount = 0;
        if (this.mPrefetchArray != null) {
            Arrays.fill(this.mPrefetchArray, -1);
        }
        final RecyclerView$LayoutManager mLayout = recyclerView.mLayout;
        if (recyclerView.mAdapter != null && mLayout != null && mLayout.isItemPrefetchEnabled()) {
            if (mPrefetchMaxObservedInInitialPrefetch) {
                if (!recyclerView.mAdapterHelper.hasPendingUpdates()) {
                    mLayout.collectInitialPrefetchPositions(recyclerView.mAdapter.getItemCount(), this);
                }
            }
            else if (!recyclerView.hasPendingAdapterUpdates()) {
                mLayout.collectAdjacentPrefetchPositions(this.mPrefetchDx, this.mPrefetchDy, recyclerView.mState, this);
            }
            if (this.mCount > mLayout.mPrefetchMaxCountObserved) {
                mLayout.mPrefetchMaxCountObserved = this.mCount;
                mLayout.mPrefetchMaxObservedInInitialPrefetch = mPrefetchMaxObservedInInitialPrefetch;
                recyclerView.mRecycler.updateViewCacheSize();
            }
        }
    }
    
    boolean lastPrefetchIncludedPosition(final int n) {
        boolean b = false;
        if (this.mPrefetchArray != null) {
            final int mCount = this.mCount;
            int n2 = 0;
            while (true) {
                b = b;
                if (n2 >= mCount * 2) {
                    break;
                }
                if (this.mPrefetchArray[n2] == n) {
                    b = true;
                    break;
                }
                n2 += 2;
            }
        }
        return b;
    }
    
    void setPrefetchVector(final int mPrefetchDx, final int mPrefetchDy) {
        this.mPrefetchDx = mPrefetchDx;
        this.mPrefetchDy = mPrefetchDy;
    }
}
