// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.recyclerview;

import android.view.ViewGroup;
import android.support.v7.widget.RecyclerView$ViewHolder;
import java.util.ArrayList;
import java.util.List;
import android.view.View$OnLayoutChangeListener;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.View;

class RecyclerViewBackedScrollView$ScrollOffsetTracker
{
    private int mLastRequestedPosition;
    private int mOffsetForLastPosition;
    private final RecyclerViewBackedScrollView$ReactListAdapter mReactListAdapter;
    
    private RecyclerViewBackedScrollView$ScrollOffsetTracker(final RecyclerViewBackedScrollView$ReactListAdapter mReactListAdapter) {
        this.mReactListAdapter = mReactListAdapter;
    }
    
    public int getTopOffsetForItem(final int mLastRequestedPosition) {
        int i = 0;
        if (this.mLastRequestedPosition != mLastRequestedPosition) {
            int mOffsetForLastPosition;
            if (this.mLastRequestedPosition < mLastRequestedPosition) {
                if (this.mLastRequestedPosition != -1) {
                    mOffsetForLastPosition = this.mOffsetForLastPosition;
                    i = this.mLastRequestedPosition;
                }
                else {
                    mOffsetForLastPosition = 0;
                }
                while (i < mLastRequestedPosition) {
                    mOffsetForLastPosition += this.mReactListAdapter.mViews.get(i).getMeasuredHeight();
                    ++i;
                }
            }
            else if (mLastRequestedPosition < this.mLastRequestedPosition - mLastRequestedPosition) {
                int j = 0;
                mOffsetForLastPosition = 0;
                while (j < mLastRequestedPosition) {
                    mOffsetForLastPosition += ((View)this.mReactListAdapter.mViews.get(j)).getMeasuredHeight();
                    ++j;
                }
            }
            else {
                mOffsetForLastPosition = this.mOffsetForLastPosition;
                for (int k = this.mLastRequestedPosition - 1; k >= mLastRequestedPosition; --k) {
                    mOffsetForLastPosition -= ((View)this.mReactListAdapter.mViews.get(k)).getMeasuredHeight();
                }
            }
            this.mLastRequestedPosition = mLastRequestedPosition;
            this.mOffsetForLastPosition = mOffsetForLastPosition;
        }
        return this.mOffsetForLastPosition;
    }
    
    public void onHeightChange(final int n, final int n2, final int n3) {
        if (n < this.mLastRequestedPosition) {
            this.mOffsetForLastPosition = this.mOffsetForLastPosition - n2 + n3;
        }
    }
}
