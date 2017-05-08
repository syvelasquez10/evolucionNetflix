// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker_kids.details;

import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.barker.details.BarkerVideoDetailsViewGroup;
import android.view.View;
import com.netflix.mediaclient.ui.barker.details.BarkerHelper;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.Log;
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
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import com.netflix.mediaclient.ui.barker.details.BarkerShowDetailsFrag;

public class BarkerKidsShowDetailsFrag extends BarkerShowDetailsFrag
{
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    private static final String TAG = "KubrickKidsShowDetailsFrag";
    boolean isSeasonUserSelected;
    protected ItemDecorationUniformPadding itemDecoration;
    protected RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    
    public BarkerKidsShowDetailsFrag() {
        this.viewCreatorEpisodes = new BarkerKidsShowDetailsFrag$1(this);
    }
    
    public static Fragment create(final String s) {
        final BarkerKidsShowDetailsFrag barkerKidsShowDetailsFrag = new BarkerKidsShowDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", s);
        arguments.putBoolean("extra_show_details", true);
        barkerKidsShowDetailsFrag.setArguments(arguments);
        return (Fragment)barkerKidsShowDetailsFrag;
    }
    
    @Override
    protected int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131427788);
    }
    
    @SuppressLint({ "ResourceAsColor" })
    @Override
    protected ViewGroup createSeasonsSelectorGroup() {
        final ViewGroup seasonsSelectorGroup = super.createSeasonsSelectorGroup();
        this.setSpinnerBackground(this.getResources().getColor(2131755146));
        final SeasonsSpinnerAdapter seasonsSpinnerAdapter = (SeasonsSpinnerAdapter)this.spinner.getAdapter();
        if (seasonsSpinnerAdapter != null) {
            seasonsSpinnerAdapter.setDropDownBackgroundColor(2131755284);
            seasonsSpinnerAdapter.setDropDownTextColor(2131755146);
        }
        return seasonsSelectorGroup;
    }
    
    @Override
    protected int getBackgroundResource() {
        return 2131755265;
    }
    
    @Override
    protected int getNumColumns() {
        return this.getActivity().getResources().getInteger(2131558420);
    }
    
    @Override
    protected int getRecyclerViewShadowWidth() {
        return KidsUtils.getDetailsPageContentWidth((Context)this.getActivity()) + (int)this.getResources().getDimension(2131427777) * 2;
    }
    
    @Override
    protected int getlayoutId() {
        return 2130903175;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new BarkerKidsShowDetailsFrag$BarkerKidsShowDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.hideRelatedTitle();
    }
    
    protected void setSpinnerBackground(final int n) {
        final Drawable drawable = this.getResources().getDrawable(2130837630);
        drawable.setColorFilter(n, PorterDuff$Mode.MULTIPLY);
        final LayerDrawable layerDrawable = (LayerDrawable)this.getResources().getDrawable(2130838015);
        final Drawable drawable2 = layerDrawable.getDrawable(1);
        if (drawable2 != null) {
            drawable2.setColorFilter(n, PorterDuff$Mode.MULTIPLY);
        }
        this.spinner.setBackground((Drawable)layerDrawable, drawable);
    }
    
    @Override
    protected void setSpinnerSelection() {
        this.setSeasonIndex();
        if (this.currSeasonIndex < 0) {
            Log.v("KubrickKidsShowDetailsFrag", "No valid season index found");
        }
        else if (this.spinner.getSelectedItemPosition() != this.currSeasonIndex) {
            if (Log.isLoggable()) {
                Log.v("KubrickKidsShowDetailsFrag", "Setting current season to: " + this.currSeasonIndex);
            }
            this.spinner.setNonTouchSelection(this.currSeasonIndex);
        }
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity && DeviceUtils.isTabletByContext((Context)this.getActivity())) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final ViewGroup footerViewGroup = this.detailsViewGroup.getFooterViewGroup();
                if (BarkerHelper.isInTest((Context)this.getActivity())) {
                    this.parallaxScroller = new KidsParallax(this.spinner, this.getRecyclerView(), new View[0], (View)this.spinnerViewGroup, (View)footerViewGroup);
                }
                else {
                    this.parallaxScroller = new KidsParallax(this.spinner, this.getRecyclerView(), new View[] { this.detailsViewGroup.getHeroImage(), ((BarkerVideoDetailsViewGroup)this.detailsViewGroup).getHeroImage2() }, (View)this.spinnerViewGroup, (View)footerViewGroup);
                }
                this.getRecyclerView().setOnScrollListener(this.parallaxScroller);
                this.parallaxScroller.setOnScrollStateChangedListener(new BarkerKidsShowDetailsFrag$2(this));
                return this.parallaxScroller;
            }
        }
        return null;
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new BarkerKidsShowDetailsFrag$BarkerKidsAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.removeItemDecoration((RecyclerView$ItemDecoration)this.itemDecoration);
        this.itemDecoration = new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131427752), this.getNumColumns());
        this.recyclerView.addItemDecoration((RecyclerView$ItemDecoration)this.itemDecoration);
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        super.setupRecyclerViewLayoutManager();
        this.recyclerView.getLayoutParams().width = KidsUtils.getDetailsPageContentWidth((Context)this.getActivity());
    }
    
    @SuppressLint({ "ResourceAsColor" })
    @Override
    protected void setupSeasonsSpinnerAdapter() {
        final SeasonsSpinnerAdapter adapter = new SeasonsSpinnerAdapter(this.getNetflixActivity(), new BarkerKidsShowDetailsFrag$3(this));
        adapter.setItemBackgroundColor(2131755146);
        this.spinner.setAdapter((SpinnerAdapter)adapter);
    }
    
    @Override
    protected void showStandardViews() {
        if (this.heroSlideshow != null) {
            this.heroSlideshow.start();
        }
    }
    
    @Override
    public void switchSeason(final int n, final boolean isSeasonUserSelected) {
        this.isSeasonUserSelected = isSeasonUserSelected;
        ((BarkerKidsShowDetailsFrag$BarkerKidsAdapter)this.episodesAdapter).updateEpisodeStartIndex(((SeasonsSpinnerAdapter)this.spinner.getAdapter()).tryGetEpisodeIndexBySeasonNumber(n));
        super.switchSeason(n, isSeasonUserSelected);
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails) {
        super.updateShowDetails(showDetails);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
    }
}
