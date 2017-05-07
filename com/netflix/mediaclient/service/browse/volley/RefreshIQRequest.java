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
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

@SuppressLint({ "DefaultLocale" })
public class RefreshIQRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_LOLOMOS = "lolomos";
    private static final String FIELD_VALUE = "value";
    private static final String TAG = "nf_service_browse_refreshcwrequest";
    private final BrowseWebClientCache browseCache;
    private boolean canMakeRequest;
    private final int fromVideo;
    private final String iqLoMoId;
    private final String iqLoMoIndex;
    private final String lolomoId;
    private final boolean lolomoIdInCache;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int toVideo;
    
    protected RefreshIQRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo, final BrowseAgentCallback responseCallback) {
        super(context);
        this.canMakeRequest = true;
        this.responseCallback = responseCallback;
        this.fromVideo = fromVideo;
        this.toVideo = toVideo;
        this.browseCache = browseCache;
        this.iqLoMoId = browseCache.getIQLoMoId();
        this.lolomoId = browseCache.getLoLoMoId();
        if (!(this.lolomoIdInCache = StringUtils.isNotEmpty(this.lolomoId))) {
            this.canMakeRequest = false;
        }
        this.iqLoMoIndex = browseCache.getIQLoMoIndex();
        if (StringUtils.isEmpty(this.iqLoMoIndex) || StringUtils.isEmpty(this.iqLoMoId)) {
            this.canMakeRequest = false;
        }
        this.pqlQuery = String.format("['lolomos', '%s', 'refreshList']", this.lolomoId);
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "PQL = " + this.pqlQuery);
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
        final String format = String.format("[{'from':%d,'to':%d}, 'summary']", this.fromVideo, this.toVideo);
        final String format2 = String.format("'%s'", this.iqLoMoId);
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), format2));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), this.iqLoMoIndex));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), "'queue'"));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", format));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
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
            Log.d("nf_service_browse_refreshcwrequest", "RefreshIQRequest finished onFailure statusCode=" + status);
            this.responseCallback.onIQListRefresh(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_refreshcwrequest", "RefreshIQRequest finished onSuccess");
            this.responseCallback.onIQListRefresh(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s.substring(0, Math.min(s.length(), 1500)));
        }
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s).getAsJsonObject();
            if (FalcorParseUtils.containsErrors(asJsonObject)) {
                throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s);
            throw new FalcorParseException("Error in creating JsonObject", ex);
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            AddToQueueRequest.parseRefreshIqVideosAndUpdateCache(asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.iqLoMoIndex), this.fromVideo, this.toVideo, this.browseCache);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
