// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.google.gson.JsonParser;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import android.annotation.SuppressLint;
import java.util.Iterator;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.servicemgr.model.CWVideo;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RefreshCWRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_VALUE = "value";
    private static final String TAG = "nf_service_browse_refreshcwrequest";
    private final BrowseWebClientCache browseCache;
    private boolean canMakeRequest;
    private final String cwLoMoId;
    private final String cwLoMoIndex;
    private final int fromSimilars;
    private final int fromVideo;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    
    public RefreshCWRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final int toSimilars, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        super(context);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.browseCache = browseCache;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.cwLoMoId = browseCache.getCWLoMoId();
        this.lolomoId = browseCache.getLoLoMoId();
        if (!(this.lolomoIdInCache = StringUtils.isNotEmpty(this.lolomoId))) {
            this.canMakeRequest = false;
        }
        this.cwLoMoIndex = browseCache.getCWLoMoIndex();
        if (StringUtils.isEmpty(this.cwLoMoIndex) || StringUtils.isEmpty(this.cwLoMoId)) {
            this.canMakeRequest = false;
        }
        this.pqlQuery = String.format("['lolomos', '%s', 'refreshList']", this.lolomoId);
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "PQL = " + this.pqlQuery);
        }
        this.rStart = System.nanoTime();
    }
    
    static void updateCWLoMoSummaryObject(final BrowseWebClientCache browseWebClientCache, final ListOfMoviesSummary listOfMoviesSummary) {
        // monitorenter(RefreshCWRequest.class)
        if (listOfMoviesSummary != null) {
            ListOfMoviesSummary cwLoMoSummary = null;
            Label_0094: {
                try {
                    Log.d("nf_service_browse_refreshcwrequest", "updateCWLoMoSummaryObject newList id:" + listOfMoviesSummary.getId() + " length:" + listOfMoviesSummary.getLength());
                    if ((cwLoMoSummary = browseWebClientCache.getCWLoMoSummary()) != null) {
                        break Label_0094;
                    }
                    if (Log.isLoggable("nf_service_browse_refreshcwrequest", 3)) {
                        throw new IllegalStateException("failed to get CW LoMo from cache");
                    }
                }
                finally {
                }
                // monitorexit(RefreshCWRequest.class)
                final BrowseWebClientCache browseWebClientCache2;
                browseWebClientCache2.putCWLoMoSummary(listOfMoviesSummary);
                cwLoMoSummary = listOfMoviesSummary;
            }
            Log.d("nf_service_browse_refreshcwrequest", "updateCWLoMoSummaryObject oldList id:" + cwLoMoSummary.getId() + " length:" + cwLoMoSummary.getLength());
            cwLoMoSummary.setId(listOfMoviesSummary.getId());
            cwLoMoSummary.setLength(listOfMoviesSummary.getLength());
        }
    }
    // monitorexit(RefreshCWRequest.class)
    
    static void updateCWVideoLists(final BrowseWebClientCache browseWebClientCache, final int n, final int n2, final ListOfMoviesSummary listOfMoviesSummary, final List<CWVideo> list) {
        synchronized (RefreshCWRequest.class) {
            for (final String s : browseWebClientCache.getCwKeysCache()) {
                Log.d("nf_service_browse_refreshcwrequest", "removing entry for key:" + s);
                if (browseWebClientCache.getSoftCache().remove(s) == null) {
                    browseWebClientCache.getHardCache().remove(s);
                }
            }
        }
        final BrowseWebClientCache browseWebClientCache2;
        browseWebClientCache2.getCwKeysCache().clear();
        final String buildBrowseCacheKey = BrowseWebClientCache.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(n), String.valueOf(n2));
        browseWebClientCache2.getHardCache().remove(buildBrowseCacheKey);
        browseWebClientCache2.putInHardCache(buildBrowseCacheKey, list);
    }
    // monitorexit(RefreshCWRequest.class)
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @SuppressLint({ "DefaultLocale" })
    @Override
    protected String getOptionalParams() {
        final String format = String.format("[{'from':%d,'to':%d}, ['summary','detail', 'rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]", this.fromVideo, this.toVideo);
        final String format2 = String.format("[{'from':%d,'to':%d}, 'episodes', 'current', ['detail', 'bookmark']]", this.fromVideo, this.toVideo);
        final String format3 = String.format("[{'from':%d,'to':%d}, 'similars', {'from':%d,'to':%d}, 'summary']", this.fromVideo, this.toVideo, this.fromSimilars, this.toSimilars);
        final String format4 = String.format("[{'from':%d,'to':%d}, 'similars', 'summary']", this.fromVideo, this.toVideo);
        final String string = "'" + this.cwLoMoId + "'";
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("param", string));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("param", this.cwLoMoIndex));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("param", "'continueWatching'"));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format3));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format4));
        Log.d("nf_service_browse_refreshcwrequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_refreshcwrequest", "RefreshCWRequest finished onFailure statusCode=" + status);
            this.responseCallback.onCWListRefresh(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_refreshcwrequest", "RefreshCWRequest finished onSuccess");
            this.responseCallback.onCWListRefresh(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String buildCWVideoList) {
        this.rEnd = System.nanoTime();
        this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
        Log.d("nf_service_browse_refreshcwrequest", String.format(" request took %d ms ", this.rDurationInMs));
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + buildCWVideoList.substring(0, Math.min(buildCWVideoList.length(), 1500)));
        }
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(buildCWVideoList).getAsJsonObject();
            if (FalcorParseUtils.containsErrors(asJsonObject)) {
                throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + buildCWVideoList);
            throw new FalcorParseException("Error in creating JsonObject", ex);
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.cwLoMoIndex);
            final long nanoTime = System.nanoTime();
            buildCWVideoList = (String)FetchCWVideosRequest.buildCWVideoList(asJsonObject3, this.fromVideo, this.toVideo, this.toSimilars, this.userConnectedToFacebook, this.browseCache);
            final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", ListOfMoviesSummary.class);
            this.browseCache.putCWLoMoId(listOfMoviesSummary.getId());
            updateCWLoMoSummaryObject(this.browseCache, listOfMoviesSummary);
            updateCWVideoLists(this.browseCache, this.fromVideo, this.toVideo, listOfMoviesSummary, (List<CWVideo>)buildCWVideoList);
            final long nanoTime2 = System.nanoTime();
            this.rEnd = nanoTime2;
            final long convert = TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
            this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
            Log.d("nf_service_browse_refreshcwrequest", String.format(" parsing  & updating MDPs took took %d ms ", convert));
            Log.d("nf_service_browse_refreshcwrequest", String.format(" refresh success - took %d ms ", this.rDurationInMs));
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + buildCWVideoList);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
