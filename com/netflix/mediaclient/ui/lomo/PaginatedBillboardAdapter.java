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
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.Billboard;

public class PaginatedBillboardAdapter extends BasePaginatedAdapter<Billboard>
{
    private static final String TAG = "PaginatedBillboardAdapter";
    
    public PaginatedBillboardAdapter(final Context context) {
        super(context);
    }
    
    public static int getLandscapeBillboardHeight(final NetflixActivity netflixActivity) {
        final float n = LoMoViewPager.computeViewPagerWidth(netflixActivity, false);
        float n2;
        if (DeviceUtils.isLandscape((Context)netflixActivity)) {
            n2 = 2.39f;
        }
        else {
            n2 = 1.778f;
        }
        return (int)(n / n2);
    }
    
    public static int getPortraitBillboardHeight(final NetflixActivity netflixActivity) {
        return (int)(LoMoViewPager.computeViewPagerWidth(netflixActivity, false) * 0.5625f);
    }
    
    public static int getPortraitBillboardPhoneOffset(final NetflixActivity netflixActivity) {
        return netflixActivity.getResources().getDimensionPixelSize(2131362018);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return 1;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return LomoConfig.computeNumVideosToFetchPerBatch((Context)this.activity, LoMoType.BILLBOARD);
    }
    
    @Override
    public int getRowHeightInPx() {
        int landscapeBillboardHeight;
        if (BillboardView.shouldShowArtworkOnly(this.activity)) {
            landscapeBillboardHeight = getPortraitBillboardHeight(this.activity) + getPortraitBillboardPhoneOffset(this.activity);
        }
        else {
            landscapeBillboardHeight = getLandscapeBillboardHeight(this.activity);
        }
        Log.v("PaginatedBillboardAdapter", "Computed view height: " + landscapeBillboardHeight);
        return landscapeBillboardHeight;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<Billboard> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        BillboardViewGroup billboardViewGroup;
        if ((billboardViewGroup = ((ObjectRecycler<BillboardViewGroup>)objectRecycler$ViewRecycler).pop(BillboardViewGroup.class)) == null) {
            billboardViewGroup = new BillboardViewGroup((Context)this.getActivity());
            billboardViewGroup.init(n);
        }
        ((VideoViewGroup<Billboard, V>)billboardViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)billboardViewGroup;
    }
}
