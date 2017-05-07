// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.BaseWebClient;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchConnectWithFacebookRequest extends FalcorVolleyWebClientRequest<String>
{
    private static final String FIELD_USER = "user";
    private static final String TAG = "nf_service_user_fetchwebuserrequest";
    private final String mAccessToken;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public FetchConnectWithFacebookRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String mAccessToken, final UserAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.mAccessToken = mAccessToken;
        this.responseCallback = responseCallback;
        this.pqlQuery = "['connectWithFacebook', '" + this.mAccessToken + "']";
        if (Log.isLoggable("nf_service_user_fetchwebuserrequest", 2)) {
            Log.v("nf_service_user_fetchwebuserrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onConnectWithFacebook(n, null);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            this.responseCallback.onConnectWithFacebook(BaseWebClient.getFBConnectStatusCode(s), s);
        }
    }
    
    @Override
    protected String parseFalcorResponse(String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_fetchwebuserrequest", 2)) {}
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_user_fetchwebuserrequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("UserConnectWith empty!!!");
        }
        try {
            s = (String)FalcorParseUtils.getPropertyObject(dataObj.getAsJsonObject("user"), "operation", UserProfile.Operation.class);
            if (s != null) {
                return ((UserProfile.Operation)s).msg;
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_fetchwebuserrequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing user json objects", ex);
        }
        return new String("unknown error");
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
