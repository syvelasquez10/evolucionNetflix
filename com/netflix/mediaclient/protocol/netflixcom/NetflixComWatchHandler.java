// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.netflixcom;

import com.netflix.mediaclient.ui.experience.BrowseExperience;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.servicemgr.IMdx;
import com.netflix.mediaclient.ui.common.PlaybackLauncher;
import com.netflix.mediaclient.servicemgr.Asset;
import com.netflix.mediaclient.ui.player.PlayerActivity;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.ui.common.PlayContext;
import com.netflix.mediaclient.servicemgr.interface_.Playable;
import java.util.List;
import com.netflix.mediaclient.servicemgr.ManagerCallback;
import com.netflix.mediaclient.protocol.nflx.NflxHandler$Response;
import com.netflix.mediaclient.android.activity.NetflixActivity;

public class NetflixComWatchHandler implements NetflixComHandler
{
    private static final String TAG = "NetflixComWatchHandler";
    
    private NflxHandler$Response handle(final String s, final String s2, final NetflixActivity netflixActivity, final String s3) {
        netflixActivity.getServiceManager().getBrowse().fetchVideoSummary(s, new NetflixComWatchHandler$1(this, netflixActivity, s, s2, s3));
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
    
    @Override
    public boolean canHandle(final List<String> list) {
        return list.size() > 1;
    }
    
    protected void play(final NetflixActivity netflixActivity, final Playable playable, final String dialUuidAsCurrentTarget, final PlayContext playContext) {
        if (StringUtils.isEmpty(dialUuidAsCurrentTarget)) {
            Log.d("NetflixComWatchHandler", "Starting local playback");
            PlayerActivity.playVideo(netflixActivity, playable, playContext);
            return;
        }
        if (Log.isLoggable()) {
            Log.d("NetflixComWatchHandler", "Remote play required on target " + dialUuidAsCurrentTarget);
        }
        final IMdx mdx = netflixActivity.getServiceManager().getMdx();
        if (mdx == null) {
            Log.d("NetflixComWatchHandler", "MDX is null, go local playback");
        }
        else {
            Log.d("NetflixComWatchHandler", "MDX exist, check if target is available");
            if (mdx.setDialUuidAsCurrentTarget(dialUuidAsCurrentTarget)) {
                NetflixComUtils.startHomeActivity(netflixActivity);
                PlaybackLauncher.startPlaybackForceRemote(netflixActivity, Asset.create(playable, playContext, !PlayerActivity.PIN_VERIFIED));
                return;
            }
            Log.d("NetflixComWatchHandler", "MDX does not know target dial UUID, go local playback");
        }
        PlayerActivity.playVideo(netflixActivity, playable, playContext);
    }
    
    protected void playVideo(final NetflixActivity netflixActivity, final VideoType videoType, final String s, final String s2, final String s3) {
        if (Log.isLoggable()) {
            Log.v("NetflixComWatchHandler", String.format("Playing video: %s, videoType: %s", s, videoType));
        }
        if (VideoType.MOVIE.equals(videoType)) {
            netflixActivity.getServiceManager().getBrowse().fetchMovieDetails(s, new NetflixComWatchHandler$1FetchPlayableCallback(s2));
        }
        else {
            if (VideoType.EPISODE.equals(videoType)) {
                netflixActivity.getServiceManager().getBrowse().fetchEpisodeDetails(s, new NetflixComWatchHandler$1FetchPlayableCallback(s2));
                return;
            }
            if (VideoType.SHOW.equals(videoType)) {
                netflixActivity.getServiceManager().getBrowse().fetchShowDetails(s, null, BrowseExperience.shouldLoadKubrickLeavesInDetails(), new NetflixComWatchHandler$1FetchPlayableCallback(s2));
            }
        }
    }
    
    @Override
    public NflxHandler$Response tryHandle(final NetflixActivity netflixActivity, final List<String> list, final String s) {
        final String s2 = list.get(1);
        String s3 = null;
        if (list.size() > 3) {
            s3 = list.get(3);
        }
        this.handle(s2, s3, netflixActivity, s);
        return NflxHandler$Response.HANDLING_WITH_DELAY;
    }
}
