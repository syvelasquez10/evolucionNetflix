// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.servicemgr.interface_.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.interface_.details.MovieDetails;
import android.app.Activity;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.interface_.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.android.activity.NetflixActivity;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;

class NetflixComWatchHandler$1FetchPlayableCallback extends SimpleManagerCallback
{
    final /* synthetic */ NetflixComWatchHandler this$0;
    private final String trackId;
    final /* synthetic */ NetflixActivity val$activity;
    final /* synthetic */ String val$targetDialUuid;
    final /* synthetic */ VideoType val$videoType;
    
    NetflixComWatchHandler$1FetchPlayableCallback(final NetflixComWatchHandler this$0, final String trackId, final NetflixActivity val$activity, final VideoType val$videoType, final String val$targetDialUuid) {
        this.this$0 = this$0;
        this.val$activity = val$activity;
        this.val$videoType = val$videoType;
        this.val$targetDialUuid = val$targetDialUuid;
        this.trackId = trackId;
    }
    
    @Override
    public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.resolveSceneAndPlay(this.val$activity, this.val$videoType, episodeDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.val$activity);
    }
    
    @Override
    public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.resolveSceneAndPlay(this.val$activity, this.val$videoType, movieDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.val$activity);
    }
    
    @Override
    public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
        if (status.isSucces()) {
            this.this$0.resolveSceneAndPlay(this.val$activity, this.val$videoType, showDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
        }
        NflxProtocolUtils.reportDelayedResponseHandled(this.val$activity);
    }
}
