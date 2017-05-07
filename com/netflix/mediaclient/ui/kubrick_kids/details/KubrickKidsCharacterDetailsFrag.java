// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick_kids.details;

import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.ui.details.DetailsPageParallaxScrollListener;
import android.view.View;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import com.netflix.mediaclient.ui.kubrick.details.KubrickShowDetailsFrag$HeroSlideshow;

public class KubrickKidsCharacterDetailsFrag extends KubrickKidsShowDetailsFrag
{
    public static Fragment create(final String s) {
        final KubrickKidsCharacterDetailsFrag kubrickKidsCharacterDetailsFrag = new KubrickKidsCharacterDetailsFrag();
        final Bundle arguments = new Bundle();
        arguments.putString("extra_show_id", "70175666");
        arguments.putBoolean("extra_show_details", true);
        kubrickKidsCharacterDetailsFrag.setArguments(arguments);
        return (Fragment)kubrickKidsCharacterDetailsFrag;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickKidsCharacterDetailsFrag$KubrickKidsVideoDetailsViewGroup(this, (Context)this.getActivity())).removeActionBarDummyView();
        this.detailsViewGroup.removeRelatedTitle();
    }
    
    @Override
    public View onCreateView(final LayoutInflater layoutInflater, final ViewGroup viewGroup, final Bundle bundle) {
        return super.onCreateView(layoutInflater, viewGroup, bundle);
    }
    
    @Override
    protected DetailsPageParallaxScrollListener setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity) {
            final View viewById = this.getActivity().findViewById(2131427444);
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), (View)this.detailsViewGroup.getBackgroundImage(), (View)this.spinnerViewGroup, this.recyclerView.getResources().getColor(2131230827), 0, null);
                this.getRecyclerView().setOnScrollListener(onScrollListener);
                onScrollListener.setInitialBottomColor(this.recyclerView.getResources().getColor(2131230829));
                onScrollListener.setInitialTopColor(this.recyclerView.getResources().getColor(2131230828));
                onScrollListener.setOnScrollStateChangedListener(new KubrickKidsCharacterDetailsFrag$1(this, viewById));
                return onScrollListener;
            }
        }
        return null;
    }
}
