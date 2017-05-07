// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.servicemgr.model.details.ShowDetails;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import android.app.Activity;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.servicemgr.model.details.EpisodeDetails;
import com.netflix.mediaclient.servicemgr.SimpleManagerCallback;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.Asset;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.model.Playable;
import org.json.JSONException;
import com.netflix.mediaclient.util.WebApiUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class PlayActionHandler extends BaseNflxHandler
{
    public PlayActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    @Override
    public Response handle() {
        Log.v("NflxHandler", "handlePlayAction starts...");
        final String justUuid = NflxProtocolUtils.extractJustUuid(this.mParamsMap.get("targetid"));
        final NflxProtocolUtils.VideoInfo videoInfo = this.getVideoInfo();
        if (videoInfo == null) {
            Log.e("NflxHandler", "handlePlayAction fails, no video info found!");
            return Response.NOT_HANDLING;
        }
        if (videoInfo.handleWithDelay()) {
            Log.v("NflxHandler", "handlePlayAction ends, handling with delay.");
            return Response.HANDLING_WITH_DELAY;
        }
        if (videoInfo != null) {
            Log.v("NflxHandler", "handlePlayAction, handling.");
            final VideoType videoType = videoInfo.getVideoType();
            if (videoType == VideoType.MOVIE || videoType == VideoType.SHOW) {
                this.playVideo(videoInfo.getCatalogId(), videoType, justUuid, NflxProtocolUtils.getTrackId(this.mParamsMap));
                return Response.HANDLING_WITH_DELAY;
            }
            if (videoType == VideoType.EPISODE) {
                final String episodeId = NflxProtocolUtils.getEpisodeId(this.mParamsMap);
                if (StringUtils.isEmpty(episodeId)) {
                    Log.v("NflxHandler", "no episode id");
                    return Response.NOT_HANDLING;
                }
                this.playVideo(episodeId, VideoType.EPISODE, justUuid, NflxProtocolUtils.getTrackId(this.mParamsMap));
                return Response.HANDLING_WITH_DELAY;
            }
            else if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Can't play video of type: " + videoType);
            }
        }
        return Response.NOT_HANDLING;
    }
    
    @Override
    protected Response handleEpisodeFromTinyUrl(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (!jsonObject.has("id")) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series id! Default to LOLOMO!");
            this.handleHomeAction();
            return Response.HANDLING;
        }
        final WebApiUtils.VideoIds isd = WebApiUtils.extractIsd(null, jsonObject.getString("id"));
        if (isd != null) {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Play episode for: " + isd);
            }
            this.playVideo(String.valueOf(isd.episodeId), VideoType.EPISODE, s, s2);
        }
        else {
            Log.e("NflxHandler", "Video ID not found, return to LOLOMO");
            this.handleHomeAction();
        }
        return Response.HANDLING;
    }
    
    protected Response handleMovieFromTinyUrl(final String s, final JSONObject jsonObject, final String s2, final String s3) {
        this.playVideo(s, VideoType.MOVIE, s2, s3);
        return Response.HANDLING_WITH_DELAY;
    }
    
    @Override
    protected Response handleMovieFromTinyUrl(final JSONObject jsonObject, final String s, final String s2) throws JSONException {
        if (!jsonObject.has("id")) {
            Log.e("NflxHandler", "It should be movie JSON, failed to get ID URL! Default to LOLOMO!");
            this.handleHomeAction();
            return Response.HANDLING;
        }
        final String string = jsonObject.getString("id");
        final String videoIdFromUri = NflxProtocolUtils.getVideoIdFromUri(string, "movies/");
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Handling movie from tiny URL. Expanded to: " + string + ". Extracted video id: " + videoIdFromUri);
        }
        if (videoIdFromUri != null) {
            this.playVideo(videoIdFromUri, VideoType.MOVIE, s, s2);
            return Response.HANDLING_WITH_DELAY;
        }
        Log.e("NflxHandler", "Video ID not found, return to LOLOMO");
        this.handleHomeAction();
        return Response.HANDLING;
    }
    
    protected void play(final Playable playable, final String dialUuidAsCurrentTarget, final PlayContext playContext) {
        if (StringUtils.isEmpty(dialUuidAsCurrentTarget)) {
            Log.d("NflxHandler", "Starting local playback");
            PlayerActivity.playVideo(this.mActivity, playable, playContext);
            return;
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Remote play required on target " + dialUuidAsCurrentTarget);
        }
        final IMdx mdx = this.mActivity.getServiceManager().getMdx();
        if (mdx == null) {
            Log.d("NflxHandler", "MDX is null, go local playback");
        }
        else {
            Log.d("NflxHandler", "MDX exist, check if target is available");
            if (mdx.setDialUuidAsCurrentTarget(dialUuidAsCurrentTarget)) {
                this.handleHomeAction();
                PlaybackLauncher.startPlaybackForceRemote(this.mActivity, Asset.create(playable, playContext, !PlayerActivity.PIN_VERIFIED));
                return;
            }
            Log.d("NflxHandler", "MDX does not know target dial UUID, go local playback");
        }
        PlayerActivity.playVideo(this.mActivity, playable, playContext);
    }
    
    protected void playVideo(final String s, final VideoType videoType, final String s2, final String s3) {
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", String.format("Playing video: %s, videoType: %s", s, videoType));
        }
        if (VideoType.MOVIE.equals(videoType)) {
            this.mActivity.getServiceManager().getBrowse().fetchMovieDetails(s, new FetchPlayableCallback(s2));
        }
        else {
            if (VideoType.EPISODE.equals(videoType)) {
                this.mActivity.getServiceManager().getBrowse().fetchEpisodeDetails(s, new FetchPlayableCallback(s2));
                return;
            }
            if (VideoType.SHOW.equals(videoType)) {
                this.mActivity.getServiceManager().getBrowse().fetchShowDetails(s, null, new FetchPlayableCallback(s2));
            }
        }
    }
    
    class FetchPlayableCallback extends SimpleManagerCallback
    {
        private final String trackId;
        final /* synthetic */ String val$targetDialUuid;
        
        FetchPlayableCallback(final String trackId, final String val$targetDialUuid) {
            this.val$targetDialUuid = val$targetDialUuid;
            this.trackId = trackId;
        }
        
        @Override
        public void onEpisodeDetailsFetched(final EpisodeDetails episodeDetails, final Status status) {
            if (status.isSucces()) {
                PlayActionHandler.this.play(episodeDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
            }
            NflxProtocolUtils.reportDelayedResponseHandled(PlayActionHandler.this.mActivity);
        }
        
        @Override
        public void onMovieDetailsFetched(final MovieDetails movieDetails, final Status status) {
            if (status.isSucces()) {
                PlayActionHandler.this.play(movieDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
            }
            NflxProtocolUtils.reportDelayedResponseHandled(PlayActionHandler.this.mActivity);
        }
        
        @Override
        public void onShowDetailsFetched(final ShowDetails showDetails, final Status status) {
            if (status.isSucces()) {
                PlayActionHandler.this.play(showDetails.getPlayable(), this.val$targetDialUuid, NflxProtocolUtils.getPlayContext(this.trackId));
            }
            NflxProtocolUtils.reportDelayedResponseHandled(PlayActionHandler.this.mActivity);
        }
    }
}
