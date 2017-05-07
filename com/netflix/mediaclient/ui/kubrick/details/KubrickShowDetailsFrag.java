// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.content.BroadcastReceiver;
import com.netflix.mediaclient.util.MdxUtils;
import android.os.Bundle;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.util.MdxUtils$SetVideoRatingCallback;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.Log;
import android.view.ViewGroup;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.mdx.DialogMessageReceiver$Callback;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;

public class KubrickShowDetailsFrag extends EpisodesFrag implements ErrorWrapper$Callback, ManagerStatusListener, ServiceManagerProvider, DialogMessageReceiver$Callback, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final String TAG = "KubrickShowDetailsFrag";
    protected List<EpisodeDetails> currentEpisodes;
    private final DialogMessageReceiver dialogMessageReceiver;
    protected KubrickShowDetailsFrag$HeroSlideshow heroSlideshow;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    
    public KubrickShowDetailsFrag() {
        this.dialogMessageReceiver = new DialogMessageReceiver(this);
        this.viewCreatorEpisodes = new KubrickShowDetailsFrag$3(this);
        this.viewCreatorSims = new KubrickShowDetailsFrag$4(this);
    }
    
    public static NetflixDialogFrag create(final String s, final String s2) {
        final KubrickShowDetailsFrag kubrickShowDetailsFrag = new KubrickShowDetailsFrag();
        kubrickShowDetailsFrag.setStyle(1, 2131558712);
        return EpisodesFrag.applyCreateArgs(kubrickShowDetailsFrag, s, s2, true, false);
    }
    
    protected int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131296469);
    }
    
    @Override
    protected ViewGroup createSeasonsSpinnerGroup() {
        final ViewGroup seasonsSpinnerGroup = super.createSeasonsSpinnerGroup();
        seasonsSpinnerGroup.setBackgroundResource(2131230820);
        seasonsSpinnerGroup.setPadding(this.calculateSpinnerLeftPosition(), 0, 0, 0);
        return seasonsSpinnerGroup;
    }
    
    @Override
    public void handleUserRatingChange(final String s, final float n) {
        if (Log.isLoggable()) {
            Log.v("KubrickShowDetailsFrag", "Change user settings for received video id: " + s + " to rating: " + n);
        }
        if (s == null) {
            if (Log.isLoggable()) {
                Log.v("KubrickShowDetailsFrag", "Can't set rating receivedVideoId is null");
            }
        }
        else {
            if (this.getServiceManager() != null) {
                this.getServiceManager().getBrowse().setVideoRating(s, VideoType.SHOW, (int)n, PlayContext.EMPTY_CONTEXT.getTrackId(), new MdxUtils$SetVideoRatingCallback((NetflixActivity)this.getActivity(), n));
                return;
            }
            if (Log.isLoggable()) {
                Log.v("KubrickShowDetailsFrag", "Can't set rating because service man is null");
            }
        }
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.heroSlideshow = new KubrickShowDetailsFrag$HeroSlideshow(this, this.getNetflixActivity(), null);
    }
    
    public void onStart() {
        super.onStart();
        MdxUtils.registerReceiver(this.getActivity(), this.dialogMessageReceiver);
    }
    
    public void onStop() {
        super.onStop();
        MdxUtils.unregisterReceiver(this.getActivity(), this.dialogMessageReceiver);
    }
    
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131361802);
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && (DeviceUtils.isNotTabletByContext((Context)this.getActivity()) || DeviceUtils.isPortrait((Context)this.getActivity()))) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), (View)this.detailsViewGroup.getHeroImage(), (View)this.spinnerViewGroup, this.recyclerView.getResources().getColor(2131230827), 0, (View)this.detailsViewGroup.getFooterViewGroup());
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                onScrollListener.setOnScrollStateChangedListener(new KubrickShowDetailsFrag$2(this));
                return onScrollListener;
            }
        }
        return null;
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296461), this.numColumns));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        this.numColumns = this.retrieveNumColumns();
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new KubrickShowDetailsFrag$1(this));
        this.recyclerView.setLayoutManager(layoutManager);
    }
    
    @Override
    protected void setupSeasonsSpinnerGroup() {
        this.spinnerViewGroup = this.createSeasonsSpinnerGroup();
        this.addSpinnerToDetailsGroup();
    }
    
    public void showCurrentSeason() {
        if (this.currentEpisodes != null && this.currentEpisodes.size() > 0) {
            this.detailsViewGroup.removeRelatedTitle();
            this.spinnerViewGroup.setVisibility(0);
            this.episodesAdapter.setItems(this.currentEpisodes, 2, this.viewCreatorEpisodes);
        }
    }
    
    public void showRelatedTitles() {
        if (this.showDetails != null && this.showDetails.getSimilars() != null) {
            this.detailsViewGroup.showRelatedTitle();
            this.spinnerViewGroup.setVisibility(8);
            this.episodesAdapter.setItems(this.showDetails.getSimilars(), 1, this.viewCreatorSims);
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails, final boolean b) {
        super.updateShowDetails(showDetails, b);
        this.heroSlideshow.start();
    }
}
