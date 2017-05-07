// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.Collection;
import android.support.v7.widget.RecyclerView$LayoutManager;
import android.support.v7.widget.GridLayoutManager$SpanSizeLookup;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView$ItemDecoration;
import com.netflix.mediaclient.util.ItemDecorationUniformPadding;
import android.support.v7.widget.RecyclerView$Adapter;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener$IScrollStateChanged;
import android.view.View;
import com.netflix.mediaclient.ui.DetailsPageParallaxScrollListener;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import android.view.ViewGroup;
import com.netflix.mediaclient.android.fragment.NetflixDialogFrag;
import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.ui.details.VideoDetailsViewGroup;
import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter;
import com.netflix.mediaclient.android.widget.RecyclerViewHeaderAdapter$IViewCreator;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import java.util.List;
import com.netflix.mediaclient.ui.mdx.MdxMiniPlayerFrag$MdxMiniPlayerDialog;
import com.netflix.mediaclient.ui.details.ServiceManagerProvider;
import com.netflix.mediaclient.servicemgr.ManagerStatusListener;
import com.netflix.mediaclient.android.widget.ErrorWrapper$Callback;
import com.netflix.mediaclient.ui.details.EpisodesFrag;

public class KubrickShowDetailsFrag extends EpisodesFrag implements ErrorWrapper$Callback, ManagerStatusListener, ServiceManagerProvider, MdxMiniPlayerFrag$MdxMiniPlayerDialog
{
    private static final String TAG = "KubrickShowDetailsFrag";
    protected List<EpisodeDetails> currentEpisodes;
    private final KubrickShowDetailsFrag$HeroSlideshow heroSlideshow;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorEpisodes;
    RecyclerViewHeaderAdapter$IViewCreator viewCreatorSims;
    
    public KubrickShowDetailsFrag() {
        this.heroSlideshow = new KubrickShowDetailsFrag$HeroSlideshow(this, null);
        this.viewCreatorEpisodes = new KubrickShowDetailsFrag$3(this);
        this.viewCreatorSims = new KubrickShowDetailsFrag$4(this);
    }
    
    private int calculateSpinnerLeftPosition() {
        int n = 0;
        final int detailsPageContentWidth = KubrickUtils.getDetailsPageContentWidth((Context)this.getActivity());
        if (detailsPageContentWidth > 0) {
            n = (DeviceUtils.getScreenWidthInPixels((Context)this.getActivity()) - detailsPageContentWidth) / 2;
        }
        return n + (int)this.getResources().getDimension(2131362005);
    }
    
    public static NetflixDialogFrag create(final String s, final String s2) {
        final KubrickShowDetailsFrag kubrickShowDetailsFrag = new KubrickShowDetailsFrag();
        kubrickShowDetailsFrag.setStyle(1, 2131558696);
        return EpisodesFrag.applyCreateArgs(kubrickShowDetailsFrag, s, s2, true, false);
    }
    
    @Override
    protected ViewGroup createSeasonsSpinnerGroup() {
        final ViewGroup seasonsSpinnerGroup = super.createSeasonsSpinnerGroup();
        seasonsSpinnerGroup.setBackgroundResource(2131296356);
        seasonsSpinnerGroup.setPadding(this.calculateSpinnerLeftPosition(), 0, 0, 0);
        return seasonsSpinnerGroup;
    }
    
    @Override
    protected void initDetailsViewGroup() {
        (this.detailsViewGroup = new KubrickVideoDetailsViewGroup((Context)this.getActivity())).removeActionBarDummyView();
    }
    
    protected int retrieveNumColumns() {
        return this.getActivity().getResources().getInteger(2131427338);
    }
    
    @Override
    protected void setupDetailsPageParallaxScrollListener() {
        if (this.getActivity() != null && this.getRecyclerView() != null && this.getRecyclerView().getContext() instanceof NetflixActivity) {
            final NetflixActionBar netflixActionBar = this.getNetflixActivity().getNetflixActionBar();
            if (netflixActionBar != null) {
                netflixActionBar.hidelogo();
                final DetailsPageParallaxScrollListener onScrollListener = new DetailsPageParallaxScrollListener(this.spinner, this.getRecyclerView(), (View)this.detailsViewGroup.getHeroImage(), null, this.getActivity().getResources().getDrawable(2130837560));
                onScrollListener.setOnScrollStateChangedListener(new KubrickShowDetailsFrag$2(this));
                this.getRecyclerView().setOnScrollListener(onScrollListener);
            }
        }
    }
    
    @Override
    protected void setupRecyclerViewAdapter() {
        this.episodesAdapter = new KubrickShowDetailsFrag$KubrickEpisodesAdapter(this, this.getNetflixActivity(), this, this.viewCreatorEpisodes);
        this.recyclerView.setAdapter(this.episodesAdapter);
        this.episodesAdapter.setViewCreator(this.viewCreatorEpisodes);
    }
    
    @Override
    protected void setupRecyclerViewItemDecoration() {
        this.recyclerView.addItemDecoration(new ItemDecorationUniformPadding(this.getActivity().getResources().getDimensionPixelOffset(2131361878), this.numColumns));
    }
    
    @Override
    protected void setupRecyclerViewLayoutManager() {
        this.numColumns = this.retrieveNumColumns();
        final GridLayoutManager layoutManager = new GridLayoutManager((Context)this.getActivity(), this.numColumns);
        layoutManager.setSpanSizeLookup(new KubrickShowDetailsFrag$1(this));
        this.recyclerView.setLayoutManager(layoutManager);
    }
    
    public void showCurrentSeason() {
        if (this.currentEpisodes != null && this.currentEpisodes.size() > 0) {
            this.detailsViewGroup.removeRelatedTitle();
            this.addSpinnerToDetailsGroup();
            this.episodesAdapter.setItems(this.currentEpisodes, 2, this.viewCreatorEpisodes);
        }
    }
    
    public void showRelatedTitles() {
        if (this.showDetails != null && this.showDetails.getSimilars() != null) {
            this.detailsViewGroup.showRelatedTitle();
            this.episodesAdapter.removeHeaderView((View)this.spinnerViewGroup);
            this.episodesAdapter.setItems(this.showDetails.getSimilars(), 1, this.viewCreatorSims);
        }
    }
    
    @Override
    protected void updateShowDetails(final ShowDetails showDetails, final boolean b) {
        super.updateShowDetails(showDetails, b);
        this.heroSlideshow.start();
    }
}
