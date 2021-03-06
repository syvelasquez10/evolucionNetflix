// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class SwitchUserProfileRequest extends FalkorVolleyWebClientRequest<UserBoundCookies>
{
    private static final String FIELD_PROFILE = "profile";
    private static final String TAG = "nf_service_user_switchuserprofilerequest";
    private final String guid;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public SwitchUserProfileRequest(final Context context, final String guid, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.guid = guid;
        this.pqlQuery = new StringBuilder("['switchProfile']").toString();
        if (Log.isLoggable()) {
            Log.v("nf_service_user_switchuserprofilerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final String urlEncodPQLParam = FalkorVolleyWebClientRequest.urlEncodPQLParam("param", "'" + this.guid + "'");
        if (Log.isLoggable()) {
            Log.d("nf_service_user_switchuserprofilerequest", "getOptionalParams: " + urlEncodPQLParam);
        }
        return urlEncodPQLParam;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserProfileSwitched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final UserBoundCookies userBoundCookies) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserProfileSwitched(userBoundCookies, CommonStatus.OK);
        }
    }
    
    @Override
    protected UserBoundCookies parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_switchuserprofilerequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_switchuserprofilerequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorException("User empty!!!");
        }
        UserBoundCookies userBoundCookies;
        try {
            userBoundCookies = FalkorParseUtils.getPropertyObject(dataObj.getAsJsonObject("profile").getAsJsonObject(this.guid), "userTokens", UserBoundCookies.class);
            if (userBoundCookies == null || StringUtils.isEmpty(userBoundCookies.getUserBoundNetflixId()) || StringUtils.isEmpty(userBoundCookies.getUserBoundSecureNetflixId())) {
                Log.v("nf_service_user_switchuserprofilerequest", "String response  = " + s);
                throw new FalkorException("SwitchProfile got empty userBoundCookies - failing");
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_switchuserprofilerequest", "String response to parse = " + s);
            throw new FalkorException("response missing user json objects", ex);
        }
        return userBoundCookies;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
