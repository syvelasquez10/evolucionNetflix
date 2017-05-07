// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.util.ThreadUtils;
import org.json.JSONException;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

abstract class BaseNflxHandler implements NflxHandler
{
    protected static final String JSON_CATALOG_TITLE = "catalog_title";
    protected static final String JSON_ID = "id";
    protected static final String JSON_TITLE_SERIES = "title_series";
    protected static final String TAG = "NflxHandler";
    protected NetflixActivity mActivity;
    protected Map<String, String> mParamsMap;
    
    BaseNflxHandler(final NetflixActivity mActivity, final Map<String, String> mParamsMap) {
        this.mActivity = mActivity;
        this.mParamsMap = mParamsMap;
    }
    
    protected NflxProtocolUtils.VideoInfo getVideoInfo() {
        final String s = this.mParamsMap.get("movieid");
        NflxProtocolUtils.VideoInfo videoInfoFromTinyUrl;
        if (StringUtils.isEmpty(s)) {
            videoInfoFromTinyUrl = this.getVideoInfoFromTinyUrl();
        }
        else {
            final NflxProtocolUtils.VideoInfo videoInfoFromMovieIdUri = NflxProtocolUtils.getVideoInfoFromMovieIdUri(s);
            if (videoInfoFromMovieIdUri == null && Log.isLoggable("NflxHandler", 5)) {
                Log.w("NflxHandler", "This should NOT happen! VideoInfo object not returned for video id " + s + ". Default to regular workflow");
            }
            final String episodeId = NflxProtocolUtils.getEpisodeId(this.mParamsMap);
            videoInfoFromTinyUrl = videoInfoFromMovieIdUri;
            if (StringUtils.isNotEmpty(episodeId) && (videoInfoFromTinyUrl = videoInfoFromMovieIdUri) != null) {
                final NflxProtocolUtils.VideoInfo fromEpisode = NflxProtocolUtils.VideoInfo.createFromEpisode(videoInfoFromMovieIdUri.getCatalogId(), episodeId);
                if (fromEpisode != null) {
                    return fromEpisode;
                }
                videoInfoFromTinyUrl = videoInfoFromMovieIdUri;
                if (Log.isLoggable("NflxHandler", 5)) {
                    Log.w("NflxHandler", "VideoInfo object not returned for episode id " + episodeId + ". Default to show");
                    return videoInfoFromMovieIdUri;
                }
            }
        }
        return videoInfoFromTinyUrl;
    }
    
    protected NflxProtocolUtils.VideoInfo getVideoInfoFromTinyUrl() {
        final String targetUrl = NflxProtocolUtils.getTargetUrl(this.mParamsMap);
        if (StringUtils.isEmpty(targetUrl)) {
            Log.v("NflxHandler", "movie id uri and tiny url both doesn't exist in params map");
            return null;
        }
        Log.v("NflxHandler", "movie id uri doesn't exist in params map, but tiny url does. Resolve it");
        new BackgroundTask().execute(new Runnable() {
            @Override
            public void run() {
                Log.d("NflxHandler", "Resolving tiny URL in background");
                BaseNflxHandler.this.handleTinyUrl(targetUrl, NflxProtocolUtils.extractJustUuid(BaseNflxHandler.this.mParamsMap.get("targetid")), NflxProtocolUtils.getTrackId(BaseNflxHandler.this.mParamsMap));
            }
        });
        return NflxProtocolUtils.VideoInfo.DELAYED;
    }
    
    protected abstract Response handleEpisodeFromTinyUrl(final JSONObject p0, final String p1, final String p2) throws JSONException;
    
    protected void handleHomeAction() {
        new HomeActionHandler(this.mActivity, this.mParamsMap).handle();
    }
    
    protected abstract Response handleMovieFromTinyUrl(final JSONObject p0, final String p1, final String p2) throws JSONException;
    
    protected void handleTinyUrl(String remoteDataAsString, final String s, final String s2) {
        ThreadUtils.assertNotOnMain();
        final Response handling = Response.HANDLING;
        while (true) {
            try {
                remoteDataAsString = StringUtils.getRemoteDataAsString(NflxProtocolUtils.getExpandUrl(remoteDataAsString));
                if (Log.isLoggable("NflxHandler", 3)) {
                    Log.d("NflxHandler", "Received response " + remoteDataAsString);
                }
                final JSONObject jsonObject = new JSONObject(remoteDataAsString);
                Response response;
                if (!jsonObject.has("catalog_title")) {
                    Log.e("NflxHandler", "No catalog_title in JSON object! Go to LOLOMO.");
                    this.handleHomeAction();
                    response = handling;
                }
                else {
                    final JSONObject jsonObject2 = jsonObject.getJSONObject("catalog_title");
                    if (!jsonObject2.has("title_series")) {
                        Log.d("NflxHandler", "No title series in JSON object title. It must be movie. ");
                        response = this.handleMovieFromTinyUrl(jsonObject2, s, s2);
                    }
                    else {
                        Log.d("NflxHandler", "Title series in JSON object title. It must be episode. ");
                        response = this.handleEpisodeFromTinyUrl(jsonObject2, s, s2);
                    }
                }
                if (!Response.HANDLING_WITH_DELAY.equals(response)) {
                    NflxProtocolUtils.reportDelayedResponseHandled(this.mActivity);
                }
                return;
            }
            catch (Throwable t) {
                Log.e("NflxHandler", "We failed to get expanded URL ", t);
                this.handleHomeAction();
                final Response response = handling;
                continue;
            }
            continue;
        }
    }
}
