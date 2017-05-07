// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.BasicLoMo;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.servicemgr.LoMoUtils;
import com.netflix.mediaclient.servicemgr.LoMo;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.LoMoType;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class PrefetchHomeLoLoMoRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_PATH = "path";
    private static final String TAG = "nf_service_browse_prefetchhomelolomorequest";
    private final int fromCWVideo;
    private int fromCharactersVideo;
    private final int fromLoMo;
    private final int fromSimilars;
    private final int fromVideo;
    private final HardCache hardCache;
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
    private final SoftCache softCache;
    private final int toCWVideo;
    private int toCharactersVideo;
    private final int toLoMo;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    private final SoftCache weakSeasonsCache;
    
    public PrefetchHomeLoLoMoRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final SoftCache weakSeasonsCache, final int fromLoMo, final int toLoMo, final int fromVideo, final int toVideo, final int fromCWVideo, final int toCWVideo, final int toSimilars, final boolean b, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.fromCharactersVideo = -1;
        this.toCharactersVideo = -1;
        this.responseCallback = responseCallback;
        this.fromLoMo = fromLoMo;
        this.toLoMo = toLoMo;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromCWVideo = fromCWVideo;
        this.toCWVideo = toCWVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.weakSeasonsCache = weakSeasonsCache;
        this.pqlQuery = "['lolomo'" + ",{'from':" + fromLoMo + ",'to':" + toLoMo + "},'summary']";
        this.pqlQuery2 = "['lolomo'" + ",{'from':" + fromLoMo + ",'to':" + toLoMo + "},{'from':" + fromVideo + ",'to':" + toVideo + "},['summary','billboardDetail']]";
        this.pqlQuery3 = "['lolomo'" + ",'continueWatching'" + ",{'from':" + fromCWVideo + ",'to':" + toCWVideo + "},['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]";
        this.pqlQuery4 = "['lolomo'" + ",'continueWatching" + "', {'to':" + toCWVideo + ",'from':" + fromCWVideo + "},'episodes', 'current', ['detail', 'bookmark']]";
        this.pqlQuery5 = "['lolomo'" + ",'continueWatching" + "', {'to':" + toCWVideo + ",'from':" + fromCWVideo + "}, 'similars'," + "{'to':" + toSimilars + ",'from':" + this.fromSimilars + "}, 'summary']";
        this.pqlQuery6 = "['lolomo'" + ",'continueWatching" + "', {'to':" + toCWVideo + ",'from':" + fromCWVideo + "}, 'similars','summary']";
        if (b) {
            this.fromCharactersVideo = toVideo + 1;
            this.toCharactersVideo = toVideo - fromVideo + this.fromCharactersVideo;
            this.pqlCharactersQuery = String.format("['lolomo','characters',{'from':%d,'to':%d},['summary']]", this.fromCharactersVideo, this.toCharactersVideo);
        }
        if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL = " + this.pqlQuery);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL2 = " + this.pqlQuery2);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL3 = " + this.pqlQuery3);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL4 = " + this.pqlQuery4);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL5 = " + this.pqlQuery5);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQL6 = " + this.pqlQuery6);
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
            this.putLoMoInBrowseCache(asString, buildVideoList, this.fromCharactersVideo, this.toCharactersVideo);
            if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
                Log.v("nf_service_browse_prefetchhomelolomorequest", "Found " + buildVideoList.size() + " extra characters in lolomoObj, list id: " + asString);
            }
        }
    }
    
    public static void injectSocialData(final LoMo loMo, final List<Video> list) {
        list.remove(0);
        LoMoUtils.injectSocialData(loMo, list);
    }
    
    public static void putCWIdsInCache(final HardCache hardCache, final ListOfMoviesSummary listOfMoviesSummary, final String s) {
        BrowseAgent.putCWLoMoSummary(hardCache, listOfMoviesSummary);
        BrowseAgent.putCWLoMoIndex(hardCache, String.valueOf(s));
        BrowseAgent.putCWLoMoId(hardCache, listOfMoviesSummary.getId());
    }
    
    private void putCWVideosInBrowseCache(final Object o) {
        BrowseAgent.putInBrowseCache(this.hardCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(this.fromCWVideo), String.valueOf(this.toCWVideo)), o);
    }
    
    public static void putIQIdsInCache(final HardCache hardCache, final ListOfMoviesSummary listOfMoviesSummary, final String s) {
        BrowseAgent.putIQLoMoSummary(hardCache, listOfMoviesSummary);
        BrowseAgent.putIQLoMoIndex(hardCache, String.valueOf(s));
        BrowseAgent.putIQLoMoId(hardCache, listOfMoviesSummary.getId());
    }
    
    private void putIQVideosInBrowseCache(final Object o) {
        BrowseAgent.putInBrowseCache(this.hardCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_IQ_VIDEOS, "queue", String.valueOf(this.fromVideo), String.valueOf(this.toVideo)), o);
    }
    
    public static void putLoLoMoIdInBrowseCache(final HardCache hardCache, final JsonObject jsonObject) throws FalcorParseException {
        try {
            putLoLoMoIdInBrowseCache(hardCache, jsonObject.getAsJsonArray("path").get(1).getAsString());
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "LoLoMoId path missing in: " + jsonObject.toString());
            throw new FalcorParseException("Missing lolomoId", ex);
        }
    }
    
    private static void putLoLoMoIdInBrowseCache(final HardCache hardCache, final Object o) {
        BrowseAgent.putInBrowseCache(hardCache, "lolomo_id", o);
        Log.d("nf_service_browse_prefetchhomelolomorequest", "lolomoId =" + o);
    }
    
    private void putLoMoInBrowseCache(String buildBrowseCacheKey, final Object o, final int n, final int n2) {
        buildBrowseCacheKey = BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_VIDEOS, buildBrowseCacheKey, String.valueOf(n), String.valueOf(n2));
        BrowseAgent.putInBrowseCache(this.hardCache, buildBrowseCacheKey, o);
    }
    
    private void putLoMoSummaryInBrowseCache(final Object o) {
        BrowseAgent.putInBrowseCache(this.hardCache, BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_LOMO, "lolomo", String.valueOf(this.fromLoMo), String.valueOf(this.toLoMo)), o);
    }
    
    @Override
    protected List<String> getPQLQueries() {
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4, this.pqlQuery5, this.pqlQuery6));
        if (this.pqlCharactersQuery != null) {
            list.add(this.pqlCharactersQuery);
        }
        return list;
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchhomelolomorequest", "prefetch finished onFailure");
            this.responseCallback.onLoLoMoPrefetched(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchhomelolomorequest", "prefetch finished onSuccess");
            this.responseCallback.onLoLoMoPrefetched(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "String response to parse = " + s.substring(0, Math.min(s.length(), 100)));
        }
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_prefetchhomelolomorequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("HomeLoLoMoPrefetch empty!!!");
        }
        JsonObject asJsonObject;
        while (true) {
            while (true) {
                Label_0465: {
                    try {
                        asJsonObject = dataObj.getAsJsonObject("lolomo");
                        if (asJsonObject.has("continueWatching")) {
                            this.putCWVideosInBrowseCache(FetchCWVideosRequest.buildCWVideoList(asJsonObject.getAsJsonObject("continueWatching"), this.fromCWVideo, this.toCWVideo, this.toSimilars, this.userConnectedToFacebook, this.hardCache, this.softCache, this.weakSeasonsCache));
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
                                    break Label_0465;
                                }
                                final boolean b = true;
                                final List<Video> buildVideoList = FetchVideosRequest.buildVideoList(type, asJsonObject2, fromVideo, toVideo, b);
                                if (LoMoUtils.shouldInjectSocialData(listOfMoviesSummary, this.userConnectedToFacebook)) {
                                    injectSocialData(listOfMoviesSummary, buildVideoList);
                                }
                                this.putLoMoInBrowseCache(listOfMoviesSummary.getId(), buildVideoList, this.fromVideo, this.toVideo);
                                if (listOfMoviesSummary.getType() == LoMoType.CONTINUE_WATCHING) {
                                    putCWIdsInCache(this.hardCache, listOfMoviesSummary, string);
                                }
                                if (listOfMoviesSummary.getType() == LoMoType.INSTANT_QUEUE) {
                                    putIQIdsInCache(this.hardCache, listOfMoviesSummary, string);
                                    this.putIQVideosInBrowseCache(buildVideoList);
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
            this.putLoMoSummaryInBrowseCache(s);
        }
        putLoLoMoIdInBrowseCache(this.hardCache, asJsonObject);
        final long nanoTime2 = System.nanoTime();
        this.rEnd = nanoTime2;
        final long convert = TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch parsing took took %d ms ", convert));
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch success - took %d ms ", this.rDurationInMs));
        return Integer.toString(0);
    }
}
