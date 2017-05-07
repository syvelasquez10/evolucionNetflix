// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.servicemgr.model.VideoType;
import com.netflix.mediaclient.service.webclient.model.PlayableVideo;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchCWVideosRequest extends FalcorVolleyWebClientRequest<List<CWVideo>>
{
    private static final int BOOKMARK_MARGIN_MS = 90000;
    private static final String BOOKMARK_TAG = "nf_bookmark";
    public static final String CW_SUB_QUERY1 = "%s, {'from':%d,'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]";
    public static final String CW_SUB_QUERY2 = "%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]";
    public static final String CW_SUB_QUERY3 = "%s, {'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']";
    public static final String CW_SUB_QUERY4 = "%s, {'from':%d,'to':%d}, 'similars', 'summary']";
    private static final String FIELD_CW = "continueWatching";
    private static final String FIELD_LISTS = "lists";
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String TAG = "nf_service_browse_fetchcwvideosrequest";
    private final BrowseWebClientCache browseCache;
    private final String cwId;
    private final boolean cwIdInCache;
    private final int fromSimilars;
    private final int fromVideo;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final BrowseAgentCallback responseCallback;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchCWVideosRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final int toSimilars, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        this.cwId = browseCache.getCWLoMoId();
        this.cwIdInCache = (this.cwId != null);
        this.lolomoId = browseCache.getLoLoMoId();
        this.lolomoIdInCache = (this.lolomoId != null);
        String s;
        if (this.cwIdInCache) {
            s = String.format("['lists','%s'", this.cwId);
        }
        else if (this.lolomoIdInCache) {
            s = String.format("['lolomos','%s','continueWatching'", this.lolomoId);
        }
        else {
            s = String.format("['lolomo', 'continueWatching'", new Object[0]);
        }
        this.pqlQuery = String.format("%s, {'from':%d,'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]", s, fromVideo, toVideo);
        this.pqlQuery2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", s, fromVideo, toVideo);
        this.pqlQuery3 = String.format("%s, {'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']", s, fromVideo, toVideo, this.fromSimilars, toSimilars);
        this.pqlQuery4 = String.format("%s, {'from':%d,'to':%d}, 'similars', 'summary']", s, fromVideo, toVideo);
        if (Log.isLoggable("nf_service_browse_fetchcwvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchcwvideosrequest", "PQL = " + this.pqlQuery + " " + this.pqlQuery2 + " " + this.pqlQuery3 + " " + this.pqlQuery4);
        }
    }
    
    public static List<CWVideo> buildCWVideoList(final JsonObject jsonObject, final int n, int i, final int n2, final boolean b, final BrowseWebClientCache browseWebClientCache) {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo> list = (ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo>)new ArrayList<CWVideo>();
        int n3 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n4;
            if (jsonObject.has(string)) {
                n4 = 1;
                final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo = new com.netflix.mediaclient.service.webclient.model.CWVideo();
                fillPlayableVideo(jsonObject, cwVideo, string, n2, b);
                cwVideo.inQueue = browseWebClientCache.updateInQueueCacheRecord(cwVideo.getId(), cwVideo.inQueue);
                if (VideoType.MOVIE.equals(cwVideo.getType())) {
                    syncWithMDP(browseWebClientCache, cwVideo);
                }
                else {
                    syncWithSDP(browseWebClientCache, cwVideo);
                }
                list.add(0, cwVideo);
            }
            else if ((n4 = n3) != 0) {
                Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Adding sentinel at index %s", string));
                list.add(0, BrowseVideoSentinels.getUnavailableCwVideo());
                n4 = n3;
            }
            --i;
            n3 = n4;
        }
        return (List<CWVideo>)list;
    }
    
    public static void buildMdpFromPlayableVideo(final BrowseWebClientCache browseWebClientCache, final String s, final PlayableVideo playableVideo) {
        if (playableVideo != null) {
            final MovieDetails movieDetails = new MovieDetails();
            final ArrayList list = new ArrayList();
            if (movieDetails != null && list != null) {
                movieDetails.summary = playableVideo.summary;
                movieDetails.detail = playableVideo.detail;
                movieDetails.rating = playableVideo.rating;
                movieDetails.inQueue = playableVideo.inQueue;
                movieDetails.bookmark = playableVideo.bookmark;
                movieDetails.socialEvidence = playableVideo.socialEvidence;
                movieDetails.similarVideos = playableVideo.similarVideos;
                movieDetails.similarListSummary = playableVideo.similarListSummary;
                movieDetails.userConnectedToFacebook = playableVideo.userConnectedToFacebook;
                browseWebClientCache.putInSoftCache(s, movieDetails);
                Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Created MDP from cw for (%s) %s", movieDetails.getId(), movieDetails.getTitle()));
            }
        }
    }
    
    public static void buildSdpFromPlayableVideo(final BrowseWebClientCache browseWebClientCache, final String s, final PlayableVideo playableVideo) {
        if (playableVideo == null) {
            return;
        }
        final ShowDetails showDetails = new ShowDetails();
        showDetails.summary = playableVideo.summary;
        showDetails.detail = playableVideo.detail;
        showDetails.rating = playableVideo.rating;
        showDetails.inQueue = playableVideo.inQueue;
        showDetails.bookmark = playableVideo.bookmark;
        showDetails.currentEpisode = playableVideo.currentEpisode;
        showDetails.currentEpisodeBookmark = playableVideo.currentEpisodeBookmark;
        showDetails.userConnectedToFacebook = playableVideo.userConnectedToFacebook;
        showDetails.socialEvidence = playableVideo.socialEvidence;
        browseWebClientCache.putInSoftCache(s, showDetails);
        Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Created SDP from cw for (%s) %s", showDetails.getId(), showDetails.getTitle()));
    }
    
    public static void fillPlayableVideo(JsonObject jsonObject, final PlayableVideo playableVideo, final String s, final int n, final boolean userConnectedToFacebook) {
        jsonObject = jsonObject.getAsJsonObject(s);
        playableVideo.summary = FalcorParseUtils.getPropertyObject(jsonObject, "summary", Video.Summary.class);
        playableVideo.detail = FalcorParseUtils.getPropertyObject(jsonObject, "detail", Video.Detail.class);
        playableVideo.rating = FalcorParseUtils.getPropertyObject(jsonObject, "rating", Video.Rating.class);
        playableVideo.inQueue = FalcorParseUtils.getPropertyObject(jsonObject, "inQueue", Video.InQueue.class);
        playableVideo.bookmark = FalcorParseUtils.getPropertyObject(jsonObject, "bookmark", Video.Bookmark.class);
        playableVideo.bookmarkStill = FalcorParseUtils.getPropertyObject(jsonObject, "bookmarkStill", Video.BookmarkStill.class);
        playableVideo.socialEvidence = FalcorParseUtils.getPropertyObject(jsonObject, "socialEvidence", SocialEvidence.class);
        if (!VideoType.MOVIE.equals(playableVideo.summary.getType()) && jsonObject.has("episodes")) {
            final JsonObject asJsonObject = jsonObject.getAsJsonObject("episodes");
            if (asJsonObject.has("current")) {
                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("current");
                playableVideo.currentEpisode = FalcorParseUtils.getPropertyObject(asJsonObject2, "detail", Episode.Detail.class);
                playableVideo.currentEpisodeBookmark = FalcorParseUtils.getPropertyObject(asJsonObject2, "bookmark", Video.Bookmark.class);
            }
        }
        playableVideo.userConnectedToFacebook = userConnectedToFacebook;
        final ArrayList<com.netflix.mediaclient.servicemgr.model.Video> similarVideos = new ArrayList<com.netflix.mediaclient.servicemgr.model.Video>();
        if (jsonObject.has("similars")) {
            jsonObject = jsonObject.getAsJsonObject("similars");
            for (int i = 0; i <= n; ++i) {
                final String string = Integer.toString(i);
                if (jsonObject.has(string)) {
                    similarVideos.add(FalcorParseUtils.getPropertyObject(jsonObject.getAsJsonObject(string), "summary", Video.Summary.class));
                }
            }
            playableVideo.similarListSummary = FalcorParseUtils.getPropertyObject(jsonObject, "summary", TrackableListSummary.class);
        }
        playableVideo.similarVideos = similarVideos;
    }
    
    public static void syncWithMDP(final BrowseWebClientCache browseWebClientCache, final PlayableVideo playableVideo) {
        while (true) {
            final String buildBrowseCacheKey;
            synchronized (FetchCWVideosRequest.class) {
                buildBrowseCacheKey = BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, playableVideo.getId(), "0", "0");
                final Object fromCaches = browseWebClientCache.getFromCaches(buildBrowseCacheKey);
                if (fromCaches != null) {
                    final MovieDetails movieDetails = (MovieDetails)fromCaches;
                    if (movieDetails.bookmark != null) {
                        Log.d("nf_bookmark", String.format("syncWithMDP: %s - server bookmarkTime %d, bookmark %d : local bookmarkTime %d bookmark %d", playableVideo.getId(), playableVideo.getBookmarkLastUpdateTime(), playableVideo.getBookmarkPosition(), movieDetails.getPlayableBookmarkUpdateTime(), movieDetails.getBookmarkPosition()));
                        if (useLocalMdpBookmark(playableVideo, movieDetails)) {
                            playableVideo.bookmark.deepCopy(movieDetails.bookmark);
                            if (playableVideo.bookmarkStill != null) {
                                playableVideo.bookmarkStill.stillUrl = BrowseAgent.buildStillUrlFromPos(playableVideo.getBaseUrl(), playableVideo.getPlayableBookmarkPosition(), playableVideo.getEndtime());
                            }
                            Log.d("nf_bookmark", String.format("syncWithMDP: %s using local MDP bookmark; new bookmarkTime %d, bookmark %d ", playableVideo.getId(), playableVideo.getBookmarkLastUpdateTime(), playableVideo.getBookmarkPosition()));
                        }
                        else if (playableVideo.getBookmarkPosition() != movieDetails.getBookmarkPosition()) {
                            movieDetails.bookmark.deepCopy(playableVideo.bookmark);
                            Log.d("nf_bookmark", String.format("syncWithMDP: %s using CW bookmark; new bookmarkTime %d, bookmark %d  ", movieDetails.getId(), movieDetails.getPlayableBookmarkUpdateTime(), movieDetails.getBookmarkPosition()));
                        }
                        movieDetails.userConnectedToFacebook = playableVideo.userConnectedToFacebook;
                    }
                    return;
                }
            }
            final BrowseWebClientCache browseWebClientCache2;
            buildMdpFromPlayableVideo(browseWebClientCache2, buildBrowseCacheKey, playableVideo);
        }
    }
    
    public static void syncWithSDP(final BrowseWebClientCache browseWebClientCache, final PlayableVideo playableVideo) {
        while (true) {
            Object buildBrowseCacheKey = null;
            final BrowseWebClientCache browseWebClientCache2;
            Label_0373: {
                Label_0277: {
                    synchronized (FetchCWVideosRequest.class) {
                        buildBrowseCacheKey = BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, playableVideo.getId(), "0", "0");
                        final Object fromCaches = browseWebClientCache.getFromCaches((String)buildBrowseCacheKey);
                        if (fromCaches != null) {
                            buildBrowseCacheKey = fromCaches;
                            ((ShowDetails)buildBrowseCacheKey).userConnectedToFacebook = playableVideo.userConnectedToFacebook;
                            if (((ShowDetails)buildBrowseCacheKey).currentEpisode != null && ((ShowDetails)buildBrowseCacheKey).currentEpisodeBookmark != null && playableVideo.currentEpisode != null && playableVideo.currentEpisodeBookmark != null) {
                                Log.d("nf_bookmark", String.format("syncWithSDP: %s - server ce %s bookmarkTime %d, bookmark %d : local ce %s bookmarkTime %d bookmark %d ", playableVideo.getId(), playableVideo.getCurrentEpisodeId(), playableVideo.getBookmarkLastUpdateTime(), playableVideo.getBookmarkPosition(), ((ShowDetails)buildBrowseCacheKey).getCurrentEpisodeId(), ((ShowDetails)buildBrowseCacheKey).getBookmarkCreationTime(), ((ShowDetails)buildBrowseCacheKey).getBookmarkPosition()));
                                if (!useLocalSdpBookmark(playableVideo, (ShowDetails)buildBrowseCacheKey)) {
                                    break Label_0277;
                                }
                                playableVideo.currentEpisode.deepCopy(((ShowDetails)buildBrowseCacheKey).currentEpisode);
                                playableVideo.currentEpisodeBookmark.deepCopy(((ShowDetails)buildBrowseCacheKey).currentEpisodeBookmark);
                                if (playableVideo.bookmarkStill != null) {
                                    playableVideo.bookmarkStill.stillUrl = BrowseAgent.buildStillUrlFromPos(playableVideo.currentEpisode.getBaseUrl(), playableVideo.getPlayableBookmarkPosition(), playableVideo.getEndtime());
                                }
                                Log.d("nf_bookmark", String.format("syncWithSDP: %s using local SDP; new ce %s bookmarkTime %d, bookmark %d ", playableVideo.getId(), playableVideo.getCurrentEpisodeId(), playableVideo.getBookmarkLastUpdateTime(), playableVideo.getBookmarkPosition()));
                            }
                            return;
                        }
                        break Label_0373;
                    }
                }
                ((ShowDetails)buildBrowseCacheKey).currentEpisode.deepCopy(playableVideo.currentEpisode);
                ((ShowDetails)buildBrowseCacheKey).currentEpisodeBookmark.deepCopy(playableVideo.currentEpisodeBookmark);
                BrowseAgent.updateSeasonsInformation(browseWebClientCache2, playableVideo.currentEpisode.getSeasonId(), playableVideo.currentEpisode.getEpisodeNumber());
                Log.d("nf_bookmark", String.format("syncWithSDP: %s updating local SDP ce %s bookmarkTime %d, bookmark %d  ", ((ShowDetails)buildBrowseCacheKey).getId(), ((ShowDetails)buildBrowseCacheKey).currentEpisode.getId(), ((ShowDetails)buildBrowseCacheKey).getBookmarkCreationTime(), ((ShowDetails)buildBrowseCacheKey).getBookmarkPosition()));
                return;
            }
            buildSdpFromPlayableVideo(browseWebClientCache2, (String)buildBrowseCacheKey, playableVideo);
        }
    }
    
    private static boolean useLocalMdpBookmark(final PlayableVideo playableVideo, final MovieDetails movieDetails) {
        return movieDetails.getPlayableBookmarkUpdateTime() != 0L && playableVideo.getBookmarkPosition() != movieDetails.getBookmarkPosition() && playableVideo.getBookmarkLastUpdateTime() < movieDetails.getPlayableBookmarkUpdateTime() + 90000L;
    }
    
    private static boolean useLocalSdpBookmark(final PlayableVideo playableVideo, final ShowDetails showDetails) {
        if (showDetails.getBookmarkCreationTime() == 0L) {
            return false;
        }
        boolean b;
        if (StringUtils.safeEquals(playableVideo.getCurrentEpisodeId(), showDetails.getCurrentEpisodeId())) {
            b = (playableVideo.getBookmarkPosition() != showDetails.getBookmarkPosition() && playableVideo.getBookmarkLastUpdateTime() < showDetails.getBookmarkCreationTime() + 90000L);
        }
        else if (playableVideo.getCurrentSeasonNumber() == showDetails.getCurrentSeasonNumber()) {
            b = (showDetails.getCurrentEpisodeNumber() > playableVideo.getCurrentEpisodeNumber());
        }
        else {
            b = (showDetails.getCurrentEpisodeNumber() < playableVideo.getCurrentEpisodeNumber());
        }
        Log.v("nf_bookmark", String.format("useLocalSdpBookmark: %s useLocal ? %b, cw-ep %s cw-sn %d, cw-en %d, sdp-ep %s sdp-sn %d, sdp-en %d", showDetails.getId(), b, playableVideo.getCurrentEpisodeId(), playableVideo.getCurrentSeasonNumber(), playableVideo.getEpisodeNumber(), showDetails.getCurrentEpisodeId(), showDetails.getCurrentSeasonNumber(), showDetails.getCurrentEpisodeNumber()));
        return b;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onCWVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<CWVideo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onCWVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<CWVideo> parseFalcorResponse(String asJsonObject) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchcwvideosrequest", 2)) {}
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchcwvideosrequest", (String)asJsonObject);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            return new ArrayList<CWVideo>();
        }
        while (true) {
            while (true) {
                Label_0164: {
                    try {
                        if (this.cwIdInCache) {
                            asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.cwId);
                        }
                        else {
                            if (!this.lolomoIdInCache) {
                                break Label_0164;
                            }
                            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject("continueWatching");
                            this.browseCache.putCWLoMoId(FalcorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject2));
                            asJsonObject = asJsonObject2;
                        }
                        return buildCWVideoList((JsonObject)asJsonObject, this.fromVideo, this.toVideo, this.toSimilars, this.userConnectedToFacebook, this.browseCache);
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchcwvideosrequest", "String response to parse = " + (String)asJsonObject);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                }
                final JsonObject asJsonObject3 = dataObj.getAsJsonObject("lolomo");
                final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("continueWatching");
                this.browseCache.putCWLoMoId(FalcorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject4));
                PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject3);
                asJsonObject = asJsonObject4;
                continue;
            }
        }
    }
}
