// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import android.annotation.SuppressLint;
import android.widget.SpinnerAdapter;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter$IViewCreator;
import com.netflix.mediaclient.ui.details.SeasonsSpinnerAdapter;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$KubrickEpisodesAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag;

public class KubrickKidsShowDetailsFrag extends KubrickShowDetailsFrag
{
    private static final String EXTRA_SHOW_ID = "extra_show_id";
    
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
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131296476);
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickKidsShowDetailsFrag$KubrickKidsVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.removeRelatedTitle();
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        final DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener = super.setupDetailsPageParallaxScrollListener();
        if (setupDetailsPageParallaxScrollListener != null) {
            setupDetailsPageParallaxScrollListener.setInitialBottomColor(this.recyclerView.getResources().getColor(2131230829));
            setupDetailsPageParallaxScrollListener.setInitialTopColor(this.recyclerView.getResources().getColor(2131230828));
            setupDetailsPageParallaxScrollListener.setOnScrollStateChangedListener(new KubrickKidsShowDetailsFrag$2(this, this.getActivity().findViewById(2131427444)));
        }
        return setupDetailsPageParallaxScrollListener;
    }
    
    @Override
    protected void setupRecyclerView(final View view) {
        this.recyclerView = (RecyclerView)view.findViewById(16908298);
        super.setupRecyclerView(view);
        this.recyclerView.setBackgroundColor(-1);
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesAdapter(this, this.getNetflixActivity(), this, new KubrickKidsShowDetailsFrag$1(this));
        this.recyclerView.setAdapter(this.episodesAdapter);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131296475), this.numColumns));
    }
    
    @SuppressLint({ "ResourceAsColor" })
    @Override
    protected void setupSeasonsSpinnerAdapter() {
        final SeasonsSpinnerAdapter adapter = new SeasonsSpinnerAdapter(this.getNetflixActivity(), new KubrickKidsShowDetailsFrag$3(this));
        adapter.setItemBackgroundColor(2130837877);
        this.spinner.setAdapter((SpinnerAdapter)adapter);
    }
}
