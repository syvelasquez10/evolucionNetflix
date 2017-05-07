// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.EpisodeDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class HideVideoRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_hidevideorequest";
    private final HardCache cache;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache secondaryCache;
    
    public HideVideoRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache cache, final SoftCache secondaryCache, final String mVideoId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.cache = cache;
        this.secondaryCache = secondaryCache;
        this.mVideoId = mVideoId;
        this.pqlQuery = "['videos','" + this.mVideoId + "','hideVideoFromSocial']";
        if (Log.isLoggable("nf_service_browse_hidevideorequest", 2)) {
            Log.v("nf_service_browse_hidevideorequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static void updateMdpWithSocialEvidence(final HardCache hardCache, final SoftCache softCache, final SocialEvidence socialEvidence, final String s, final String s2) {
        synchronized (HideVideoRequest.class) {
            if (s.equals("movies")) {
                final MovieDetails movieDetails = (MovieDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, s2, "0", "0"));
                if (movieDetails != null && movieDetails.socialEvidence != null) {
                    movieDetails.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
            }
            if (s.equals("episodes")) {
                final EpisodeDetails episodeDetails = (EpisodeDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_EPISODE_DETAILS, s2, "0", "0"));
                if (episodeDetails != null && episodeDetails.showSocialEvidence != null) {
                    episodeDetails.showSocialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
                if (episodeDetails != null) {
                    final ShowDetails showDetails = (ShowDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, episodeDetails.getParentId(), "0", "0"));
                    if (showDetails != null && showDetails.socialEvidence != null) {
                        showDetails.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                    }
                }
            }
            else {
                final ShowDetails showDetails2 = (ShowDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, s2, "0", "0"));
                if (showDetails2 != null && showDetails2.socialEvidence != null) {
                    showDetails2.socialEvidence.setVideoHidden(socialEvidence.isVideoHidden());
                }
            }
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_hidevideorequest", "HideVideoRequest finished onFailure statusCode=" + n);
            this.responseCallback.onVideoHide(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_hidevideorequest", "HideVideoRequest finished onSuccess");
            this.responseCallback.onVideoHide(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
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
            final HardCache hardCache = hideVideoRequest.cache;
            final HideVideoRequest hideVideoRequest2 = this;
            final SoftCache softCache = hideVideoRequest2.secondaryCache;
            final String s4 = s;
            final String s5 = s3;
            final HideVideoRequest hideVideoRequest3 = this;
            final String s6 = hideVideoRequest3.mVideoId;
            updateMdpWithSocialEvidence(hardCache, softCache, (SocialEvidence)s4, s5, s6);
            final int n2 = 0;
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
            final HardCache hardCache = hideVideoRequest.cache;
            final HideVideoRequest hideVideoRequest2 = this;
            final SoftCache softCache = hideVideoRequest2.secondaryCache;
            final String s4 = s;
            final String s5 = s3;
            final HideVideoRequest hideVideoRequest3 = this;
            final String s6 = hideVideoRequest3.mVideoId;
            updateMdpWithSocialEvidence(hardCache, softCache, (SocialEvidence)s4, s5, s6);
            final int n2 = 0;
            return Integer.toString(n2);
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_hidevideorequest", " PathObj missing in: " + asJsonObject.toString());
            throw new FalcorParseException("Missing hideVideoPathObj", ex);
        }
    }
}
