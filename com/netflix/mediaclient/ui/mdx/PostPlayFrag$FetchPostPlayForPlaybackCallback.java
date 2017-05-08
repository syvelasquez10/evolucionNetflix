// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.mdx;

import com.netflix.mediaclient.Log;
import android.os.Bundle;
import android.view.LayoutInflater;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.interface_.Ratable;
import com.netflix.mediaclient.service.mdx.MdxAgent;
import android.text.TextUtils;
import android.view.View$OnClickListener;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlayContext;
import android.view.View;
import com.netflix.mediaclient.servicemgr.ServiceManager;
import android.content.Context;
import com.netflix.mediaclient.service.mdx.MdxAgent$Utils;
import com.netflix.mediaclient.servicemgr.ServiceManagerUtils;
import android.content.Intent;
import com.netflix.mediaclient.ui.details.NetflixRatingBar;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.BroadcastReceiver;
import android.widget.ImageView;
import android.widget.TextView;
import com.netflix.mediaclient.ui.details.NetflixRatingBar$RatingBarDataProvider;
import com.netflix.mediaclient.android.fragment.NetflixFrag;
import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.interface_.details.PostPlayVideosProvider;
import com.netflix.mediaclient.servicemgr.interface_.details.VideoDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.LoggingManagerCallback;

class PostPlayFrag$FetchPostPlayForPlaybackCallback extends LoggingManagerCallback
{
    final /* synthetic */ PostPlayFrag this$0;
    
    public PostPlayFrag$FetchPostPlayForPlaybackCallback(final PostPlayFrag this$0, final String s) {
        this.this$0 = this$0;
        super(s);
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        super.onEpisodeDetailsFetched(episodeDetails, status);
        this.this$0.episodeDetails = episodeDetails;
        this.this$0.updateDetails(episodeDetails);
        this.this$0.showPostPlayViewsForNext();
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        super.onMovieDetailsFetched(movieDetails, status);
        this.this$0.updateDetails(movieDetails);
        this.this$0.showPostPlayViewsForTitleEnd();
    }
    
    @Override
    public void onPostPlayVideosFetched(final PostPlayVideosProvider postPlayVideosProvider, final Status status) {
        super.onPostPlayVideosFetched(postPlayVideosProvider, status);
        if ((postPlayVideosProvider == null || postPlayVideosProvider.getPostPlayVideos() == null || postPlayVideosProvider.getPostPlayVideos().size() == 0) && this.this$0.showTitle != null && this.this$0.video != null) {
            this.this$0.getNetflixActivity().getServiceManager().getBrowse().fetchShowDetails(this.this$0.video.getPlayable().getParentId(), null, false, new PostPlayFrag$FetchPostPlayForPlaybackCallback(this.this$0, "PostPlayFrag"));
        }
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        super.onShowDetailsFetched(showDetails, status);
        this.this$0.updateDetails(showDetails);
        this.this$0.showPostPlayViewsForTitleEnd();
    }
}
