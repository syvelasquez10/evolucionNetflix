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
import com.netflix.mediaclient.ui.kubrick.KubrickUtils;
import com.netflix.mediaclient.android.widget.NetflixActionBar;
import android.view.View$OnClickListener;
import android.graphics.drawable.Drawable;
import com.netflix.mediaclient.util.api.Api16Util;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import com.netflix.mediaclient.android.widget.LoadingAndErrorWrapper;
import java.util.Stack;
import android.view.View;
import com.netflix.mediaclient.ui.details.IHandleBackPress;
import com.netflix.mediaclient.ui.details.MovieDetailsFrag;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Video;
import android.content.Context;
import com.netflix.mediaclient.util.DeviceUtils;
import com.netflix.mediaclient.ui.common.PlayContextProvider;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class KubrickMovieDetailsFrag$KubrickRelatedVideoDetailsClickListener extends KubrickVideoDetailsClickListener
{
    final /* synthetic */ KubrickMovieDetailsFrag this$0;
    
    public KubrickMovieDetailsFrag$KubrickRelatedVideoDetailsClickListener(final KubrickMovieDetailsFrag this$0, final NetflixActivity netflixActivity, final PlayContextProvider playContextProvider) {
        this.this$0 = this$0;
        super(netflixActivity, playContextProvider);
    }
    
    private void saveCurrentTitleState() {
        this.this$0.relatedTitlesHistory.push(new RelatedTitleState(this.this$0.videoId, this.this$0.recyclerView.getLayoutManager().onSaveInstanceState(), 0, DeviceUtils.getBasicScreenOrientation((Context)this.this$0.getActivity())));
    }
    
    @Override
    protected void launchDetailsActivity(final NetflixActivity netflixActivity, final Video video, final PlayContext playContext) {
        if (video.getType() == VideoType.MOVIE) {
            this.this$0.leWrapper.showLoadingView(false);
            this.saveCurrentTitleState();
            this.this$0.videoId = video.getId();
            this.this$0.fetchMovieData();
            return;
        }
        super.launchDetailsActivity(netflixActivity, video, playContext);
    }
}
