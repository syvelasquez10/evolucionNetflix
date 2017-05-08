// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.annotation.SuppressLint;
import android.view.ViewGroup$LayoutParams;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import android.widget.AdapterView$OnItemSelectedListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;

public class KubrickShowDetailsFrag extends EpisodesFrag implements ErrorWrapper$Callback, ManagerStatusListener, IHandleBackPress, ServiceManagerProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    protected static final int ANIMATE_IN_DURATION_MS = 500;
    private static final String SAVED_STATE_SHOW_RELATED = "saved_state_show_related";
    private static final String SAVED_STATE_SUPRESS_ANIMATEIN = "saved_state_supress_animatein";
    private static final String TAG = "KubrickShowDetailsFrag";
    private List<EpisodeDetails> currentEpisodes;
    DetailsPageParallaxScrollListener detailsPageParallaxScrollListener;
    private View fragBackground;
    private boolean fromSameActivityType;
    private boolean hasBookmark;
    protected KubrickShowDetailsFrag$HeroSlideshow heroSlideshow;
    RelatedTitleState previousRelatedTitleState;
    private Stack<RelatedTitleState> relatedTitlesHistory;
    private View rootContainer;
    private KubrickSeasonsDialogAdapter seasonsDialogAdapter;
    protected RecyclerView seasonsDialogRecyclerView;
    private boolean showRecyclerBackground;
    private boolean showRelated;
    private boolean supressAnimateIn;
    protected RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    
    public KubrickShowDetailsFrag() {
        this.relatedTitlesHistory = new Stack<RelatedTitleState>();
        this.viewCreatorEpisodes = new KubrickShowDetailsFrag$6(this);
        this.viewCreatorSims = new KubrickShowDetailsFrag$7(this);
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final KubrickShowDetailsFrag kubrickShowDetailsFrag = new KubrickShowDetailsFrag();
        kubrickShowDetailsFrag.setStyle(1, 2131361916);
        return EpisodesFrag.applyCreateArgs(kubrickShowDetailsFrag, s, s2, b, false);
    }
    
    private void restorePreviousRelatedTitle() {
        this.previousRelatedTitleState = this.relatedTitlesHistory.pop();
        this.showId = this.previousRelatedTitleState.titleId;
        this.currSeasonIndex = this.previousRelatedTitleState.seasonSelectIndex;
        this.fetchShowDetailsAndSeasons();
    }
    
    private void setSameActivity() {
        this.fromSameActivityType = this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false);
    }
    
    private void setupBackground(final View view) {
        if (view == null) {
            return;
        }
        if (this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false)) {
            Api16Util.setBackgroundDrawableCompat(view, null);
            return;
        }
        this.setupRecyclerShadow();
    }
    
    private void setupRecyclerShadow() {
        if (this.showDetailsOnLaunch && KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) < DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) && this.fragBackground != null) {
            this.showRecyclerBackground = true;
            this.fragBackground.getLayoutParams().width = this.getRecyclerViewShadowWidth();
        }
    }
    
    private void setupSpinnerScroller() {
        if (this.spinner == null || this.recyclerView == null) {
            return;
        }
        this.spinner.setOnItemTouchListener((AdapterView$OnItemSelectedListener)new KubrickShowDetailsFrag$4(this));
    }
    
    protected void animateIn() {
        if (!this.isListVisible()) {
            Log.v("KubrickShowDetailsFrag", "Showing recycler view");
            this.recyclerView.setVisibility(0);
        }
        final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
        if (!this.fromSameActivityType && this.showRecyclerBackground) {
            this.fragBackground.setVisibility(0);
            this.fragBackground.animate().alpha(1.0f).setStartDelay(500L).setDuration(500L);
        }
        netflixActionBar.setAlphaWithAnimation(1.0f, 500);
    }
    
    protected int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131296599);
    }
    
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        final ViewGroup seasonsSelectorGroup = super.createSeasonsSelectorGroup();
        if (this.showDetailsOnLaunch) {
            if (seasonsSelectorGroup != null) {
                seasonsSelectorGroup.setBackgroundResource(2131558596);
            }
            if (seasonsSelectorGroup != null) {
                seasonsSelectorGroup.setPadding(this.calculateSpinnerLeftPosition(), 0, 0, 0);
            }
            this.setupSpinnerScroller();
            return seasonsSelectorGroup;
        }
        this.seasonsDialogRecyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity(), 0, false));
        this.seasonsDialogAdapter = new KubrickSeasonsDialogAdapter((Context)this.getActivity(), new KubrickShowDetailsFrag$3(this));
        this.seasonsDialogRecyclerView.setAdapter(this.seasonsDialogAdapter);
        return seasonsSelectorGroup;
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131624285);
        if (!this.showDetailsOnLaunch) {
            this.seasonsDialogRecyclerView = (RecyclerView)view.findViewById(2131624284);
            this.fragBackground = view.findViewById(2131624285);
            return;
        }
        this.fragBackground = view.findViewById(2131624299);
    }
    
    protected int getRecyclerViewShadowWidth() {
        return KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131296591) * 2;
    }
    
    @Override
    protected int getlayoutId() {
        if (this.showDetailsOnLaunch) {
            return 2130903228;
        }
        return 2130903129;
    }
    
    @Override
    public boolean handleBackPressed() {
        if (!this.relatedTitlesHistory.empty()) {
            this.restorePreviousRelatedTitle();
            return true;
        }
        return false;
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
            this.supressAnimateIn = bundle.getBoolean("saved_state_supress_animatein", false);
            new RelatedTitlesHistoryRestoreInstanceState(bundle, this.relatedTitlesHistory).invoke();
            if (!this.relatedTitlesHistory.empty()) {
                this.previousRelatedTitleState = this.relatedTitlesHistory.pop();
                this.showId = this.previousRelatedTitleState.titleId;
            }
        }
        else if (!this.showDetailsOnLaunch) {
            this.supressAnimateIn = true;
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.setSameActivity();
        if (!this.showDetailsOnLaunch) {
            this.getDialog().getWindow().setGravity(48);
        }
        this.setupBackground(onCreateView);
        return onCreateView;
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        bundle.putBoolean("saved_state_show_related", this.showRelated);
        if (this.getActivity().isChangingConfigurations()) {
            bundle.putBoolean("saved_state_supress_animatein", true);
        }
        if (!this.relatedTitlesHistory.empty()) {
            this.relatedTitlesHistory.push(new RelatedTitleState(this.showId, this.recyclerView.getLayoutManager().onSaveInstanceState(), this.currSeasonIndex, DeviceUtils.getBasicScreenOrientation((Context)this.getActivity())));
            new RelatedTitlesHistorySaveInstanceState(bundle, this.relatedTitlesHistory).invoke();
        }
    }
    
    public void onStart() {
        super.onStart();
        if (!this.showDetailsOnLaunch) {
            this.getDialog().getWindow().setLayout(-1, (int)this.getResources().getDimension(2131296594));
        }
    }
    
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131427333);
    }
    
    protected void setScrollPosition() {
        if (this.previousRelatedTitleState == null) {
            this.recyclerView.smoothScrollToPosition(0);
            this.showCurrentSeason();
            return;
        }
        if (this.previousRelatedTitleState.orientation == DeviceUtils.getBasicScreenOrientation((Context)this.getActivity())) {
            this.recyclerView.getLayoutManager().onRestoreInstanceState(this.previousRelatedTitleState.recyclerViewState);
        }
        this.showRelatedTitles();
        this.previousRelatedTitleState = null;
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && (DeviceUtils.isTabletByContext((Context)this.getActivity()) || DeviceUtils.isPortrait((Context)this.getActivity()))) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                this.detailsPageParallaxScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), new View[] { this.detailsViewGroup.getHeroImage(), ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, (View)this.spinnerViewGroup, this.recyclerView.getResources().getColor(2131558591), 0, (View)this.detailsViewGroup.getFooterViewGroup());
                this.getRecyclerView().setOnScrollListener(this.detailsPageParallaxScrollListener);
                this.detailsPageParallaxScrollListener.setOnScrollStateChangedListener(new KubrickShowDetailsFrag$5(this));
                return this.detailsPageParallaxScrollListener;
            }
        }
        return null;
    }
    
    protected void setupDismissClick() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickShowDetailsFrag$1(this));
        }
    }
    
    @SuppressLint({ "InlinedApi" })
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
        final View view = new View((Context)this.getActivity());
        view.setLayoutParams(new ViewGroup$LayoutParams(-2, this.getResources().getDimensionPixelOffset(2131296577) / 2));
        this.episodesAdapter.addFooterView(view);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        if (this.showDetailsOnLaunch) {
            this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296577), this.numColumns));
        }
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        if (this.showDetailsOnLaunch) {
            this.numColumns = this.retrieveNumColumns();
            final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
            layoutManager.setSpanSizeLookup(new KubrickShowDetailsFrag$2(this));
            this.recyclerView.setLayoutManager(layoutManager);
            this.recyclerView.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
            this.recyclerView.setAlpha(0.0f);
            return;
        }
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager((Context)this.getActivity(), 0, false);
        layoutManager2.setStackFromEnd(true);
        this.recyclerView.setLayoutManager(layoutManager2);
    }
    
    @Override
    protected void setupSeasonsSpinnerGroup() {
        this.spinnerViewGroup = this.createSeasonsSelectorGroup();
        this.addSpinnerToDetailsGroup();
    }
    
    public void showCurrentSeason() {
        this.showRelated = false;
        if (this.currentEpisodes != null && this.currentEpisodes.size() > 0) {
            this.spinnerViewGroup.setVisibility(0);
            this.episodesAdapter.setItems(this.currentEpisodes, 2, this.viewCreatorEpisodes);
            this.setSpinnerSelection();
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
            this.onLoaded(CommonStatus.OK);
        }
        this.showRelated = true;
    }
    
    @Override
    protected void showStandardViews() {
        Log.v("KubrickShowDetailsFrag", "showStandardViews()");
        this.postSetSpinnerSelectionRunnable();
        if (this.showRelated) {
            ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).performClickOnRelatedTitles();
        }
    }
    
    protected void showViews() {
        Log.v("KubrickShowDetailsFrag", "animateIn()");
        if (this.recyclerView != null && this.rootContainer != null) {
            if (!this.supressAnimateIn) {
                this.animateIn();
                return;
            }
            if (!this.isListVisible()) {
                Log.v("KubrickShowDetailsFrag", "Showing recycler view");
                this.recyclerView.setVisibility(0);
            }
            this.recyclerView.setAlpha(1.0f);
            if (!this.fromSameActivityType && this.showRecyclerBackground && this.showDetailsOnLaunch) {
                this.fragBackground.setVisibility(0);
                this.fragBackground.setAlpha(1.0f);
            }
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.setAlpha(1.0f);
            }
        }
    }
    
    @Override
    protected void updateSeasonData(final List<SeasonDetails> list) {
        super.updateSeasonData(list);
        if (!this.showDetailsOnLaunch) {
            this.seasonsDialogRecyclerView.scrollToPosition(this.seasonsDialogAdapter.updateSeasonData(list, this.showDetails.getCurrentSeasonNumber()));
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        super.updateShowDetails(showDetails);
        this.setScrollPosition();
        this.setupDismissClick();
    }
}
