// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import android.view.ViewGroup$LayoutParams;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
import android.view.View;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;

public class KubrickMovieDetailsFrag extends MovieDetailsFrag
{
    private static final int ANIMATE_IN_DURATION_MS = 500;
    private static final String SAVED_STATE_SUPRESS_ANIMATEIN = "saved_state_supress_animatein";
    private View fragBackground;
    private boolean fromSameActivityType;
    private View rootContainer;
    private boolean showRecyclerBackground;
    private boolean supressAnimateIn;
    
    public KubrickMovieDetailsFrag() {
        this.supressAnimateIn = false;
    }
    
    public static KubrickMovieDetailsFrag create(final String s) {
        final KubrickMovieDetailsFrag kubrickMovieDetailsFrag = new KubrickMovieDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("video_id", s);
        kubrickMovieDetailsFrag.setArguments(arguments);
        return kubrickMovieDetailsFrag;
    }
    
    private void setSameActivity() {
        this.fromSameActivityType = this.getActivity().getIntent().getBooleanExtra("extra_same_activity_type", false);
    }
    
    private void setupBackground(final View view) {
        if (view == null) {
            return;
        }
        if (this.fromSameActivityType) {
            Api16Util.setBackgroundDrawableCompat(view, null);
            return;
        }
        this.setupRecyclerBackground();
    }
    
    private void setupDismissClick() {
        if (this.rootContainer != null) {
            this.rootContainer.setOnClickListener((View$OnClickListener)new KubrickMovieDetailsFrag$1(this));
        }
    }
    
    private void setupRecyclerBackground() {
        if (KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) < DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) && this.fragBackground != null) {
            this.showRecyclerBackground = true;
            this.fragBackground.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity()) + 60;
        }
    }
    
    protected void animateIn() {
        if (this.recyclerView != null && this.rootContainer != null) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            this.recyclerView.animate().alpha(1.0f).setDuration(500L);
            netflixActionBar.setAlphaWithAnimation(1.0f, 500);
            if (!this.fromSameActivityType && this.showRecyclerBackground) {
                this.fragBackground.setVisibility(0);
                this.fragBackground.animate().alpha(1.0f).setStartDelay(500L).setDuration(500L);
            }
        }
    }
    
    @Override
    protected void findViews(final View view) {
        super.findViews(view);
        this.rootContainer = view.findViewById(2131427564);
        this.fragBackground = view.findViewById(2131427565);
    }
    
    @Override
    protected void initDetailsViewGroup(final View view) {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.showRelatedTitle();
        ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).hideDataSelector();
    }
    
    @Override
    public void onCreate(final Bundle bundle) {
        super.onCreate(bundle);
        if (bundle != null) {
            this.supressAnimateIn = bundle.getBoolean("saved_state_supress_animatein", false);
        }
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        final View onCreateView = super.onCreateView(layoutInflater, viewGroup, bundle);
        this.setSameActivity();
        this.setupBackground(onCreateView);
        return onCreateView;
    }
    
    public void onSaveInstanceState(final Bundle bundle) {
        super.onSaveInstanceState(bundle);
        if (this.getActivity().isChangingConfigurations()) {
            bundle.putBoolean("saved_state_supress_animatein", true);
        }
    }
    
    @Override
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131361802);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        (this.adapter = new KubrickMovieDetailsFrag$KubrickSimilarItemsGridViewAdapter(this, this.recyclerView, true, this.numColumns)).addHeaderView((View)this.detailsViewGroup);
        final View view = new View((Context)this.getActivity());
        view.setLayoutParams(new ViewGroup$LayoutParams(-2, this.getResources().getDimensionPixelOffset(2131296450) / 2));
        this.adapter.addFooterView(view);
        this.recyclerView.setAdapter(this.adapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296450), this.numColumns));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    @Override
    protected void showDetailsView(final MovieDetails movieDetails) {
        super.showDetailsView(movieDetails);
        this.showViews();
        this.setupDismissClick();
        this.updateBookmark(movieDetails);
    }
    
    protected void showViews() {
        if (this.recyclerView != null && this.rootContainer != null) {
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
        final KubrickVideoDetailsViewGroup kubrickVideoDetailsViewGroup = (KubrickVideoDetailsViewGroup)this.detailsViewGroup;
        if (movieDetails.getPlayable().getPlayableBookmarkPosition() > 0) {
            kubrickVideoDetailsViewGroup.setBookmarkVisibility(0);
            kubrickVideoDetailsViewGroup.updateBookmark(movieDetails.getPlayable());
            return;
        }
        kubrickVideoDetailsViewGroup.setBookmarkVisibility(8);
    }
}
