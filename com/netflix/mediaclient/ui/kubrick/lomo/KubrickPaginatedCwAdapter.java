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
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.interface_.LoMoType;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedCwAdapter extends BasePaginatedAdapter<CWVideo>
{
    private static final String TAG = "KubrickPaginatedCwAdapter";
    
    public KubrickPaginatedCwAdapter(final Context context) {
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
    protected int computeNumVideosToFetchPerBatch(final int n) {
        return LomoConfig.computeNumVideosToFetchPerBatch((Context)this.activity, LoMoType.CONTINUE_WATCHING);
    }
    
    @Override
    public int getRowHeightInPx() {
        final int n = (int)(LoMoViewPager.computeViewPagerWidth(this.activity, true) / this.numItemsPerPage * 0.5625f);
        final int n2 = this.activity.getResources().getDimensionPixelSize(2131362179) + n;
        if (Log.isLoggable()) {
            Log.v("KubrickPaginatedCwAdapter", "Computed view height: " + n + ", height with footer: " + n2 + " (px)");
        }
        return n2;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<CWVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickHighDensityCwViewGroup kubrickHighDensityCwViewGroup;
        if ((kubrickHighDensityCwViewGroup = ((ObjectRecycler<KubrickHighDensityCwViewGroup>)objectRecycler$ViewRecycler).pop(KubrickHighDensityCwViewGroup.class)) == null) {
            kubrickHighDensityCwViewGroup = new KubrickHighDensityCwViewGroup((Context)this.getActivity());
            kubrickHighDensityCwViewGroup.init(n);
        }
        ((VideoViewGroup<CWVideo, V>)kubrickHighDensityCwViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickHighDensityCwViewGroup;
    }
}
