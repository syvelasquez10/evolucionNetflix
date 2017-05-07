// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseException;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class CreateAutoLoginTokenRequest extends FalkorVolleyWebClientRequest<String>
{
    private static final String TAG = "nf_service_user_autologinrequest";
    private long mExpirationInMs;
    private String mPqlQuery;
    private UserAgentWebCallback mResponseCallback;
    
    public CreateAutoLoginTokenRequest(final Context context, final long mExpirationInMs, final UserAgentWebCallback mResponseCallback) {
        super(context);
        this.mPqlQuery = "['createAutoLoginToken']";
        this.mResponseCallback = mResponseCallback;
        this.mExpirationInMs = mExpirationInMs;
        if (Log.isLoggable()) {
            Log.v("nf_service_user_autologinrequest", "PQL = " + this.mPqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "get";
    }
    
    @Override
    protected String getOptionalParams() {
        final StringBuilder sb = new StringBuilder(FalkorVolleyWebClientRequest.urlEncodPQLParam("param", "\"" + this.mExpirationInMs + "\""));
        if (Log.isLoggable()) {
            Log.d("nf_service_user_autologinrequest", " getOptionalParams: " + sb.toString());
        }
        return sb.toString();
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.mPqlQuery);
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.mResponseCallback != null) {
            this.mResponseCallback.onAutoLoginTokenCreated(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final String s) {
        if (this.mResponseCallback != null) {
            this.mResponseCallback.onAutoLoginTokenCreated(s, CommonStatus.OK);
        }
    }
    
    @Override
    protected String parseFalkorResponse(String string) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_autologinrequest", "String response to parse = " + string);
        }
        try {
            string = new JSONObject(string).getJSONObject("value").getString("token");
            if (StringUtils.isEmpty(string)) {
                throw new FalkorParseException("Empty token!");
            }
        }
        catch (Throwable t) {
            Log.e("nf_service_user_autologinrequest", "Failed", t);
            throw new FalkorParseException(t);
        }
        return string;
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
