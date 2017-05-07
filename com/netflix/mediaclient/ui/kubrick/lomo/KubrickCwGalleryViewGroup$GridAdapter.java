// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.lomo;

import android.view.View;
import android.content.Context;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.lolomo.LoLoMoFocusHandler;
import android.view.ViewGroup$LayoutParams;
import android.support.v7.widget.RecyclerView$ViewHolder;
import com.netflix.mediaclient.ui.lomo.LoMoUtils$LoMoWidthType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.servicemgr.interface_.CWVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.support.v7.widget.RecyclerView$LayoutParams;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;

class KubrickCwGalleryViewGroup$GridAdapter extends RecyclerView$Adapter<KubrickCwGalleryViewGroup$Holder>
{
    private final NetflixActivity activity;
    private final RecyclerView$LayoutParams expandedParams;
    private int listViewPos;
    private final int numVideosPerPage;
    private final int padding;
    private int page;
    private final RecyclerView$LayoutParams params;
    private Trackable trackable;
    private List<CWVideo> videos;
    
    public KubrickCwGalleryViewGroup$GridAdapter(final KubrickCwGalleryViewGroup kubrickCwGalleryViewGroup, int numVideosPerPage) {
        this.activity = (NetflixActivity)kubrickCwGalleryViewGroup.getContext();
        this.numVideosPerPage = numVideosPerPage;
        final LoMoUtils$LoMoWidthType cwGalleryWidthType = KubrickUtils.getCwGalleryWidthType(this.activity);
        final int computeViewPagerWidth = LoMoViewPager.computeViewPagerWidth(this.activity, true, cwGalleryWidthType);
        numVideosPerPage = MathUtils.divideIntsWithRounding(computeViewPagerWidth, numVideosPerPage) * 2;
        if (Log.isLoggable()) {
            Log.v("KubrickCwGalleryViewGroup", "parent width: " + computeViewPagerWidth + ", child width: " + numVideosPerPage + ", widthType: " + cwGalleryWidthType);
        }
        this.params = new RecyclerView$LayoutParams(numVideosPerPage, -1);
        this.expandedParams = new RecyclerView$LayoutParams(numVideosPerPage * 2, -1);
        this.padding = kubrickCwGalleryViewGroup.getResources().getDimensionPixelOffset(2131296535);
    }
    
    @Override
    public int getItemCount() {
        if (this.page == 0) {
            return this.numVideosPerPage - 3;
        }
        return this.numVideosPerPage;
    }
    
    @Override
    public void onBindViewHolder(final KubrickCwGalleryViewGroup$Holder kubrickCwGalleryViewGroup$Holder, final int n) {
        boolean b = true;
        CWVideo cwVideo;
        if (n < this.videos.size()) {
            cwVideo = this.videos.get(n);
        }
        else {
            cwVideo = null;
        }
        final KubrickCwGalleryView access$100 = kubrickCwGalleryViewGroup$Holder.view;
        final boolean b2 = this.page == 0 && n == 0;
        RecyclerView$LayoutParams layoutParams;
        if (b2) {
            layoutParams = this.expandedParams;
        }
        else {
            layoutParams = this.params;
        }
        access$100.setLayoutParams((ViewGroup$LayoutParams)layoutParams);
        final int n2 = this.page * this.numVideosPerPage;
        access$100.setId(LoLoMoFocusHandler.computeViewId(this.listViewPos, n2 + n));
        if (cwVideo == null) {
            access$100.hide();
            return;
        }
        final Trackable trackable = this.trackable;
        if (this.page != 0) {
            b = false;
        }
        access$100.update(cwVideo, trackable, n2 + n, b, b2);
    }
    
    @Override
    public KubrickCwGalleryViewGroup$Holder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        final KubrickCwGalleryView kubrickCwGalleryView = new KubrickCwGalleryView((Context)this.activity);
        kubrickCwGalleryView.setPadding(this.padding, this.padding, this.padding, this.padding);
        return new KubrickCwGalleryViewGroup$Holder(kubrickCwGalleryView);
    }
    
    public void updateData(final List<CWVideo> videos, final int page, final int listViewPos, final Trackable trackable) {
        this.videos = videos;
        this.page = page;
        this.listViewPos = listViewPos;
        this.trackable = trackable;
        this.notifyDataSetChanged();
    }
}
