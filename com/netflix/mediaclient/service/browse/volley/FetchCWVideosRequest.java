// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import java.util.Collections;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.BrowseVideoSentinels;
import com.netflix.mediaclient.service.webclient.model.PlayableVideo;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import java.util.ArrayList;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchCWVideosRequest extends FalkorVolleyWebClientRequest<List<CWVideo>>
{
    public static final String CW_SUB_QUERY1 = "%s, {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill']]";
    public static final String CW_SUB_QUERY2 = "%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]";
    private static final String FIELD_CW = "continueWatching";
    private static final String FIELD_LISTS = "lists";
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String TAG = "nf_service_browse_fetchcwvideosrequest";
    private final BrowseWebClientCache browseCache;
    private final String cwId;
    private final boolean cwIdInCache;
    private final int fromVideo;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public FetchCWVideosRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final int n, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
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
        this.pqlQuery = String.format("%s, {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill']]", s, fromVideo, toVideo);
        this.pqlQuery2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", s, fromVideo, toVideo);
        if (Log.isLoggable("nf_service_browse_fetchcwvideosrequest", 2)) {
            Log.v("nf_service_browse_fetchcwvideosrequest", "PQL = " + this.pqlQuery + " " + this.pqlQuery2);
        }
    }
    
    public static List<CWVideo> buildCWVideoList(final JsonObject jsonObject, final int n, int i, final boolean b, final BrowseWebClientCache browseWebClientCache) {
        final ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo> list = (ArrayList<com.netflix.mediaclient.service.webclient.model.CWVideo>)new ArrayList<CWVideo>();
        int n2 = 0;
        while (i >= n) {
            final String string = Integer.toString(i);
            int n3;
            if (jsonObject.has(string)) {
                final com.netflix.mediaclient.service.webclient.model.CWVideo cwVideo = new com.netflix.mediaclient.service.webclient.model.CWVideo();
                FalkorParseUtils.fillPlayableVideo(jsonObject.getAsJsonObject(string), cwVideo, b);
                cwVideo.inQueue = browseWebClientCache.updateInQueueCacheRecord(cwVideo.getId(), cwVideo.inQueue);
                list.add(0, cwVideo);
                n3 = 1;
            }
            else if ((n3 = n2) != 0) {
                if (Log.isLoggable("nf_service_browse_fetchcwvideosrequest", 3)) {
                    Log.d("nf_service_browse_fetchcwvideosrequest", String.format("Adding sentinel at index %s", string));
                }
                list.add(0, BrowseVideoSentinels.getUnavailableCwVideo());
                n3 = n2;
            }
            --i;
            n2 = n3;
        }
        return (List<CWVideo>)list;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery, this.pqlQuery2);
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
    protected List<CWVideo> parseFalkorResponse(String asJsonObject) {
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_fetchcwvideosrequest", (String)asJsonObject);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            return new ArrayList<CWVideo>();
        }
        while (true) {
            while (true) {
                Label_0149: {
                    try {
                        if (this.cwIdInCache) {
                            asJsonObject = dataObj.getAsJsonObject("lists").getAsJsonObject(this.cwId);
                        }
                        else {
                            if (!this.lolomoIdInCache) {
                                break Label_0149;
                            }
                            final JsonObject asJsonObject2 = dataObj.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject("continueWatching");
                            this.browseCache.putCWLoMoId(FalkorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject2));
                            asJsonObject = asJsonObject2;
                        }
                        return buildCWVideoList((JsonObject)asJsonObject, this.fromVideo, this.toVideo, this.userConnectedToFacebook, this.browseCache);
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_fetchcwvideosrequest", "String response to parse = " + (String)asJsonObject);
                        throw new FalkorParseException("response missing expected json objects", ex);
                    }
                }
                final JsonObject asJsonObject3 = dataObj.getAsJsonObject("lolomo");
                final JsonObject asJsonObject4 = asJsonObject3.getAsJsonObject("continueWatching");
                this.browseCache.putCWLoMoId(FalkorParseUtils.getIdFromPath("nf_service_browse_fetchcwvideosrequest", asJsonObject4));
                PrefetchHomeLoLoMoRequest.putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject3);
                asJsonObject = asJsonObject4;
                continue;
            }
        }
    }
}
