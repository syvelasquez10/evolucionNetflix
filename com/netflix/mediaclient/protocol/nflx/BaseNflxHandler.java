// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.protocol.nflx;

import android.app.Activity;
import com.netflix.mediaclient.util.BaseConverter;
import com.netflix.mediaclient.util.ThreadUtils;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.NflxProtocolUtils;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.NflxProtocolUtils$VideoInfo;
import java.util.Map;
import com.netflix.mediaclient.android.activity.NetflixActivity;

abstract class BaseNflxHandler implements NflxHandler
{
    public static final int BASE_ENCODING_MODULE = 62;
    protected static final String TAG = "NflxHandler";
    protected NetflixActivity mActivity;
    protected Map<String, String> mParamsMap;
    
    BaseNflxHandler(final NetflixActivity mActivity, final Map<String, String> mParamsMap) {
        this.mActivity = mActivity;
        this.mParamsMap = mParamsMap;
    }
    
    protected NflxProtocolUtils$VideoInfo getVideoInfo() {
        final String s = this.mParamsMap.get("movieid");
        if (!StringUtils.isEmpty(s)) {
            final NflxProtocolUtils$VideoInfo videoInfoFromVideoIdUrl = NflxProtocolUtils.getVideoInfoFromVideoIdUrl(s, (Map)this.mParamsMap);
            if (videoInfoFromVideoIdUrl == null && Log.isLoggable()) {
                Log.w("NflxHandler", "This should NOT happen! VideoInfo object not returned for video id " + s + ". Default to regular workflow");
            }
            final String episodeId = NflxProtocolUtils.getEpisodeId((Map)this.mParamsMap);
            if (StringUtils.isNotEmpty(episodeId) && videoInfoFromVideoIdUrl != null) {
                final NflxProtocolUtils$VideoInfo nflxProtocolUtils$VideoInfo;
                if ((nflxProtocolUtils$VideoInfo = NflxProtocolUtils$VideoInfo.createFromEpisode(videoInfoFromVideoIdUrl.getCatalogId(), episodeId)) != null) {
                    return nflxProtocolUtils$VideoInfo;
                }
                if (Log.isLoggable()) {
                    Log.w("NflxHandler", "VideoInfo object not returned for episode id " + episodeId + ". Default to show");
                    return videoInfoFromVideoIdUrl;
                }
            }
            return videoInfoFromVideoIdUrl;
        }
        return this.getVideoInfoFromTinyUrl();
    }
    
    protected NflxProtocolUtils$VideoInfo getVideoInfoFromTinyUrl() {
        final String targetUrl = NflxProtocolUtils.getTargetUrl((Map)this.mParamsMap);
        if (StringUtils.isEmpty(targetUrl)) {
            Log.v("NflxHandler", "movie id uri and tiny url both doesn't exist in params map");
            return null;
        }
        Log.v("NflxHandler", "movie id uri doesn't exist in params map, but tiny url does. Resolve it");
        new BackgroundTask().execute(new BaseNflxHandler$1(this, targetUrl));
        return NflxProtocolUtils$VideoInfo.DELAYED;
    }
    
    protected abstract NflxHandler$Response handleEpisodeFromTinyUrl(final String p0, final String p1, final String p2);
    
    protected void handleHomeAction() {
        new HomeActionHandler(this.mActivity, this.mParamsMap).handle();
    }
    
    protected abstract NflxHandler$Response handleMovieFromTinyUrl(final String p0, final String p1, final String p2);
    
    protected void handleTinyUrl(String value, final String s, final String s2) {
        boolean b = true;
        ThreadUtils.assertNotOnMain();
        final NflxHandler$Response handling = NflxHandler$Response.HANDLING;
        while (true) {
            try {
                value = String.valueOf(BaseConverter.convertFromBaseToInteger(NflxProtocolUtils.getExpandUrl(value), 62));
                if (BaseNflxHandler$TinyTypes.MOVIE_TYPE != BaseNflxHandler$TinyTypes.ordinalToType(Integer.parseInt(value.substring(0, 1)))) {
                    b = false;
                }
                final String substring = value.substring(1);
                if (Log.isLoggable()) {
                    Log.d("NflxHandler", "Received decodedVideoUrl " + value);
                }
                NflxHandler$Response nflxHandler$Response;
                if (StringUtils.isEmpty(substring)) {
                    Log.e("NflxHandler", "No catalog_title in JSON object! Go to LOLOMO.");
                    this.handleHomeAction();
                    nflxHandler$Response = handling;
                }
                else if (b) {
                    Log.d("NflxHandler", "This was a movie url");
                    nflxHandler$Response = this.handleMovieFromTinyUrl(substring, s, s2);
                }
                else {
                    Log.d("NflxHandler", "This was a TV Show url");
                    nflxHandler$Response = this.handleEpisodeFromTinyUrl(substring, s, s2);
                }
                if (!NflxHandler$Response.HANDLING_WITH_DELAY.equals(nflxHandler$Response)) {
                    NflxProtocolUtils.reportDelayedResponseHandled((Activity)this.mActivity);
                }
                return;
            }
            catch (Throwable t) {
                Log.e("NflxHandler", "We failed to get expanded URL ", t);
                this.handleHomeAction();
                final NflxHandler$Response nflxHandler$Response = handling;
                continue;
            }
            continue;
        }
    }
}
