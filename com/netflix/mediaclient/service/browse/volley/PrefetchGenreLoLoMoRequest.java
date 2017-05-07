// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.servicemgr.model.LoMoType;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class PrefetchGenreLoLoMoRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_FLATGENRE = "flatGenre";
    private static final String FIELD_PATH = "path";
    private static final String FIELD_TOPGENRE = "topGenre";
    private static final String TAG = "nf_service_browse_prefetchgenrelolomorequest";
    private final BrowseWebClientCache browseCache;
    private final int mFromLoMo;
    private final int mFromVideo;
    private final String mGenreId;
    private final boolean mIsKopExp;
    private final int mToLoMo;
    private final int mToVideo;
    private final String pqlKop;
    private final String pqlQuery;
    private final String pqlQuery2;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    
    public PrefetchGenreLoLoMoRequest(final Context context, final boolean mIsKopExp, final BrowseWebClientCache browseCache, final String mGenreId, final int mFromLoMo, final int mToLoMo, final int mFromVideo, final int mToVideo, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mGenreId = mGenreId;
        this.mFromLoMo = mFromLoMo;
        this.mToLoMo = mToLoMo;
        this.mFromVideo = mFromVideo;
        this.mToVideo = mToVideo;
        this.browseCache = browseCache;
        this.mIsKopExp = mIsKopExp;
        this.pqlQuery = String.format("['topGenre', '%s', {'from':%d,'to':%d}, 'summary']", mGenreId, mFromLoMo, mToLoMo);
        this.pqlQuery2 = String.format("['topGenre', '%s', {'from':%d,'to':%d}, {'from':%d,'to':%d}, 'summary']", mGenreId, mFromLoMo, mToLoMo, mFromVideo, mToVideo);
        this.pqlKop = String.format("['flatGenre', '%s', {'from':%s, 'to':%s}, 'summary']", mGenreId, mFromVideo, mToVideo);
        if (Log.isLoggable("nf_service_browse_prefetchgenrelolomorequest", 2)) {
            if (mIsKopExp) {
                Log.v("nf_service_browse_prefetchgenrelolomorequest", "pqlKop= " + this.pqlKop);
            }
            else {
                Log.v("nf_service_browse_prefetchgenrelolomorequest", "PQL = " + this.pqlQuery);
                Log.v("nf_service_browse_prefetchgenrelolomorequest", "PQL2 = " + this.pqlQuery2);
            }
        }
        this.rStart = System.nanoTime();
    }
    
    public static void putGenreLoLoMoIdInBrowseCache(final BrowseWebClientCache browseWebClientCache, final String s, final JsonObject jsonObject) throws FalcorParseException {
        try {
            browseWebClientCache.putGenreLoLoMoIdInBrowseCache(s, jsonObject.getAsJsonArray("path").get(1).getAsString());
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_prefetchgenrelolomorequest", "Genre LoLoMoId path missing in: " + jsonObject.toString());
            throw new FalcorParseException("Missing Genre lolomoId", ex);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        if (this.mIsKopExp) {
            return Arrays.asList(this.pqlKop);
        }
        return Arrays.asList(this.pqlQuery, this.pqlQuery2);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchgenrelolomorequest", "prefetchGenreLoLoMo finished onFailure statusCode=" + status);
            this.responseCallback.onGenreLoLoMoPrefetched(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_prefetchgenrelolomorequest", "prefetchGenreLoLoMo finished onSuccess");
            this.responseCallback.onGenreLoLoMoPrefetched(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchgenrelolomorequest", String.format("prefetchGenre request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_prefetchgenrelolomorequest", 2)) {}
        final long nanoTime = System.nanoTime();
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_browse_prefetchgenrelolomorequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("GenreLoLoMoPrefetch empty!!!");
        }
        Label_0282: {
            if (!this.mIsKopExp) {
                break Label_0282;
            }
            String s2 = "flatGenre";
        Label_0239_Outer:
            while (true) {
                JsonObject asJsonObject = null;
                Label_0483: {
                Label_0336:
                    while (true) {
                        Label_0330: {
                            try {
                                asJsonObject = dataObj.getAsJsonObject(s2).getAsJsonObject(this.mGenreId);
                                if (!this.mIsKopExp) {
                                    s = (String)new ArrayList();
                                    for (int i = this.mFromLoMo; i <= this.mToLoMo; ++i) {
                                        final String string = Integer.toString(i);
                                        if (asJsonObject.has(string)) {
                                            final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject(string);
                                            final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", ListOfMoviesSummary.class);
                                            if (listOfMoviesSummary != null) {
                                                listOfMoviesSummary.setListPos(i);
                                            }
                                            ((List<ListOfMoviesSummary>)s).add(listOfMoviesSummary);
                                            final LoMoType type = listOfMoviesSummary.getType();
                                            final int mFromVideo = this.mFromVideo;
                                            final int mToVideo = this.mToVideo;
                                            if (listOfMoviesSummary.isBillboard()) {
                                                break Label_0330;
                                            }
                                            final boolean b = true;
                                            this.browseCache.putGenreLoMoInBrowseCache(listOfMoviesSummary.getId(), FetchVideosRequest.buildVideoList(type, asJsonObject2, mFromVideo, mToVideo, b), this.mFromVideo, this.mToVideo);
                                        }
                                    }
                                    break Label_0336;
                                }
                                break Label_0483;
                                s2 = "topGenre";
                                continue Label_0239_Outer;
                            }
                            catch (Exception ex) {
                                Log.v("nf_service_browse_prefetchgenrelolomorequest", "String response to parse = " + s);
                                throw new FalcorParseException("response missing expected json objects", ex);
                            }
                        }
                        final boolean b = false;
                        continue;
                    }
                    if (((List)s).size() > 0) {
                        this.browseCache.putGenreLoMoSummaryInBrowseCache(s, this.mGenreId, this.mFromLoMo, this.mToLoMo);
                        putGenreLoLoMoIdInBrowseCache(this.browseCache, this.mGenreId, asJsonObject);
                        break;
                    }
                    break;
                }
                this.browseCache.putGenreLoMoInBrowseCache(this.mGenreId, FetchVideosRequest.buildVideoList(LoMoType.FLAT_GENRE, asJsonObject, this.mFromVideo, this.mToVideo, true), this.mFromVideo, this.mToVideo);
                break;
            }
        }
        final long nanoTime2 = System.nanoTime();
        this.rEnd = nanoTime2;
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_prefetchgenrelolomorequest", String.format("prefetch pasing took took %d ms ", TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS)));
        Log.d("nf_service_browse_prefetchgenrelolomorequest", String.format("prefetch success - took %d ms ", this.rDurationInMs));
        return Integer.toString(StatusCode.OK.getValue());
    }
}
