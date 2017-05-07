// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalcorServerException;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseException;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountData;
import com.netflix.mediaclient.service.webclient.volley.FalcorVolleyWebClientRequest;

public class RemoveUserProfileRequest extends FalcorVolleyWebClientRequest<AccountData>
{
    private static final String TAG = "nf_service_user_removeuserprofilerequest";
    private final String pqlQuery;
    private final UserAgentWebCallback responseCallback;
    
    public RemoveUserProfileRequest(final Context context, final String s, final UserAgentWebCallback responseCallback) {
        super(context);
        this.responseCallback = responseCallback;
        this.pqlQuery = "['profiles', ['" + s + "'], 'remove']";
        if (Log.isLoggable("nf_service_user_removeuserprofilerequest", 2)) {
            Log.v("nf_service_user_removeuserprofilerequest", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return FalcorParseUtils.getMethodNameCall();
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", String.format("[{'to':%s}, 'summary']", 5)));
        sb.append(FalcorVolleyWebClientRequest.urlEncodPQLParam("pathSuffix", "['summary']"));
        if (Log.isLoggable("nf_service_user_removeuserprofilerequest", 3)) {
            Log.d("nf_service_user_removeuserprofilerequest", " getOptionalParams: " + sb.toString());
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
    protected AccountData parseFalcorResponse(final String s) throws FalcorParseException, FalcorServerException {
        if (Log.isLoggable("nf_service_user_removeuserprofilerequest", 2)) {
            Log.v("nf_service_user_removeuserprofilerequest", "String response to parse = " + s);
        }
        return FetchAccountDataRequest.parseProfilesList(s, false);
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
