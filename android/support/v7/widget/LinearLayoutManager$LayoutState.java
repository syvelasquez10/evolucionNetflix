// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;
import java.util.List;

class LinearLayoutManager$LayoutState
{
    int mAvailable;
    int mCurrentPosition;
    int mExtra;
    boolean mIsPreLayout;
    int mItemDirection;
    int mLayoutDirection;
    int mOffset;
    boolean mRecycle;
    List<RecyclerView$ViewHolder> mScrapList;
    int mScrollingOffset;
    
    LinearLayoutManager$LayoutState() {
        this.mRecycle = true;
        this.mExtra = 0;
        this.mIsPreLayout = false;
        this.mScrapList = null;
    }
    
    private View nextFromLimitedList() {
        final int size = this.mScrapList.size();
        int n = Integer.MAX_VALUE;
        int i = 0;
        RecyclerView$ViewHolder recyclerView$ViewHolder = null;
        while (true) {
            while (i < size) {
                final RecyclerView$ViewHolder recyclerView$ViewHolder2 = this.mScrapList.get(i);
                if (this.mIsPreLayout || !recyclerView$ViewHolder2.isRemoved()) {
                    final int n2 = (recyclerView$ViewHolder2.getPosition() - this.mCurrentPosition) * this.mItemDirection;
                    if (n2 >= 0) {
                        if (n2 < n) {
                            if (n2 == 0) {
                                if (recyclerView$ViewHolder2 != null) {
                                    this.mCurrentPosition = recyclerView$ViewHolder2.getPosition() + this.mItemDirection;
                                    return recyclerView$ViewHolder2.itemView;
                                }
                                return null;
                            }
                            else {
                                recyclerView$ViewHolder = recyclerView$ViewHolder2;
                                n = n2;
                            }
                        }
                    }
                }
                ++i;
            }
            final RecyclerView$ViewHolder recyclerView$ViewHolder2 = recyclerView$ViewHolder;
            continue;
        }
    }
    
    boolean hasMore(final RecyclerView$State recyclerView$State) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < recyclerView$State.getItemCount();
    }
    
    View next(final RecyclerView$Recycler recyclerView$Recycler) {
        if (this.mScrapList != null) {
            return this.nextFromLimitedList();
        }
        final View viewForPosition = recyclerView$Recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }
}
