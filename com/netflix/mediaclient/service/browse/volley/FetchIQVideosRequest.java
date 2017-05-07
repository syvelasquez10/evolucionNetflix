// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.service.webclient.model.branches.Video$Summary;
import java.util.ArrayList;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchIQVideosRequest extends FalkorVolleyWebClientRequest<List<Video>>
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
    
    public FetchIQVideosRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final boolean b, final BrowseAgentCallback responseCallback) {
        final boolean b2 = true;
        super(context);
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.browseCache = browseCache;
        this.iqId = browseCache.getIQLoMoId();
        this.iqInCache = (this.iqId != null);
        this.lolomoId = browseCache.getLoLoMoId();
        this.lolomoIdInCache = (this.lolomoId != null && b2);
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
        final StringBuilder append = sb.append("', {'to':").append(toVideo).append(",'from':").append(fromVideo).append("},");
        String s;
        if (b) {
            s = "['summary', 'kubrick']";
        }
        else {
            s = "'summary'";
        }
        this.pqlQuery = append.append(s).append("]").toString();
        if (Log.isLoggable("nf_service_browse_fetchiqvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchiqvideosrequest", "PQL = " + this.pqlQuery);
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
    protected List<Video> parseFalkorResponse(String asJsonObject) {
        if (Log.isLoggable("nf_service_browse_fetchiqvideosrequest", 2)) {}
        final ArrayList<Video$Summary> list = new ArrayList<Video$Summary>();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchiqvideosrequest", (String)asJsonObject);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return (List<Video>)list;
        }
    Label_0132_Outer:
        while (true) {
            while (true) {
                int n = 0;
                String string = null;
            Label_0284:
                while (true) {
                    Label_0233: {
                        try {
                            if (this.iqInCache) {
                                asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.iqId);
                            }
                            else {
                                if (!this.lolomoIdInCache) {
                                    break Label_0233;
                                }
                                final JsonObject asJsonObject2 = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject("queue");
                                this.browseCache.putIQLoMoId(FalkorParseUtils.getIdFromPath("nf_service_browse_fetchiqvideosrequest", asJsonObject2));
                                asJsonObject = asJsonObject2;
                            }
                            int i = this.toVideo;
                            n = 0;
                            while (i >= this.fromVideo) {
                                string = Integer.toString(i);
                                if (!((JsonObject)asJsonObject).has(string)) {
                                    break Label_0284;
                                }
                                final Video videoSummaryObject = FalkorParseUtils.createVideoSummaryObject(((JsonObject)asJsonObject).getAsJsonObject(string));
                                this.browseCache.updateInQueueCacheRecord(videoSummaryObject.getId(), true);
                                list.add(0, (Video$Summary)videoSummaryObject);
                                final int n2 = 1;
                                --i;
                                n = n2;
                            }
                            break;
                        }
                        catch (Exception ex) {
                            Log.v("nf_service_browse_fetchiqvideosrequest", "String response to parse = " + (String)asJsonObject);
                            throw new FalkorParseException("response missing expected json objects", ex);
                        }
                    }
                    final JsonObject asJsonObject3 = dataObj.getAsJsonObject("lolomo");
                    final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("queue");
                    this.browseCache.putIQLoMoId(FalkorParseUtils.getIdFromPath("nf_service_browse_fetchiqvideosrequest", asJsonObject4));
                    PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject3);
                    asJsonObject = asJsonObject4;
                    continue Label_0132_Outer;
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
        return (List<Video>)list;
    }
}
