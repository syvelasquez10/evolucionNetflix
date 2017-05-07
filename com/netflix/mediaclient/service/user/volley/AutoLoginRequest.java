// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.user.volley;

import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.user.UserAgentWebCallback;
import com.netflix.mediaclient.javabridge.ui.ActivationTokens;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class AutoLoginRequest extends FalkorVolleyWebClientRequest<ActivationTokens>
{
    private static final String TAG = "nf_service_user_autologinrequest";
    private String mPqlQuery;
    private UserAgentWebCallback mResponseCallback;
    private String mToken;
    
    public AutoLoginRequest(final Context context, final String mToken, final UserAgentWebCallback mResponseCallback) {
        super(context);
        this.mResponseCallback = mResponseCallback;
        if (mToken == null) {
            this.mToken = "";
        }
        else {
            this.mToken = mToken;
        }
        this.mPqlQuery = "['autoLogin','" + this.mToken + "']";
        if (Log.isLoggable()) {
            Log.v("nf_service_user_autologinrequest", "PQL = " + this.mPqlQuery);
        }
    }
    
    @Override
    protected String getMethodType() {
        return "get";
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.mPqlQuery);
    }
    
    @Override
    protected boolean isAuthorizationRequired() {
        return false;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.mResponseCallback != null) {
            this.mResponseCallback.onAutologinCompleted(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final ActivationTokens activationTokens) {
        if (this.mResponseCallback != null) {
            this.mResponseCallback.onAutologinCompleted(activationTokens, CommonStatus.OK);
        }
    }
    
    @Override
    protected ActivationTokens parseFalkorResponse(String string) {
        if (Log.isLoggable()) {
            Log.v("nf_service_user_autologinrequest", "String response to parse = " + string);
        }
        String string2;
        try {
            final JSONObject jsonObject = new JSONObject(string).getJSONObject("value").getJSONObject("credentials");
            string = jsonObject.getString("netflixId");
            string2 = jsonObject.getString("secureNetflixId");
            if (StringUtils.isEmpty(string) || StringUtils.isEmpty(string2)) {
                throw new FalkorException("Empty tokens!");
            }
        }
        catch (Throwable t) {
            Log.e("nf_service_user_autologinrequest", "Failed", t);
            throw new FalkorException(t);
        }
        return new ActivationTokens(string, string2);
    }
}
