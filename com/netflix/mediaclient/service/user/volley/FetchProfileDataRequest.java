// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchProfileDataRequest extends FalcorVolleyWebClientRequest<UserProfile>
{
    private static final String FIELD_PROFILES = "profiles";
    private static final String TAG = "nf_service_user_fetchprofiledatarequest";
    private final String mProfileId;
    private final String pqlQuery1;
    private final UserAgentWebCallback responseCallback;
    
    public FetchProfileDataRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final String mProfileId, final UserAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.mProfileId = mProfileId;
        this.pqlQuery1 = "['profiles','" + mProfileId + "',['summary', 'subtitlePreference']]";
        if (Log.isLoggable("nf_service_user_fetchprofiledatarequest", 2)) {
            Log.v("nf_service_user_fetchprofiledatarequest", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onProfileDataFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final UserProfile userProfile) {
        if (this.responseCallback != null) {
            this.responseCallback.onProfileDataFetched(userProfile, 0);
        }
    }
    
    @Override
    protected UserProfile parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_fetchprofiledatarequest", 2)) {
            Log.v("nf_service_user_fetchprofiledatarequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_user_fetchprofiledatarequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("UserProfile empty!!!");
        }
        JsonObject asJsonObject;
        UserProfile userProfile;
        try {
            asJsonObject = dataObj.getAsJsonObject("profiles").getAsJsonObject(this.mProfileId);
            userProfile = new UserProfile();
            userProfile.summary = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", UserProfile.Summary.class);
            if (userProfile.summary == null || StringUtils.isEmpty(userProfile.getUserId())) {
                throw new FalcorParseException("response missing summary" + s);
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_fetchprofiledatarequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing profiles json objects", ex);
        }
        userProfile.subtitlePreference = FalcorParseUtils.getPropertyObject(asJsonObject, "subtitlePreference", SubtitlePreference.class);
        return userProfile;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
