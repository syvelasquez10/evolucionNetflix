// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedGalleryAdapter extends BasePaginatedAdapter<KubrickVideo>
{
    private static final int NUM_GALLERY_VIDEOS_LANDSCAPE = 8;
    private static final int NUM_GALLERY_VIDEOS_PORTRAIT = 6;
    private static final String TAG = "KubrickPaginatedGalleryAdapter";
    
    public KubrickPaginatedGalleryAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        if (DeviceUtils.isLandscape((Context)this.activity)) {
            return 8;
        }
        return 6;
    }
    
    @Override
    protected int computeNumPages() {
        return MathUtils.ceiling(this.data.size() + 3, this.numItemsPerPage);
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(int n) {
        int computeNumVideosToFetchPerBatch = LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD);
        if (n == 0) {
            n = (computeNumVideosToFetchPerBatch -= 3);
            if (Log.isLoggable()) {
                Log.v("KubrickPaginatedGalleryAdapter", "For first batch of videos, reducing count for Gallery view - fetch size: " + n);
                computeNumVideosToFetchPerBatch = n;
            }
        }
        return computeNumVideosToFetchPerBatch;
    }
    
    @Override
    public List<KubrickVideo> getDataForPage(final int n) {
        if (n >= this.computeNumPages()) {
            return null;
        }
        final int n2 = this.numItemsPerPage * n;
        int n3;
        if ((n3 = n2) >= 3) {
            n3 = n2 - 3;
        }
        final int min = Math.min(n3, this.data.size());
        final int min2 = Math.min(this.numItemsPerPage + min, this.data.size());
        if (Log.isLoggable()) {
            Log.v("KubrickPaginatedGalleryAdapter", this.getCurrTitleFormatted() + String.format("Getting [%d, %d], data set size: %d, page: %d", min, min2, this.data.size(), n));
        }
        return (List<KubrickVideo>)this.data.subList(min, min2);
    }
    
    @Override
    public int getRowHeightInPx() {
        int n2;
        final int n = n2 = (int)(LoMoViewPager.computeViewPagerWidth(this.activity, 1 != 0) / this.numItemsPerPage * 0.5625f) * 4;
        if (BrowseExperience.isKubrickKids()) {
            n2 = n + this.activity.getResources().getDimensionPixelSize(2131296478);
        }
        if (Log.isLoggable()) {
            Log.v("KubrickPaginatedGalleryAdapter", "Computed view height: " + n2 + " (px)");
        }
        return n2;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickGalleryViewGroup kubrickGalleryViewGroup;
        if ((kubrickGalleryViewGroup = ((ObjectRecycler<KubrickGalleryViewGroup>)objectRecycler$ViewRecycler).pop(KubrickGalleryViewGroup.class)) == null) {
            kubrickGalleryViewGroup = new KubrickGalleryViewGroup((Context)this.getActivity(), n);
        }
        kubrickGalleryViewGroup.updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickGalleryViewGroup;
    }
}
