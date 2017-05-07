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

public class AddToQueueRequestNoLolomo extends FalcorVolleyWebClientRequest<String>
{
    public static final String FIELD_VIDEOS = "videos";
    public static final String TAG = "nf_service_browse_addtoqueuerequest";
    private final HardCache hardCache;
    private final String mVideoId;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final SoftCache softCache;
    private final int trackId;
    
    public AddToQueueRequestNoLolomo(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final HardCache hardCache, final SoftCache softCache, final String mVideoId, final int trackId, final BrowseAgentCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.hardCache = hardCache;
        this.softCache = softCache;
        this.mVideoId = mVideoId;
        this.trackId = trackId;
        this.pqlQuery = String.format("['videos', '%s', 'addToQueue']", mVideoId);
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder();
        sb.append(AddToQueueRequest.optionalParam).append(this.trackId);
        Log.d("nf_service_browse_addtoqueuerequest", " getOptionalParams: " + sb.toString());
        return sb.toString();
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished onFailure statusCode=" + n);
            this.responseCallback.onQueueAdd(n);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished onSuccess");
            this.responseCallback.onQueueAdd(0);
        }
    }
    
    @Override
    protected String parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + s);
        }
        JsonObject asJsonObject = null;
        Label_0122: {
            try {
                asJsonObject = new JsonParser().parse(s).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0122;
                }
                if (FalcorParseUtils.getErrorMessage(asJsonObject).contains("AlreadyInQueue")) {
                    return Integer.toString(0);
                }
            }
            catch (Exception ex) {
                Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + s);
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
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
