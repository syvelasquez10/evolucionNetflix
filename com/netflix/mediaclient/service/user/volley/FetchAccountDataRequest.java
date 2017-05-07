// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.google.gson.JsonObject;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.ServiceAgent;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class FetchAccountDataRequest extends FalcorVolleyWebClientRequest<AccountData>
{
    private static final String FIELD_PROFILES = "profilesList";
    private static final String FIELD_USER = "user";
    private static final int MAX_PROFILES = 20;
    private static final String TAG = "nf_service_user_fetchaccountdatarequest";
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final UserAgentWebCallback responseCallback;
    
    public FetchAccountDataRequest(final Context context, final ServiceAgent.ConfigurationAgentInterface configurationAgentInterface, final UserAgentWebCallback responseCallback) {
        super(context, configurationAgentInterface);
        this.responseCallback = responseCallback;
        this.pqlQuery1 = new StringBuilder("['profilesList', 'summary']").toString();
        this.pqlQuery2 = "['profilesList', {'to':" + 20 + "}, ['summary', 'subtitlePreference']]";
        this.pqlQuery3 = new StringBuilder("['user', ['summary', 'subtitleDefaults']]").toString();
        if (Log.isLoggable("nf_service_user_fetchaccountdatarequest", 2)) {
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery1);
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery2);
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery3);
        }
    }
    
    @Override
    protected String[] getPQLQueries() {
        return new String[] { this.pqlQuery1, this.pqlQuery2, this.pqlQuery3 };
    }
    
    @Override
    protected void onFailure(final int n) {
        if (this.responseCallback != null) {
            this.responseCallback.onAccountDataFetched(null, n);
        }
    }
    
    @Override
    protected void onSuccess(final AccountData accountData) {
        if (this.responseCallback != null) {
            this.responseCallback.onAccountDataFetched(accountData, 0);
        }
    }
    
    @Override
    protected AccountData parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_fetchaccountdatarequest", 2)) {
            Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalcorParseUtils.getDataObj("nf_service_user_fetchaccountdatarequest", s);
        if (FalcorParseUtils.isEmpty(dataObj)) {
            throw new FalcorParseException("UserProfiles empty!!!");
        }
        ArrayList<UserProfile> userProfiles;
        while (true) {
            while (true) {
                int n = 0;
                Label_0277: {
                    JsonObject asJsonObject2;
                    UserProfile userProfile;
                    try {
                        final JsonObject asJsonObject = dataObj.getAsJsonObject("profilesList");
                        int length = 20;
                        if (asJsonObject.has("summary")) {
                            length = FalcorParseUtils.getPropertyObject(asJsonObject, "summary", ListSummary.class).getLength();
                        }
                        userProfiles = new ArrayList<UserProfile>();
                        n = 0;
                        if (n > length) {
                            break;
                        }
                        final String string = Integer.toString(n);
                        if (!asJsonObject.has(string)) {
                            break Label_0277;
                        }
                        asJsonObject2 = asJsonObject.getAsJsonObject(string);
                        userProfile = new UserProfile();
                        userProfile.summary = FalcorParseUtils.getPropertyObject(asJsonObject2, "summary", UserProfile.Summary.class);
                        if (userProfile.summary == null || StringUtils.isEmpty(userProfile.getUserId())) {
                            throw new FalcorParseException("response missing summary" + s);
                        }
                    }
                    catch (Exception ex) {
                        Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
                        throw new FalcorParseException("response missing user json objects", ex);
                    }
                    userProfile.subtitlePreference = FalcorParseUtils.getPropertyObject(asJsonObject2, "subtitlePreference", SubtitlePreference.class);
                    userProfiles.add(userProfile);
                }
                ++n;
                continue;
            }
        }
        final User user = new User();
        JsonObject asJsonObject3;
        try {
            asJsonObject3 = dataObj.getAsJsonObject("user");
            user.summary = FalcorParseUtils.getPropertyObject(asJsonObject3, "summary", User.Summary.class);
            if (user.summary == null || StringUtils.isEmpty(user.getUserId())) {
                throw new FalcorParseException("response missing summary" + s);
            }
        }
        catch (Exception ex2) {
            Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
            throw new FalcorParseException("response missing user json objects", ex2);
        }
        user.subtitleDefaults = FalcorParseUtils.getPropertyObject(asJsonObject3, "subtitleDefaults", SubtitlePreference.class);
        final AccountData accountData = new AccountData();
        accountData.setUser(user);
        accountData.setUserProfiles(userProfiles);
        return accountData;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
