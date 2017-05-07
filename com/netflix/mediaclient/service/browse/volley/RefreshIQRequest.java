// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.preapp.PreAppAgent;
import com.netflix.mediaclient.service.browse.BrowseAgent;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import android.annotation.SuppressLint;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

@SuppressLint({ "DefaultLocale" })
public class RefreshIQRequest extends FalkorVolleyWebClientRequest<String>
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
    private final int toVideo;
    
    protected RefreshIQRequest(final Context context, final BrowseWebClientCache browseCache, final int fromVideo, final int toVideo) {
        super(context);
        this.canMakeRequest = true;
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
    
    private void notifyOfRefresh() {
        BrowseAgent.sendIqRefreshBrodcast(this.mContext);
        PreAppAgent.informIqUpdated(this.mContext);
    }
    
    public boolean canProceed() {
        return this.canMakeRequest;
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final String format = String.format("'%s'", this.iqLoMoId);
        final StringBuilder sb = new StringBuilder();
        sb.append(FalkorVolleyWebClientRequest.urlEncodPQLParam("param", format));
        sb.append(FalkorVolleyWebClientRequest.urlEncodPQLParam("param", this.iqLoMoIndex));
        sb.append(FalkorVolleyWebClientRequest.urlEncodPQLParam("param", "'queue'"));
        sb.append(FalkorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", String.format("[{'from':%d,'to':%d}, 'summary']", this.fromVideo, this.toVideo)));
        sb.append(FalkorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        Log.d("nf_service_browse_refreshcwrequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        Log.d("nf_service_browse_refreshcwrequest", "RefreshIQRequest finished onFailure statusCode=" + status);
        this.notifyOfRefresh();
    }
    
    @Override
    protected void onSuccess(final String s) {
        Log.d("nf_service_browse_refreshcwrequest", "RefreshIQRequest finished onSuccess");
        this.notifyOfRefresh();
    }
    
    @Override
    protected String parseFalkorResponse(final String s) {
        if (Log.isLoggable("nf_service_browse_refreshcwrequest", 2)) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s.substring(0, Math.min(s.length(), 1500)));
        }
        JsonObject asJsonObject;
        try {
            asJsonObject = new JsonParser().parse(s).getAsJsonObject();
            if (FalkorParseUtils.hasErrors(asJsonObject)) {
                throw new FalkorServerException(FalkorParseUtils.getErrorMessage(asJsonObject));
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s);
            throw new FalkorParseException("Error in creating JsonObject", ex);
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalkorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            AddToQueueRequest.parseRefreshIqVideosAndUpdateCache(asJsonObject2.getAsJsonObject("lolomos").getAsJsonObject(this.lolomoId).getAsJsonObject(this.iqLoMoIndex), this.fromVideo, this.toVideo, this.browseCache);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_refreshcwrequest", "String response to parse = " + s);
            throw new FalkorParseException("response missing expected json objects", ex2);
        }
    }
}
