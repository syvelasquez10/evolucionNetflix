// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedLoMoAdapter extends BasePaginatedAdapter<KubrickVideo>
{
    private static final String TAG = "KubrickPaginatedLoMoAdapter";
    
    public KubrickPaginatedLoMoAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        if (DeviceUtils.getBasicScreenOrientation((Context)this.activity) == 2) {
            return 4;
        }
        return 3;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch() {
        return LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD);
    }
    
    @Override
    public int getRowHeightInPx() {
        final int n = (int)(BasePaginatedAdapter.computeViewPagerWidth(this.activity, true) / this.computeNumItemsPerPage() * 0.5625f);
        if (Log.isLoggable("KubrickPaginatedLoMoAdapter", 2)) {
            Log.v("KubrickPaginatedLoMoAdapter", "Computed view height: " + n + " (px)");
        }
        return n;
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
