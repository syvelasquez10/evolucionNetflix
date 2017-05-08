// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.barker.details;

import android.support.v7.widget.RecyclerView$ItemDecoration;
import android.support.v7.widget.RecyclerView$Adapter;
import android.support.v7.widget.RecyclerView$OnScrollListener;
import com.netflix.mediaclient.ui.details.SeasonsSpinner;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import com.netflix.mediaclient.servicemgr.interface_.details.Similarable;
import com.netflix.mediaclient.ui.barker.BarkerUtils;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
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
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import com.netflix.mediaclient.ui.details.DetailsActivity;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class BarkerMovieDetailsFrag$BarkerRelatedVideoDetailsClickListener extends BarkerVideoDetailsClickListener
{
    final /* synthetic */ BarkerMovieDetailsFrag this$0;
    
    public BarkerMovieDetailsFrag$BarkerRelatedVideoDetailsClickListener(final BarkerMovieDetailsFrag this$0, final NetflixActivity netflixActivity, final PlayContextProvider playContextProvider) {
        this.this$0 = this$0;
        super(netflixActivity, playContextProvider);
    }
    
    private void saveCurrentTitleState() {
        this.this$0.relatedTitlesHistory.push(new RelatedTitleState(this.this$0.videoId, this.this$0.recyclerView.getLayoutManager().onSaveInstanceState(), 0, DeviceUtils.getBasicScreenOrientation((Context)this.this$0.getActivity()), ((DetailsActivity)this.this$0.getActivity()).getPlayContext()));
    }
    
    @Override
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        if (video.getType() == VideoType.MOVIE) {
            this.this$0.leWrapper.showLoadingView(false);
            this.saveCurrentTitleState();
            this.this$0.videoId = video.getId();
            final boolean b = netflixActivity instanceof DetailsActivity;
            if (b) {
                ((DetailsActivity)netflixActivity).startDPTTISession();
            }
            this.this$0.fetchMovieData();
            if (b) {
                ((DetailsActivity)netflixActivity).registerLoadingStatusCallback();
            }
        }
        else {
            super.launchDetailsActivity(netflixActivity, video, playContext);
        }
        this.this$0.isFromRelated = true;
    }
}
