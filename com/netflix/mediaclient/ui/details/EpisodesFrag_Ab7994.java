// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.trackable.Trackable;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.RecyclerView$LayoutManager;
import com.netflix.mediaclient.util.ViewUtils;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.lomo.LomoConfig;
import android.os.Bundle;
import com.netflix.mediaclient.util.TrailerUtils;
import android.view.ViewGroup$LayoutParams;
import android.view.View;
import android.widget.FrameLayout$LayoutParams;
import android.widget.FrameLayout;
import android.content.Context;
import android.view.ViewGroup;
import android.os.Parcelable;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableObject;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.GridLayoutManager;

public class EpisodesFrag_Ab7994 extends EpisodesFrag
{
    private static final int EPISODES = 0;
    private static final String SAVED_STATE_ACTIVE_TAB = "saved_state_active_tab";
    private static final int SIMS = 1;
    private static final int TRAILERS = 2;
    private int activeTab;
    private GridLayoutManager gridLayoutManager;
    private GridLayoutManager gridLayoutManagerForTrailers;
    private ItemDecorationUniformPadding mItemDecorationForSims;
    private int numColumns;
    private TrackableObject trackableForSims;
    private TrackableObject trackableForTrailers;
    private RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    private RecyclerViewHeaderAdapter$IViewCreator viewCreatorTrailers;
    
    public EpisodesFrag_Ab7994() {
        this.activeTab = 0;
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final EpisodesFrag_Ab7994 episodesFrag_Ab7994 = new EpisodesFrag_Ab7994();
        episodesFrag_Ab7994.setStyle(1, 2131493008);
        return EpisodesFrag.applyCreateArgs(episodesFrag_Ab7994, s, s2, b, false);
    }
    
    private void removeRecyclerViewItemDecoration() {
        if (this.mItemDecorationForSims != null) {
            this.recyclerView.removeItemDecoration((RecyclerView$ItemDecoration)this.mItemDecorationForSims);
        }
    }
    
    private void setupRecyclerViewItemDecorationForSims() {
        if (this.mItemDecorationForSims == null) {
            this.mItemDecorationForSims = new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131427641), this.numColumns);
        }
        this.recyclerView.removeItemDecoration((RecyclerView$ItemDecoration)this.mItemDecorationForSims);
        this.recyclerView.addItemDecoration((RecyclerView$ItemDecoration)this.mItemDecorationForSims);
    }
    
    private void setupTrackIds() {
        if (this.showDetails == null) {
            return;
        }
        this.trackableForSims = new TrackableObject(this.showDetails.getSimilarsRequestId(), this.showDetails.getSimilarsTrackId(), this.showDetails.getSimilarsListPos());
        this.trackableForTrailers = new TrackableObject(this.showDetails.getTrailersRequestId(), this.showDetails.getTrailersTrackId(), this.showDetails.getTrailersListPos());
    }
    
    private void updateRecyclerViewLayoutManager() {
        final Parcelable onSaveInstanceState = this.recyclerView.getLayoutManager().onSaveInstanceState();
        switch (this.activeTab) {
            case 0: {
                this.setupRecyclerViewLayoutManager();
                this.removeRecyclerViewItemDecoration();
                break;
            }
            case 1: {
                this.initializeViewCreatorForSims();
                this.setupRecyclerViewLayoutManagerForSims();
                this.setupRecyclerViewItemDecorationForSims();
                break;
            }
            case 2: {
                this.setupRecyclerViewLayoutManagerForTrailers();
                this.initializeViewCreatorForTrailers();
                break;
            }
        }
        this.recyclerView.getLayoutManager().onRestoreInstanceState(onSaveInstanceState);
    }
    
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        if (this.getActivity() == null) {
            return null;
        }
        this.spinner = new SeasonsSpinner((Context)this.getActivity());
        this.setupSeasonsSpinnerAdapter();
        this.setupSeasonsSpinnerListener();
        (this.spinnerViewGroup = (ViewGroup)new FrameLayout((Context)this.getActivity())).setBackgroundResource(2131755279);
        this.spinnerViewGroup.setPadding(0, 0, 0, (int)this.getResources().getDimension(2131427712));
        this.spinnerViewGroup.addView((View)this.spinner, (ViewGroup$LayoutParams)new FrameLayout$LayoutParams(-2, -2, 8388627));
        return this.spinnerViewGroup;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new VideoDetailsViewGroup_Ab7994((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    protected void initializeViewCreatorForSims() {
        if (this.viewCreatorSims != null) {
            return;
        }
        this.viewCreatorSims = new EpisodesFrag_Ab7994$2(this);
    }
    
    protected void initializeViewCreatorForTrailers() {
        if (this.viewCreatorTrailers == null) {
            this.viewCreatorTrailers = TrailerUtils.generateAdapterForTrailers((View)this.recyclerView);
        }
    }
    
    public boolean isShowingEpisodes() {
        return this.activeTab == 0;
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.activeTab = bundle.getInt("saved_state_active_tab", 0);
        }
        this.numColumns = LomoConfig.computeStandardNumVideosPerPage(this.getNetflixActivity(), false);
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putInt("saved_state_active_tab", this.activeTab);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        if (this.episodesAdapter != null) {
            this.recyclerView.setAdapter(this.episodesAdapter);
            return;
        }
        this.episodesAdapter = new EpisodesAdapter_Ab7994((NetflixActivity)this.getActivity(), this, this.viewCreatorEpisodes);
        if (DeviceUtils.isTabletByContext((Context)this.getActivity()) && DeviceUtils.isLandscape((Context)this.getActivity())) {
            this.episodesAdapter.addHeaderView(ViewUtils.createActionBarDummyView(this.getNetflixActivity()));
        }
        this.episodesAdapter.addFooterView(ViewUtils.createActionBarDummyView(this.getNetflixActivity(), this.getResources().getDimensionPixelOffset(2131427907)));
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setSingleChoiceMode(false);
        this.addOfflineAgentListener(this.recyclerView);
    }
    
    protected void setupRecyclerViewLayoutManagerForSims() {
        if (this.gridLayoutManager != null) {
            this.recyclerView.setLayoutManager(this.gridLayoutManager);
            return;
        }
        (this.gridLayoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns)).setSpanSizeLookup(new EpisodesFrag_Ab7994$1(this));
        this.recyclerView.setLayoutManager(this.gridLayoutManager);
    }
    
    protected void setupRecyclerViewLayoutManagerForTrailers() {
        if (this.gridLayoutManagerForTrailers == null) {
            this.gridLayoutManagerForTrailers = TrailerUtils.generateGridViewLayoutManagerForTrailers((Context)this.getActivity(), (RecyclerViewHeaderAdapter)this.recyclerView.getAdapter());
        }
        this.recyclerView.setLayoutManager(this.gridLayoutManagerForTrailers);
    }
    
    public void showEpisodes() {
        this.activeTab = 0;
        this.spinnerViewGroup.setVisibility(0);
        this.updateRecyclerViewLayoutManager();
        if (((EpisodesAdapter_Ab7994)this.episodesAdapter).getEpisodeList() != null && ((EpisodesAdapter_Ab7994)this.episodesAdapter).getEpisodeList().size() > 0) {
            this.episodesAdapter.setItems(((EpisodesAdapter_Ab7994)this.episodesAdapter).getEpisodeList(), 2, this.viewCreatorEpisodes);
            this.setSpinnerSelection();
            return;
        }
        this.episodesAdapter.setItemContentType(2);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
        this.episodesAdapter.clearData();
        this.postSetSpinnerSelectionRunnable();
    }
    
    public void showSims() {
        this.activeTab = 1;
        this.spinnerViewGroup.setVisibility(8);
        if (this.showDetails != null && this.showDetails.getSimilars() != null) {
            this.updateRecyclerViewLayoutManager();
            this.episodesAdapter.setItems(this.showDetails.getSimilars(), 1, this.viewCreatorSims);
            if (this.trackableForSims != null) {
                this.episodesAdapter.setTrackable(this.trackableForSims);
            }
            this.leWrapper.hide(false);
            this.onLoaded(CommonStatus.OK);
        }
    }
    
    @Override
    protected void showStandardViews() {
        if (this.activeTab == 0) {
            super.showStandardViews();
        }
    }
    
    public void showTrailers() {
        this.activeTab = 2;
        this.spinnerViewGroup.setVisibility(8);
        if (this.showDetails != null && this.showDetails.getTrailers() != null) {
            this.updateRecyclerViewLayoutManager();
            this.episodesAdapter.setItems(this.showDetails.getTrailers(), 1, this.viewCreatorTrailers);
            if (this.trackableForTrailers != null) {
                this.episodesAdapter.setTrackable(this.trackableForTrailers);
            }
            this.leWrapper.hide(false);
            this.onLoaded(CommonStatus.OK);
        }
    }
    
    @Override
    public void switchSeason(final int n, final boolean b) {
        if (this.activeTab == 0) {
            super.switchSeason(n, b);
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        super.updateShowDetails(showDetails);
        this.setupTrackIds();
        switch (this.activeTab) {
            case 0: {
                this.showEpisodes();
                break;
            }
            case 1: {
                this.showSims();
                break;
            }
            case 2: {
                this.showTrailers();
                break;
            }
        }
        this.recyclerView.setVisibility(0);
        if (this.activeTab == 2 || (this.activeTab == 1 && this.detailsViewGroup != null && this.detailsViewGroup instanceof VideoDetailsViewGroup_Ab7994)) {
            ((VideoDetailsViewGroup_Ab7994)this.detailsViewGroup).setSecondTabAsSelected();
        }
    }
}
