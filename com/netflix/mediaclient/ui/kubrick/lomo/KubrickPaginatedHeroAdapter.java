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
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.model.KubrickVideo;
import com.netflix.mediaclient.ui.lomo.BasePaginatedAdapter;

public class KubrickPaginatedHeroAdapter extends BasePaginatedAdapter<KubrickVideo>
{
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.75f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.1f;
    private static final String TAG = "KubrickPaginatedHeroAdapter";
    
    public KubrickPaginatedHeroAdapter(final Context context) {
        super(context);
    }
    
    @Override
    protected int computeNumItemsPerPage() {
        return 1;
    }
    
    @Override
    protected int computeNumVideosToFetchPerBatch() {
        return LomoConfig.computeNumVideosToFetchPerBatch(this.activity, LoMoType.STANDARD);
    }
    
    @Override
    public int getRowHeightInPx() {
        final float n = (int)(BasePaginatedAdapter.computeViewPagerWidth(this.activity, false) * 0.5625f);
        float n2;
        if (DeviceUtils.isLandscape((Context)this.activity)) {
            n2 = 0.75f;
        }
        else {
            n2 = 1.1f;
        }
        final int n3 = (int)(n2 * n);
        if (Log.isLoggable("KubrickPaginatedHeroAdapter", 2)) {
            Log.v("KubrickPaginatedHeroAdapter", "Computed view height: " + n3 + " (px)");
        }
        return n3;
    }
    
    @Override
    protected View getView(final ObjectRecycler$ViewRecycler objectRecycler$ViewRecycler, final List<KubrickVideo> list, final int n, final int n2, final BasicLoMo basicLoMo) {
        KubrickHeroViewGroup kubrickHeroViewGroup;
        if ((kubrickHeroViewGroup = ((ObjectRecycler<KubrickHeroViewGroup>)objectRecycler$ViewRecycler).pop(KubrickHeroViewGroup.class)) == null) {
            kubrickHeroViewGroup = new KubrickHeroViewGroup((Context)this.getActivity());
            kubrickHeroViewGroup.init(n);
        }
        ((VideoViewGroup<KubrickVideo, V>)kubrickHeroViewGroup).updateDataThenViews(list, n, n2, this.getListViewPos(), basicLoMo);
        return (View)kubrickHeroViewGroup;
    }
}
