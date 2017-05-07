// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidUtils;

public final class SignUpConfiguration
{
    public static final Boolean DEFAULT_SIGNUP_ENABLED;
    public static final long SIGNUP_TIMEOUT_MS = 40000L;
    private static final String TAG = "nf_service_configuration_signuppref";
    private static final String mSignUpBootloader;
    private boolean mIsSignUpEnabled;
    private long mSignUpTimeout;
    
    static {
        DEFAULT_SIGNUP_ENABLED = (AndroidUtils.getAndroidVersion() > 8);
        mSignUpBootloader = SecurityRepository.getBootloaderUrl();
    }
    
    public SignUpConfiguration(final Context context) {
        this.mIsSignUpEnabled = PreferenceUtils.getBooleanPref(context, "signup_enabled", SignUpConfiguration.DEFAULT_SIGNUP_ENABLED);
        this.mSignUpTimeout = PreferenceUtils.getLongPref(context, "signup_timeout", 40000L);
        Log.d("nf_service_configuration_signuppref", "SignUpConfiguration Enabled: " + this.mIsSignUpEnabled + " timeout: " + this.mSignUpTimeout);
    }
    
    public static void clearRecords(final Context context) {
        PreferenceUtils.removePref(context, "signup_enabled");
        PreferenceUtils.removePref(context, "signup_timeout");
    }
    
    public String getSignUpBootloader() {
        return SignUpConfiguration.mSignUpBootloader;
    }
    
    public long getSignUpTimeout() {
        return this.mSignUpTimeout;
    }
    
    public boolean isSignEnabled() {
        return this.mIsSignUpEnabled;
    }
    
    public void setIsSignEnabled(final boolean mIsSignUpEnabled) {
        this.mIsSignUpEnabled = mIsSignUpEnabled;
    }
    
    public void setSignUpTimeout(final long mSignUpTimeout) {
        this.mSignUpTimeout = mSignUpTimeout;
    }
    
    public void update(final Context context, final String s, final String s2) {
        if (s != null) {
            PreferenceUtils.putBooleanPref(context, "signup_enabled", this.mIsSignUpEnabled = Boolean.parseBoolean(s));
        }
        else {
            this.mIsSignUpEnabled = SignUpConfiguration.DEFAULT_SIGNUP_ENABLED;
            PreferenceUtils.removePref(context, "signup_enabled");
        }
        if (s2 != null) {
            PreferenceUtils.putLongPref(context, "signup_timeout", this.mSignUpTimeout = Long.parseLong(s2));
        }
        else {
            this.mSignUpTimeout = 40000L;
            PreferenceUtils.removePref(context, "signup_timeout");
        }
        Log.d("nf_service_configuration_signuppref", "SignUp parameters overriden SignUpEnabled: " + s + " timeout:" + s2);
    }
}
