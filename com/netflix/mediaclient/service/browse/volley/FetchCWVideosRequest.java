// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Collections;
import java.util.Arrays;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.leafs.TrackableListSummary;
import com.netflix.mediaclient.service.webclient.model.branches.Episode;
import com.netflix.mediaclient.servicemgr.VideoType;
import com.netflix.mediaclient.service.webclient.model.leafs.SocialEvidence;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.servicemgr.CWVideo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchCWVideosRequest extends FalcorVolleyWebClientRequest<List<CWVideo>>
{
    private static final int BOOKMARK_MARGIN_MS = 90000;
    private static final String BOOKMARK_TAG = "nf_bookmark";
    private static final String FIELD_CW = "continueWatching";
    private static final String FIELD_LISTS = "lists";
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String TAG = "nf_service_browse_fetchcwvideosrequest";
    private final String cwId;
    private final boolean cwIdInCache;
    private final int fromSimilars;
    private final int fromVideo;
    private final HardCache hardCache;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache softCache;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    private final SoftCache weakSeasonsCache;
    
    public FetchCWVideosRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final SoftCache weakSeasonsCache, final int fromVideo, final int toVideo, final int toSimilars, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        final boolean b = true;
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.weakSeasonsCache = weakSeasonsCache;
        this.cwId = BrowseAgent.getCWLoMoId(hardCache);
        this.cwIdInCache = (this.cwId != null);
        this.lolomoId = BrowseAgent.getLoLoMoId(hardCache);
        this.lolomoIdInCache = (this.lolomoId != null && b);
        StringBuilder sb;
        if (this.cwIdInCache) {
            sb = new StringBuilder("['lists','").append(this.cwId);
        }
        else if (this.lolomoIdInCache) {
            sb = new StringBuilder("['lolomos','").append(this.lolomoId).append("','continueWatching");
        }
        else {
            sb = new StringBuilder("['lolomo',").append("'continueWatching");
        }
        this.pqlQuery = new StringBuilder(sb).append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("},['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]").toString();
        this.pqlQuery2 = new StringBuilder(sb).append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("},'episodes', 'current', ['detail', 'bookmark']]").toString();
        this.pqlQuery3 = new StringBuilder(sb).append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("}, 'similars',").append("{'to':").append(toSimilars).append(",'from':").append(this.fromSimilars).append("}, 'summary']").toString();
        this.pqlQuery4 = new StringBuilder(sb).append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("}, 'similars', 'summary']").toString();
        if (Log.isLoggable("nf_service_browse_fetchcwvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchcwvideosrequest", "PQL = " + this.pqlQuery + " " + this.pqlQuery2 + " " + this.pqlQuery3 + " " + this.pqlQuery4);
        }
    }
    
    public static List<CWVideo> buildCWVideoList(final JsonObject jsonObject, final int n, int i, final int n2, final boolean userConnectedToFacebook, final HardCache hardCache, final SoftCache softCache, final SoftCache softCache2) {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo> list = (ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo>)new ArrayList<CWVideo>();
        int n3 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n4;
            if (jsonObject.has(string)) {
                n4 = 1;
                final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo = new com.netflix.mediaclient.service.webclient.model.CWVideo();
                final JsonObject asJsonObject = jsonObject.getAsJsonObject(string);
                cwVideo.summary = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", Video.Summary.class);
                cwVideo.detail = FalcorParseUtils.getPropertyObject(asJsonObject, "detail", Video.Detail.class);
                cwVideo.rating = FalcorParseUtils.getPropertyObject(asJsonObject, "rating", Video.Rating.class);
                cwVideo.inQueue = FalcorParseUtils.getPropertyObject(asJsonObject, "inQueue", Video.InQueue.class);
                cwVideo.bookmark = FalcorParseUtils.getPropertyObject(asJsonObject, "bookmark", Video.Bookmark.class);
                cwVideo.bookmarkStill = FalcorParseUtils.getPropertyObject(asJsonObject, "bookmarkStill", Video.BookmarkStill.class);
                cwVideo.socialEvidence = FalcorParseUtils.getPropertyObject(asJsonObject, "socialEvidence", SocialEvidence.class);
                if (!VideoType.MOVIE.equals(cwVideo.summary.getType()) && asJsonObject.has("episodes")) {
                    final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("episodes");
                    if (asJsonObject2.has("current")) {
                        final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("current");
                        cwVideo.currentEpisode = FalcorParseUtils.getPropertyObject(asJsonObject3, "detail", Episode.Detail.class);
                        cwVideo.currentEpisodeBookmark = FalcorParseUtils.getPropertyObject(asJsonObject3, "bookmark", Video.Bookmark.class);
                    }
                }
                cwVideo.userConnectedToFacebook = userConnectedToFacebook;
                final ArrayList<Video.Summary> similarVideos = new ArrayList<Video.Summary>();
                if (asJsonObject.has("similars")) {
                    final JsonObject asJsonObject4 = asJsonObject.getAsJsonObject("similars");
                    for (int j = 0; j <= n2; ++j) {
                        final String string2 = Integer.toString(j);
                        if (asJsonObject4.has(string2)) {
                            similarVideos.add(FalcorParseUtils.getPropertyObject(asJsonObject4.getAsJsonObject(string2), "summary", Video.Summary.class));
                        }
                    }
                    cwVideo.similarListSummary = FalcorParseUtils.getPropertyObject(asJsonObject4, "summary", TrackableListSummary.class);
                }
                cwVideo.similarVideos = (List<com.netflix.mediaclient.servicemgr.Video>)similarVideos;
                if (VideoType.MOVIE.equals(cwVideo.getType())) {
                    mergeWithMDP(hardCache, softCache, softCache2, cwVideo);
                }
                else {
                    mergeWithSDP(hardCache, softCache, softCache2, cwVideo);
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
    
    private static void buildMdpFromCw(final SoftCache softCache, final String s, final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo) {
        if (cwVideo != null) {
            final MovieDetails movieDetails = new MovieDetails();
            final ArrayList list = new ArrayList();
            if (movieDetails != null && list != null) {
                movieDetails.summary = cwVideo.summary;
                movieDetails.detail = cwVideo.detail;
                movieDetails.rating = cwVideo.rating;
                movieDetails.inQueue = cwVideo.inQueue;
                movieDetails.bookmark = cwVideo.bookmark;
                movieDetails.bookmarkStill = cwVideo.bookmarkStill;
                movieDetails.socialEvidence = cwVideo.socialEvidence;
                movieDetails.similarVideos = cwVideo.similarVideos;
                movieDetails.similarListSummary = cwVideo.similarListSummary;
                movieDetails.userConnectedToFacebook = cwVideo.userConnectedToFacebook;
                softCache.put(s, movieDetails);
                Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Created MDP from cw for (%s) %s", movieDetails.getId(), movieDetails.getTitle()));
            }
        }
    }
    
    private static void buildSdpFromCw(final SoftCache softCache, final String s, final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo) {
        if (cwVideo == null) {
            return;
        }
        final ShowDetails showDetails = new ShowDetails();
        showDetails.summary = cwVideo.summary;
        showDetails.detail = cwVideo.detail;
        showDetails.rating = cwVideo.rating;
        showDetails.inQueue = cwVideo.inQueue;
        showDetails.bookmark = cwVideo.bookmark;
        showDetails.bookmarkStill = cwVideo.bookmarkStill;
        showDetails.socialEvidence = cwVideo.socialEvidence;
        showDetails.currentEpisode = cwVideo.currentEpisode;
        showDetails.currentEpisodeBookmark = cwVideo.currentEpisodeBookmark;
        showDetails.userConnectedToFacebook = cwVideo.userConnectedToFacebook;
        softCache.put(s, showDetails);
        Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Created SDP from cw for (%s) %s", showDetails.getId(), showDetails.getTitle()));
    }
    
    public static void mergeWithMDP(final HardCache hardCache, final SoftCache softCache, SoftCache buildBrowseCacheKey, final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo) {
        while (true) {
            synchronized (FetchCWVideosRequest.class) {
                buildBrowseCacheKey = (SoftCache)BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, cwVideo.getId(), "0", "0");
                final Object fromCaches = BrowseAgent.getFromCaches(hardCache, softCache, (String)buildBrowseCacheKey);
                if (fromCaches != null) {
                    final MovieDetails movieDetails = (MovieDetails)fromCaches;
                    if (movieDetails.bookmark != null) {
                        Log.d("nf_bookmark", String.format("mergeWithMDP: %s - server bookmarkTime %d, bookmark %d : local bookmarkTime %d bookmark %d", cwVideo.getId(), cwVideo.getBookmarkLastUpdateTime(), cwVideo.getBookmarkPosition(), movieDetails.getPlayableBookmarkUpdateTime(), movieDetails.getBookmarkPosition()));
                        if (useLocalMdpBookmark(cwVideo, movieDetails)) {
                            cwVideo.bookmark.setBookmarkPosition(movieDetails.getBookmarkPosition());
                            cwVideo.bookmark.setLastModified(movieDetails.getPlayableBookmarkUpdateTime());
                            if (cwVideo.bookmarkStill != null) {
                                cwVideo.bookmarkStill.stillUrl = BrowseAgent.buildStillUrlFromPos(cwVideo.getBaseUrl(), cwVideo.getPlayableBookmarkPosition(), cwVideo.getEndtime());
                            }
                            Log.d("nf_bookmark", String.format("mergeWithMDP: %s using local MDP bookmark; new bookmarkTime %d, bookmark %d ", cwVideo.getId(), cwVideo.getBookmarkLastUpdateTime(), cwVideo.getBookmarkPosition()));
                        }
                        else if (cwVideo.getBookmarkPosition() != movieDetails.getBookmarkPosition()) {
                            movieDetails.bookmark.setBookmarkPosition(cwVideo.getBookmarkPosition());
                            movieDetails.bookmark.setLastModified(cwVideo.getBookmarkLastUpdateTime());
                            Log.d("nf_bookmark", String.format("mergeWithMDP: %s using CW bookmark; new bookmarkTime %d, bookmark %d  ", movieDetails.getId(), movieDetails.getPlayableBookmarkUpdateTime(), movieDetails.getBookmarkPosition()));
                        }
                        movieDetails.userConnectedToFacebook = cwVideo.userConnectedToFacebook;
                    }
                    return;
                }
            }
            buildMdpFromCw(softCache, (String)buildBrowseCacheKey, cwVideo);
        }
    }
    
    public static void mergeWithSDP(final HardCache hardCache, SoftCache currentEpisodeBookmark, final SoftCache softCache, final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo) {
        while (true) {
            final String buildBrowseCacheKey;
            Label_0364: {
                Label_0274: {
                    synchronized (FetchCWVideosRequest.class) {
                        buildBrowseCacheKey = BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, cwVideo.getId(), "0", "0");
                        final Object fromCaches = BrowseAgent.getFromCaches(hardCache, currentEpisodeBookmark, buildBrowseCacheKey);
                        if (fromCaches != null) {
                            final ShowDetails showDetails = (ShowDetails)fromCaches;
                            showDetails.userConnectedToFacebook = cwVideo.userConnectedToFacebook;
                            if (showDetails.currentEpisode != null && showDetails.currentEpisodeBookmark != null && cwVideo.currentEpisode != null) {
                                currentEpisodeBookmark = (SoftCache)cwVideo.currentEpisodeBookmark;
                                if (currentEpisodeBookmark != null) {
                                    Log.d("nf_bookmark", String.format("mergeWithSDP: %s - server ce %s bookmarkTime %d, bookmark %d : local ce %s bookmarkTime %d bookmark %d ", cwVideo.getId(), cwVideo.getCurrentEpisodeId(), cwVideo.getBookmarkLastUpdateTime(), cwVideo.getBookmarkPosition(), showDetails.getCurrentEpisodeId(), showDetails.getBookmarkCreationTime(), showDetails.getBookmarkPosition()));
                                    if (!useLocalSdpBookmark(cwVideo, showDetails)) {
                                        break Label_0274;
                                    }
                                    cwVideo.currentEpisode = showDetails.currentEpisode;
                                    cwVideo.currentEpisodeBookmark = showDetails.currentEpisodeBookmark;
                                    if (cwVideo.bookmarkStill != null) {
                                        cwVideo.bookmarkStill.stillUrl = BrowseAgent.buildStillUrlFromPos(cwVideo.currentEpisode.getBaseUrl(), cwVideo.getPlayableBookmarkPosition(), cwVideo.getEndtime());
                                    }
                                    Log.d("nf_bookmark", String.format("mergeWithSDP: %s using local SDP; new ce %s bookmarkTime %d, bookmark %d ", cwVideo.getId(), cwVideo.getCurrentEpisodeId(), cwVideo.getBookmarkLastUpdateTime(), cwVideo.getBookmarkPosition()));
                                }
                            }
                            return;
                        }
                        break Label_0364;
                    }
                }
                final ShowDetails showDetails2;
                showDetails2.currentEpisode = cwVideo.currentEpisode;
                showDetails2.currentEpisodeBookmark = cwVideo.currentEpisodeBookmark;
                BrowseAgent.updateSeasonsInformation(softCache, cwVideo.currentEpisode.getSeasonId(), cwVideo.currentEpisode.getEpisodeNumber());
                Log.d("nf_bookmark", String.format("mergeWithSDP: %s updating local SDP ce %s bookmarkTime %d, bookmark %d  ", showDetails2.getId(), showDetails2.currentEpisode.getId(), showDetails2.getBookmarkCreationTime(), showDetails2.getBookmarkPosition()));
                return;
            }
            buildSdpFromCw(currentEpisodeBookmark, buildBrowseCacheKey, cwVideo);
        }
    }
    
    private static boolean useLocalMdpBookmark(final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo, final MovieDetails movieDetails) {
        return movieDetails.getPlayableBookmarkUpdateTime() != 0L && cwVideo.getBookmarkPosition() != movieDetails.getBookmarkPosition() && cwVideo.getBookmarkLastUpdateTime() < movieDetails.getPlayableBookmarkUpdateTime() + 90000L;
    }
    
    private static boolean useLocalSdpBookmark(final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo, final ShowDetails showDetails) {
        if (showDetails.getBookmarkCreationTime() == 0L) {
            return false;
        }
        boolean b;
        if (StringUtils.safeEquals(cwVideo.getCurrentEpisodeId(), showDetails.getCurrentEpisodeId())) {
            b = (cwVideo.getBookmarkPosition() != showDetails.getBookmarkPosition() && cwVideo.getBookmarkLastUpdateTime() < showDetails.getBookmarkCreationTime() + 90000L);
        }
        else if (cwVideo.getCurrentSeasonNumber() == showDetails.getCurrentSeasonNumber()) {
            b = (showDetails.getCurrentEpisodeNumber() > cwVideo.getCurrentEpisodeNumber());
        }
        else {
            b = (showDetails.getCurrentEpisodeNumber() < cwVideo.getCurrentEpisodeNumber());
        }
        Log.v("nf_bookmark", String.format("useLocalSdpBookmark: %s useLocal ? %b, cw-ep %s cw-sn %d, cw-en %d, sdp-ep %s sdp-sn %d, sdp-en %d", showDetails.getId(), b, cwVideo.getCurrentEpisodeId(), cwVideo.getCurrentSeasonNumber(), cwVideo.getEpisodeNumber(), showDetails.getCurrentEpisodeId(), showDetails.getCurrentSeasonNumber(), showDetails.getCurrentEpisodeNumber()));
        return b;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onCWVideosFetched(Collections.emptyList(), n);
        }
    }
    
    @Override
    protected void onSuccess(final List<CWVideo> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onCWVideosFetched(list, 0);
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
                Label_0172: {
                    try {
                        if (this.cwIdInCache) {
                            asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.cwId);
                        }
                        else {
                            if (!this.lolomoIdInCache) {
                                break Label_0172;
                            }
                            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject("continueWatching");
                            BrowseAgent.putCWLoMoId(this.hardCache, FalcorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject2));
                            asJsonObject = asJsonObject2;
                        }
                        return buildCWVideoList((JsonObject)asJsonObject, this.fromVideo, this.toVideo, this.toSimilars, this.userConnectedToFacebook, this.hardCache, this.softCache, this.weakSeasonsCache);
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchcwvideosrequest", "String response to parse = " + (String)asJsonObject);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                }
                final JsonObject asJsonObject3 = dataObj.getAsJsonObject("lolomo");
                final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("continueWatching");
                BrowseAgent.putCWLoMoId(this.hardCache, FalcorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject4));
                PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.hardCache, asJsonObject3);
                asJsonObject = asJsonObject4;
                continue;
            }
        }
    }
}
