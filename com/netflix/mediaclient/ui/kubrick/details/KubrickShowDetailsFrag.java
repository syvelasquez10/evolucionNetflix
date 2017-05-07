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
import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView$OnItemSelectedListener;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;

public class KubrickShowDetailsFrag extends EpisodesFrag implements ErrorWrapper$Callback, ManagerStatusListener, ServiceManagerProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final int ANIMATE_IN_DURATION_MS = 500;
    private static final String SAVED_STATE_SHOW_RELATED = "saved_state_show_related";
    private static final String TAG = "KubrickShowDetailsFrag";
    private List<EpisodeDetails> currentEpisodes;
    private boolean hasBookmark;
    protected KubrickShowDetailsFrag$HeroSlideshow heroSlideshow;
    private View rootContainer;
    private boolean showRelated;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    
    public KubrickShowDetailsFrag() {
        this.viewCreatorEpisodes = new KubrickShowDetailsFrag$5(this);
        this.viewCreatorSims = new KubrickShowDetailsFrag$6(this);
    }
    
    private void adjustBackroundForNestedActivities(final View view) {
        if (view != null && this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false)) {
            Api16Util.setBackgroundDrawableCompat(view, null);
        }
    }
    
    public static NetflixDialogFrag create(final String s, final String s2) {
        final KubrickShowDetailsFrag kubrickShowDetailsFrag = new KubrickShowDetailsFrag();
        kubrickShowDetailsFrag.setStyle(1, 2131558720);
        return EpisodesFrag.applyCreateArgs(kubrickShowDetailsFrag, s, s2, true, false);
    }
    
    private void setupDismissClick() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickShowDetailsFrag$1(this));
        }
    }
    
    private void setupSpinnerScroller() {
        if (this.spinner == null || this.recyclerView == null) {
            return;
        }
        this.spinner.setOnItemTouchListener((AdapterView$OnItemSelectedListener)new KubrickShowDetailsFrag$3(this));
    }
    
    protected void animateIn() {
        Log.v("KubrickShowDetailsFrag", "animateIn()");
        if (this.recyclerView == null) {
            Log.d("KubrickShowDetailsFrag", "RecyclerView is null - can't animate view in");
            return;
        }
        if (!this.isListVisible()) {
            Log.v("KubrickShowDetailsFrag", "Showing recycler view");
            this.recyclerView.setVisibility(0);
        }
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
        this.getNetflixActivity().getNetflixActionBar().setAlphaWithAnimation(1.0f, 500);
    }
    
    protected int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131296465);
    }
    
    @Override
    protected ViewGroup createSeasonsSpinnerGroup() {
        final ViewGroup seasonsSpinnerGroup = super.createSeasonsSpinnerGroup();
        seasonsSpinnerGroup.setBackgroundResource(2131230820);
        seasonsSpinnerGroup.setPadding(this.calculateSpinnerLeftPosition(), 0, 0, 0);
        this.setupSpinnerScroller();
        return seasonsSpinnerGroup;
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131427613);
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickShowDetailsFrag$BookmarkedVideoDetails(this, (Context)this.getActivity())).removeActionBarDummyView();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.heroSlideshow = new KubrickShowDetailsFrag$HeroSlideshow(this, this.getNetflixActivity(), null);
        if (bundle != null) {
            this.showRelated = bundle.getBoolean("saved_state_show_related", false);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.adjustBackroundForNestedActivities(onCreateView);
        return onCreateView;
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("saved_state_show_related", this.showRelated);
    }
    
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131361802);
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && (DeviceUtils.isTabletByContext((Context)this.getActivity()) || DeviceUtils.isPortrait((Context)this.getActivity()))) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), new View[] { this.detailsViewGroup.getHeroImage(), ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, (View)this.spinnerViewGroup, this.recyclerView.getResources().getColor(2131230830), 0, (View)this.detailsViewGroup.getFooterViewGroup());
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                onScrollListener.setOnScrollStateChangedListener(new KubrickShowDetailsFrag$4(this));
                return onScrollListener;
            }
        }
        return null;
    }
    
    @SuppressLint({ "InlinedApi" })
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296452), this.numColumns));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        this.numColumns = this.retrieveNumColumns();
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new KubrickShowDetailsFrag$2(this));
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    @Override
    protected void setupSeasonsSpinnerGroup() {
        this.spinnerViewGroup = this.createSeasonsSpinnerGroup();
        this.addSpinnerToDetailsGroup();
    }
    
    public void showCurrentSeason() {
        this.showRelated = false;
        if (this.currentEpisodes != null && this.currentEpisodes.size() > 0) {
            this.spinnerViewGroup.setVisibility(0);
            this.episodesAdapter.setItems(this.currentEpisodes, 2, this.viewCreatorEpisodes);
            return;
        }
        this.episodesAdapter.setItemContentType(2);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
        this.episodesAdapter.clearData();
        this.postSetSpinnerSelectionRunnable();
    }
    
    public void showRelatedTitles() {
        if (this.showDetails != null && this.showDetails.getSimilars() != null) {
            this.spinnerViewGroup.setVisibility(8);
            this.episodesAdapter.setItems(this.showDetails.getSimilars(), 1, this.viewCreatorSims);
            this.leWrapper.hide(false);
        }
        this.showRelated = true;
    }
    
    @Override
    protected void showStandardViews() {
        this.postSetSpinnerSelectionRunnable();
        if (this.showRelated) {
            ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).performClickOnRelatedTitles();
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        super.updateShowDetails(showDetails);
        this.setupDismissClick();
    }
}
