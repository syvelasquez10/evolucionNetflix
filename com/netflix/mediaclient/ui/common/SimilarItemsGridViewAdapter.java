// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.common;

import android.view.View;
import com.netflix.mediaclient.android.widget.VideoView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;

public class SimilarItemsGridViewAdapter extends RecyclerViewHeaderAdapter
{
    private static final String TAG = "SimilarItemsGridViewAdapter";
    private final boolean clipToCompleteRows;
    private int numColumns;
    private RecyclerView recyclerView;
    
    public SimilarItemsGridViewAdapter(final RecyclerView recyclerView, final boolean clipToCompleteRows, final int numColumns, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        super(recyclerViewHeaderAdapter$IViewCreator);
        this.clipToCompleteRows = clipToCompleteRows;
        this.recyclerView = recyclerView;
        this.numColumns = numColumns;
    }
    
    private int clipCountToCompleteRows() {
        return this.data.size() / this.numColumns * this.numColumns + this.getHeaderViewsCount();
    }
    
    @Override
    public int getItemCount() {
        if (this.clipToCompleteRows) {
            return this.clipCountToCompleteRows();
        }
        return super.getItemCount();
    }
    
    public void refreshImagesIfNecessary() {
        for (int i = 0; i < this.recyclerView.getChildCount(); ++i) {
            final View child = this.recyclerView.getChildAt(i);
            if (child instanceof VideoView) {
                ((VideoView)child).refreshImageIfNecessary();
            }
        }
    }
}
