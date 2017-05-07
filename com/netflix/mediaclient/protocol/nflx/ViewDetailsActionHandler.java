// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.util.NflxProtocolUtils$VideoInfo;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.servicemgr.interface_.VideoType;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.ui.details.DetailsActivity$Action;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

class ViewDetailsActionHandler extends BaseNflxHandler
{
    public ViewDetailsActionHandler(final NetflixActivity netflixActivity, final Map<String, String> map) {
        super(netflixActivity, map);
    }
    
    protected DetailsActivity$Action getAction() {
        return null;
    }
    
    protected String getActionToken() {
        return null;
    }
    
    @Override
    public NflxHandler$Response handle() {
        Log.v("NflxHandler", "handleViewDetailsAction starts...");
        final NflxProtocolUtils$VideoInfo videoInfo = this.getVideoInfo();
        if (videoInfo == null) {
            Log.e("NflxHandler", "handleViewDetailsAction fails, no video info found!");
            return NflxHandler$Response.NOT_HANDLING;
        }
        if (videoInfo.handleWithDelay()) {
            Log.v("NflxHandler", "handleViewDetailsAction ends, handling with delay.");
            return NflxHandler$Response.HANDLING_WITH_DELAY;
        }
        final VideoType videoType = videoInfo.getVideoType();
        final String catalogId = videoInfo.getCatalogId();
        final String trackId = NflxProtocolUtils.getTrackId(this.mParamsMap);
        final DetailsActivity$Action action = this.getAction();
        final String actionToken = this.getActionToken();
        if (Log.isLoggable()) {
            Log.v("NflxHandler", "Action: " + action + ", actionToken: " + actionToken);
        }
        if (videoType == VideoType.EPISODE) {
            if (Log.isLoggable()) {
                Log.v("NflxHandler", "Showing details for episode: " + catalogId + ", type: " + videoType + ", show: " + videoInfo.getShowId());
            }
            DetailsActivityLauncher.showEpisodeDetails(this.mActivity, videoInfo.getShowId(), catalogId, NflxProtocolUtils.getPlayContext(trackId), action, actionToken);
        }
        else {
            if (Log.isLoggable()) {
                Log.v("NflxHandler", "Showing details for: " + catalogId + ", type: " + videoType);
            }
            DetailsActivityLauncher.show(this.mActivity, videoType, catalogId, "", NflxProtocolUtils.getPlayContext(trackId), action, actionToken, this.getClass().getSimpleName() + ":mov");
        }
        return NflxHandler$Response.HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleEpisodeFromTinyUrl(final String s, final String s2, final String s3) {
        DetailsActivityLauncher.show(this.mActivity, VideoType.SHOW, s, "", NflxProtocolUtils.getPlayContext(s3), this.getAction(), this.getActionToken(), this.getClass().getSimpleName() + ":tinyUrlEp");
        return NflxHandler$Response.HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleMovieFromTinyUrl(final String s, final String s2, final String s3) {
        DetailsActivityLauncher.show(this.mActivity, VideoType.MOVIE, s, "", NflxProtocolUtils.getPlayContext(s3), this.getAction(), this.getActionToken(), this.getClass().getSimpleName() + ":tinyUrlMov");
        return NflxHandler$Response.HANDLING;
    }
}
