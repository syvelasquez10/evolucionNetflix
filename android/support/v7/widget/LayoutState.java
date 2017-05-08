// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v7.widget;

import android.view.View;

class LayoutState
{
    int mAvailable;
    int mCurrentPosition;
    int mEndLine;
    boolean mInfinite;
    int mItemDirection;
    int mLayoutDirection;
    boolean mRecycle;
    int mStartLine;
    boolean mStopInFocusable;
    
    LayoutState() {
        this.mRecycle = true;
        this.mStartLine = 0;
        this.mEndLine = 0;
    }
    
    boolean hasMore(final RecyclerView$State recyclerView$State) {
        return this.mCurrentPosition >= 0 && this.mCurrentPosition < recyclerView$State.getItemCount();
    }
    
    View next(final RecyclerView$Recycler recyclerView$Recycler) {
        final View viewForPosition = recyclerView$Recycler.getViewForPosition(this.mCurrentPosition);
        this.mCurrentPosition += this.mItemDirection;
        return viewForPosition;
    }
    
    @Override
    public String toString() {
        return "LayoutState{mAvailable=" + this.mAvailable + ", mCurrentPosition=" + this.mCurrentPosition + ", mItemDirection=" + this.mItemDirection + ", mLayoutDirection=" + this.mLayoutDirection + ", mStartLine=" + this.mStartLine + ", mEndLine=" + this.mEndLine + '}';
    }
}
