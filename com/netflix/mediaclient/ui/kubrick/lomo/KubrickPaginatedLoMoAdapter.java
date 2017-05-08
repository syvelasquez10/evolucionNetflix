// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedLoMoAdapter extends BasePaginatedAdapter<KubrickVideo>
{
    private static final String TAG = "KubrickPaginatedLoMoAdapter";
    
    public KubrickPaginatedLoMoAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        if (DeviceUtils.isLandscape((Context)this.activity)) {
            return 4;
        }
        return 3;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return LomoConfig.computeNumVideosToFetchPerBatch((Context)this.activity, LoMoType.STANDARD);
    }
    
    @Override
    public int getRowHeightInPx() {
        int n2;
        final int n = n2 = (int)(LoMoViewPager.computeViewPagerWidth(this.activity, 1 != 0) / this.numItemsPerPage * 0.5625f);
        if (BrowseExperience.showKidsExperience()) {
            n2 = n + this.activity.getResources().getDimensionPixelSize(2131362204);
        }
        if (Log.isLoggable()) {
            Log.v("KubrickPaginatedLoMoAdapter", "Computed view height: " + n2 + " (px)");
        }
        return n2;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickLoMoViewGroup kubrickLoMoViewGroup;
        if ((kubrickLoMoViewGroup = ((ObjectRecycler<KubrickLoMoViewGroup>)objectRecycler$ViewRecycler).pop(KubrickLoMoViewGroup.class)) == null) {
            kubrickLoMoViewGroup = new KubrickLoMoViewGroup((Context)this.getActivity());
            kubrickLoMoViewGroup.init(n);
        }
        ((VideoViewGroup<KubrickVideo, V>)kubrickLoMoViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickLoMoViewGroup;
    }
}
