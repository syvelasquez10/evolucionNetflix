// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedCwGalleryAdapter extends BasePaginatedAdapter<CWVideo>
{
    private static final int NUM_VIDEOS_LANDSCAPE_LARGE_SCREEN = 6;
    private static final int NUM_VIDEOS_LANDSCAPE_X_LARGE_SCREEN = 6;
    private static final int NUM_VIDEOS_PORTRAIT_LARGE_SCREEN = 4;
    private static final int NUM_VIDEOS_PORTRAIT_X_LARGE_SCREEN = 6;
    private static final String TAG = "KubrickPaginatedCwGalleryAdapter";
    
    public KubrickPaginatedCwGalleryAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        if (DeviceUtils.getScreenSizeCategory((Context)this.activity) == 3) {
            if (!DeviceUtils.isLandscape((Context)this.activity)) {
                return 4;
            }
        }
        else if (DeviceUtils.isLandscape((Context)this.activity)) {
            return 6;
        }
        return 6;
    }
    
    @Override
    protected int computeNumPages() {
        return MathUtils.ceiling(this.data.size() + 3, this.numItemsPerPage);
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(int n) {
        int computeNumVideosToFetchPerBatch = LomoConfig.computeNumVideosToFetchPerBatch((Context)this.activity, LoMoType.STANDARD);
        if (n == 0) {
            n = (computeNumVideosToFetchPerBatch -= 3);
            if (Log.isLoggable()) {
                Log.v("KubrickPaginatedCwGalleryAdapter", "For first batch of videos, reducing count for Gallery view - fetch size: " + n);
                computeNumVideosToFetchPerBatch = n;
            }
        }
        return computeNumVideosToFetchPerBatch;
    }
    
    @Override
    public List<CWVideo> getDataForPage(final int n) {
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
            Log.v("KubrickPaginatedCwGalleryAdapter", this.getCurrTitleFormatted() + String.format("Getting [%d, %d], data set size: %d, page: %d", min, min2, this.data.size(), n));
        }
        return (List<CWVideo>)this.data.subList(min, min2);
    }
    
    @Override
    public int getRowHeightInPx() {
        final LoMoUtils$LoMoWidthType cwGalleryWidthType = BarkerUtils.getCwGalleryWidthType(this.activity);
        final int n = (int)(LoMoViewPager.computeViewPagerWidth(this.activity, true, cwGalleryWidthType) / this.numItemsPerPage * 0.5625f) * 4;
        if (Log.isLoggable()) {
            Log.v("KubrickPaginatedCwGalleryAdapter", "Computed view height: " + n + " (px), widthType: " + cwGalleryWidthType);
        }
        return n;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<CWVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickCwGalleryViewGroup kubrickCwGalleryViewGroup;
        if ((kubrickCwGalleryViewGroup = ((ObjectRecycler<KubrickCwGalleryViewGroup>)objectRecycler$ViewRecycler).pop(KubrickCwGalleryViewGroup.class)) == null) {
            kubrickCwGalleryViewGroup = new KubrickCwGalleryViewGroup((Context)this.getActivity(), n);
        }
        kubrickCwGalleryViewGroup.updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickCwGalleryViewGroup;
    }
}
