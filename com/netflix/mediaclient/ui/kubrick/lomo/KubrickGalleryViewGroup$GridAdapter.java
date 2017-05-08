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
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.MathUtils;
import com.netflix.mediaclient.ui.lomo.LoMoViewPager;
import com.netflix.mediaclient.servicemgr.interface_.KubrickVideo;
import java.util.List;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import android.support.v7.widget.RecyclerView$LayoutParams;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;

class KubrickGalleryViewGroup$GridAdapter extends RecyclerView$Adapter<KubrickGalleryViewGroup$Holder>
{
    private final NetflixActivity activity;
    private final RecyclerView$LayoutParams expandedParams;
    private int listViewPos;
    private final int numVideosPerPage;
    private final int padding;
    private int page;
    private final RecyclerView$LayoutParams params;
    private Trackable trackable;
    private List<KubrickVideo> videos;
    
    public KubrickGalleryViewGroup$GridAdapter(final KubrickGalleryViewGroup kubrickGalleryViewGroup, int numVideosPerPage) {
        this.activity = (NetflixActivity)kubrickGalleryViewGroup.getContext();
        this.numVideosPerPage = numVideosPerPage;
        final int computeViewPagerWidth = LoMoViewPager.computeViewPagerWidth(this.activity, true);
        numVideosPerPage = MathUtils.divideIntsWithRounding(computeViewPagerWidth, numVideosPerPage) * 2;
        if (Log.isLoggable()) {
            Log.v("KubrickGalleryViewGroup", "parent width: " + computeViewPagerWidth + ", child width: " + numVideosPerPage);
        }
        this.params = new RecyclerView$LayoutParams(numVideosPerPage, -1);
        this.expandedParams = new RecyclerView$LayoutParams(numVideosPerPage * 2, -1);
        this.padding = kubrickGalleryViewGroup.getResources().getDimensionPixelOffset(2131362252);
    }
    
    @Override
    public int getItemCount() {
        if (this.page == 0) {
            return this.numVideosPerPage - 3;
        }
        return this.numVideosPerPage;
    }
    
    @Override
    public void onBindViewHolder(final KubrickGalleryViewGroup$Holder kubrickGalleryViewGroup$Holder, final int n) {
        boolean b = true;
        KubrickVideo kubrickVideo;
        if (n < this.videos.size()) {
            kubrickVideo = this.videos.get(n);
        }
        else {
            kubrickVideo = null;
        }
        final KubrickVideoView access$100 = kubrickGalleryViewGroup$Holder.view;
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
        if (kubrickVideo == null) {
            access$100.hide();
            return;
        }
        final Trackable trackable = this.trackable;
        if (this.page != 0) {
            b = false;
        }
        access$100.update(kubrickVideo, trackable, n2 + n, b, b2);
    }
    
    @Override
    public KubrickGalleryViewGroup$Holder onCreateViewHolder(final ViewGroup viewGroup, final int n) {
        final KubrickVideoView kubrickVideoView = new KubrickVideoView((Context)this.activity);
        kubrickVideoView.setPadding(this.padding, this.padding, this.padding, this.padding);
        return new KubrickGalleryViewGroup$Holder(kubrickVideoView);
    }
    
    public void updateData(final List<KubrickVideo> videos, final int page, final int listViewPos, final Trackable trackable) {
        this.videos = videos;
        this.page = page;
        this.listViewPos = listViewPos;
        this.trackable = trackable;
        this.notifyDataSetChanged();
    }
}
