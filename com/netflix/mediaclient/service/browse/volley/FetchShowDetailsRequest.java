// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.model.branches.Video$Bookmark;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Episode$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.service.webclient.model.branches.Video$HeroImages;
import com.netflix.mediaclient.service.webclient.model.branches.Video$KubrickSummary;
import com.netflix.mediaclient.service.webclient.model.leafs.social.SocialEvidence;
import com.netflix.mediaclient.service.webclient.model.branches.Video$InQueue;
import com.netflix.mediaclient.service.webclient.model.branches.Video$UserRating;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Detail;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.details.SeasonDetails;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import android.util.Pair;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchShowDetailsRequest extends FalkorVolleyWebClientRequest<Pair<ShowDetails, List<SeasonDetails>>>
{
    private static final String FIELD_VIDEOS = "videos";
    private static final int FROM_SEASON = 0;
    private static final String TAG = "nf_service_browse_fetchshowdetailsrequest";
    private static final int TO_SEASON = 99;
    private final BrowseWebClientCache browseCache;
    private final int fromSimilarVideo;
    private final boolean includeSeasons;
    private final boolean isCurrentEpisodeLocal;
    private final String mReqEpisodeId;
    private final String mShowId;
    private final String pqlQuery;
    private final String pqlQuery2;
    private String pqlQuery3;
    private String pqlQuery4;
    private String pqlSeasons;
    private final BrowseAgentCallback responseCallback;
    private final int toSimilarVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchShowDetailsRequest(final Context context, final BrowseWebClientCache browseCache, final String mShowId, final String mReqEpisodeId, final int toSimilarVideo, final boolean includeSeasons, final boolean b, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mShowId = mShowId;
        this.mReqEpisodeId = mReqEpisodeId;
        this.fromSimilarVideo = 0;
        this.toSimilarVideo = toSimilarVideo;
        this.isCurrentEpisodeLocal = StringUtils.isNotEmpty(this.mReqEpisodeId);
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.includeSeasons = includeSeasons;
        this.browseCache = browseCache;
        final StringBuilder sb = new StringBuilder("['summary','detail', 'rating', 'inQueue', 'socialEvidence'");
        if (b) {
            sb.append(",'kubrick','heroImgs','evidence'");
        }
        sb.append("]");
        this.pqlQuery = String.format("['videos','%s',%s]", this.mShowId, sb.toString());
        if (this.isCurrentEpisodeLocal) {
            this.pqlQuery2 = String.format("['episodes','%s', ['detail', 'bookmark']]", this.mReqEpisodeId);
        }
        else {
            this.pqlQuery2 = String.format("['videos','%s','episodes', 'current', ['detail', 'bookmark']]", this.mShowId);
        }
        if (b) {
            this.pqlQuery3 = String.format("['videos', '%s', 'similars', {'from':%d,'to':%d}, 'summary']", this.mShowId, this.fromSimilarVideo, toSimilarVideo);
            this.pqlQuery4 = String.format("['videos', '%s', 'similars', 'summary']", this.mShowId);
        }
        if (includeSeasons) {
            this.pqlSeasons = String.format("['videos', '%s', 'seasons', {'from':%d,'to':%d}, 'detail']", this.mShowId, 0, 99);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.pqlQuery, this.pqlQuery2));
        if (this.pqlQuery3 != null) {
            list.add(this.pqlQuery3);
        }
        if (this.pqlQuery4 != null) {
            list.add(this.pqlQuery4);
        }
        if (this.pqlSeasons != null) {
            list.add(this.pqlSeasons);
        }
        if (Log.isLoggable("nf_service_browse_fetchshowdetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchshowdetailsrequest", "PQLs = " + list);
        }
        return list;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onShowDetailsFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final Pair<ShowDetails, List<SeasonDetails>> pair) {
        if (this.responseCallback != null) {
            if (!this.includeSeasons) {
                this.responseCallback.onShowDetailsFetched((com.netflix.mediaclient.servicemgr.model.details.ShowDetails)pair.first, CommonStatus.OK);
                return;
            }
            this.responseCallback.onShowDetailsAndSeasonsFetched((com.netflix.mediaclient.servicemgr.model.details.ShowDetails)pair.first, (List<SeasonDetails>)pair.second, CommonStatus.OK);
        }
    }
    
    @Override
    protected Pair<ShowDetails, List<SeasonDetails>> parseFalkorResponse(String s) {
        if (Log.isLoggable("nf_service_browse_fetchshowdetailsrequest", 2)) {
            Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchshowdetailsrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("ShowDetails empty!!!");
        }
        while (true) {
            while (true) {
                ShowDetails showDetails;
                try {
                    final JsonObject asJsonObject = dataObj.getAsJsonObject("videos").getAsJsonObject(this.mShowId);
                    showDetails = new ShowDetails();
                    showDetails.summary = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", Video$Summary.class);
                    showDetails.detail = FalkorParseUtils.getPropertyObject(asJsonObject, "detail", Video$Detail.class);
                    showDetails.rating = FalkorParseUtils.getPropertyObject(asJsonObject, "rating", Video$UserRating.class);
                    showDetails.inQueue = FalkorParseUtils.getPropertyObject(asJsonObject, "inQueue", Video$InQueue.class);
                    showDetails.socialEvidence = FalkorParseUtils.getPropertyObject(asJsonObject, "socialEvidence", SocialEvidence.class);
                    showDetails.kubrickSummary = FalkorParseUtils.getPropertyObject(asJsonObject, "kubrick", Video$KubrickSummary.class);
                    showDetails.heroImgs = FalkorParseUtils.getPropertyObject(asJsonObject, "heroImgs", Video$HeroImages.class);
                    showDetails.evidence = FalkorParseUtils.buildEvidence(asJsonObject);
                    FalkorParseUtils.buildSimilarVideosList(asJsonObject, showDetails, this.toSimilarVideo);
                    showDetails.userConnectedToFacebook = this.userConnectedToFacebook;
                    if (!this.isCurrentEpisodeLocal) {
                        if (asJsonObject.has("episodes")) {
                            s = (String)asJsonObject.getAsJsonObject("episodes");
                            if (((JsonObject)s).has("current")) {
                                s = (String)((JsonObject)s).getAsJsonObject("current");
                                showDetails.currentEpisode = FalkorParseUtils.getPropertyObject((JsonObject)s, "detail", Episode$Detail.class);
                                showDetails.currentEpisodeBookmark = FalkorParseUtils.getPropertyObject((JsonObject)s, "bookmark", Video$Bookmark.class);
                            }
                        }
                        s = (String)new ArrayList();
                        if (asJsonObject.has("seasons")) {
                            FalkorParseUtils.buildSeasonsList("nf_service_browse_fetchshowdetailsrequest", asJsonObject.getAsJsonObject("seasons"), (List<SeasonDetails>)s, 0, 99);
                        }
                        showDetails.inQueue = this.browseCache.updateInQueueCacheRecord(showDetails.getId(), showDetails.inQueue);
                        return (Pair<ShowDetails, List<SeasonDetails>>)new Pair((Object)showDetails, (Object)s);
                    }
                }
                catch (Exception ex) {
                    Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
                    throw new FalkorParseException("response missing expected json objects", ex);
                }
                if (!dataObj.has("episodes")) {
                    continue;
                }
                final JsonObject asJsonObject2 = dataObj.getAsJsonObject("episodes");
                try {
                    final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject(this.mReqEpisodeId);
                    showDetails.currentEpisode = FalkorParseUtils.getPropertyObject(asJsonObject3, "detail", Episode$Detail.class);
                    showDetails.currentEpisodeBookmark = FalkorParseUtils.getPropertyObject(asJsonObject3, "bookmark", Video$Bookmark.class);
                    continue;
                }
                catch (Exception ex2) {
                    Log.v("nf_service_browse_fetchshowdetailsrequest", "String response to parse = " + s);
                    throw new FalkorParseException("response missing expected json objects", ex2);
                }
                break;
            }
        }
    }
}
