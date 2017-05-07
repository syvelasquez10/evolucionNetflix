// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.servicemgr.model.LoMo;
import com.netflix.mediaclient.servicemgr.model.BasicLoMo;
import com.netflix.mediaclient.servicemgr.model.LoMoUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.google.gson.JsonArray;
import com.netflix.mediaclient.servicemgr.model.Video;
import java.util.List;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class PrefetchHomeLoLoMoRequest extends FalkorVolleyWebClientRequest<Void>
{
    private static final String FIELD_LOLOMO = "lolomo";
    private static final String FIELD_PATH = "path";
    private static final String TAG = "nf_service_browse_prefetchhomelolomorequest";
    private final BrowseWebClientCache browseCache;
    private final int fromBBVideo;
    private final int fromCWVideo;
    private int fromCharactersVideo;
    private final int fromLoMo;
    private final int fromVideo;
    private final boolean includeKubrick;
    private final String pqlBB1;
    private final String pqlBB2;
    private final String pqlBBSocial;
    private String pqlCharactersQuery;
    private final String pqlQuery;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final String pqlQuery4;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    private final int toBBVideo;
    private final int toCWVideo;
    private int toCharactersVideo;
    private final int toLoMo;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public PrefetchHomeLoLoMoRequest(final Context context, final BrowseWebClientCache browseCache, final int toLoMo, final int toVideo, final int toCWVideo, final int toBBVideo, int fromLoMo, final boolean b, final boolean includeKubrick, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
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
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.includeKubrick = includeKubrick;
        this.browseCache = browseCache;
        this.pqlQuery = String.format("['lolomo', {'from':%d,'to':%d}, 'summary']", this.fromLoMo, toLoMo);
        fromLoMo = this.fromLoMo;
        final int fromVideo = this.fromVideo;
        String s;
        if (includeKubrick) {
            s = "['summary', 'kubrick']";
        }
        else {
            s = "'summary'";
        }
        this.pqlQuery2 = String.format("['lolomo', {'from':%d,'to':%d}, {'from':%d,'to':%d}, %s]", fromLoMo, toLoMo, fromVideo, toVideo, s);
        this.pqlQuery3 = String.format("%s, {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill']]", "['lolomo','continueWatching'", this.fromCWVideo, toCWVideo);
        this.pqlQuery4 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", "['lolomo','continueWatching'", this.fromCWVideo, toCWVideo);
        this.pqlBB1 = String.format("%s, {'from':%d,'to':%d}, ['summary', 'detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill']]", "['lolomo',['billboard', 'postcard'], 'videoEvidence'", this.fromBBVideo, toBBVideo);
        this.pqlBB2 = String.format("%s, {'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", "['lolomo',['billboard', 'postcard'], 'videoEvidence'", this.fromBBVideo, toBBVideo);
        this.pqlBBSocial = String.format("['lolomo',['billboard', 'postcard'], 'socialEvidence', {'from':%d,'to':%d}, 'socialBadge']", this.fromBBVideo, toBBVideo);
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
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB1 = " + this.pqlBB1);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBB2 = " + this.pqlBB2);
            Log.v("nf_service_browse_prefetchhomelolomorequest", "PQLBBSocial = " + this.pqlBBSocial);
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
            this.browseCache.putVideoListInBrowseCache(asString, buildVideoList, this.fromCharactersVideo, this.toCharactersVideo);
            if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
                Log.v("nf_service_browse_prefetchhomelolomorequest", "Found " + buildVideoList.size() + " extra characters in lolomoObj, list id: " + asString);
            }
        }
    }
    
    public static void putLoLoMoIdInBrowseCache(final BrowseWebClientCache browseWebClientCache, final JsonObject jsonObject) {
        try {
            browseWebClientCache.putLoLoMoIdInBrowseCache(jsonObject.getAsJsonArray("path").get(1).getAsString());
        }
        catch (Exception ex) {
            if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 6)) {
                Log.e("nf_service_browse_prefetchhomelolomorequest", "LoLoMoId path missing in: " + jsonObject.toString(), ex);
            }
            throw new FalkorParseException("Missing lolomoId", ex);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        final ArrayList<String> list = new ArrayList<String>(Arrays.asList(this.pqlQuery, this.pqlQuery2, this.pqlQuery3, this.pqlQuery4, this.pqlBB1, this.pqlBB2, this.pqlBBSocial));
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
    protected void onSuccess(final Void void1) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchhomelolomorequest", "prefetch finished onSuccess");
            this.responseCallback.onLoLoMoPrefetched(CommonStatus.OK);
        }
    }
    
    @Override
    protected Void parseFalkorResponse(String s) {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_prefetchhomelolomorequest", 2)) {
            Log.v("nf_service_browse_prefetchhomelolomorequest", "String response to parse = " + s.substring(0, Math.min(s.length(), 1000)));
        }
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_browse_prefetchhomelolomorequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("HomeLoLoMoPrefetch empty!!!");
        }
        JsonObject asJsonObject;
        while (true) {
            while (true) {
                Label_0733: {
                    while (true) {
                        Label_0592: {
                            try {
                                asJsonObject = dataObj.getAsJsonObject("lolomo");
                                if (asJsonObject.has("continueWatching")) {
                                    s = (String)FetchCWVideosRequest.buildCWVideoList(asJsonObject.getAsJsonObject("continueWatching"), this.fromCWVideo, this.toCWVideo, this.userConnectedToFacebook, this.browseCache);
                                    this.browseCache.putCWVideosInBrowseCache(s, this.fromCWVideo, this.toCWVideo);
                                }
                                final String s2 = s = "billboard";
                                if (!asJsonObject.has("billboard")) {
                                    s = s2;
                                    if (asJsonObject.has("postcard")) {
                                        s = "postcard";
                                    }
                                }
                                if (asJsonObject.has(s)) {
                                    s = (String)asJsonObject.getAsJsonObject(s);
                                    s = (String)FetchBBVideosRequest.buildBBVideoList(((JsonObject)s).getAsJsonObject("videoEvidence"), ((JsonObject)s).getAsJsonObject("socialEvidence"), this.fromBBVideo, this.toBBVideo, this.userConnectedToFacebook, this.browseCache);
                                    this.browseCache.putBBVideosInBrowseCache(s, this.fromBBVideo, this.toBBVideo);
                                }
                                this.handleExtraChacterDataIfAvailable(asJsonObject);
                                s = (String)new ArrayList();
                                for (int i = this.fromLoMo; i <= this.toLoMo; ++i) {
                                    final String string = Integer.toString(i);
                                    if (asJsonObject.has(string)) {
                                        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                                        final ListOfMoviesSummary listOfMoviesSummary = FalkorParseUtils.getPropertyObject(asJsonObject2, "summary", ListOfMoviesSummary.class);
                                        if (listOfMoviesSummary == null) {
                                            break Label_0733;
                                        }
                                        listOfMoviesSummary.setListPos(i);
                                        final int billboard = listOfMoviesSummary.isBillboard() ? 1 : 0;
                                        ((List<ListOfMoviesSummary>)s).add(listOfMoviesSummary);
                                        final LoMoType type = listOfMoviesSummary.getType();
                                        final int fromVideo = this.fromVideo;
                                        final int toVideo = this.toVideo;
                                        if (billboard != 0) {
                                            break Label_0592;
                                        }
                                        final boolean b = true;
                                        final List<Video> buildVideoList = FetchVideosRequest.buildVideoList(type, asJsonObject2, fromVideo, toVideo, b);
                                        if (LoMoUtils.shouldInjectSocialData(listOfMoviesSummary, this.userConnectedToFacebook, this.includeKubrick)) {
                                            LoMoUtils.injectSocialData(listOfMoviesSummary, buildVideoList);
                                        }
                                        this.browseCache.putVideoListInBrowseCache(listOfMoviesSummary.getId(), buildVideoList, this.fromVideo, this.toVideo);
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
                                throw new FalkorParseException("response missing expected json objects", ex);
                            }
                        }
                        final boolean b = false;
                        continue;
                    }
                }
                final int billboard = 0;
                continue;
            }
        }
        if (((List)s).size() > 0) {
            this.browseCache.putLoMoListInBrowseCache(s, this.fromLoMo, this.toLoMo);
        }
        putLoLoMoIdInBrowseCache(this.browseCache, asJsonObject);
        final long nanoTime2 = System.nanoTime();
        this.rEnd = nanoTime2;
        final long convert = TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch parsing took took %d ms ", convert));
        Log.d("nf_service_browse_prefetchhomelolomorequest", String.format(" prefetch success - took %d ms ", this.rDurationInMs));
        return null;
    }
    
    @Override
    protected boolean parsedResponseCanBeNull() {
        return true;
    }
}
