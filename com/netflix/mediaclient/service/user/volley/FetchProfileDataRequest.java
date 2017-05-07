// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile$Summary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchProfileDataRequest extends FalkorVolleyWebClientRequest<UserProfile>
{
    private static final String FIELD_PROFILES = "profiles";
    private static final String TAG = "nf_service_user_fetchprofiledatarequest";
    private final String mProfileId;
    private final String pqlQuery1;
    private final UserAgentWebCallback responseCallback;
    
    public FetchProfileDataRequest(final Context context, final String mProfileId, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.mProfileId = mProfileId;
        this.pqlQuery1 = "['profiles','" + mProfileId + "',['summary', 'subtitlePreference']]";
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchprofiledatarequest", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onProfileDataFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final UserProfile userProfile) {
        if (this.responseCallback != null) {
            this.responseCallback.onProfileDataFetched(userProfile, CommonStatus.OK);
        }
    }
    
    @Override
    protected UserProfile parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchprofiledatarequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchprofiledatarequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("UserProfile empty!!!");
        }
        JsonObject asJsonObject;
        UserProfile userProfile;
        try {
            asJsonObject = dataObj.getAsJsonObject("profiles").getAsJsonObject(this.mProfileId);
            userProfile = new UserProfile();
            userProfile.summary = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", UserProfile$Summary.class);
            if (userProfile.summary == null || StringUtils.isEmpty(userProfile.getProfileToken())) {
                throw new FalkorParseException("response missing summary" + s);
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_fetchprofiledatarequest", "String response to parse = " + s);
            throw new FalkorParseException("response missing profiles json objects", ex);
        }
        userProfile.subtitlePreference = FalkorParseUtils.getPropertyObject(asJsonObject, "subtitlePreference", SubtitlePreference.class);
        return userProfile;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
