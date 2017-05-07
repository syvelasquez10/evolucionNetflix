// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchMovieDetailsRequest extends FalcorVolleyWebClientRequest<MovieDetails>
{
    private static final String FIELD_MOVIES = "movies";
    private static final String TAG = "nf_service_browse_fetchmoviedetailsrequest";
    private BrowseWebClientCache browseCache;
    private final int fromVideo;
    private final String mMovieId;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchMovieDetailsRequest(final Context context, final BrowseWebClientCache browseCache, final String mMovieId, final int fromVideo, final int toVideo, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mMovieId = mMovieId;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['movies', '%s', ['summary','detail', 'rating', 'inQueue', 'bookmark', 'socialEvidence']]", this.mMovieId);
        this.pqlQuery2 = String.format("['movies', '%s', 'similars', {'from':%d,'to':%d}, 'summary']", this.mMovieId, fromVideo, toVideo);
        this.pqlQuery3 = String.format("['movies', '%s', 'similars', 'summary']", this.mMovieId);
        if (Log.isLoggable("nf_service_browse_fetchmoviedetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchmoviedetailsrequest", "PQL = " + this.pqlQuery + " " + this.pqlQuery2 + " " + this.pqlQuery3);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onMovieDetailsFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final MovieDetails movieDetails) {
        if (this.responseCallback != null) {
            this.responseCallback.onMovieDetailsFetched(movieDetails, CommonStatus.OK);
        }
    }
    
    @Override
    protected MovieDetails parseFalcorResponse(String asJsonObject) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchmoviedetailsrequest", 2)) {}
        final com.netflix.mediaclient.service.webclient.model.MovieDetails movieDetails = new com.netflix.mediaclient.service.webclient.model.MovieDetails();
        final ArrayList<Video.Summary> similarVideos = new ArrayList<Video.Summary>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchmoviedetailsrequest", asJsonObject);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("MovieDetails empty!!!");
        }
        Label_0293: {
            try {
                final JsonObject asJsonObject2 = dataObj.getAsJsonObject("movies").getAsJsonObject(this.mMovieId);
                movieDetails.summary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", Video.Summary.class);
                movieDetails.detail = FalcorParseUtils.getPropertyObject(asJsonObject2, "detail", Video.Detail.class);
                movieDetails.rating = FalcorParseUtils.getPropertyObject(asJsonObject2, "rating", Video.Rating.class);
                movieDetails.inQueue = FalcorParseUtils.getPropertyObject(asJsonObject2, "inQueue", Video.InQueue.class);
                movieDetails.bookmark = FalcorParseUtils.getPropertyObject(asJsonObject2, "bookmark", Video.Bookmark.class);
                movieDetails.socialEvidence = FalcorParseUtils.getPropertyObject(asJsonObject2, "socialEvidence", SocialEvidence.class);
                if (!asJsonObject2.has("similars")) {
                    break Label_0293;
                }
                asJsonObject = (String)asJsonObject2.getAsJsonObject("similars");
                for (int i = this.fromVideo; i <= this.toVideo; ++i) {
                    final String string = Integer.toString(i);
                    if (((JsonObject)asJsonObject).has(string)) {
                        similarVideos.add(FalcorParseUtils.getPropertyObject(((JsonObject)asJsonObject).getAsJsonObject(string), "summary", Video.Summary.class));
                    }
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_fetchmoviedetailsrequest", "String response to parse = " + asJsonObject);
                throw new FalcorParseException("response missing expected json objects", ex);
            }
            movieDetails.similarListSummary = FalcorParseUtils.getPropertyObject((JsonObject)asJsonObject, "summary", TrackableListSummary.class);
        }
        movieDetails.similarVideos = (List<com.netflix.mediaclient.servicemgr.model.Video>)similarVideos;
        movieDetails.userConnectedToFacebook = this.userConnectedToFacebook;
        movieDetails.inQueue = this.browseCache.updateInQueueCacheRecord(movieDetails.getId(), movieDetails.inQueue);
        return movieDetails;
    }
}
