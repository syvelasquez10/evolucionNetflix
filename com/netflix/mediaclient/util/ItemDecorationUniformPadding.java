// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.RecyclerView$State;
import android.graphics.Rect;
import android.view.View;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView$ItemDecoration;

public class ItemDecorationUniformPadding extends RecyclerView$ItemDecoration
{
    private int headerOffset;
    protected int numColumns;
    protected int padding;
    
    public ItemDecorationUniformPadding(final int padding, final int numColumns) {
        this.headerOffset = 0;
        this.padding = padding;
        this.numColumns = numColumns;
    }
    
    private int adjustForHeader(final RecyclerView recyclerView, final View view) {
        return recyclerView.getChildPosition(view) + 1 + this.headerOffset;
    }
    
    @Override
    public void getItemOffsets(final Rect rect, final View view, final RecyclerView recyclerView, final RecyclerView$State recyclerView$State) {
        if (recyclerView.getAdapter() instanceof RecyclerViewHeaderAdapter && ((RecyclerViewHeaderAdapter)recyclerView.getAdapter()).isViewHeader(view, recyclerView)) {
            this.headerOffset = -1;
        }
        else {
            rect.left = this.padding / 2;
            rect.right = this.padding / 2;
            rect.bottom = this.padding / 2;
            rect.top = this.padding / 2;
            if (this.adjustForHeader(recyclerView, view) % this.numColumns == 1 || this.numColumns == 1) {
                rect.left = this.padding;
            }
            if (this.adjustForHeader(recyclerView, view) % this.numColumns == 0 || this.numColumns == 1) {
                rect.right = this.padding;
            }
        }
    }
}
