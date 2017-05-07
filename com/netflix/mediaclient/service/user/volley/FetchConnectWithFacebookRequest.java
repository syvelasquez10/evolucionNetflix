// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile$Operation;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchConnectWithFacebookRequest extends FalkorVolleyWebClientRequest<String>
{
    private static final String FIELD_USER = "user";
    private static final String TAG = "nf_service_user_fetchwebuserrequest";
    private final String mAccessToken;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public FetchConnectWithFacebookRequest(final Context context, final String mAccessToken, final UserAgentWebCallback responseCallback) {
        super(context);
        this.mAccessToken = mAccessToken;
        this.responseCallback = responseCallback;
        this.pqlQuery = "['connectWithFacebook', '" + this.mAccessToken + "']";
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchwebuserrequest", "PQL = " + this.pqlQuery);
        }
    }
    
    private static Status getFBConnectStatus(final String message) {
        NetflixStatus netflixStatus = new NetflixStatus(StatusCode.OK);
        if (message.contains("202") || message.contains("200")) {
            netflixStatus = new NetflixStatus(StatusCode.OK);
        }
        else if (message.contains("400")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_CONNECT_MISSING_PARAMS);
        }
        else if (message.contains("401")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_CONNECT_INVALID_CREDENTIALS);
        }
        else if (message.contains("403") || message.contains("405")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_CONNECT_ID_ALREADY_IN_USE);
        }
        else if (message.contains("406")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_CONNECT_RETRY_AFTER_FB_SMS);
        }
        else if (message.contains("500")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_TRANSIENT_DO_NOT_RETRY);
        }
        else if (message.contains("503")) {
            netflixStatus = new NetflixStatus(StatusCode.USER_FB_TRANSIENT_RETRY);
        }
        netflixStatus.setMessage(message);
        return netflixStatus;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onConnectWithFacebook(status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.responseCallback != null) {
            this.responseCallback.onConnectWithFacebook(getFBConnectStatus(s));
        }
    }
    
    @Override
    protected String parseFalkorResponse(String s) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchwebuserrequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchwebuserrequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("UserConnectWith empty!!!");
        }
        try {
            s = (String)FalkorParseUtils.getPropertyObject(dataObj.getAsJsonObject("user"), "operation", UserProfile$Operation.class);
            if (s != null) {
                return ((UserProfile$Operation)s).status;
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_fetchwebuserrequest", "String response to parse = " + s);
            throw new FalkorParseException("response missing user json objects", ex);
        }
        return new String("400");
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
