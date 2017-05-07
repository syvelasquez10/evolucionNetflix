// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedLoMoAdapter;

public class KubrickKidsPaginatedTopTenAdapter extends KubrickPaginatedLoMoAdapter
{
    public KubrickKidsPaginatedTopTenAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return 1;
    }
    
    @Override
    public int getRowHeightInPx() {
        return (int)(LoMoViewPager.computeViewPagerWidth(this.activity, true, LoMoUtils$LoMoWidthType.KUBRICK_KIDS_TOP_TEN_ROW) / this.computeNumItemsPerPage() * 0.5625f);
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickKidsTopTenVideoViewGroup kubrickKidsTopTenVideoViewGroup;
        if ((kubrickKidsTopTenVideoViewGroup = ((ObjectRecycler<KubrickKidsTopTenVideoViewGroup>)objectRecycler$ViewRecycler).pop(KubrickKidsTopTenVideoViewGroup.class)) == null) {
            kubrickKidsTopTenVideoViewGroup = new KubrickKidsTopTenVideoViewGroup((Context)this.getActivity());
            kubrickKidsTopTenVideoViewGroup.init(n);
        }
        ((VideoViewGroup<KubrickVideo, V>)kubrickKidsTopTenVideoViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickKidsTopTenVideoViewGroup;
    }
}
