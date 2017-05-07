// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import com.netflix.mediaclient.servicemgr.model.trackable.Trackable;
import android.view.View;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.Billboard;

public class PaginatedBillboardAdapter extends BasePaginatedAdapter<Billboard>
{
    public static final int NUM_BILLBOARDS_TO_FETCH_PER_BATCH = 10;
    private static final String TAG = "PaginatedBillboardAdapter";
    
    public PaginatedBillboardAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return 1;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final Context context) {
        return 10;
    }
    
    @Override
    public int getRowHeightInPx() {
        int n;
        if (BillboardView.shouldShowArtworkOnly(this.activity)) {
            n = (int)(BasePaginatedAdapter.computeViewPagerWidth(this.activity, false) * 0.5625f);
        }
        else {
            final int computeViewPagerWidth = BasePaginatedAdapter.computeViewPagerWidth(this.activity, false);
            int n2;
            if (DeviceUtils.isLandscape((Context)this.activity)) {
                n2 = 3;
            }
            else {
                n2 = 2;
            }
            n = computeViewPagerWidth / n2;
        }
        Log.v("PaginatedBillboardAdapter", "Computed view height: " + n);
        return n;
    }
    
    @Override
    protected View getView(final ViewRecycler viewRecycler, final List<Billboard> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        BillboardViewGroup billboardViewGroup;
        if ((billboardViewGroup = (BillboardViewGroup)viewRecycler.pop(BillboardViewGroup.class)) == null) {
            billboardViewGroup = new BillboardViewGroup((Context)this.getActivity());
            billboardViewGroup.init(n);
        }
        ((VideoViewGroup<Billboard, V>)billboardViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)billboardViewGroup;
    }
}
