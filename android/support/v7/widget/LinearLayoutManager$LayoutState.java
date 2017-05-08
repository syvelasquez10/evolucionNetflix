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
    int mLastScrollDelta;
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
    
    private View nextViewFromScrapList() {
        for (int size = this.mScrapList.size(), i = 0; i < size; ++i) {
            final View itemView = this.mScrapList.get(i).itemView;
            final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)itemView.getLayoutParams();
            if (!recyclerView$LayoutParams.isItemRemoved() && this.mCurrentPosition == recyclerView$LayoutParams.getViewLayoutPosition()) {
                this.assignPositionFromScrapList(itemView);
                return itemView;
            }
        }
        return null;
    }
    
    public void assignPositionFromScrapList() {
        this.assignPositionFromScrapList(null);
    }
    
    public void assignPositionFromScrapList(View nextViewInLimitedList) {
        nextViewInLimitedList = this.nextViewInLimitedList(nextViewInLimitedList);
        if (nextViewInLimitedList == null) {
            this.mCurrentPosition = -1;
            return;
        }
        this.mCurrentPosition = ((RecyclerView$LayoutParams)nextViewInLimitedList.getLayoutParams()).getViewLayoutPosition();
    }
    
    boolean hasMore(final RecyclerView$State recyclerView$State) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < recyclerView$State.getItemCount();
    }
    
    View next(final RecyclerView$Recycler recyclerView$Recycler) {
        if (this.mScrapList != null) {
            return this.nextViewFromScrapList();
        }
        final View viewForPosition = recyclerView$Recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }
    
    public View nextViewInLimitedList(final View view) {
        final int size = this.mScrapList.size();
        View view2 = null;
        int n = Integer.MAX_VALUE;
        int i = 0;
    Label_0067_Outer:
        while (i < size) {
            final View itemView = this.mScrapList.get(i).itemView;
            final RecyclerView$LayoutParams recyclerView$LayoutParams = (RecyclerView$LayoutParams)itemView.getLayoutParams();
            if (itemView != view) {
                if (!recyclerView$LayoutParams.isItemRemoved()) {
                    final int n2 = (recyclerView$LayoutParams.getViewLayoutPosition() - this.mCurrentPosition) * this.mItemDirection;
                    if (n2 >= 0) {
                        if (n2 < n) {
                            if (n2 == 0) {
                                return itemView;
                            }
                            view2 = itemView;
                            n = n2;
                        }
                    }
                }
            }
            while (true) {
                ++i;
                continue Label_0067_Outer;
                continue;
            }
        }
        return view2;
    }
}
