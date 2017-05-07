// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonElement;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class SetVideoRatingRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_PATH = "path";
    private static final String FIELD_VALUE = "value";
    private static final String FIELD_VIDEOS = "videos";
    private static final String TAG = "nf_service_browse_setvideoratingrequest";
    private final HardCache cache;
    private final int mNewRating;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache secondaryCache;
    private final int trackId;
    
    public SetVideoRatingRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache cache, final SoftCache secondaryCache, final String mVideoId, final int mNewRating, final int trackId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.cache = cache;
        this.secondaryCache = secondaryCache;
        this.mVideoId = mVideoId;
        this.mNewRating = mNewRating;
        this.trackId = trackId;
        this.pqlQuery = "['videos','" + this.mVideoId + "','setRating']";
        if (Log.isLoggable("nf_service_browse_setvideoratingrequest", 2)) {
            Log.v("nf_service_browse_setvideoratingrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static void updateMdpWithNewRating(final HardCache hardCache, final SoftCache softCache, final String s, final String s2, final float n) {
        synchronized (SetVideoRatingRequest.class) {
            if (s2.equals("movies")) {
                final MovieDetails movieDetails = (MovieDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, s, "0", "0"));
                if (movieDetails != null && movieDetails.rating != null) {
                    movieDetails.rating.userRating = n;
                }
            }
            else {
                final ShowDetails showDetails = (ShowDetails)BrowseAgent.getFromCaches(hardCache, softCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, s, "0", "0"));
                if (showDetails != null && showDetails.rating != null) {
                    showDetails.rating.userRating = n;
                }
            }
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final String string = "&" + FalcorParseUtils.getParamNameParam() + "=";
        final StringBuilder sb = new StringBuilder();
        sb.append(string).append(this.mNewRating);
        sb.append(string).append(this.trackId);
        Log.d("nf_service_browse_setvideoratingrequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_setvideoratingrequest", "SetVideoRatingRequest6 finished onFailure statusCode=" + n);
            this.responseCallback.onVideoRatingSet(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_setvideoratingrequest", "SetVideoRatingRequest6 finished onSuccess");
            this.responseCallback.onVideoRatingSet(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_setvideoratingrequest", 2)) {
            Log.v("nf_service_browse_setvideoratingrequest", "String response to parse = " + s);
        }
        Object asJsonObject;
        JsonObject asJsonObject2;
        try {
            asJsonObject = new JsonParser().parse(s).getAsJsonObject();
            asJsonObject2 = ((JsonObject)asJsonObject).getAsJsonObject("value");
            if (FalcorParseUtils.isEmpty(asJsonObject2)) {
                throw new FalcorParseException("setRating failed ?");
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_setvideoratingrequest", "String response to parse = " + s);
            throw new FalcorParseException("Error in creating JsonObject", ex);
        }
        if (FalcorParseUtils.containsErrors((JsonObject)asJsonObject)) {
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage((JsonObject)asJsonObject));
        }
        JsonObject jsonObject;
        try {
            final JsonObject asJsonObject3;
            jsonObject = (asJsonObject3 = asJsonObject2.getAsJsonObject("videos").getAsJsonObject(this.mVideoId));
            final String s2 = "path";
            final JsonArray jsonArray = asJsonObject3.getAsJsonArray(s2);
            final int n = 0;
            final JsonElement jsonElement = jsonArray.get(n);
            s = jsonElement.getAsString();
            final JsonObject jsonObject2 = jsonObject;
            final String s3 = "rating";
            final Class<Video.Rating> clazz = Video.Rating.class;
            final Video.Rating rating = FalcorParseUtils.getPropertyObject(jsonObject2, s3, clazz);
            asJsonObject = rating;
            final String s4 = "nf_service_browse_setvideoratingrequest";
            final String s5 = "VideoId:%s, videoType: %s, newRating:%f";
            final int n2 = 3;
            final Object[] array = new Object[n2];
            final int n3 = 0;
            final SetVideoRatingRequest setVideoRatingRequest = this;
            final String s6 = setVideoRatingRequest.mVideoId;
            array[n3] = s6;
            final int n4 = 1;
            final String s7 = s;
            array[n4] = s7;
            final int n5 = 2;
            final Object o = asJsonObject;
            final float n6 = ((Video.Rating)o).userRating;
            final Float n7 = n6;
            array[n5] = n7;
            final String s8 = String.format(s5, array);
            Log.d(s4, s8);
            final SetVideoRatingRequest setVideoRatingRequest2 = this;
            final HardCache hardCache = setVideoRatingRequest2.cache;
            final SetVideoRatingRequest setVideoRatingRequest3 = this;
            final SoftCache softCache = setVideoRatingRequest3.secondaryCache;
            final SetVideoRatingRequest setVideoRatingRequest4 = this;
            final String s9 = setVideoRatingRequest4.mVideoId;
            final String s10 = s;
            final Object o2 = asJsonObject;
            final float n8 = ((Video.Rating)o2).userRating;
            updateMdpWithNewRating(hardCache, softCache, s9, s10, n8);
            final int n9 = 0;
            return Integer.toString(n9);
        }
        catch (Exception asJsonObject) {
            Log.v("nf_service_browse_setvideoratingrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", (Throwable)asJsonObject);
        }
        try {
            final JsonObject asJsonObject3 = jsonObject;
            final String s2 = "path";
            final JsonArray jsonArray = asJsonObject3.getAsJsonArray(s2);
            final int n = 0;
            final JsonElement jsonElement = jsonArray.get(n);
            s = jsonElement.getAsString();
            final JsonObject jsonObject2 = jsonObject;
            final String s3 = "rating";
            final Class<Video.Rating> clazz = Video.Rating.class;
            final Video.Rating rating = FalcorParseUtils.getPropertyObject(jsonObject2, s3, clazz);
            asJsonObject = rating;
            final String s4 = "nf_service_browse_setvideoratingrequest";
            final String s5 = "VideoId:%s, videoType: %s, newRating:%f";
            final int n2 = 3;
            final Object[] array = new Object[n2];
            final int n3 = 0;
            final SetVideoRatingRequest setVideoRatingRequest = this;
            final String s6 = setVideoRatingRequest.mVideoId;
            array[n3] = s6;
            final int n4 = 1;
            final String s7 = s;
            array[n4] = s7;
            final int n5 = 2;
            final Object o = asJsonObject;
            final float n6 = ((Video.Rating)o).userRating;
            final Float n7 = n6;
            array[n5] = n7;
            final String s8 = String.format(s5, array);
            Log.d(s4, s8);
            final SetVideoRatingRequest setVideoRatingRequest2 = this;
            final HardCache hardCache = setVideoRatingRequest2.cache;
            final SetVideoRatingRequest setVideoRatingRequest3 = this;
            final SoftCache softCache = setVideoRatingRequest3.secondaryCache;
            final SetVideoRatingRequest setVideoRatingRequest4 = this;
            final String s9 = setVideoRatingRequest4.mVideoId;
            final String s10 = s;
            final Object o2 = asJsonObject;
            final float n8 = ((Video.Rating)o2).userRating;
            updateMdpWithNewRating(hardCache, softCache, s9, s10, n8);
            final int n9 = 0;
            return Integer.toString(n9);
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_setvideoratingrequest", "setRating PathObj missing in: " + ((JsonElement)asJsonObject).toString());
            throw new FalcorParseException("Missing setRatingPathObj", ex2);
        }
    }
}
