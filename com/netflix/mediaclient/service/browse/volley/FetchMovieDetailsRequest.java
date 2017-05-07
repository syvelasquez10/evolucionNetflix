// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.service.webclient.model.branches.Video$UserRating;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.details.MovieDetails;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchMovieDetailsRequest extends FalkorVolleyWebClientRequest<MovieDetails>
{
    private static final String FIELD_MOVIES = "movies";
    private static final String TAG = "nf_service_browse_fetchmoviedetailsrequest";
    private final BrowseWebClientCache browseCache;
    private final int fromSimilarVideo;
    private final String mMovieId;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final BrowseAgentCallback responseCallback;
    private final int toSimilarVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchMovieDetailsRequest(final Context context, final BrowseWebClientCache browseCache, final String mMovieId, final int toSimilarVideo, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mMovieId = mMovieId;
        this.fromSimilarVideo = 0;
        this.toSimilarVideo = toSimilarVideo;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['movies', '%s', ['summary','detail', 'rating', 'inQueue', 'bookmark', 'socialEvidence', 'evidence']]", this.mMovieId);
        this.pqlQuery2 = String.format("['movies', '%s', 'similars', {'from':%d,'to':%d}, 'summary']", this.mMovieId, this.fromSimilarVideo, toSimilarVideo);
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
    protected MovieDetails parseFalkorResponse(String s) {
        if (Log.isLoggable("nf_service_browse_fetchmoviedetailsrequest", 2)) {}
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchmoviedetailsrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("MovieDetails empty!!!");
        }
        try {
            final JsonObject asJsonObject = dataObj.getAsJsonObject("movies").getAsJsonObject(this.mMovieId);
            s = (String)new com.netflix.mediaclient.service.webclient.model.MovieDetails();
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).summary = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", Video$Summary.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).detail = FalkorParseUtils.getPropertyObject(asJsonObject, "detail", Video$Detail.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).rating = FalkorParseUtils.getPropertyObject(asJsonObject, "rating", Video$UserRating.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).inQueue = FalkorParseUtils.getPropertyObject(asJsonObject, "inQueue", Video$InQueue.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).bookmark = FalkorParseUtils.getPropertyObject(asJsonObject, "bookmark", Video$Bookmark.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).socialEvidence = FalkorParseUtils.getPropertyObject(asJsonObject, "socialEvidence", SocialEvidence.class);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).evidence = FalkorParseUtils.buildEvidence(asJsonObject);
            FalkorParseUtils.buildSimilarVideosList(asJsonObject, (Video)s, this.toSimilarVideo);
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).userConnectedToFacebook = this.userConnectedToFacebook;
            ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).inQueue = this.browseCache.updateInQueueCacheRecord(((Video)s).getId(), ((com.netflix.mediaclient.service.webclient.model.MovieDetails)s).inQueue);
            return (MovieDetails)s;
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchmoviedetailsrequest", "String response to parse = " + s);
            throw new FalkorParseException("response missing expected json objects", ex);
        }
    }
}
