// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public class KubrickPaginatedLargeVideoAdapter extends KubrickPaginatedLoMoAdapter
{
    public KubrickPaginatedLargeVideoAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        if (DeviceUtils.isLandscape((Context)this.activity)) {
            return 3;
        }
        return 2;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickLargeVideoViewGroup kubrickLargeVideoViewGroup;
        if ((kubrickLargeVideoViewGroup = ((ObjectRecycler<KubrickLargeVideoViewGroup>)objectRecycler$ViewRecycler).pop(KubrickLargeVideoViewGroup.class)) == null) {
            kubrickLargeVideoViewGroup = new KubrickLargeVideoViewGroup((Context)this.getActivity());
            kubrickLargeVideoViewGroup.init(n);
        }
        ((VideoViewGroup<KubrickVideo, V>)kubrickLargeVideoViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickLargeVideoViewGroup;
    }
}
