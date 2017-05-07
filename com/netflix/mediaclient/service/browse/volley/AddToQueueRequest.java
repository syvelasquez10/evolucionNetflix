// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Arrays;
import com.netflix.mediaclient.service.browse.cache.BrowseCache;
import java.util.Iterator;
import com.netflix.mediaclient.servicemgr.Video;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class AddToQueueRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    public static final String FIELD_VALUE = "value";
    public static final String TAG = "nf_service_browse_addtoqueuerequest";
    public static final String optionalParam;
    private boolean canMakeRequest;
    private final int fromVideo;
    private final HardCache hardCache;
    private final List<String> iqKeysCache;
    private final String iqLoMoId;
    private final String iqLoMoIndex;
    private final String lolomoId;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache softCache;
    private final int toVideo;
    private final int trackId;
    
    static {
        optionalParam = "&" + FalcorParseUtils.getParamNameParam() + "=";
    }
    
    public AddToQueueRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final List<String> iqKeysCache, final String mVideoId, final int fromVideo, final int toVideo, final int trackId, final BrowseAgentCallback responseCallback) {
        boolean canMakeRequest = true;
        super(context, configurationAgentInterface);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.iqKeysCache = iqKeysCache;
        this.mVideoId = mVideoId;
        this.trackId = trackId;
        if (!BrowseAgent.areIqIdsInCache(hardCache)) {
            canMakeRequest = false;
        }
        if (!(this.canMakeRequest = canMakeRequest)) {}
        this.iqLoMoId = BrowseAgent.getIQLoMoId(hardCache);
        this.lolomoId = BrowseAgent.getLoLoMoId(hardCache);
        this.iqLoMoIndex = BrowseAgent.getIQLoMoIndex(hardCache);
        this.pqlQuery = "['lolomos','" + this.lolomoId + "','add']";
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    static void updateIQLoMoSummaryObject(final HardCache hardCache, final ListOfMoviesSummary listOfMoviesSummary) {
        // monitorenter(AddToQueueRequest.class)
        if (listOfMoviesSummary != null) {
            try {
                Log.d("nf_service_browse_addtoqueuerequest", "updateIQLoMoSummaryObject newList id:" + listOfMoviesSummary.getId() + " length:" + listOfMoviesSummary.getLength());
                ListOfMoviesSummary iqLoMoSummary;
                if ((iqLoMoSummary = BrowseAgent.getIQLoMoSummary(hardCache)) == null) {
                    BrowseAgent.putIQLoMoSummary(hardCache, listOfMoviesSummary);
                    iqLoMoSummary = listOfMoviesSummary;
                }
                Log.d("nf_service_browse_addtoqueuerequest", "updateIQLoMoSummaryObject oldList id:" + iqLoMoSummary.getId() + " length:" + iqLoMoSummary.getLength());
                iqLoMoSummary.setId(listOfMoviesSummary.getId());
                iqLoMoSummary.setLength(listOfMoviesSummary.getLength());
            }
            finally {
            }
            // monitorexit(AddToQueueRequest.class)
        }
    }
    // monitorexit(AddToQueueRequest.class)
    
    static void updateIQVideoLists(final HardCache hardCache, final SoftCache softCache, final List<String> list, final int n, final int n2, final ListOfMoviesSummary listOfMoviesSummary, final List<Video> list2) {
        synchronized (AddToQueueRequest.class) {
            for (final String s : list) {
                Log.d("nf_service_browse_addtoqueuerequest", "removing entry for key:" + s);
                if (softCache.remove(s) == null) {
                    hardCache.remove(s);
                }
            }
        }
        list.clear();
        final String buildBrowseCacheKey = BrowseAgent.buildBrowseCacheKey(BrowseAgent.CACHE_KEY_PREFIX_IQ_VIDEOS, "queue", String.valueOf(n), String.valueOf(n2));
        final BrowseCache browseCache;
        browseCache.remove(buildBrowseCacheKey);
        BrowseAgent.putInBrowseCache(browseCache, buildBrowseCacheKey, list2);
    }
    // monitorexit(AddToQueueRequest.class)
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final String string = "['videos','" + this.mVideoId + "']";
        final String string2 = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "},'summary']";
        final String string3 = "'" + this.iqLoMoId + "'";
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), string3));
        sb.append(AddToQueueRequest.optionalParam).append(this.iqLoMoIndex);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), string));
        sb.append(AddToQueueRequest.optionalParam).append(this.trackId);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        Log.d("nf_service_browse_addtoqueuerequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished onFailure statusCode=" + n);
            this.responseCallback.onQueueAdd(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequest finished onSuccess");
            this.responseCallback.onQueueAdd(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String string) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + (String)string);
        }
        final ArrayList<Video> list = new ArrayList<Video>();
        JsonObject asJsonObject = null;
        Label_0138: {
            try {
                asJsonObject = new JsonParser().parse((String)string).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0138;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("AlreadyInQueue")) {
                    return Integer.toString(0);
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + (String)string);
                throw new FalcorParseException("Error in creating JsonObject", ex);
            }
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(0);
        }
        JsonObject asJsonObject3;
        try {
            asJsonObject3 = asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.iqLoMoIndex);
            for (int i = this.fromVideo; i <= this.toVideo; ++i) {
                string = Integer.toString(i);
                if (asJsonObject3.has((String)string)) {
                    string = FalcorParseUtils.getPropertyObject(asJsonObject3.getAsJsonObject((String)string), "summary", com.netflix.mediaclient.service.webclient.model.branches.Video.Summary.class);
                    FetchIQVideosRequest.updateMdpWithIQInfo(this.hardCache, this.softCache, ((com.netflix.mediaclient.service.webclient.model.branches.Video.Summary)string).getId(), true);
                    list.add((Video)string);
                }
            }
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + (String)string);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
        final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", ListOfMoviesSummary.class);
        BrowseAgent.putIQLoMoId(this.hardCache, listOfMoviesSummary.getId());
        updateIQLoMoSummaryObject(this.hardCache, listOfMoviesSummary);
        updateIQVideoLists(this.hardCache, this.softCache, this.iqKeysCache, this.fromVideo, this.toVideo, listOfMoviesSummary, list);
        return Integer.toString(0);
    }
}
