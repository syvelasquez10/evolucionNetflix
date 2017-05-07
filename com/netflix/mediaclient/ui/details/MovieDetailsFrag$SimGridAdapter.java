// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import android.widget.AdapterView;
import android.view.View;
import android.widget.GridView;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;
import com.tonicartos.widget.stickygridheaders.StickyGridHeadersBaseAdapter;
import com.netflix.mediaclient.ui.common.SimilarItemsGridViewAdapter;

public class MovieDetailsFrag$SimGridAdapter extends SimilarItemsGridViewAdapter implements StickyGridHeadersBaseAdapter
{
    private ViewGroup headerView;
    final /* synthetic */ MovieDetailsFrag this$0;
    
    public MovieDetailsFrag$SimGridAdapter(final MovieDetailsFrag this$0, final NetflixActivity netflixActivity, final GridView gridView) {
        this.this$0 = this$0;
        super(netflixActivity, gridView, true);
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
}
