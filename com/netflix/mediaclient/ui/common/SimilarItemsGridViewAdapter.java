// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.View;
import com.netflix.mediaclient.android.widget.VideoView;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;

public class SimilarItemsGridViewAdapter extends RecyclerViewHeaderAdapter
{
    private final boolean clipToCompleteRows;
    private int numColumns;
    
    public SimilarItemsGridViewAdapter(final boolean clipToCompleteRows, final int numColumns, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        super(recyclerViewHeaderAdapter$IViewCreator);
        this.clipToCompleteRows = clipToCompleteRows;
        this.numColumns = numColumns;
    }
    
    private int clipCountToCompleteRows() {
        int n = 1;
        final int n2 = 1;
        if (this.getItemCountExcludingHeadersAndFooters() == 1) {
            final int headerViewsCount = this.getHeaderViewsCount();
            int n3;
            if (this.hasFooter()) {
                n3 = n2;
            }
            else {
                n3 = 0;
            }
            return n3 + (headerViewsCount + 1);
        }
        final int n4 = this.getItemCountExcludingHeadersAndFooters() / this.numColumns;
        final int numColumns = this.numColumns;
        final int headerViewsCount2 = this.getHeaderViewsCount();
        if (!this.hasFooter()) {
            n = 0;
        }
        return n + (n4 * numColumns + headerViewsCount2);
    }
    
    public static void refreshImagesIfNecessary(final RecyclerView recyclerView) {
        if (recyclerView != null) {
            for (int i = 0; i < recyclerView.getChildCount(); ++i) {
                final View child = recyclerView.getChildAt(i);
                if (child instanceof VideoView) {
                    ((VideoView)child).refreshImageIfNecessary();
                }
            }
        }
    }
    
    @Override
    public int getItemCount() {
        if (this.clipToCompleteRows) {
            return this.clipCountToCompleteRows();
        }
        return super.getItemCount();
    }
}
