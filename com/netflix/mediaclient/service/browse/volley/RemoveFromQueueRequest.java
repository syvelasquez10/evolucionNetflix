// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.StatusCode;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RemoveFromQueueRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_VALUE = "value";
    public static final String TAG = "nf_service_browse_removefromqueuerequest";
    private final BrowseWebClientCache browseCache;
    private boolean canMakeRequest;
    private final int fromVideo;
    private final String iqLoMoIndex;
    private final String lolomoId;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    
    public RemoveFromQueueRequest(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final int fromVideo, final int toVideo, final BrowseAgentCallback responseCallback) {
        super(context);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.mVideoId = mVideoId;
        this.browseCache = browseCache;
        if (!(this.canMakeRequest = browseCache.areIqIdsInCache())) {}
        this.lolomoId = browseCache.getLoLoMoId();
        this.iqLoMoIndex = browseCache.getIQLoMoIndex();
        this.pqlQuery = String.format("['lolomos', '%s', 'remove']", this.lolomoId);
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
    
    @SuppressLint({ "DefaultLocale" })
    @Override
    protected String getOptionalParams() {
        final String string = "&" + FalcorParseUtils.getParamNameParam() + "=";
        final String format = String.format("['videos','%s']", this.mVideoId);
        final String format2 = String.format("[{'from':%d,'to':%d},'summary']", this.fromVideo, this.toVideo);
        final StringBuilder sb = new StringBuilder();
        sb.append(string).append(this.iqLoMoIndex);
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), format));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        Log.d("nf_service_browse_removefromqueuerequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequest finished onFailure statusCode=" + status);
            this.responseCallback.onQueueRemove(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequest finished onSuccess");
            this.responseCallback.onQueueRemove(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
        }
        JsonObject asJsonObject = null;
        Label_0127: {
            try {
                asJsonObject = new JsonParser().parse(s).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0127;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("AlreadyInQueue")) {
                    return Integer.toString(StatusCode.OK.getValue());
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
                throw new FalcorParseException("Error in creating JsonObject", ex);
            }
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            AddToQueueRequest.parseRefreshIqVideosAndUpdateCache(asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.iqLoMoIndex), this.fromVideo, this.toVideo, this.browseCache);
            FetchIQVideosRequest.updateMdpAndSdpWithIQInfo(this.browseCache, this.mVideoId, false);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
