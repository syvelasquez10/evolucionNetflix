// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.model.ShowDetails;
import com.netflix.mediaclient.service.webclient.model.MovieDetails;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchIQVideosRequest extends FalcorVolleyWebClientRequest<List<Video>>
{
    private static final String FIELD_LISTS = "lists";
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_QUEUE = "queue";
    private static final String TAG = "nf_service_browse_fetchiqvideosrequest";
    private final BrowseWebClientCache browseCache;
    private final int fromVideo;
    private final String iqId;
    private final boolean iqInCache;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    
    public FetchIQVideosRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final BrowseAgentCallback responseCallback) {
        final boolean b = true;
        super(context);
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.browseCache = browseCache;
        this.iqId = browseCache.getIQLoMoId();
        this.iqInCache = (this.iqId != null);
        this.lolomoId = browseCache.getLoLoMoId();
        this.lolomoIdInCache = (this.lolomoId != null && b);
        StringBuilder sb;
        if (this.iqInCache) {
            sb = new StringBuilder("['lists','").append(this.iqId);
        }
        else if (this.lolomoIdInCache) {
            sb = new StringBuilder("['lolomos','").append(this.lolomoId).append("','queue");
        }
        else {
            sb = new StringBuilder("['lolomo',").append("'queue");
        }
        this.pqlQuery = sb.append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("},'summary']").toString();
        if (Log.isLoggable("nf_service_browse_fetchiqvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchiqvideosrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public static void updateMdpAndSdpWithIQInfo(final BrowseWebClientCache browseWebClientCache, final String s, final boolean b) {
        while (true) {
            Label_0096: {
                synchronized (FetchIQVideosRequest.class) {
                    final Object fromCaches = browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_MDP, s, "0", "0"));
                    if (fromCaches != null) {
                        final MovieDetails movieDetails = (MovieDetails)fromCaches;
                        if (movieDetails.inQueue != null) {
                            movieDetails.inQueue.inQueue = b;
                        }
                    }
                    else {
                        final Object fromCaches2 = browseWebClientCache.getFromCaches(BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_SDP, s, "0", "0"));
                        if (fromCaches2 == null) {
                            break Label_0096;
                        }
                        final ShowDetails showDetails = (ShowDetails)fromCaches2;
                        if (showDetails.inQueue != null) {
                            showDetails.inQueue.inQueue = b;
                        }
                    }
                    return;
                }
            }
            Log.d("nf_service_browse_fetchiqvideosrequest", "updateMdpWithIQInfo !(movie or show) id:" + s);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(Collections.emptyList(), status);
        }
    }
    
    @Override
    protected void onSuccess(final List<Video> list) {
        if (this.responseCallback != null) {
            this.responseCallback.onVideosFetched(list, CommonStatus.OK);
        }
    }
    
    @Override
    protected List<Video> parseFalcorResponse(String asJsonObject) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_fetchiqvideosrequest", 2)) {}
        final ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary> list = (ArrayList<com.netflix.mediaclient.service.webclient.model.branches.Video.Summary>)new ArrayList<Video>();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_fetchiqvideosrequest", (String)asJsonObject);
        if (!FalcorParseUtils.isEmpty(dataObj)) {
        Label_0137_Outer:
            while (true) {
                while (true) {
                    int n = 0;
                    String string = null;
                Label_0290:
                    while (true) {
                        Label_0239: {
                            try {
                                if (this.iqInCache) {
                                    asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.iqId);
                                }
                                else {
                                    if (!this.lolomoIdInCache) {
                                        break Label_0239;
                                    }
                                    final JsonObject asJsonObject2 = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject("queue");
                                    this.browseCache.putIQLoMoId(FalcorParseUtils.getIdFromPath("nf_service_browse_fetchiqvideosrequest", asJsonObject2));
                                    asJsonObject = asJsonObject2;
                                }
                                n = 0;
                                int n2;
                                for (int i = this.toVideo; i >= this.fromVideo; --i, n = n2) {
                                    string = Integer.toString(i);
                                    if (!((JsonObject)asJsonObject).has(string)) {
                                        break Label_0290;
                                    }
                                    n2 = 1;
                                    final com.netflix.mediaclient.service.webclient.model.branches.Video.Summary summary = FalcorParseUtils.getPropertyObject(((JsonObject)asJsonObject).getAsJsonObject(string), "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
                                    updateMdpAndSdpWithIQInfo(this.browseCache, summary.getId(), true);
                                    list.add(0, summary);
                                }
                                break;
                            }
                            catch (Exception ex) {
                                Log.v("nf_service_browse_fetchiqvideosrequest", "String response to parse = " + (String)asJsonObject);
                                throw new FalcorParseException("response missing expected json objects", ex);
                            }
                        }
                        final JsonObject asJsonObject3 = dataObj.getAsJsonObject("lolomo");
                        final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("queue");
                        this.browseCache.putIQLoMoId(FalcorParseUtils.getIdFromPath("nf_service_browse_fetchiqvideosrequest", asJsonObject4));
                        PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject3);
                        asJsonObject = asJsonObject4;
                        continue Label_0137_Outer;
                    }
                    int n2;
                    if ((n2 = n) != 0) {
                        Log.d("nf_service_browse_fetchiqvideosrequest", String.format("Adding sentinel at index %s", string));
                        list.add(0, BrowseVideoSentinels.getUnavailableVideoSummary());
                        n2 = n;
                        continue;
                    }
                    continue;
                }
            }
        }
        return (List<Video>)list;
    }
}
