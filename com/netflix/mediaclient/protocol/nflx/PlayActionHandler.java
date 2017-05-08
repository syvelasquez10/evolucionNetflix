// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import org.json.JSONObject;
import com.netflix.mediaclient.util.NflxProtocolUtils$VideoInfo;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
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
    public NflxHandler$Response handle() {
        Log.v("NflxHandler", "handlePlayAction starts...");
        final String justUuid = NflxProtocolUtils.extractJustUuid(this.mParamsMap.get("targetid"));
        final NflxProtocolUtils$VideoInfo videoInfo = this.getVideoInfo();
        if (videoInfo == null) {
            Log.e("NflxHandler", "handlePlayAction fails, no video info found!");
            return NflxHandler$Response.NOT_HANDLING;
        }
        if (videoInfo.handleWithDelay()) {
            Log.v("NflxHandler", "handlePlayAction ends, handling with delay.");
            return NflxHandler$Response.HANDLING_WITH_DELAY;
        }
        if (videoInfo != null) {
            Log.v("NflxHandler", "handlePlayAction, handling.");
            final VideoType videoType = videoInfo.getVideoType();
            if (videoType == VideoType.MOVIE || videoType == VideoType.SHOW) {
                this.playVideo(videoInfo.getCatalogId(), videoType, justUuid, NflxProtocolUtils.getTrackId(this.mParamsMap));
                return NflxHandler$Response.HANDLING_WITH_DELAY;
            }
            if (videoType == VideoType.EPISODE) {
                final String episodeId = NflxProtocolUtils.getEpisodeId(this.mParamsMap);
                if (StringUtils.isEmpty(episodeId)) {
                    Log.v("NflxHandler", "no episode id");
                    return NflxHandler$Response.NOT_HANDLING;
                }
                this.playVideo(episodeId, VideoType.EPISODE, justUuid, NflxProtocolUtils.getTrackId(this.mParamsMap));
                return NflxHandler$Response.HANDLING_WITH_DELAY;
            }
            else if (Log.isLoggable()) {
                Log.v("NflxHandler", "Can't play video of type: " + videoType);
            }
        }
        return NflxHandler$Response.NOT_HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleEpisodeFromTinyUrl(final String s, final String s2, final String s3) {
        if (Log.isLoggable()) {
            Log.v("NflxHandler", "Play episode for: " + s);
        }
        this.playVideo(String.valueOf(s), VideoType.EPISODE, s2, s3);
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
    
    @Override
    protected NflxHandler$Response handleMovieFromTinyUrl(final String s, final String s2, final String s3) {
        if (Log.isLoggable()) {
            Log.d("NflxHandler", "Handling movie from tiny URL. Extracted video id: " + s);
        }
        if (s != null) {
            this.playVideo(s, VideoType.MOVIE, s2, s3);
            return NflxHandler$Response.HANDLING_WITH_DELAY;
        }
        Log.e("NflxHandler", "Video ID not found, return to LOLOMO");
        this.handleHomeAction();
        return NflxHandler$Response.HANDLING;
    }
    
    protected NflxHandler$Response handleMovieFromTinyUrl(final String s, final JSONObject jsonObject, final String s2, final String s3) {
        this.playVideo(s, VideoType.MOVIE, s2, s3);
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
    
    protected void play(final Playable playable, final String dialUuidAsCurrentTarget, final PlayContext playContext) {
        if (StringUtils.isEmpty(dialUuidAsCurrentTarget)) {
            Log.d("NflxHandler", "Starting local playback");
            PlaybackLauncher.playVideo(this.mActivity, playable, playContext);
            return;
        }
        if (Log.isLoggable()) {
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
                PlaybackLauncher.startPlaybackForceRemote(this.mActivity, Asset.create(playable, playContext, false));
                return;
            }
            Log.d("NflxHandler", "MDX does not know target dial UUID, go local playback");
        }
        PlaybackLauncher.playVideo(this.mActivity, playable, playContext);
    }
    
    protected void playVideo(final String s, final VideoType videoType, final String s2, final String s3) {
        if (Log.isLoggable()) {
            Log.v("NflxHandler", String.format("Playing video: %s, videoType: %s", s, videoType));
        }
        if (VideoType.MOVIE.equals(videoType)) {
            this.mActivity.getServiceManager().getBrowse().fetchMovieDetails(s, null, new PlayActionHandler$1FetchPlayableCallback(s2));
        }
        else {
            if (VideoType.EPISODE.equals(videoType)) {
                this.mActivity.getServiceManager().getBrowse().fetchEpisodeDetails(s, null, new PlayActionHandler$1FetchPlayableCallback(s2));
                return;
            }
            if (VideoType.SHOW.equals(videoType)) {
                this.mActivity.getServiceManager().getBrowse().fetchShowDetails(s, null, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new PlayActionHandler$1FetchPlayableCallback(s2));
            }
        }
    }
}
