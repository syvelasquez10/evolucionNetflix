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
import com.netflix.mediaclient.android.activity.NetflixActivity;
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
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;

public class KubrickKidsShowDetailsFrag extends KubrickShowDetailsFrag
{
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    boolean isSeasonUserSelected;
    protected RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    
    public KubrickKidsShowDetailsFrag() {
        this.viewCreatorEpisodes = new KubrickKidsShowDetailsFrag$1(this);
    }
    
    public static Fragment create(final String s) {
        final KubrickKidsShowDetailsFrag kubrickKidsShowDetailsFrag = new KubrickKidsShowDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", s);
        arguments.putBoolean("extra_show_details", true);
        kubrickKidsShowDetailsFrag.setArguments(arguments);
        return (Fragment)kubrickKidsShowDetailsFrag;
    }
    
    @Override
    protected int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131296516);
    }
    
    @SuppressLint({ "ResourceAsColor" })
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        final ViewGroup seasonsSelectorGroup = super.createSeasonsSelectorGroup();
        this.setSpinnerBackground(this.getResources().getColor(2131558489));
        final SeasonsSpinnerAdapter seasonsSpinnerAdapter = (SeasonsSpinnerAdapter)this.spinner.getAdapter();
        if (seasonsSpinnerAdapter != null) {
            seasonsSpinnerAdapter.setDropDownBackgroundColor(2131558592);
            seasonsSpinnerAdapter.setDropDownTextColor(2131558489);
        }
        return seasonsSelectorGroup;
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131296501) * 2;
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903115;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickKidsShowDetailsFrag$KubrickKidsShowDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.hideRelatedTitle();
    }
    
    protected void setSpinnerBackground(final int n) {
        final Drawable drawable = this.getResources().getDrawable(2130837592);
        drawable.setColorFilter(n, PorterDuff$Mode.MULTIPLY);
        final LayerDrawable layerDrawable = (LayerDrawable)this.getResources().getDrawable(2130837776);
        final Drawable drawable2 = layerDrawable.getDrawable(1);
        if (drawable2 != null) {
            drawable2.setColorFilter(n, PorterDuff$Mode.MULTIPLY);
        }
        this.spinner.setBackground((Drawable)layerDrawable, drawable);
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final KidsParallax onScrollListener = new KidsParallax(this, this.spinner, this.getRecyclerView(), new View[] { this.detailsViewGroup.getHeroImage(), ((KubrickVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, (View)this.spinnerViewGroup, (View)this.detailsViewGroup.getFooterViewGroup());
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                onScrollListener.setOnScrollStateChangedListener(new KubrickKidsShowDetailsFrag$2(this));
                return onScrollListener;
            }
        }
        return null;
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickKidsShowDetailsFrag$KubrickKidsAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationEdgePadding(this.getActivity().getResources().getDimensionPixelOffset(2131296515), this.numColumns, 3));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
        this.recyclerView.setAlpha(0.0f);
    }
    
    @SuppressLint({ "ResourceAsColor" })
    @Override
    protected void setupSeasonsSpinnerAdapter() {
        final SeasonsSpinnerAdapter adapter = new SeasonsSpinnerAdapter(this.getNetflixActivity(), new KubrickKidsShowDetailsFrag$3(this));
        adapter.setItemBackgroundColor(2131558489);
        this.spinner.setAdapter((SpinnerAdapter)adapter);
    }
    
    @Override
    protected void showStandardViews() {
        if (this.heroSlideshow != null) {
            this.heroSlideshow.start();
        }
    }
    
    public void showViews() {
        if (this.showDetails != null && new MaturityValidator(this, this.getNetflixActivity(), this.showDetails).isRestricted()) {
            this.leWrapper.hide(false);
            return;
        }
        super.showViews();
    }
    
    @Override
    protected void switchSeason(final int n, final boolean isSeasonUserSelected) {
        this.isSeasonUserSelected = isSeasonUserSelected;
        ((KubrickKidsShowDetailsFrag$KubrickKidsAdapter)this.episodesAdapter).updateEpisodeStartIndex(((SeasonsSpinnerAdapter)this.spinner.getAdapter()).tryGetEpisodeIndexBySeasonNumber(n));
        super.switchSeason(n, isSeasonUserSelected);
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        super.updateShowDetails(showDetails);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
    }
}
