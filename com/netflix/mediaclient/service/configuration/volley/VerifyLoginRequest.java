// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration.volley;

import com.google.gson.JsonObject;
import com.netflix.mediaclient.service.webclient.volley.FalkorException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.android.app.NetflixImmutableStatus;
import com.netflix.mediaclient.android.app.CommonStatus;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.List;
import com.netflix.mediaclient.Log;
import android.content.Context;
import com.netflix.mediaclient.service.configuration.ConfigurationAgentWebCallback;
import com.netflix.mediaclient.service.ServiceAgent$ConfigurationAgentInterface;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInData;
import com.netflix.mediaclient.service.webclient.volley.FalkorVolleyWebClientRequest;

public class VerifyLoginRequest extends FalkorVolleyWebClientRequest<SignInData>
{
    private static final String FIELD_SIGNIN_VERIFY = "signInVerify";
    private static final String TAG = "nf_login";
    private final String mCode;
    private final ServiceAgent$ConfigurationAgentInterface mConfigAgent;
    private final String mId;
    private final String pqlQuery;
    private final ConfigurationAgentWebCallback responseCallback;
    
    protected VerifyLoginRequest(final Context context, final ServiceAgent$ConfigurationAgentInterface mConfigAgent, final String mId, final String mCode, final ConfigurationAgentWebCallback responseCallback) {
        super(context, 1);
        this.responseCallback = responseCallback;
        this.mId = mId;
        this.mCode = mCode;
        this.mConfigAgent = mConfigAgent;
        this.pqlQuery = "['signInVerify']";
        if (Log.isLoggable()) {
            Log.v("nf_login", "PQL = " + this.pqlQuery);
        }
    }
    
    @Override
    protected List<String> getPQLQueries() {
        return Arrays.asList(this.pqlQuery);
    }
    
    @Override
    protected Map<String, String> getParams() {
        final NrmConfigData nrmConfigData = this.mConfigAgent.getNrmConfigData();
        final SignInConfigData signInConfigData = this.mConfigAgent.getSignInConfigData();
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        if (signInConfigData != null) {
            hashMap.put("flwssn", signInConfigData.flwssn);
        }
        if (nrmConfigData != null) {
            hashMap.put("netflixId", nrmConfigData.netflixId);
            hashMap.put("secureNetflixId", nrmConfigData.secureNetflixId);
        }
        hashMap.put("email", this.mId);
        hashMap.put("password", this.mCode);
        hashMap.put("esn", this.mConfigAgent.getEsnProvider().getEsn());
        Log.d("nf_login", "signInParams= " + hashMap.toString());
        return hashMap;
    }
    
    @Override
    protected boolean isAuthorizationRequired() {
        return false;
    }
    
    @Override
    protected void onFailure(final Status status) {
        if (this.responseCallback != null) {
            this.responseCallback.onLoginVerified(null, status);
        }
    }
    
    @Override
    protected void onSuccess(final SignInData signInData) {
        Log.d("nf_login", "login verify: singInData:" + signInData);
        if (this.responseCallback != null) {
            NetflixImmutableStatus netflixImmutableStatus = CommonStatus.SIGNIN_FAILURE;
            if (signInData != null) {
                netflixImmutableStatus = netflixImmutableStatus;
                if (signInData.isSignInSuccessful()) {
                    netflixImmutableStatus = CommonStatus.OK;
                }
            }
            this.responseCallback.onLoginVerified(signInData, netflixImmutableStatus);
        }
    }
    
    @Override
    protected SignInData parseFalkorResponse(final String s) {
        if (Log.isLoggable()) {
            Log.v("nf_login", "String response to parse = " + s);
        }
        final JsonObject dataObj = FalkorParseUtils.getDataObj("nf_login", s);
        if (FalkorParseUtils.isEmpty(dataObj)) {
            throw new FalkorException("verifyLogin empty!!!");
        }
        try {
            return FalkorParseUtils.getPropertyObject(dataObj, "signInVerify", SignInData.class);
        }
        catch (Exception ex) {
            Log.v("nf_login", "String response to parse = " + s);
            throw new FalkorException("response missing json objects", ex);
        }
    }
    
    @Override
    protected boolean shouldSkipProcessingOnInvalidUser() {
        return false;
    }
}
