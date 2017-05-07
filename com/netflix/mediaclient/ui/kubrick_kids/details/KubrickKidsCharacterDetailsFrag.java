// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.util.gfx.AnimationUtils;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup$DetailsStringProvider;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.app.Status;
import android.view.View;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.KidsCharacterDetails;
import android.annotation.SuppressLint;

@SuppressLint({ "ResourceAsColor" })
public class KubrickKidsCharacterDetailsFrag extends KubrickKidsShowDetailsFrag
{
    private static final String EXTRA_CHARACTER_ID = "extra_chararcter_id";
    public static final String EXTRA_KIDS_COLOR = "extra_kids_color";
    private static final float ROW_HEIGHT_LANDSCAPE_SCALE_FACTOR = 0.8f;
    private static final float ROW_HEIGHT_PORTRAIT_SCALE_FACTOR = 1.1f;
    private static final String TAG = "KidsCharacterDetailsFrag";
    private String characterId;
    private KidsCharacterDetails kidsCharacterDetails;
    private int kidsColor;
    boolean shouldRenderAsSDP;
    
    public static Fragment create(final String s, final int n) {
        final KubrickKidsCharacterDetailsFrag kubrickKidsCharacterDetailsFrag = new KubrickKidsCharacterDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_chararcter_id", s);
        arguments.putInt("extra_kids_color", n);
        arguments.putBoolean("extra_show_details", true);
        kubrickKidsCharacterDetailsFrag.setArguments(arguments);
        return (Fragment)kubrickKidsCharacterDetailsFrag;
    }
    
    private void fetchCharacterDetails() {
        if (this.manager == null) {
            Log.w("KidsCharacterDetailsFrag", "Manager is null - can't get character details");
            return;
        }
        this.manager.getBrowse().fetchKidsCharacterDetails(this.characterId, new KubrickKidsCharacterDetailsFrag$FetchCharacterDetailsCallback(this));
    }
    
    private void renderAsSDP(final ShowDetails showDetails) {
        this.setupSeasonsSpinnerGroup();
        super.updateShowDetails(showDetails);
        ((KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup)this.detailsViewGroup).updateCharacterDetails(this.kidsCharacterDetails);
        this.episodesAdapter.setItemContentType(2);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
        this.showViews();
        this.leWrapper.hide(false);
        if (this.heroSlideshow != null) {
            this.heroSlideshow.start();
        }
        this.postSetSpinnerSelectionRunnable();
    }
    
    private DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListenerLocal() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final KubrickKidsCharacterDetailsFrag$CharacterKidsParallax onScrollListener = new KubrickKidsCharacterDetailsFrag$CharacterKidsParallax(this, this);
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                onScrollListener.setOnScrollStateChangedListener(new KubrickKidsCharacterDetailsFrag$1(this));
                return onScrollListener;
            }
        }
        return null;
    }
    
    private void setupSeasonsSpinnerGroupLocal() {
        if (this.shouldRenderAsSDP) {
            this.spinnerViewGroup = this.createSeasonsSelectorGroup();
            this.addSpinnerToDetailsGroup();
        }
    }
    
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        final ViewGroup seasonsSelectorGroup = super.createSeasonsSelectorGroup();
        this.setSpinnerBackground(this.getResources().getColor(this.kidsColor));
        final SeasonsSpinnerAdapter seasonsSpinnerAdapter = (SeasonsSpinnerAdapter)this.spinner.getAdapter();
        if (seasonsSpinnerAdapter != null) {
            seasonsSpinnerAdapter.setItemBackgroundColor(this.kidsColor);
            seasonsSpinnerAdapter.setDropDownBackgroundColor(2131558601);
            seasonsSpinnerAdapter.setDropDownTextColor(this.kidsColor);
        }
        return seasonsSelectorGroup;
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131296521) * 2;
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903128;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.hideRelatedTitle();
    }
    
    @Override
    protected void initDetailsViewGroupAsHeader() {
        this.initDetailsViewGroup();
        ((RecyclerViewHeaderAdapter)this.recyclerView.getAdapter()).addHeaderView((View)this.detailsViewGroup);
        this.setDetailViewGroupVisibility(0);
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.characterId = this.getArguments().getString("extra_chararcter_id");
        this.kidsColor = this.getArguments().getInt("extra_kids_color");
    }
    
    @Override
    public void onManagerReady(final ServiceManager serviceManager, final Status status) {
        Log.v("KidsCharacterDetailsFrag", "onManagerReady");
        super.onManagerReady(serviceManager, status);
        this.fetchCharacterDetails();
    }
    
    protected void renderAsMDP(final VideoDetails videoDetails) {
        this.episodesAdapter.setItems(this.kidsCharacterDetails.getGallery());
        ((KubrickKidsCharacterDetailsFrag$KubrickKidsCharacterDetailsViewGroup)this.detailsViewGroup).updateCharacterDetails(this.kidsCharacterDetails);
        this.detailsViewGroup.updateDetails(videoDetails, new KubrickKidsCharacterDetailsFrag$StringProvider((Context)this.getActivity(), videoDetails));
        if (this.heroSlideshow != null) {
            this.heroSlideshow.start();
        }
        this.showViews();
        this.leWrapper.hide(false);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickKidsCharacterDetailsFrag$GalleryAdapter(this);
        this.recyclerView.setAdapter(this.episodesAdapter);
    }
    
    @Override
    protected void setupSeasonsSpinnerGroup() {
    }
    
    @Override
    protected void showStandardViews() {
        if (!this.isListVisible()) {
            AnimationUtils.showView((View)this.recyclerView, true);
        }
    }
    
    @Override
    protected void updateSeasonData(final List<SeasonDetails> list) {
        if (this.shouldRenderAsSDP) {
            super.updateSeasonData(list);
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        if (this.shouldRenderAsSDP) {
            this.renderAsSDP(showDetails);
            return;
        }
        this.renderAsMDP(showDetails);
    }
}
