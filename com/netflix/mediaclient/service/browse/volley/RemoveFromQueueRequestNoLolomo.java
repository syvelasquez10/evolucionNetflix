// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.netflix.mediaclient.StatusCode;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.BrowseWebClientCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RemoveFromQueueRequestNoLolomo extends FalcorVolleyWebClientRequest<String>
{
    public static final String TAG = "nf_service_browse_removefromqueuerequest";
    private final BrowseWebClientCache browseCache;
    private final String mVideoId;
    private final String messageToken;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    
    public RemoveFromQueueRequestNoLolomo(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final String messageToken, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.browseCache = browseCache;
        this.messageToken = messageToken;
        this.pqlQuery = String.format("['videos', '%s', 'removeFromQueue']", mVideoId);
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        if (StringUtils.isEmpty(this.messageToken)) {
            return null;
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("signature", this.messageToken));
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 3)) {
            Log.d("nf_service_browse_removefromqueuerequest", " getOptionalParams: " + sb.toString());
        }
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 3)) {
                Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequestNoLolomo finished onFailure statusCode=" + status.getStatusCode());
            }
            this.responseCallback.onQueueRemove(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequestNoLolomo finished onSuccess");
            this.responseCallback.onQueueRemove(CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
        }
        JsonObject asJsonObject = null;
        Label_0138: {
            try {
                asJsonObject = new JsonParser().parse(s).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0138;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("NotInQueue")) {
                    return Integer.toString(StatusCode.OK.getValue());
                }
            }
            catch (Exception ex) {
                if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 6)) {
                    Log.d("nf_service_browse_removefromqueuerequest", "String response to parse = " + s, ex);
                }
                throw new FalcorParseException("Error in creating JsonObject", ex);
            }
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            this.browseCache.updateInQueueCacheRecord(this.mVideoId, FalcorParseUtils.getPropertyObject(asJsonObject2.getAsJsonObject("videos").getAsJsonObject(this.mVideoId), "inQueue", Video.InQueue.class).inQueue);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 6)) {
                Log.d("nf_service_browse_removefromqueuerequest", "String response to parse = " + s, ex2);
            }
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
