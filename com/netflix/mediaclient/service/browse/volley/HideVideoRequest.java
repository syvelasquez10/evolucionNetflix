// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.EpisodeDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class HideVideoRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_hidevideorequest";
    private final BrowseWebClientCache browseCache;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public HideVideoRequest(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['videos', '%s', 'hideVideoFromSocial']", this.mVideoId);
        if (Log.isLoggable("nf_service_browse_hidevideorequest", 2)) {
            Log.v("nf_service_browse_hidevideorequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static void updateMdpWithSocialEvidence(final BrowseWebClientCache browseWebClientCache, final SocialEvidence socialEvidence, final String s, final String s2) {
        synchronized (HideVideoRequest.class) {
            if (s.equals("movies")) {
                final MovieDetails movieDetails = (MovieDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, s2, "0", "0"));
                if (movieDetails != null && movieDetails.socialEvidence != null) {
                    movieDetails.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
            }
            if (s.equals("episodes")) {
                final EpisodeDetails episodeDetails = (EpisodeDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_EPISODE_DETAILS, s2, "0", "0"));
                if (episodeDetails != null && episodeDetails.showSocialEvidence != null) {
                    episodeDetails.showSocialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
                if (episodeDetails != null) {
                    final ShowDetails showDetails = (ShowDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, episodeDetails.getParentId(), "0", "0"));
                    if (showDetails != null && showDetails.socialEvidence != null) {
                        showDetails.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                    }
                }
            }
            else {
                final ShowDetails showDetails2 = (ShowDetails)browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, s2, "0", "0"));
                if (showDetails2 != null && showDetails2.socialEvidence != null) {
                    showDetails2.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
            }
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_hidevideorequest", "HideVideoRequest finished onFailure statusCode=" + status);
            this.responseCallback.onVideoHide(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_hidevideorequest", "HideVideoRequest finished onSuccess");
            this.responseCallback.onVideoHide(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) {
        if (Log.isLoggable("nf_service_browse_hidevideorequest", 2)) {
            Log.v("nf_service_browse_hidevideorequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_hidevideorequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("hide response empty!! " + this.mVideoId);
        }
        JsonObject asJsonObject = null;
        try {
            asJsonObject = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mVideoId);
            s = (String)FalcorParseUtils.getPropertyObject(asJsonObject, "socialEvidence", SocialEvidence.class);
            final JsonObject jsonObject = asJsonObject;
            final String s2 = "path";
            final JsonArray jsonArray = jsonObject.getAsJsonArray(s2);
            final int n = 0;
            final JsonElement jsonElement = jsonArray.get(n);
            final String s3 = jsonElement.getAsString();
            final HideVideoRequest hideVideoRequest = this;
            final BrowseWebClientCache browseWebClientCache = hideVideoRequest.browseCache;
            final String s4 = s;
            final String s5 = s3;
            final HideVideoRequest hideVideoRequest2 = this;
            final String s6 = hideVideoRequest2.mVideoId;
            updateMdpWithSocialEvidence(browseWebClientCache, (SocialEvidence)s4, s5, s6);
            final StatusCode statusCode = StatusCode.OK;
            final int n2 = statusCode.getValue();
            return Integer.toString(n2);
        }
        catch (Exception asJsonObject) {
            Log.v("nf_service_browse_hidevideorequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", (Throwable)asJsonObject);
        }
        try {
            final JsonObject jsonObject = asJsonObject;
            final String s2 = "path";
            final JsonArray jsonArray = jsonObject.getAsJsonArray(s2);
            final int n = 0;
            final JsonElement jsonElement = jsonArray.get(n);
            final String s3 = jsonElement.getAsString();
            final HideVideoRequest hideVideoRequest = this;
            final BrowseWebClientCache browseWebClientCache = hideVideoRequest.browseCache;
            final String s4 = s;
            final String s5 = s3;
            final HideVideoRequest hideVideoRequest2 = this;
            final String s6 = hideVideoRequest2.mVideoId;
            updateMdpWithSocialEvidence(browseWebClientCache, (SocialEvidence)s4, s5, s6);
            final StatusCode statusCode = StatusCode.OK;
            final int n2 = statusCode.getValue();
            return Integer.toString(n2);
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_hidevideorequest", " PathObj missing in: " + asJsonObject.toString());
            throw new FalcorParseException("Missing hideVideoPathObj", ex);
        }
    }
}
