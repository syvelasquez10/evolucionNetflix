// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import java.util.List;
import android.view.View;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

public class KubrickCwGalleryViewGroup extends RecyclerView
{
    static final int NUM_REMOVED_VIDEOS = 3;
    static final int NUM_ROWS = 2;
    private static final String TAG = "KubrickCwGalleryViewGroup";
    private final KubrickCwGalleryViewGroup$GridAdapter adapter;
    private int page;
    
    public KubrickCwGalleryViewGroup(final Context context, final int n) {
        super(context);
        this.setId(2131689500);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        final KubrickCwGalleryViewGroup$1 layoutManager = new KubrickCwGalleryViewGroup$1(this, context, 2, 0, false);
        layoutManager.setSpanSizeLookup(new KubrickCwGalleryViewGroup$2(this));
        this.setLayoutManager(layoutManager);
        this.setAdapter(this.adapter = new KubrickCwGalleryViewGroup$GridAdapter(this, n));
        final NetflixActivity netflixActivity = (NetflixActivity)this.getContext();
        LoMoUtils.applyContentOverlapPadding(netflixActivity, (View)this, BarkerUtils.getCwGalleryWidthType(netflixActivity));
    }
    
    public void updateDataThenViews(final List<CWVideo> list, final int n, final int page, final int n2, final Trackable trackable) {
        if (Log.isLoggable()) {
            Log.v("KubrickCwGalleryViewGroup", "Setting data, first: " + DataUtil.getFirstItemSafely(list) + ", hash: " + this.hashCode() + ", num per page: " + n + ", page: " + page + ", listViewPos: " + n2);
        }
        this.page = page;
        this.adapter.updateData(list, page, n2, trackable);
    }
}
