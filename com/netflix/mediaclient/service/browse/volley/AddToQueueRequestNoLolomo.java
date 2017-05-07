// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.browse.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.google.gson.JsonParser;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.StatusCode;
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

public class AddToQueueRequestNoLolomo extends FalcorVolleyWebClientRequest<String>
{
    public static final String FIELD_VIDEOS = "videos";
    public static final String TAG = "nf_service_browse_addtoqueuerequest";
    private final BrowseWebClientCache browseCache;
    private final String mVideoId;
    private final String messageToken;
    private final String pqlQuery;
    private final BrowseAgentCallback responseCallback;
    private final int trackId;
    
    public AddToQueueRequestNoLolomo(final Context context, final BrowseWebClientCache browseCache, final String mVideoId, final int trackId, final String messageToken, final BrowseAgentCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mVideoId = mVideoId;
        this.trackId = trackId;
        this.browseCache = browseCache;
        this.messageToken = messageToken;
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
        if (StringUtils.isNotEmpty(this.messageToken)) {
            sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("signature", this.messageToken));
        }
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 3)) {
            Log.d("nf_service_browse_addtoqueuerequest", " getOptionalParams: " + sb.toString());
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
            if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 3)) {
                Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished onFailure statusCode=" + status.getStatusCode());
            }
            this.responseCallback.onQueueAdd(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            if (s != null && s.equals(Integer.toString(StatusCode.ALREADY_IN_QUEUE.getValue()))) {
                Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished with alreadyInQueue");
                this.responseCallback.onQueueAdd(CommonStatus.ALREADY_IN_QUEUE);
            }
            else {
                if (s != null && s.equals(Integer.toString(StatusCode.NOT_VALID.getValue()))) {
                    Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished with not valid requst");
                    this.responseCallback.onQueueAdd(CommonStatus.NOT_VALID);
                    return;
                }
                Log.d("nf_service_browse_addtoqueuerequest", "AddToQueueRequestNoLolomo finished onSuccess");
                this.responseCallback.onQueueAdd(CommonStatus.OK);
            }
        }
    }
    
    @Override
    protected String parseFalcorResponse(String errorMessage) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 2)) {
            Log.v("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage);
        }
        JsonObject asJsonObject = null;
        Label_0179: {
            try {
                asJsonObject = new JsonParser().parse(errorMessage).getAsJsonObject();
                if (!FalcorParseUtils.containsErrors(asJsonObject)) {
                    break Label_0179;
                }
                Log.v("nf_service_browse_addtoqueuerequest", "Has errors");
                errorMessage = FalcorParseUtils.getErrorMessage(asJsonObject);
                if (FalcorParseUtils.isAlreadyInQueue(errorMessage)) {
                    Log.v("nf_service_browse_addtoqueuerequest", "AlreadyInQueue");
                    return Integer.toString(StatusCode.ALREADY_IN_QUEUE.getValue());
                }
            }
            catch (Exception ex) {
                if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 6)) {
                    Log.e("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage, ex);
                }
                throw new FalcorParseException("Error in creating JsonObject", ex);
            }
            if (FalcorParseUtils.wasRequestNotValid(errorMessage)) {
                Log.v("nf_service_browse_addtoqueuerequest", "Add to Queue Request not valid");
                return Integer.toString(StatusCode.NOT_VALID.getValue());
            }
            throw new FalcorServerException(FalcorParseUtils.getErrorMessage(asJsonObject));
        }
        final JsonObject asJsonObject2 = asJsonObject.getAsJsonObject("value");
        if (FalcorParseUtils.isEmpty(asJsonObject2)) {
            return Integer.toString(StatusCode.OK.getValue());
        }
        try {
            FetchIQVideosRequest.updateMdpAndSdpWithIQInfo(this.browseCache, this.mVideoId, FalcorParseUtils.getPropertyObject(asJsonObject2.getAsJsonObject("videos").getAsJsonObject(this.mVideoId), "inQueue", Video.InQueue.class).inQueue);
            return Integer.toString(StatusCode.OK.getValue());
        }
        catch (Exception ex2) {
            if (Log.isLoggable("nf_service_browse_addtoqueuerequest", 6)) {
                Log.e("nf_service_browse_addtoqueuerequest", "String response to parse = " + errorMessage, ex2);
            }
            throw new FalcorParseException("response missing expected json objects", ex2);
        }
    }
}
