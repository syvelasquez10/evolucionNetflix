// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.android.widget.ObjectRecycler;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ObjectRecycler$ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.CWTestUtil;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;

public class PaginatedCwAdapter extends BasePaginatedAdapter<CWVideo>
{
    private static final String TAG = "PaginatedCwAdapter";
    
    public PaginatedCwAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return LomoConfig.computeStandardNumVideosPerPage(this.activity, !CWTestUtil.isInTest((Context)this.activity));
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return LomoConfig.computeNumVideosToFetchPerBatch((Context)this.activity, LoMoType.CONTINUE_WATCHING);
    }
    
    @Override
    public int getRowHeightInPx() {
        int n;
        if (CWTestUtil.isInTest((Context)this.activity)) {
            n = (int)(super.getRowHeightInPx() + this.activity.getResources().getDimension(2131362085));
        }
        else {
            n = (int)(LoMoViewPager.computeViewPagerWidth(this.activity, true) / this.numItemsPerPage * 0.5625f + 0.5f) + this.activity.getResources().getDimensionPixelOffset(2131362082);
        }
        Log.v("PaginatedCwAdapter", "Computed view height: " + n);
        return n;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<CWVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        CwViewGroup cwViewGroup;
        if ((cwViewGroup = ((ObjectRecycler<CwViewGroup>)objectRecycler$ViewRecycler).pop(CwViewGroup.class)) == null) {
            cwViewGroup = new CwViewGroup((Context)this.getActivity());
            cwViewGroup.init(n);
        }
        ((VideoViewGroup<CWVideo, V>)cwViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)cwViewGroup;
    }
}
