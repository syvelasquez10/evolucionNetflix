// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.PlayableVideo;
import com.netflix.mediaclient.service.webclient.model.BillboardDetails;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.Billboard;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchBBVideosRequest extends FalcorVolleyWebClientRequest<List<Billboard>>
{
    private static final String TAG = "nf_service_browse_fetchbbvideosrequest";
    private final BrowseWebClientCache browseCache;
    private final int fromSimilars;
    private final int fromVideo;
    private final LoMo mLoMo;
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final BrowseAgentCallback responseCallback;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchBBVideosRequest(final Context context, final BrowseWebClientCache browseCache, final LoMo mLoMo, final int fromVideo, final int toVideo, final int toSimilars, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mLoMo = mLoMo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        final String format = String.format("['lists','%s'", mLoMo.getId());
        this.pqlQuery1 = String.format("%s, {'from':%d,'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]", format, fromVideo, toVideo);
        this.pqlQuery2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", format, fromVideo, toVideo);
        this.pqlQuery3 = String.format("%s, {'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']", format, fromVideo, toVideo, this.fromSimilars, toSimilars);
        this.pqlQuery4 = String.format("%s, {'from':%d,'to':%d}, 'similars', 'summary']", format, fromVideo, toVideo);
        if (Log.isLoggable("nf_service_browse_fetchbbvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "PQL = " + this.pqlQuery1 + " " + this.pqlQuery2 + " " + this.pqlQuery3 + " " + this.pqlQuery4);
        }
    }
    
    public static List<Billboard> buildBBVideoList(final JsonObject jsonObject, final int n, int i, final int n2, final boolean b, final BrowseWebClientCache browseWebClientCache) {
        final ArrayList<BillboardDetails> list = (ArrayList<BillboardDetails>)new ArrayList<Billboard>();
        Log.d("nf_service_browse_fetchbbvideosrequest", String.format("buildBBVideoList - %s", jsonObject));
        int n3 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n4;
            if (jsonObject.has(string)) {
                n4 = 1;
                final BillboardDetails billboardDetails = new BillboardDetails();
                FetchCWVideosRequest.fillPlayableVideo(jsonObject, billboardDetails, string, n2, b);
                billboardDetails.inQueue = browseWebClientCache.updateInQueueCacheRecord(billboardDetails.getId(), billboardDetails.inQueue);
                if (VideoType.MOVIE.equals(billboardDetails.getType())) {
                    uniteWithMDP(browseWebClientCache, billboardDetails);
                }
                else {
                    uniteWithSDP(browseWebClientCache, billboardDetails);
                }
                list.add(0, billboardDetails);
            }
            else if ((n4 = n3) != 0) {
                Log.d("nf_service_browse_fetchbbvideosrequest", String.format("Adding sentinel at index %s", string));
                list.add(0, BrowseVideoSentinels.getUnavailableBBVideo());
                n4 = n3;
            }
            --i;
            n3 = n4;
        }
        return (List<Billboard>)list;
    }
    
    private static void uniteWithMDP(final BrowseWebClientCache browseWebClientCache, final PlayableVideo playableVideo) {
        final String buildBrowseCacheKey = BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, playableVideo.getId(), "0", "0");
        final Object fromCaches = browseWebClientCache.getFromCaches(buildBrowseCacheKey);
        if (fromCaches != null) {
            final MovieDetails movieDetails = (MovieDetails)fromCaches;
            Log.d("nf_service_browse_fetchbbvideosrequest", String.format("billlboard merging with mdp (%s - %s)", playableVideo.getParentId(), playableVideo.getParentTitle()));
            Video.Bookmark bookmark;
            if (movieDetails.bookmark != null) {
                bookmark = movieDetails.bookmark;
            }
            else {
                bookmark = playableVideo.bookmark;
            }
            playableVideo.bookmark = bookmark;
            SocialEvidence socialEvidence;
            if (movieDetails.socialEvidence != null) {
                socialEvidence = movieDetails.socialEvidence;
            }
            else {
                socialEvidence = playableVideo.socialEvidence;
            }
            playableVideo.socialEvidence = socialEvidence;
            Video.Rating rating;
            if (movieDetails.rating != null) {
                rating = movieDetails.rating;
            }
            else {
                rating = playableVideo.rating;
            }
            playableVideo.rating = rating;
            Video.InQueue inQueue;
            if (movieDetails.inQueue != null) {
                inQueue = movieDetails.inQueue;
            }
            else {
                inQueue = playableVideo.inQueue;
            }
            playableVideo.inQueue = inQueue;
            List<com.netflix.mediaclient.servicemgr.model.Video> similarVideos;
            if (movieDetails.similarVideos != null) {
                similarVideos = movieDetails.similarVideos;
            }
            else {
                similarVideos = playableVideo.similarVideos;
            }
            playableVideo.similarVideos = similarVideos;
            TrackableListSummary similarListSummary;
            if (movieDetails.similarListSummary != null) {
                similarListSummary = movieDetails.similarListSummary;
            }
            else {
                similarListSummary = playableVideo.similarListSummary;
            }
            playableVideo.similarListSummary = similarListSummary;
            playableVideo.userConnectedToFacebook = movieDetails.userConnectedToFacebook;
            return;
        }
        Log.d("nf_service_browse_fetchbbvideosrequest", String.format("billboards creating mdp (%s - %s) ", playableVideo.getParentId(), playableVideo.getParentTitle()));
        FetchCWVideosRequest.buildMdpFromPlayableVideo(browseWebClientCache, buildBrowseCacheKey, playableVideo);
    }
    
    private static void uniteWithSDP(final BrowseWebClientCache browseWebClientCache, final PlayableVideo playableVideo) {
        final String buildBrowseCacheKey = BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, playableVideo.getId(), "0", "0");
        final Object fromCaches = browseWebClientCache.getFromCaches(buildBrowseCacheKey);
        if (fromCaches != null) {
            final ShowDetails showDetails = (ShowDetails)fromCaches;
            Log.d("nf_service_browse_fetchbbvideosrequest", String.format("billboards merging with sdp (%s - %s)", playableVideo.getParentId(), playableVideo.getParentTitle()));
            Video.Bookmark bookmark;
            if (showDetails.bookmark != null) {
                bookmark = showDetails.bookmark;
            }
            else {
                bookmark = playableVideo.bookmark;
            }
            playableVideo.bookmark = bookmark;
            SocialEvidence socialEvidence;
            if (showDetails.socialEvidence != null) {
                socialEvidence = showDetails.socialEvidence;
            }
            else {
                socialEvidence = playableVideo.socialEvidence;
            }
            playableVideo.socialEvidence = socialEvidence;
            Episode.Detail currentEpisode;
            if (showDetails.currentEpisode != null) {
                currentEpisode = showDetails.currentEpisode;
            }
            else {
                currentEpisode = playableVideo.currentEpisode;
            }
            playableVideo.currentEpisode = currentEpisode;
            Video.Bookmark currentEpisodeBookmark;
            if (showDetails.currentEpisodeBookmark != null) {
                currentEpisodeBookmark = showDetails.currentEpisodeBookmark;
            }
            else {
                currentEpisodeBookmark = playableVideo.currentEpisodeBookmark;
            }
            playableVideo.currentEpisodeBookmark = currentEpisodeBookmark;
            Video.Rating rating;
            if (showDetails.rating != null) {
                rating = showDetails.rating;
            }
            else {
                rating = playableVideo.rating;
            }
            playableVideo.rating = rating;
            Video.InQueue inQueue;
            if (showDetails.inQueue != null) {
                inQueue = showDetails.inQueue;
            }
            else {
                inQueue = playableVideo.inQueue;
            }
            playableVideo.inQueue = inQueue;
            playableVideo.userConnectedToFacebook = showDetails.userConnectedToFacebook;
            return;
        }
        Log.d("nf_service_browse_fetchbbvideosrequest", String.format("billboards creating sdp (%s - %s) ", playableVideo.getParentId(), playableVideo.getParentTitle()));
        FetchCWVideosRequest.buildSdpFromPlayableVideo(browseWebClientCache, buildBrowseCacheKey, playableVideo);
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onBBVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<Billboard> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onBBVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<Billboard> parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchbbvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchbbvideosrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            return new ArrayList<Billboard>();
        }
        try {
            return buildBBVideoList(dataObj.getAsJsonObject("lists").getAsJsonObject(this.mLoMo.getId()), this.fromVideo, this.toVideo, this.toSimilars, this.userConnectedToFacebook, this.browseCache);
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_fetchbbvideosrequest", "String response to parse = " + s);
            throw new FalcorParseException("Does not contain required fields", ex);
        }
    }
}
