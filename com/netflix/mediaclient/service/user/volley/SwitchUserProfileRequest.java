// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.UserBoundCookies;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class SwitchUserProfileRequest extends FalcorVolleyWebClientRequest<UserBoundCookies>
{
    private static final String FIELD_PROFILE = "profile";
    private static final String TAG = "nf_service_user_switchuserprofilerequest";
    private final String guid;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public SwitchUserProfileRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String guid, final UserAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.guid = guid;
        this.pqlQuery = new StringBuilder("['switchProfile']").toString();
        if (Log.isLoggable("nf_service_user_switchuserprofilerequest", 2)) {
            Log.v("nf_service_user_switchuserprofilerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder append = new StringBuilder().append(FalcorVolleyWebClientRequest.urlEncodPQLParam(FalcorParseUtils.getParamNameParam(), "'" + this.guid + "'"));
        Log.d("nf_service_user_switchuserprofilerequest", " getOptionalParams: " + append.toString());
        return append.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserProfileSwitched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final UserBoundCookies userBoundCookies) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserProfileSwitched(userBoundCookies, 0);
        }
    }
    
    @Override
    protected UserBoundCookies parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_switchuserprofilerequest", 2)) {
            Log.v("nf_service_user_switchuserprofilerequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_user_switchuserprofilerequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("User empty!!!");
        }
        try {
            return FalcorParseUtils.getPropertyObject(dataObj.getAsJsonObject("profile").getAsJsonObject(this.guid), "userTokens", UserBoundCookies.class);
        }
        catch (Exception ex) {
            Log.v("nf_service_user_switchuserprofilerequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing user json objects", ex);
        }
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
