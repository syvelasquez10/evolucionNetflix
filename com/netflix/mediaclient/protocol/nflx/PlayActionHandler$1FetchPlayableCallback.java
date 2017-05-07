// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.app.Activity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class PlayActionHandler$1FetchPlayableCallback extends SimpleManagerCallback
{
    final /* synthetic */ PlayActionHandler this$0;
    private final String trackId;
    final /* synthetic */ String val$targetDialUuid;
    
    PlayActionHandler$1FetchPlayableCallback(final PlayActionHandler this$0, final String trackId, final String val$targetDialUuid) {
        this.this$0 = this$0;
        this.val$targetDialUuid = val$targetDialUuid;
        this.trackId = trackId;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.play(episodeDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.this$0.mActivity);
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.play(movieDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.this$0.mActivity);
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.play(showDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.this$0.mActivity);
    }
}
