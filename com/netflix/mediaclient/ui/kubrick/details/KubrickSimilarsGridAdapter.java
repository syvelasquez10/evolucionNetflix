// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import android.widget.AdapterView;
import android.view.View;
import android.widget.GridView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;

@Deprecated
class KubrickSimilarsGridAdapter
{
    private int episodeImageHeight;
    private ViewGroup headerView;
    private int numColumns;
    
    public KubrickSimilarsGridAdapter(final NetflixActivity netflixActivity, final GridView gridView) {
        this.calculateEpisodeImageHeight();
    }
    
    private void calculateEpisodeImageHeight() {
    }
    
    private void retrieveNumColumns() {
    }
    
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        if (n == 0) {
            return (View)this.headerView;
        }
        return null;
    }
    
    protected int getImageHeight() {
        return this.episodeImageHeight;
    }
    
    protected int getNumGridCols() {
        if (this.numColumns == 0) {
            this.retrieveNumColumns();
        }
        return this.numColumns;
    }
    
    public int getNumHeaders() {
        return 1;
    }
    
    public void onItemClick(final AdapterView<?> adapterView, final View view, final int n, final long n2) {
        if (!view.isInTouchMode()) {
            ((EpisodeRowView)view).handleItemClick();
        }
    }
    
    public void setDetailsHeaderView(final ViewGroup headerView) {
        this.headerView = headerView;
    }
    
    protected void setPadding(final View view, final int n) {
        ViewUtils.applyUniformPaddingToGridItem(view, view.getContext().getResources().getDimensionPixelOffset(2131361998), this.numColumns, n);
    }
}
