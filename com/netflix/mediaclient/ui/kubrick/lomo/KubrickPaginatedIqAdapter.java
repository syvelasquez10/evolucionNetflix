// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public class KubrickPaginatedIqAdapter extends KubrickPaginatedLoMoAdapter
{
    public KubrickPaginatedIqAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        int n = 2;
        if (DeviceUtils.getBasicScreenOrientation((Context)this.activity) == 2) {
            n = 3;
        }
        return n;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickIqViewGroup kubrickIqViewGroup;
        if ((kubrickIqViewGroup = ((ObjectRecycler<KubrickIqViewGroup>)objectRecycler$ViewRecycler).pop(KubrickIqViewGroup.class)) == null) {
            kubrickIqViewGroup = new KubrickIqViewGroup((Context)this.getActivity());
            kubrickIqViewGroup.init(n);
        }
        ((VideoViewGroup<KubrickVideo, V>)kubrickIqViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickIqViewGroup;
    }
}
