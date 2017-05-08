// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import com.netflix.mediaclient.util.DataUtil;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.ui.experience.BrowseExperience;
import android.view.View;
import com.netflix.mediaclient.ui.lomo.LoMoUtils;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.view.ViewGroup$LayoutParams;
import android.widget.AbsListView$LayoutParams;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

public class KubrickGalleryViewGroup extends RecyclerView
{
    static final int NUM_REMOVED_VIDEOS = 3;
    static final int NUM_ROWS = 2;
    private static final String TAG = "KubrickGalleryViewGroup";
    private final KubrickGalleryViewGroup$GridAdapter adapter;
    private int page;
    
    public KubrickGalleryViewGroup(final Context context, final int n) {
        super(context);
        this.setId(2131689499);
        this.setLayoutParams((ViewGroup$LayoutParams)new AbsListView$LayoutParams(-1, -2));
        final KubrickGalleryViewGroup$1 layoutManager = new KubrickGalleryViewGroup$1(this, context, 2, 0, false);
        layoutManager.setSpanSizeLookup(new KubrickGalleryViewGroup$2(this));
        this.setLayoutManager(layoutManager);
        this.setAdapter(this.adapter = new KubrickGalleryViewGroup$GridAdapter(this, n));
        LoMoUtils.applyContentOverlapPadding((NetflixActivity)this.getContext(), (View)this, LoMoUtils$LoMoWidthType.STANDARD);
        if (BrowseExperience.showKidsExperience()) {
            ViewUtils.setPaddingBottom((View)this, this.getResources().getDimensionPixelSize(2131362202));
        }
    }
    
    public void updateDataThenViews(final List<KubrickVideo> list, final int n, final int page, final int n2, final Trackable trackable) {
        if (Log.isLoggable()) {
            Log.v("KubrickGalleryViewGroup", "Setting data, first: " + DataUtil.getFirstItemSafely(list) + ", hash: " + this.hashCode() + ", num per page: " + n + ", page: " + page + ", listViewPos: " + n2);
        }
        this.page = page;
        this.adapter.updateData(list, page, n2, trackable);
    }
}
