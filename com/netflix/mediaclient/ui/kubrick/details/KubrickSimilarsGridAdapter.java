// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.details.EpisodeRowView;
import android.widget.AdapterView;
import android.view.View;
import com.netflix.mediaclient.android.widget.VideoView;
import com.netflix.mediaclient.util.DeviceUtils;
import android.widget.GridView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

class KubrickSimilarsGridAdapter extends SimilarItemsGridViewAdapter implements StickyGridHeadersBaseAdapter
{
    private int episodeImageHeight;
    private ViewGroup headerView;
    private int numColumns;
    
    public KubrickSimilarsGridAdapter(final NetflixActivity netflixActivity, final GridView gridView) {
        super(netflixActivity, gridView, false);
        this.calculateEpisodeImageHeight();
    }
    
    private void calculateEpisodeImageHeight() {
        this.episodeImageHeight = (int)(DeviceUtils.getScreenWidthInPixels(this.gridView.getContext()) / this.numColumns * 0.5625f);
    }
    
    private void retrieveNumColumns() {
        this.numColumns = this.gridView.getContext().getResources().getInteger(2131427338);
    }
    
    @Override
    protected VideoView createView(final int n) {
        final VideoView view = super.createView(n);
        view.setIsHorizontal(true);
        return view;
    }
    
    @Override
    public int getCountForHeader(final int n) {
        return this.getCount();
    }
    
    @Override
    public View getHeaderView(final int n, final View view, final ViewGroup viewGroup) {
        if (n == 0) {
            return (View)this.headerView;
        }
        return null;
    }
    
    @Override
    protected int getImageHeight() {
        return this.episodeImageHeight;
    }
    
    @Override
    protected int getNumGridCols() {
        if (this.numColumns == 0) {
            this.retrieveNumColumns();
        }
        return this.numColumns;
    }
    
    @Override
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
    
    @Override
    protected void setPadding(final View view, final int n) {
        ViewUtils.applyUniformPaddingToGridItem(view, view.getContext().getResources().getDimensionPixelOffset(2131361990), this.numColumns, n);
    }
}
