// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import com.netflix.mediaclient.util.DeviceUtils;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.kubrick.BarkerUtils;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
import android.content.Context;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.support.v7.widget.RecyclerView;
import java.util.Stack;
import com.netflix.mediaclient.util.ItemDecorationBarkerGrid;
import android.view.View;
import com.netflix.mediaclient.ui.details.CopyrightView;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class BarkerMovieDetailsFrag extends MovieDetailsFrag implements IHandleBackPress
{
    private static final int ANIMATE_IN_DURATION_MS = 500;
    private static final String TAG = "BarkerMovieDetailsFrag";
    private BarkerHelper$BarkerBars barker;
    private CopyrightView copyrightView;
    private View fragBackground;
    private boolean fromSameActivityType;
    private ItemDecorationBarkerGrid innerGridDecoration;
    private boolean isFromRelated;
    RelatedTitleState previousRelatedTitleState;
    private Stack<RelatedTitleState> relatedTitlesHistory;
    private View rootContainer;
    private boolean showRecyclerBackground;
    private boolean supressAnimateIn;
    
    public BarkerMovieDetailsFrag() {
        this.supressAnimateIn = false;
        this.relatedTitlesHistory = new Stack<RelatedTitleState>();
    }
    
    private void addCopyrightAsFooter() {
        if (this.adapter != null && this.getActivity() != null) {
            this.copyrightView = CopyrightView.create(null, (Context)this.getActivity());
            if (this.copyrightView != null) {
                this.copyrightView.setGravityAsCenter();
                this.adapter.addFooterView(this.copyrightView.getView());
            }
        }
    }
    
    public static BarkerMovieDetailsFrag create(final String s) {
        final BarkerMovieDetailsFrag barkerMovieDetailsFrag = new BarkerMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        barkerMovieDetailsFrag.setArguments(arguments);
        return barkerMovieDetailsFrag;
    }
    
    private void restorePreviousRelatedTitle() {
        this.previousRelatedTitleState = this.relatedTitlesHistory.pop();
        if (this.previousRelatedTitleState != null) {
            this.videoId = this.previousRelatedTitleState.titleId;
            this.fetchMovieData();
        }
    }
    
    private void setSameActivity() {
        if (this.getActivity() != null) {
            this.fromSameActivityType = this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false);
        }
    }
    
    private void setupBackground(final View view) {
        if (view == null) {
            return;
        }
        if (this.fromSameActivityType) {
            Api16Util.setBackgroundDrawableCompat(view, null);
            return;
        }
        this.setupRecyclerShadow();
    }
    
    private void setupDismissClick() {
        if (this.rootContainer != null && this.getActivity() != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new BarkerMovieDetailsFrag$2(this));
        }
    }
    
    private void updateTrackId(final MovieDetails movieDetails) {
        if (this.getActivity() instanceof DetailsActivity) {
            BarkerUtils.updateTrackId((DetailsActivity)this.getActivity(), movieDetails, this.relatedTitlesHistory, this.previousRelatedTitleState);
        }
    }
    
    protected void animateIn() {
        final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
        this.recyclerView.animate().alpha(1.0f).setDuration(500L);
        netflixActionBar.setAlphaWithAnimation(1.0f, 500);
        if (!this.fromSameActivityType && this.showRecyclerBackground) {
            this.fragBackground.setVisibility(0);
            this.fragBackground.animate().alpha(1.0f).setStartDelay(500L).setDuration(500L);
        }
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131689870);
        this.fragBackground = view.findViewById(2131689882);
    }
    
    @Override
    protected int getNumColumns() {
        return this.barker.getNumberOfSims();
    }
    
    protected int getRecyclerViewShadowWidth() {
        if (this.getActivity() == null) {
            return 0;
        }
        return BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131362181) * 2;
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
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new BarkerVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((BarkerVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        this.barker = new BarkerHelper$BarkerBars((Context)this.getActivity());
        this.supressAnimateIn = (bundle != null);
        if (bundle != null) {
            RelatedTitleState$RestoreInstanceState.invoke(bundle, this.relatedTitlesHistory);
            if (!this.relatedTitlesHistory.empty()) {
                this.previousRelatedTitleState = this.relatedTitlesHistory.pop();
                this.videoId = this.previousRelatedTitleState.titleId;
            }
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.setSameActivity();
        this.setupBackground(onCreateView);
        return onCreateView;
    }
    
    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (!this.relatedTitlesHistory.empty() && this.getActivity() != null) {
            this.relatedTitlesHistory.push(new RelatedTitleState(this.getVideoId(), this.recyclerView.getLayoutManager().onSaveInstanceState(), 0, DeviceUtils.getBasicScreenOrientation((Context)this.getActivity()), ((DetailsActivity)this.getActivity()).getPlayContext()));
            RelatedTitleState$SaveInstanceState.invoke(bundle, this.relatedTitlesHistory);
        }
    }
    
    protected void setScrollPosition() {
        if (this.isFromRelated) {
            this.isFromRelated = false;
            this.recyclerView.getHandler().post((Runnable)new BarkerMovieDetailsFrag$1(this));
        }
        if (this.previousRelatedTitleState != null && this.previousRelatedTitleState.recyclerViewState != null) {
            this.recyclerView.getLayoutManager().onRestoreInstanceState(this.previousRelatedTitleState.recyclerViewState);
            this.previousRelatedTitleState = null;
        }
    }
    
    @Override
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.recyclerView != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                this.parallaxScroller = DetailsPageParallaxScrollListener.createDefault(null, this.recyclerView, new View[0], null, null);
                this.recyclerView.setOnScrollListener(this.parallaxScroller);
            }
        }
    }
    
    protected void setupRecyclerShadow() {
        if (this.getActivity() != null && BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity()) < DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) && this.fragBackground != null) {
            this.showRecyclerBackground = true;
            this.fragBackground.getLayoutParams().width = this.getRecyclerViewShadowWidth();
        }
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new BarkerMovieDetailsFrag$BarkerSimilarItemsGridViewAdapter(this, true, this.numColumns)).addHeaderView((View)this.detailsViewGroup);
        this.addCopyrightAsFooter();
        this.recyclerView.setAdapter(this.adapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.innerGridDecoration = new ItemDecorationBarkerGrid((Context)this.getActivity(), this.numColumns);
        this.recyclerView.addItemDecoration(this.innerGridDecoration);
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = BarkerUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        if (this.getActivity() == null) {
            return;
        }
        if (this.getActivity() instanceof DetailsActivity) {
            ((DetailsActivity)this.getActivity()).setVideoAndEpisodeIds(movieDetails.getId(), null);
        }
        super.showDetailsView(movieDetails);
        this.showViews();
        this.setupDismissClick();
        this.updateBookmark(movieDetails);
        this.updateTrackId(movieDetails);
        this.setScrollPosition();
    }
    
    protected void showViews() {
        if (this.recyclerView != null && this.rootContainer != null && this.getNetflixActivity() != null) {
            if (!this.supressAnimateIn) {
                this.animateIn();
                return;
            }
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            this.recyclerView.setAlpha(1.0f);
            netflixActionBar.setAlpha(1.0f);
            if (!this.fromSameActivityType && this.showRecyclerBackground) {
                this.fragBackground.setVisibility(0);
                this.fragBackground.setAlpha(1.0f);
            }
        }
    }
    
    protected void updateBookmark(final MovieDetails movieDetails) {
        final BarkerVideoDetailsViewGroup barkerVideoDetailsViewGroup = (BarkerVideoDetailsViewGroup)this.detailsViewGroup;
        if (movieDetails.getPlayable().getPlayableBookmarkPosition() > 0) {
            barkerVideoDetailsViewGroup.setBookmarkVisibility(0);
            barkerVideoDetailsViewGroup.updateBookmark(movieDetails.getPlayable());
            return;
        }
        barkerVideoDetailsViewGroup.setBookmarkVisibility(8);
    }
}
