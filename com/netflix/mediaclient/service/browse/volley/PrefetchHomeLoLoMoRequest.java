// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class PrefetchHomeLoLoMoRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_PATH = "path";
    private static final String TAG = "nf_service_browse_prefetchhomelolomorequest";
    private final BrowseWebClientCache browseCache;
    private final int fromBBVideo;
    private final int fromCWVideo;
    private int fromCharactersVideo;
    private final int fromLoMo;
    private final int fromSimilars;
    private final int fromVideo;
    private final String pqlBB1;
    private final String pqlBB2;
    private final String pqlBB3;
    private final String pqlBB4;
    private String pqlCharactersQuery;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private final String pqlQuery5;
    private final String pqlQuery6;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    private final int toBBVideo;
    private final int toCWVideo;
    private int toCharactersVideo;
    private final int toLoMo;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public PrefetchHomeLoLoMoRequest(final Context context, final BrowseWebClientCache browseCache, final int toLoMo, final int toVideo, final int toCWVideo, final int toBBVideo, final int toSimilars, final boolean b, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.fromCharactersVideo = -1;
        this.toCharactersVideo = -1;
        this.responseCallback = responseCallback;
        this.fromLoMo = 0;
        this.toLoMo = toLoMo;
        this.fromVideo = 0;
        this.toVideo = toVideo;
        this.fromCWVideo = 0;
        this.toCWVideo = toCWVideo;
        this.fromBBVideo = 0;
        this.toBBVideo = toBBVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['lolomo', {'from':%d,'to':%d}, 'summary']", this.fromLoMo, toLoMo);
        this.pqlQuery2 = String.format("['lolomo', {'from':%d,'to':%d}, {'from':%d,'to':%d}, 'summary']", this.fromLoMo, toLoMo, this.fromVideo, toVideo);
        this.pqlQuery3 = String.format("['lolomo','continueWatching', {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]", this.fromCWVideo, toCWVideo);
        this.pqlQuery4 = String.format("['lolomo','continueWatching', {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", this.fromCWVideo, toCWVideo);
        this.pqlQuery5 = String.format("['lolomo','continueWatching', {'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']", this.fromCWVideo, toCWVideo, this.fromSimilars, toSimilars);
        this.pqlQuery6 = String.format("['lolomo','continueWatching', {'from':%d,'to':%d}, 'similars', 'summary']", this.fromCWVideo, toCWVideo);
        this.pqlBB1 = String.format("%s, {'from':%d,'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]", "['lolomo','billboard'", this.fromBBVideo, toBBVideo);
        this.pqlBB2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", "['lolomo','billboard'", this.fromBBVideo, toBBVideo);
        this.pqlBB3 = String.format("%s, {'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']", "['lolomo','billboard'", this.fromBBVideo, toBBVideo, this.fromSimilars, toSimilars);
        this.pqlBB4 = String.format("%s, {'from':%d,'to':%d}, 'similars', 'summary']", "['lolomo','billboard'", this.fromBBVideo, toBBVideo);
        if (b) {
            this.fromCharactersVideo = toVideo + 1;
            this.toCharactersVideo = toVideo - this.fromVideo + this.fromCharactersVideo;
            this.pqlCharactersQuery = String.format("['lolomo','characters',{'from':%d,'to':%d},['summary']]", this.fromCharactersVideo, this.toCharactersVideo);
        }
        if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL = " + this.pqlQuery);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL2 = " + this.pqlQuery2);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL3 = " + this.pqlQuery3);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL4 = " + this.pqlQuery4);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL5 = " + this.pqlQuery5);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL6 = " + this.pqlQuery6);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB1 = " + this.pqlBB1);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB2 = " + this.pqlBB2);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB3 = " + this.pqlBB3);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB4 = " + this.pqlBB4);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "pqlCharactersQuery = " + this.pqlCharactersQuery);
        }
        this.rStart = System.nanoTime();
    }
    
    private void handleExtraChacterDataIfAvailable(final JsonObject jsonObject) {
        if (!jsonObject.has("characters")) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "No extra characters found in lolomo data");
        }
        else {
            final JsonObject asJsonObject = jsonObject.getAsJsonObject("characters");
            final List<Video> buildVideoList = FetchVideosRequest.buildVideoList(LoMoType.CHARACTERS, asJsonObject, this.fromCharactersVideo, this.toCharactersVideo, false);
            if (!asJsonObject.has("path")) {
                Log.w("nf_service_browse_prefetchhomelolomorequest", "Chars json does not have a path field - can't parse list id");
                return;
            }
            final JsonArray asJsonArray = asJsonObject.getAsJsonArray("path");
            if (asJsonArray == null || asJsonArray.size() < 2) {
                Log.w("nf_service_browse_prefetchhomelolomorequest", "Invalid path array for characters json [path: " + asJsonArray + "]");
                return;
            }
            final String asString = asJsonArray.get(1).getAsString();
            this.browseCache.putLoMoInBrowseCache(asString, buildVideoList, this.fromCharactersVideo, this.toCharactersVideo);
            if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
                Log.v("nf_service_browse_prefetchhomelolomorequest", "Found " + buildVideoList.size() + " extra characters in lolomoObj, list id: " + asString);
            }
        }
    }
    
    public static void injectSocialData(final LoMo loMo, final List<Video> list) {
        list.remove(0);
        LoMoUtils.injectSocialData(loMo, list);
    }
    
    public static void putLoLoMoIdInBrowseCache(final BrowseWebClientCache browseWebClientCache, final JsonObject jsonObject) throws FalcorParseException {
        try {
            browseWebClientCache.putLoLoMoIdInBrowseCache(jsonObject.getAsJsonArray("path").get(1).getAsString());
        }
        catch (Exception ex) {
            if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 6)) {
                Log.e("nf_service_browse_prefetchhomelolomorequest", "LoLoMoId path missing in: " + jsonObject.toString(), ex);
            }
            throw new FalcorParseException("Missing lolomoId", ex);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4, this.pqlQuery5, this.pqlQuery6, this.pqlBB1, this.pqlBB2, this.pqlBB3, this.pqlBB4));
        if (this.pqlCharactersQuery != null) {
            list.add(this.pqlCharactersQuery);
        }
        return list;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchhomelolomorequest", "prefetch finished onFailure");
            this.responseCallback.onLoLoMoPrefetched(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchhomelolomorequest", "prefetch finished onSuccess");
            this.responseCallback.onLoLoMoPrefetched(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "String response to parse = " + s.substring(0, Math.min(s.length(), 1000)));
        }
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_prefetchhomelolomorequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("HomeLoLoMoPrefetch empty!!!");
        }
        JsonObject asJsonObject;
        while (true) {
            while (true) {
                Label_0544: {
                    try {
                        asJsonObject = dataObj.getAsJsonObject("lolomo");
                        if (asJsonObject.has("continueWatching")) {
                            s = (String)FetchCWVideosRequest.buildCWVideoList(asJsonObject.getAsJsonObject("continueWatching"), this.fromCWVideo, this.toCWVideo, this.toSimilars, this.userConnectedToFacebook, this.browseCache);
                            this.browseCache.putCWVideosInBrowseCache(s, this.fromCWVideo, this.toCWVideo);
                        }
                        if (asJsonObject.has("billboard")) {
                            s = (String)FetchBBVideosRequest.buildBBVideoList(asJsonObject.getAsJsonObject("billboard"), this.fromBBVideo, this.toBBVideo, this.toSimilars, this.userConnectedToFacebook, this.browseCache);
                            this.browseCache.putBBVideosInBrowseCache(s, this.fromBBVideo, this.toBBVideo);
                        }
                        this.handleExtraChacterDataIfAvailable(asJsonObject);
                        s = (String)new ArrayList();
                        for (int i = this.fromLoMo; i <= this.toLoMo; ++i) {
                            final String string = Integer.toString(i);
                            if (asJsonObject.has(string)) {
                                final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                                final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", ListOfMoviesSummary.class);
                                if (listOfMoviesSummary != null) {
                                    listOfMoviesSummary.setListPos(i);
                                }
                                ((List<ListOfMoviesSummary>)s).add(listOfMoviesSummary);
                                final LoMoType type = listOfMoviesSummary.getType();
                                final int fromVideo = this.fromVideo;
                                final int toVideo = this.toVideo;
                                if (listOfMoviesSummary.isBillboard()) {
                                    break Label_0544;
                                }
                                final boolean b = true;
                                final List<Video> buildVideoList = FetchVideosRequest.buildVideoList(type, asJsonObject2, fromVideo, toVideo, b);
                                if (LoMoUtils.shouldInjectSocialData(listOfMoviesSummary, this.userConnectedToFacebook)) {
                                    injectSocialData(listOfMoviesSummary, buildVideoList);
                                }
                                this.browseCache.putLoMoInBrowseCache(listOfMoviesSummary.getId(), buildVideoList, this.fromVideo, this.toVideo);
                                if (listOfMoviesSummary.getType() == LoMoType.CONTINUE_WATCHING) {
                                    this.browseCache.putCWIdsInCache(listOfMoviesSummary, string);
                                }
                                if (listOfMoviesSummary.getType() == LoMoType.INSTANT_QUEUE) {
                                    this.browseCache.putIQIdsInCache(listOfMoviesSummary, string);
                                    this.browseCache.putIQVideosInBrowseCache(buildVideoList, this.fromVideo, this.toVideo);
                                }
                            }
                        }
                        break;
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_browse_prefetchhomelolomorequest", "String response to parse = " + s);
                        throw new FalcorParseException("response missing expected json objects", ex);
                    }
                }
                final boolean b = false;
                continue;
            }
        }
        if (((List)s).size() > 0) {
            this.browseCache.putLoMoSummaryInBrowseCache(s, this.fromLoMo, this.toLoMo);
        }
        putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject);
        final long nanoTime2 = System.nanoTime();
        this.rEnd = nanoTime2;
        final long convert = TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch parsing took took %d ms ", convert));
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch success - took %d ms ", this.rDurationInMs));
        return Integer.toString(StatusCode.OK.getValue());
    }
}
