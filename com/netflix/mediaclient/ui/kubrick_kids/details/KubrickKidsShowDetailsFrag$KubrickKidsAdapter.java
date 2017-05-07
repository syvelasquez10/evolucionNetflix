// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationEdgePadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.kubrick.details.KubrickVideoDetailsViewGroup;
import android.view.View;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.graphics.PorterDuff$Mode;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import android.view.ViewGroup;
import android.content.Context;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.ui.details.EpisodesAdapter;
import com.netflix.mediaclient.ui.details.EpisodesAdapter$FetchEpisodesCallback;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$KubrickEpisodesAdapter;

class KubrickKidsShowDetailsFrag$KubrickKidsAdapter extends KubrickShowDetailsFrag$KubrickEpisodesAdapter
{
    private static final String TAG = "KubrickEpisodesAdapter";
    final /* synthetic */ KubrickKidsShowDetailsFrag this$0;
    
    public KubrickKidsShowDetailsFrag$KubrickKidsAdapter(final KubrickKidsShowDetailsFrag this$0, final NetflixActivity netflixActivity, final KubrickShowDetailsFrag kubrickShowDetailsFrag, final RecyclerViewHeaderAdapter$IViewCreator recyclerViewHeaderAdapter$IViewCreator) {
        this.this$0 = this$0;
        super(this$0, netflixActivity, kubrickShowDetailsFrag, recyclerViewHeaderAdapter$IViewCreator);
    }
    
    @Override
    protected void fetchMoreData() {
        ServiceManager serviceManager = null;
        if (this.episodeListFrag instanceof ServiceManagerProvider) {
            serviceManager = this.episodeListFrag.getServiceManager();
        }
        if (serviceManager == null || !serviceManager.isReady()) {
            Log.d("KubrickEpisodesAdapter", "Manager is not ready");
            return;
        }
        if (this.currSeasonDetails == null) {
            Log.v("KubrickEpisodesAdapter", "No season details yet");
            return;
        }
        if (Log.isLoggable()) {
            Log.v("KubrickEpisodesAdapter", "curr season number of episodes: " + this.currSeasonDetails.getNumOfEpisodes());
        }
        this.requestId = System.nanoTime();
        final int n = this.episodeStartIndex + 40 - 1;
        final String id = this.this$0.showDetails.getId();
        if (Log.isLoggable()) {
            Log.v("KubrickEpisodesAdapter", "Fetching data for show: " + id + ", start: " + this.episodeStartIndex + ", end: " + n);
        }
        serviceManager.getBrowse().fetchEpisodes(id, VideoType.SHOW, this.episodeStartIndex, n, new EpisodesAdapter$FetchEpisodesCallback(this, this.requestId, this.episodeStartIndex, n));
    }
    
    @Override
    public int getItemCount() {
        int n = 0;
        if (this.data == null) {
            return 0;
        }
        final int size = this.data.size();
        final int headerViewsCount = this.getHeaderViewsCount();
        if (this.hasFooter()) {
            n = 1;
        }
        return n + (size + headerViewsCount);
    }
    
    @Override
    protected void initToLoadingState() {
        Log.v("KubrickEpisodesAdapter", "initToLoadingState");
        this.isLoading = true;
        this.hasMoreData = true;
        this.requestId = -1L;
        this.fetchMoreData();
    }
    
    public void updateEpisodeStartIndex(final int episodeStartIndex) {
        this.episodeStartIndex = episodeStartIndex;
    }
    
    @Override
    protected void updateEpisodesData(final List<EpisodeDetails> items, final int n) {
        if (this.this$0.isSeasonUserSelected) {
            super.setItems(items);
        }
        else {
            super.updateEpisodesData(items, n);
        }
        this.this$0.showViews();
    }
    
    public void updateItems(final Collection<? extends Video> collection, final int n, final int n2) {
        super.updateItems(collection, n);
        Log.v("KubrickEpisodesAdapter", "updateItems: startIndex" + n + " episodeStartIndex " + this.episodeStartIndex);
    }
}
