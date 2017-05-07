// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import com.google.gson.JsonObject;
import java.util.List;
import com.netflix.mediaclient.service.webclient.model.leafs.User$Summary;
import com.netflix.mediaclient.service.webclient.model.leafs.User;
import com.netflix.mediaclient.service.webclient.model.leafs.SubtitlePreference;
import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile$Summary;
import com.netflix.mediaclient.service.webclient.model.leafs.UserProfile;
import java.util.ArrayList;
import com.netflix.mediaclient.service.webclient.model.leafs.ListSummary;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class FetchAccountDataRequest extends FalkorVolleyWebClientRequest<AccountData>
{
    private static final String FIELD_PROFILES = "profilesList";
    private static final String FIELD_USER = "user";
    public static final int MAX_PROFILES = 5;
    private static final String TAG = "nf_service_user_fetchaccountdatarequest";
    private final String pqlQuery1;
    private final String pqlQuery2;
    private final String pqlQuery3;
    private final UserAgentWebCallback responseCallback;
    
    public FetchAccountDataRequest(final Context context, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.pqlQuery1 = new StringBuilder("['profilesList', 'summary']").toString();
        this.pqlQuery2 = "['profilesList', {'to':" + 5 + "}, ['summary', 'subtitlePreference']]";
        this.pqlQuery3 = new StringBuilder("['user', ['summary', 'subtitleDefaults']]").toString();
        if (Log.isLoggable("nf_service_user_fetchaccountdatarequest", 2)) {
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery1);
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery2);
            Log.v("nf_service_user_fetchaccountdatarequest", "PQL = " + this.pqlQuery3);
        }
    }
    
    public static AccountData parseProfilesList(final String s, final boolean b) {
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_service_user_fetchaccountdatarequest", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorParseException("UserProfiles empty!!!");
        }
        ArrayList<UserProfile> userProfiles;
        while (true) {
            while (true) {
                Label_0414: {
                    while (true) {
                        int n = 0;
                        Label_0240: {
                            JsonObject asJsonObject2;
                            UserProfile userProfile;
                            try {
                                final JsonObject asJsonObject = dataObj.getAsJsonObject("profilesList");
                                if (!asJsonObject.has("summary")) {
                                    break Label_0414;
                                }
                                final int length = FalkorParseUtils.getPropertyObject(asJsonObject, "summary", ListSummary.class).getLength();
                                userProfiles = new ArrayList<UserProfile>();
                                n = 0;
                                if (n > length) {
                                    break;
                                }
                                final String string = Integer.toString(n);
                                if (!asJsonObject.has(string)) {
                                    break Label_0240;
                                }
                                asJsonObject2 = asJsonObject.getAsJsonObject(string);
                                userProfile = new UserProfile();
                                userProfile.summary = FalkorParseUtils.getPropertyObject(asJsonObject2, "summary", UserProfile$Summary.class);
                                if (userProfile.summary == null || StringUtils.isEmpty(userProfile.getProfileToken())) {
                                    throw new FalkorParseException("response missing summary" + s);
                                }
                            }
                            catch (Exception ex) {
                                Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
                                throw new FalkorParseException("response missing user json objects", ex);
                            }
                            userProfile.subtitlePreference = FalkorParseUtils.getPropertyObject(asJsonObject2, "subtitlePreference", SubtitlePreference.class);
                            userProfiles.add(userProfile);
                        }
                        ++n;
                        continue;
                    }
                }
                final int length = 5;
                continue;
            }
        }
        final AccountData accountData = new AccountData();
        if (b) {
            final User user = new User();
            JsonObject asJsonObject3;
            try {
                asJsonObject3 = dataObj.getAsJsonObject("user");
                user.summary = FalkorParseUtils.getPropertyObject(asJsonObject3, "summary", User$Summary.class);
                if (user.summary == null || StringUtils.isEmpty(user.getUserToken())) {
                    throw new FalkorParseException("response missing summary" + s);
                }
            }
            catch (Exception ex2) {
                Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
                throw new FalkorParseException("response missing user json objects", ex2);
            }
            user.subtitleDefaults = FalkorParseUtils.getPropertyObject(asJsonObject3, "subtitleDefaults", SubtitlePreference.class);
            accountData.setUser(user);
        }
        accountData.setUserProfiles(userProfiles);
        return accountData;
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery1, this.pqlQuery2, this.pqlQuery3);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onAccountDataFetched(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final AccountData accountData) {
        if (this.responseCallback != null) {
            this.responseCallback.onAccountDataFetched(accountData, CommonStatus.OK);
        }
    }
    
    @Override
    protected AccountData parseFalkorResponse(final String s) {
        if (Log.isLoggable("nf_service_user_fetchaccountdatarequest", 2)) {
            Log.v("nf_service_user_fetchaccountdatarequest", "String response to parse = " + s);
        }
        return parseProfilesList(s, true);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
