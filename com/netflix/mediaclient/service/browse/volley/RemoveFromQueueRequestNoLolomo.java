// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.browse.cache.SoftCache;
import com.netflix.mediaclient.service.browse.BrowseAgentCallback;
import com.netflix.mediaclient.service.browse.cache.HardCache;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RemoveFromQueueRequestNoLolomo extends FalcorVolleyWebClientRequest<String>
{
    public static final String TAG = "nf_service_browse_removefromqueuerequest";
    private final HardCache hardCache;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache softCache;
    
    public RemoveFromQueueRequestNoLolomo(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final String mVideoId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.mVideoId = mVideoId;
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
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequestNoLolomo finished onFailure statusCode=" + n);
            this.responseCallback.onQueueRemove(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_removefromqueuerequest", "RemoveFromQueueRequestNoLolomo finished onSuccess");
            this.responseCallback.onQueueRemove(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_removefromqueuerequest", 2)) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
        }
        JsonObject asJsonObject = null;
        Label_0122: {
            try {
                asJsonObject = new JsonParser().parse(s).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0122;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("NotInQueue")) {
                    return Integer.toString(0);
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
            return Integer.toString(0);
        }
        try {
            FetchIQVideosRequest.updateMdpWithIQInfo(this.hardCache, this.softCache, this.mVideoId, FalcorParseUtils.getPropertyObject(asJsonObject2.getAsJsonObject("videos").getAsJsonObject(this.mVideoId), "inQueue", Video.InQueue.class).inQueue);
            return Integer.toString(0);
        }
        catch (Exception ex2) {
            Log.v("nf_service_browse_removefromqueuerequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
