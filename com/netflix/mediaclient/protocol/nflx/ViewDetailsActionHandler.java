// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.NflxProtocolUtils$VideoInfo;
import android.app.Activity;
import com.netflix.mediaclient.ui.details.DetailsActivityLauncher;
import com.netflix.mediaclient.servicemgr.model.VideoType;
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
        if (Log.isLoggable("NflxHandler", 2)) {
            Log.v("NflxHandler", "Action: " + action + ", actionToken: " + actionToken);
        }
        if (videoType == VideoType.EPISODE) {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Showing details for episode: " + catalogId + ", type: " + videoType + ", show: " + videoInfo.getShowId());
            }
            DetailsActivityLauncher.showEpisodeDetails(this.mActivity, videoInfo.getShowId(), catalogId, NflxProtocolUtils.getPlayContext(trackId), action, actionToken);
        }
        else {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Showing details for: " + catalogId + ", type: " + videoType);
            }
            DetailsActivityLauncher.show(this.mActivity, videoType, catalogId, "", NflxProtocolUtils.getPlayContext(trackId), action, actionToken);
        }
        return NflxHandler$Response.HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleEpisodeFromTinyUrl(JSONObject optJSONObject, String optString, final String s) {
        optString = optJSONObject.optString("id");
        if (StringUtils.isEmpty(optString)) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get id! Default to LOLOMO!");
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        optJSONObject = optJSONObject.optJSONObject("title_series");
        if (optJSONObject == null) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series! Default to LOLOMO!");
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        if (!optJSONObject.has("id")) {
            Log.e("NflxHandler", "It should be episode JSON, failed to get title series id! Default to LOLOMO!");
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        final String string = optJSONObject.getString("id");
        if (StringUtils.isEmpty(string)) {
            Log.e("NflxHandler", "It should be episode, failed to get showIdUri! Default to LOLOMO!");
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        final String id = NflxProtocolUtils.extractId(string);
        if (StringUtils.isEmpty(id)) {
            Log.e("NflxHandler", "It should be episode, failed to get show id from url! Default to LOLOMO! Url was: " + string);
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        final String id2 = NflxProtocolUtils.extractId(optString);
        if (StringUtils.isEmpty(id2)) {
            Log.e("NflxHandler", "It should be episode, failed to get episode id from url! Default to show SDP! Url was: " + optString);
            DetailsActivityLauncher.show(this.mActivity, VideoType.SHOW, id, "", NflxProtocolUtils.getPlayContext(s), this.getAction(), this.getActionToken());
            return NflxHandler$Response.HANDLING;
        }
        if (Log.isLoggable("NflxHandler", 3)) {
            Log.d("NflxHandler", "Handling episode from tiny URL. Expanded to: episodeId " + id2 + " and showId " + id);
            Log.d("NflxHandler", "Expanded from: episodeIdUri " + optString + " and shodIdUri " + string);
            Log.v("NflxHandler", "Showing SDP");
        }
        DetailsActivityLauncher.showEpisodeDetails(this.mActivity, id, id2, NflxProtocolUtils.getPlayContext(s), this.getAction(), this.getActionToken());
        return NflxHandler$Response.HANDLING;
    }
    
    @Override
    protected NflxHandler$Response handleMovieFromTinyUrl(final JSONObject jsonObject, final String s, final String s2) {
        if (!jsonObject.has("id")) {
            Log.e("NflxHandler", "It should be movie JSON, failed to get ID URL! Default to LOLOMO!");
            this.handleHomeAction();
            return NflxHandler$Response.HANDLING;
        }
        final String videoIdFromUri = NflxProtocolUtils.getVideoIdFromUri(jsonObject.getString("id"), "movies/");
        if (videoIdFromUri != null) {
            if (Log.isLoggable("NflxHandler", 2)) {
                Log.v("NflxHandler", "Showing MDP for: " + videoIdFromUri);
            }
            DetailsActivityLauncher.show(this.mActivity, VideoType.MOVIE, videoIdFromUri, "", NflxProtocolUtils.getPlayContext(s2), this.getAction(), this.getActionToken());
        }
        else {
            Log.e("NflxHandler", "Video ID not found, return to LOLOMO");
            this.handleHomeAction();
        }
        return NflxHandler$Response.HANDLING;
    }
}
