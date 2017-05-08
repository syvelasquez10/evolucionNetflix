// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.SignInConfigData;
import android.content.Context;

public class SignInConfiguration
{
    private static String TAG;
    Context mContext;
    SignInConfigData mSignInConfigData;
    
    static {
        SignInConfiguration.TAG = "nf_config_signin";
    }
    
    public SignInConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mSignInConfigData = SignInConfigData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "signInConfigData", null));
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "signInConfigData", null);
    }
    
    public SignInConfigData getSignInConfigData() {
        return this.mSignInConfigData;
    }
    
    public void persistSignInConfigOverride(final SignInConfigData mSignInConfigData) {
        if (mSignInConfigData == null) {
            Log.e(SignInConfiguration.TAG, "signInConfigData object is null - ignore overwrite");
            return;
        }
        final String jsonString = mSignInConfigData.toJsonString();
        if (Log.isLoggable()) {
            Log.d(SignInConfiguration.TAG, "Persisting signInConfigData to config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "signInConfigData", jsonString);
        this.mSignInConfigData = mSignInConfigData;
    }
}
