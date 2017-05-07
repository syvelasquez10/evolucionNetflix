// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.User$Summary;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchUserDataRequest extends FalkorVolleyWebClientRequest<User>
{
    private static final String FIELD_USER = "user";
    private static final String TAG = "nf_service_user_fetchuserdatarequest";
    private final String pqlQuery1;
    private final UserAgentWebCallback responseCallback;
    
    public FetchUserDataRequest(final Context context, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.pqlQuery1 = "['user', ['summary', 'subtitleDefaults']]";
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchuserdatarequest", "PQL = " + this.pqlQuery1);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserDataFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final User user) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserDataFetched(user, CommonStatus.OK);
        }
    }
    
    @Override
    protected User parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_fetchuserdatarequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchuserdatarequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorException("UserProfile empty!!!");
        }
        final User user = new User();
        JsonObject asJsonObject;
        try {
            asJsonObject = dataObj.getAsJsonObject("user");
            user.summary = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", User$Summary.class);
            if (user.summary == null || StringUtils.isEmpty(user.getUserToken())) {
                throw new FalkorException("response missing summary" + s);
            }
        }
        catch (Exception ex) {
            Log.v("nf_service_user_fetchuserdatarequest", "String response to parse = " + s);
            throw new FalkorException("response missing user json objects", ex);
        }
        user.subtitleDefaults = FalkorParseUtils.getPropertyObject(asJsonObject, "subtitleDefaults", SubtitlePreference.class);
        return user;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
