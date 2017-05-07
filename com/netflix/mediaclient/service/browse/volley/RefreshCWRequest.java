// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Arrays;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.CWVideo;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RefreshCWRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_VALUE = "value";
    private static final String TAG = "nf_service_browse_refreshcwrequest";
    private boolean canMakeRequest;
    private final List<String> cwKeysCache;
    private final String cwLoMoId;
    private final String cwLoMoIndex;
    private final int fromSimilars;
    private final int fromVideo;
    private final HardCache hardCache;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private long rDurationInMs;
    private long rEnd;
    private final long rStart;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache softCache;
    private final int toSimilars;
    private final int toVideo;
    private final boolean userConnectedToFacebook;
    private final SoftCache weakSeasonsCache;
    
    public RefreshCWRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final SoftCache weakSeasonsCache, final List<String> cwKeysCache, final int fromVideo, final int toVideo, final int toSimilars, final boolean userConnectedToFacebook, final BrowseAgentCallback responseCallback) {
        final boolean b = true;
        super(context, configurationAgentInterface);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.fromSimilars = 0;
        this.toSimilars = toSimilars;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.weakSeasonsCache = weakSeasonsCache;
        this.cwKeysCache = cwKeysCache;
        this.userConnectedToFacebook = userConnectedToFacebook;
        this.cwLoMoId = BrowseAgent.getCWLoMoId(hardCache);
        this.lolomoId = BrowseAgent.getLoLoMoId(hardCache);
        if (!(this.lolomoIdInCache = (StringUtils.isNotEmpty(this.lolomoId) && b))) {
            this.canMakeRequest = false;
        }
        this.cwLoMoIndex = BrowseAgent.getCWLoMoIndex(hardCache);
        if (StringUtils.isEmpty(this.cwLoMoIndex) || StringUtils.isEmpty(this.cwLoMoId)) {
            this.canMakeRequest = false;
        }
        this.pqlQuery = "['lolomos','" + this.lolomoId + "','refreshList']";
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "PQL = " + this.pqlQuery);
        }
        this.rStart = System.nanoTime();
    }
    
    static void updateCWLoMoSummaryObject(final HardCache hardCache, final ListOfMoviesSummary listOfMoviesSummary) {
        // monitorenter(RefreshCWRequest.class)
        if (listOfMoviesSummary != null) {
            ListOfMoviesSummary cwLoMoSummary = null;
            Label_0094: {
                try {
                    Log.d("nf_service_browse_refreshcwrequest", "updateCWLoMoSummaryObject newList id:" + listOfMoviesSummary.getId() + " length:" + listOfMoviesSummary.getLength());
                    if ((cwLoMoSummary = BrowseAgent.getCWLoMoSummary(hardCache)) != null) {
                        break Label_0094;
                    }
                    if (Log.isLoggable("nf_service_browse_refreshcwrequest", 3)) {
                        throw new IllegalStateException("failed to get CW LoMo from cache");
                    }
                }
                finally {
                }
                // monitorexit(RefreshCWRequest.class)
                final HardCache hardCache2;
                BrowseAgent.putCWLoMoSummary(hardCache2, listOfMoviesSummary);
                cwLoMoSummary = listOfMoviesSummary;
            }
            Log.d("nf_service_browse_refreshcwrequest", "updateCWLoMoSummaryObject oldList id:" + cwLoMoSummary.getId() + " length:" + cwLoMoSummary.getLength());
            cwLoMoSummary.setId(listOfMoviesSummary.getId());
            cwLoMoSummary.setLength(listOfMoviesSummary.getLength());
        }
    }
    // monitorexit(RefreshCWRequest.class)
    
    static void updateCWVideoLists(final HardCache hardCache, final SoftCache softCache, final List<String> list, final int n, final int n2, final ListOfMoviesSummary listOfMoviesSummary, final List<CWVideo> list2) {
        synchronized (RefreshCWRequest.class) {
            for (final String s : list) {
                Log.d("nf_service_browse_refreshcwrequest", "removing entry for key:" + s);
                if (softCache.remove(s) == null) {
                    hardCache.remove(s);
                }
            }
        }
        list.clear();
        final String buildBrowseCacheKey = BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_CW_VIDEOS, "continueWatching", String.valueOf(n), String.valueOf(n2));
        final BrowseCache browseCache;
        browseCache.remove(buildBrowseCacheKey);
        BrowseAgent.putInBrowseCache(browseCache, buildBrowseCacheKey, list2);
    }
    // monitorexit(RefreshCWRequest.class)
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final String string = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "},['summary','detail','rating', 'inQueue', 'bookmark', 'bookmarkStill', 'socialEvidence']]";
        final String string2 = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "},'episodes', 'current', ['detail', 'bookmark']]";
        final String string3 = new StringBuilder("['summary']").toString();
        final String string4 = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "}, 'similars'," + "{'to':" + this.toSimilars + ",'from':" + this.fromSimilars + "}, 'summary']";
        final String string5 = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "}, 'similars', 'summary']";
        final String string6 = "'" + this.cwLoMoId + "'";
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), string6));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), this.cwLoMoIndex));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), "'continueWatching'"));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string3));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string4));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string5));
        Log.d("nf_service_browse_refreshcwrequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_refreshcwrequest", "RefreshCWRequest finished onFailure statusCode=" + n);
            this.responseCallback.onCWListRefresh(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_refreshcwrequest", "RefreshCWRequest finished onSuccess");
            this.responseCallback.onCWListRefresh(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String buildCWVideoList) throws FalcorParseException, FalcorServerException {
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
            return Integer.toString(0);
        }
        try {
            final JsonObject asJsonObject3 = asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.cwLoMoIndex);
            final long nanoTime = System.nanoTime();
            buildCWVideoList = (String)FetchCWVideosRequest.buildCWVideoList(asJsonObject3, this.fromVideo, this.toVideo, this.toSimilars, this.userConnectedToFacebook, this.hardCache, this.softCache, this.weakSeasonsCache);
            final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", ListOfMoviesSummary.class);
            BrowseAgent.putCWLoMoId(this.hardCache, listOfMoviesSummary.getId());
            updateCWLoMoSummaryObject(this.hardCache, listOfMoviesSummary);
            updateCWVideoLists(this.hardCache, this.softCache, this.cwKeysCache, this.fromVideo, this.toVideo, listOfMoviesSummary, (List<CWVideo>)buildCWVideoList);
            final long nanoTime2 = System.nanoTime();
            this.rEnd = nanoTime2;
            final long convert = TimeUnit.MILLISECONDS.convert(nanoTime2 - nanoTime, TimeUnit.NANOSECONDS);
            this.rDurationInMs = TimeUnit.MILLISECONDS.convert(this.rEnd - this.rStart, TimeUnit.NANOSECONDS);
            Log.d("nf_service_browse_refreshcwrequest", String.format(" parsing  & updating MDPs took took %d ms ", convert));
            Log.d("nf_service_browse_refreshcwrequest", String.format(" refresh success - took %d ms ", this.rDurationInMs));
            return Integer.toString(0);
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + buildCWVideoList);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
