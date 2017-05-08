// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.SeasonDetails;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import java.util.Collection;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.annotation.SuppressLint;
import android.view.View$OnClickListener;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import android.view.LayoutInflater;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.widget.FrameLayout$LayoutParams;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.widget.AdapterView$OnItemSelectedListener;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Parcelable;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.app.Status;
import android.view.ViewGroup;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import android.view.View;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;

public class BarkerShowDetailsFrag extends EpisodesFrag implements ErrorWrapper$Callback, ManagerStatusListener, IHandleBackPress, ServiceManagerProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    protected static final int ANIMATE_IN_DURATION_MS = 500;
    private static final String SAVED_STATE_SHOW_RELATED = "saved_state_show_related";
    private static final int SYNOPSIS_LINES_LANDSCAPE = 5;
    private static final int SYNOPSIS_LINES_MAX = 10;
    private static final int SYNOPSIS_LINES_PORTRAIT = 3;
    private static final String TAG = "BarkerShowDetailsFrag";
    protected BarkerHelper$BarkerBars barker;
    private CopyrightView copyrightView;
    private List<EpisodeDetails> currentEpisodes;
    private View fragBackground;
    private boolean fromSameActivityType;
    private boolean hasBookmark;
    protected BarkerShowDetailsFrag$HeroSlideshow heroSlideshow;
    private ItemDecorationBarkerGrid innerGridDecoration;
    private boolean isFromRelated;
    protected DetailsPageParallaxScrollListener parallaxScroller;
    RelatedTitleState previousRelatedTitleState;
    private Stack<RelatedTitleState> relatedTitlesHistory;
    private View rootContainer;
    private BarkerSeasonsDialogAdapter seasonsDialogAdapter;
    protected RecyclerView seasonsDialogRecyclerView;
    private boolean showRecyclerBackground;
    private boolean showRelated;
    private boolean supressAnimateIn;
    protected RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    
    public BarkerShowDetailsFrag() {
        this.relatedTitlesHistory = new Stack<RelatedTitleState>();
        this.viewCreatorEpisodes = new BarkerShowDetailsFrag$6(this);
        this.viewCreatorSims = new BarkerShowDetailsFrag$7(this);
    }
    
    private void addCopyrightAsFooter() {
        if (this.episodesAdapter != null && this.getActivity() != null) {
            this.copyrightView = CopyrightView.create(null, (Context)this.getActivity());
            if (this.copyrightView != null) {
                this.copyrightView.setGravityAsCenter();
                this.episodesAdapter.addFooterView(this.copyrightView.getView());
            }
        }
    }
    
    public static NetflixDialogFrag create(final String s, final String s2, final boolean b) {
        final BarkerShowDetailsFrag barkerShowDetailsFrag = new BarkerShowDetailsFrag();
        barkerShowDetailsFrag.setStyle(1, 2131427465);
        return EpisodesFrag.applyCreateArgs(barkerShowDetailsFrag, s, s2, b, false);
    }
    
    public static NetflixDialogFrag create(final boolean b) {
        final BarkerShowDetailsFrag barkerShowDetailsFrag = new BarkerShowDetailsFrag();
        barkerShowDetailsFrag.setStyle(1, 2131427465);
        return EpisodesFrag.applyCreateArgs(barkerShowDetailsFrag, null, null, b, false);
    }
    
    private void invalidateRecyclerView() {
        final Parcelable onSaveInstanceState = this.recyclerView.getLayoutManager().onSaveInstanceState();
        this.setupRecyclerViewLayoutManager();
        this.setupRecyclerViewItemDecoration();
        this.recyclerView.getLayoutManager().onRestoreInstanceState(onSaveInstanceState);
    }
    
    private void restorePreviousRelatedTitle() {
        this.previousRelatedTitleState = this.relatedTitlesHistory.pop();
        this.showId = this.previousRelatedTitleState.titleId;
        this.currSeasonIndex = this.previousRelatedTitleState.seasonSelectIndex;
        this.fetchShowDetailsAndSeasons();
    }
    
    private void setSameActivity() {
        if (this.getActivity() != null) {
            this.fromSameActivityType = this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false);
        }
    }
    
    private void setupBackground(final View view) {
        if (view == null || this.getActivity() == null) {
            return;
        }
        if (this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false)) {
            Api16Util.setBackgroundDrawableCompat(view, null);
            return;
        }
        this.setupRecyclerShadow();
    }
    
    private void setupRecyclerShadow() {
        if (this.showDetailsOnLaunch && this.getActivity() != null && BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity()) < DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) && this.fragBackground != null) {
            this.showRecyclerBackground = true;
            this.fragBackground.getLayoutParams().width = this.getRecyclerViewShadowWidth();
        }
    }
    
    private void setupSpinnerScroller() {
        if (this.spinner == null || this.recyclerView == null) {
            return;
        }
        this.spinner.setOnItemTouchListener((AdapterView$OnItemSelectedListener)new BarkerShowDetailsFrag$4(this));
    }
    
    private void updateTrackId() {
        if (this.getActivity() instanceof DetailsActivity) {
            BarkerUtils.updateTrackId((DetailsActivity)this.getActivity(), this.showDetails, this.relatedTitlesHistory, this.previousRelatedTitleState);
        }
    }
    
    public void addEpisodeArguments(final String s, final String s2) {
        if (this.getArguments() != null) {
            this.getArguments().putString("extra_episode_id", s2);
            this.getArguments().putString("extra_show_id", s);
        }
    }
    
    protected void animateIn() {
        if (!this.isListVisible()) {
            Log.v("BarkerShowDetailsFrag", "Showing recycler view");
            this.recyclerView.setVisibility(0);
        }
        final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
        if (!this.fromSameActivityType && this.showRecyclerBackground && this.fragBackground != null) {
            this.fragBackground.setVisibility(0);
            this.fragBackground.animate().alpha(1.0f).setStartDelay(500L).setDuration(500L);
        }
        netflixActionBar.setAlphaWithAnimation(1.0f, 500);
    }
    
    protected int calculateSpinnerLeftPosition() {
        return -this.getActivity().getResources().getDimensionPixelOffset(2131362082);
    }
    
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        final ViewGroup seasonsSelectorGroup = super.createSeasonsSelectorGroup();
        if (this.showDetailsOnLaunch) {
            if (seasonsSelectorGroup != null) {
                final int calculateSpinnerLeftPosition = this.calculateSpinnerLeftPosition();
                if (calculateSpinnerLeftPosition < 0) {
                    ((FrameLayout$LayoutParams)this.getSeasonSpinner().getLayoutParams()).setMarginStart(calculateSpinnerLeftPosition);
                }
                else {
                    seasonsSelectorGroup.setPadding(calculateSpinnerLeftPosition, 0, 0, 0);
                }
            }
            this.setupSpinnerScroller();
            return seasonsSelectorGroup;
        }
        this.seasonsDialogRecyclerView.setLayoutManager(new LinearLayoutManager((Context)this.getActivity(), 0, false));
        this.seasonsDialogAdapter = new BarkerSeasonsDialogAdapter((Context)this.getActivity(), new BarkerShowDetailsFrag$3(this));
        this.seasonsDialogRecyclerView.setAdapter(this.seasonsDialogAdapter);
        return seasonsSelectorGroup;
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131689897);
        if (this.rootContainer != null) {
            this.rootContainer.setBackgroundResource(this.getBackgroundResource());
        }
        if (!this.showDetailsOnLaunch) {
            this.seasonsDialogRecyclerView = (RecyclerView)view.findViewById(2131689896);
            this.fragBackground = view.findViewById(2131689897);
            return;
        }
        this.fragBackground = view.findViewById(2131689909);
    }
    
    protected int getBackgroundResource() {
        return 2131624168;
    }
    
    protected int getNumColumns() {
        if (this.getActivity() == null) {
            return 1;
        }
        if (this.showRelated) {
            return this.barker.getNumberOfSims();
        }
        return this.getActivity().getResources().getInteger(2131492872);
    }
    
    protected int getRecyclerViewShadowWidth() {
        if (this.getActivity() == null) {
            return 0;
        }
        return BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131362214) * 2;
    }
    
    @Override
    protected int getlayoutId() {
        if (this.showDetailsOnLaunch) {
            return 2130903277;
        }
        return 2130903155;
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
        (this.detailsViewGroup = new BarkerShowDetailsFrag$BookmarkedVideoDetails(this, (Context)this.getActivity())).removeActionBarDummyView();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.barker = new BarkerHelper$BarkerBars((Context)this.getActivity());
        this.heroSlideshow = new BarkerShowDetailsFrag$HeroSlideshow(this, this.getNetflixActivity(), null);
        this.supressAnimateIn = (bundle != null);
        if (bundle != null) {
            this.showRelated = bundle.getBoolean("saved_state_show_related", false);
            RelatedTitleState$RestoreInstanceState.invoke(bundle, this.relatedTitlesHistory);
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
        bundle.putString("extra_episode_id", this.episodeId);
        if (!this.relatedTitlesHistory.empty()) {
            this.relatedTitlesHistory.push(new RelatedTitleState(this.showId, this.recyclerView.getLayoutManager().onSaveInstanceState(), this.currSeasonIndex, DeviceUtils.getBasicScreenOrientation((Context)this.getActivity()), ((DetailsActivity)this.getActivity()).getPlayContext()));
            RelatedTitleState$SaveInstanceState.invoke(bundle, this.relatedTitlesHistory);
        }
    }
    
    public void onStart() {
        super.onStart();
        if (!this.showDetailsOnLaunch) {
            this.getDialog().getWindow().setLayout(-1, (int)this.getResources().getDimension(2131362217));
        }
    }
    
    protected void setScrollPosition() {
        if (this.isFromRelated) {
            this.isFromRelated = false;
            this.recyclerView.getHandler().post((Runnable)new BarkerShowDetailsFrag$5(this));
        }
        if (this.previousRelatedTitleState != null && this.previousRelatedTitleState.recyclerViewState != null) {
            this.recyclerView.getLayoutManager().onRestoreInstanceState(this.previousRelatedTitleState.recyclerViewState);
            this.previousRelatedTitleState = null;
        }
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                this.parallaxScroller = DetailsPageParallaxScrollListener.createDefault(this.spinner, this.recyclerView, new View[0], (View)this.spinnerViewGroup, (View)this.detailsViewGroup.getFooterViewGroup());
                this.recyclerView.setOnScrollListener(this.parallaxScroller);
                return this.parallaxScroller;
            }
        }
        return null;
    }
    
    protected void setupDismissClick() {
        if (this.rootContainer != null && this.getActivity() != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new BarkerShowDetailsFrag$1(this));
        }
    }
    
    @Override
    protected void setupRecyclerView() {
        super.setupRecyclerView();
        if (this.recyclerView != null) {
            this.recyclerView.setBackgroundColor(-1);
        }
    }
    
    @SuppressLint({ "InlinedApi" })
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new BarkerShowDetailsFrag$BarkerEpisodesAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.addCopyrightAsFooter();
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.removeItemDecoration(this.innerGridDecoration);
        this.innerGridDecoration = new ItemDecorationBarkerGrid((Context)this.getActivity(), this.getNumColumns());
        this.recyclerView.addItemDecoration(this.innerGridDecoration);
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        if (this.getActivity() == null) {
            return;
        }
        if (this.showDetailsOnLaunch) {
            final int numColumns = this.getNumColumns();
            final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), numColumns);
            layoutManager.setSpanSizeLookup(new BarkerShowDetailsFrag$2(this, numColumns));
            this.recyclerView.setLayoutManager(layoutManager);
            this.recyclerView.getLayoutParams().width = BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity());
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
        this.invalidateRecyclerView();
        if (this.currentEpisodes != null && this.currentEpisodes.size() > 0) {
            this.detailsViewGroup.getFooterViewGroup().setVisibility(0);
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
        this.showRelated = true;
        this.invalidateRecyclerView();
        if (this.showDetails != null && this.showDetails.getSimilars() != null) {
            this.detailsViewGroup.getFooterViewGroup().setVisibility(8);
            this.episodesAdapter.setItems(this.showDetails.getSimilars(), 1, this.viewCreatorSims);
            this.leWrapper.hide(false);
            this.onLoaded(CommonStatus.OK);
        }
    }
    
    @Override
    protected void showStandardViews() {
        Log.v("BarkerShowDetailsFrag", "showStandardViews()");
        this.postSetSpinnerSelectionRunnable();
        if (this.showRelated) {
            ((BarkerVideoDetailsViewGroup)this.detailsViewGroup).performClickOnRelatedTitles();
        }
    }
    
    protected void showViews() {
        Log.v("BarkerShowDetailsFrag", "animateIn()");
        if (this.recyclerView != null && this.rootContainer != null && this.getActivity() != null) {
            if (!this.supressAnimateIn) {
                this.animateIn();
                return;
            }
            if (!this.isListVisible()) {
                Log.v("BarkerShowDetailsFrag", "Showing recycler view");
                this.recyclerView.setVisibility(0);
            }
            this.recyclerView.setAlpha(1.0f);
            if (!this.fromSameActivityType && this.showRecyclerBackground && this.showDetailsOnLaunch && this.fragBackground != null) {
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
        if (this.getActivity() != null) {
            super.updateSeasonData(list);
            if (!this.showDetailsOnLaunch && this.showDetails != null) {
                this.seasonsDialogAdapter.updateSeasonData(list, this.showDetails.getCurrentSeasonNumber());
            }
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        if (this.getActivity() == null) {
            return;
        }
        if (this.getActivity() instanceof DetailsActivity) {
            ((DetailsActivity)this.getActivity()).setVideoAndEpisodeIds(showDetails.getId(), showDetails.getCurrentEpisodeId());
        }
        super.updateShowDetails(showDetails);
        if (!this.showRelated) {
            this.showCurrentSeason();
        }
        else {
            this.showRelatedTitles();
        }
        if (this.copyrightView != null && StringUtils.isNotEmpty(showDetails.getCopyright())) {
            this.copyrightView.update(showDetails);
            this.copyrightView.getView().setVisibility(0);
        }
        this.updateTrackId();
        this.setScrollPosition();
        this.setupDismissClick();
    }
}
