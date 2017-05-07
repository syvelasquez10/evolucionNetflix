// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import android.text.TextUtils;
import com.netflix.mediaclient.service.browse.volley.AddToQueueRequest;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class AddUserProfileRequest extends FalcorVolleyWebClientRequest<AccountData>
{
    private static final String TAG = "nf_service_user_adduserprofilerequest";
    private final String iconName;
    private final String name;
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    private final boolean startInKidsZone;
    
    public AddUserProfileRequest(final Context context, final String name, final boolean startInKidsZone, final String iconName, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.name = name;
        this.startInKidsZone = startInKidsZone;
        this.iconName = iconName;
        this.pqlQuery = new StringBuilder("['profilesList', 'add']").toString();
        if (Log.isLoggable("nf_service_user_adduserprofilerequest", 2)) {
            Log.v("nf_service_user_adduserprofilerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "call";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(FalcorVolleyWebClientRequest.urlEncodPQLParam("param", "'" + this.name + "'"));
        String s;
        if (this.startInKidsZone) {
            s = "\"jfk\"";
        }
        else {
            s = "\"standard\"";
        }
        sb.append(AddToQueueRequest.optionalParam).append(s);
        if (!TextUtils.isEmpty((CharSequence)this.iconName)) {
            sb.append(AddToQueueRequest.optionalParam).append("\"" + this.iconName + "\"");
        }
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", String.format("[{'to':%s}, 'summary']", 5)));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        if (Log.isLoggable("nf_service_user_adduserprofilerequest", 3)) {
            Log.d("nf_service_user_adduserprofilerequest", " getOptionalParams: " + sb.toString());
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
            this.responseCallback.onUserProfilesUpdated(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final AccountData accountData) {
        if (this.responseCallback != null) {
            this.responseCallback.onUserProfilesUpdated(accountData, CommonStatus.OK);
        }
    }
    
    @Override
    protected AccountData parseFalcorResponse(final String s) {
        if (Log.isLoggable("nf_service_user_adduserprofilerequest", 2)) {
            Log.v("nf_service_user_adduserprofilerequest", "String response to parse = " + s);
        }
        return FetchAccountDataRequest.parseProfilesList(s, false);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
