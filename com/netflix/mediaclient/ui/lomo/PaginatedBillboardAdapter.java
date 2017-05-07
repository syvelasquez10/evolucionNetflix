// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.lomo;

import android.view.View;
import com.netflix.mediaclient.servicemgr.Trackable;
import java.util.List;
import com.netflix.mediaclient.android.widget.ViewRecycler;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;

public class PaginatedBillboardAdapter extends BasePaginatedAdapter<BillboardDetails>
{
    private static final String TAG = "PaginatedBillboardAdapter";
    
    public PaginatedBillboardAdapter(final Context context) {
        super(context);
    }
    
    public static int getViewHeightInPixels(final Context context) {
        int n;
        if (BillboardView.shouldShowArtworkOnly((NetflixActivity)context)) {
            n = (int)(BasePaginatedAdapter.computeViewPagerWidth(context, false) * 0.562f);
        }
        else {
            final int computeViewPagerWidth = BasePaginatedAdapter.computeViewPagerWidth(context, false);
            int n2;
            if (DeviceUtils.isLandscape(context)) {
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
    protected int computeNumItemsPerPage(final int n, final int n2) {
        return 1;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final Context context) {
        return 20;
    }
    
    @Override
    protected View getView(final ViewRecycler viewRecycler, final List<BillboardDetails> list, final int n, final int n2, final Trackable trackable) {
        BillboardViewGroup billboardViewGroup;
        if ((billboardViewGroup = (BillboardViewGroup)viewRecycler.pop(BillboardViewGroup.class)) == null) {
            billboardViewGroup = new BillboardViewGroup((Context)this.getActivity());
            billboardViewGroup.init(n);
        }
        ((VideoViewGroup<BillboardDetails, V>)billboardViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), trackable);
        return (View)billboardViewGroup;
    }
}
