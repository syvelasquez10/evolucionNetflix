// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.lomo;

import com.netflix.mediaclient.ui.lomo.VideoViewGroup;
import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.CWTestUtil;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.lomo.KubrickPaginatedCwAdapter;

public class KubrickKidsPaginatedCwAdapter extends KubrickPaginatedCwAdapter
{
    public KubrickKidsPaginatedCwAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        int computeNumItemsPerPage = super.computeNumItemsPerPage();
        if (CWTestUtil.isInTest((Context)this.getActivity())) {
            computeNumItemsPerPage *= 2;
        }
        return computeNumItemsPerPage;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        if (CWTestUtil.isInTest((Context)this.getActivity())) {
            return LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD);
        }
        return super.computeNumVideosToFetchPerBatch(n);
    }
    
    @Override
    public int getRowHeightInPx() {
        if (CWTestUtil.isInTest((Context)this.getActivity())) {
            return (int)(LoMoViewPager.computeViewPagerWidth(this.activity, true) / this.numItemsPerPage * 1.43f + this.activity.getResources().getDimension(2131362082) + this.activity.getResources().getDimension(2131362064));
        }
        return super.getRowHeightInPx();
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<CWVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickKidsCwViewGroup kubrickKidsCwViewGroup;
        if ((kubrickKidsCwViewGroup = ((ObjectRecycler<KubrickKidsCwViewGroup>)objectRecycler$ViewRecycler).pop(KubrickKidsCwViewGroup.class)) == null) {
            kubrickKidsCwViewGroup = new KubrickKidsCwViewGroup((Context)this.getActivity());
            kubrickKidsCwViewGroup.init(n);
        }
        ((VideoViewGroup<CWVideo, V>)kubrickKidsCwViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickKidsCwViewGroup;
    }
}
