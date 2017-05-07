// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.ListOfMoviesSummary;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.servicemgr.Video;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import java.util.List;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RemoveFromQueueRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_VALUE = "value";
    public static final String TAG = "nf_service_browse_removefromqueuerequest";
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
    
    public RemoveFromQueueRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final List<String> iqKeysCache, final String mVideoId, final int fromVideo, final int toVideo, final BrowseAgentCallback responseCallback) {
        boolean canMakeRequest = true;
        super(context, configurationAgentInterface);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.mVideoId = mVideoId;
        this.iqKeysCache = iqKeysCache;
        if (!BrowseAgent.areIqIdsInCache(hardCache)) {
            canMakeRequest = false;
        }
        if (!(this.canMakeRequest = canMakeRequest)) {}
        this.iqLoMoId = BrowseAgent.getIQLoMoId(hardCache);
        this.lolomoId = BrowseAgent.getLoLoMoId(hardCache);
        this.iqLoMoIndex = BrowseAgent.getIQLoMoIndex(hardCache);
        this.pqlQuery = "['lolomos','" + this.lolomoId + "','remove']";
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final String string = "&" + FalcorParseUtils.getParamNameParam() + "=";
        final String string2 = "['videos','" + this.mVideoId + "']";
        final String string3 = "[{'to':" + this.toVideo + ",'from':" + this.fromVideo + "},'summary']";
        final String string4 = new StringBuilder("['summary']").toString();
        final String string5 = "'" + this.iqLoMoId + "'";
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), string5));
        sb.append(string).append(this.iqLoMoIndex);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), "'queue'"));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), string2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string3));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", string4));
        Log.d("nf_service_browse_removefromqueuerequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequest finished onFailure statusCode=" + n);
            this.responseCallback.onQueueRemove(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequest finished onSuccess");
            this.responseCallback.onQueueRemove(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String string) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + (String)string);
        }
        final ArrayList<Video> list = new ArrayList<Video>();
        JsonObject asJsonObject = null;
        Label_0134: {
            try {
                asJsonObject = new JsonParser().parse((String)string).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0134;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("AlreadyInQueue")) {
                    return Integer.toString(0);
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + (String)string);
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
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + (String)string);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
        FetchIQVideosRequest.updateMdpWithIQInfo(this.hardCache, this.softCache, this.mVideoId, false);
        final ListOfMoviesSummary listOfMoviesSummary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", ListOfMoviesSummary.class);
        BrowseAgent.putIQLoMoId(this.hardCache, listOfMoviesSummary.getId());
        AddToQueueRequest.updateIQLoMoSummaryObject(this.hardCache, listOfMoviesSummary);
        AddToQueueRequest.updateIQVideoLists(this.hardCache, this.softCache, this.iqKeysCache, this.fromVideo, this.toVideo, listOfMoviesSummary, list);
        return Integer.toString(0);
    }
}
