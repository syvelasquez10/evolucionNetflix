// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.util.StringUtils;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.repository.SecurityRepository;
import com.netflix.mediaclient.util.AndroidUtils;

public final class SignUpConfiguration
{
    private static String BASE_BOOTLOADER_URL;
    public static final Boolean DEFAULT_SIGNUP_ENABLED;
    public static final long SIGNUP_TIMEOUT_MS = 120000L;
    private static final String TAG = "nf_service_configuration_signuppref";
    private static String mSignUpBootloader;
    private boolean mIsSignUpEnabled;
    private long mSignUpTimeout;
    
    static {
        DEFAULT_SIGNUP_ENABLED = (AndroidUtils.getAndroidVersion() > 8);
        SignUpConfiguration.BASE_BOOTLOADER_URL = SecurityRepository.getBootloaderUrl();
        SignUpConfiguration.mSignUpBootloader = SignUpConfiguration.BASE_BOOTLOADER_URL;
    }
    
    public SignUpConfiguration(final Context context) {
        this.mIsSignUpEnabled = PreferenceUtils.getBooleanPref(context, "signup_enabled", SignUpConfiguration.DEFAULT_SIGNUP_ENABLED);
        this.mSignUpTimeout = PreferenceUtils.getLongPref(context, "signup_timeout", 120000L);
        Log.d("nf_service_configuration_signuppref", "SignUpConfiguration Enabled: " + this.mIsSignUpEnabled + " timeout: " + this.mSignUpTimeout);
    }
    
    public static void clearRecords(final Context context) {
        final NetflixPreference netflixPreference = new NetflixPreference(context);
        netflixPreference.removePref("signup_enabled");
        netflixPreference.removePref("signup_timeout");
        netflixPreference.commit();
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
    
    public void update(final NetflixPreference netflixPreference, final String s, final String s2, final String s3) {
        if (s != null) {
            netflixPreference.putBooleanPref("signup_enabled", this.mIsSignUpEnabled = Boolean.parseBoolean(s));
        }
        else {
            this.mIsSignUpEnabled = SignUpConfiguration.DEFAULT_SIGNUP_ENABLED;
            netflixPreference.removePref("signup_enabled");
        }
        if (s2 != null) {
            netflixPreference.putLongPref("signup_timeout", this.mSignUpTimeout = Long.parseLong(s2));
        }
        else {
            this.mSignUpTimeout = 120000L;
            netflixPreference.removePref("signup_timeout");
        }
        if (StringUtils.isNotEmpty(s3)) {
            SignUpConfiguration.mSignUpBootloader = SignUpConfiguration.BASE_BOOTLOADER_URL + s3;
            Log.d("nf_service_configuration_signuppref", "mSignUpBootloader: " + SignUpConfiguration.mSignUpBootloader);
        }
        if (Log.isLoggable()) {
            Log.d("nf_service_configuration_signuppref", String.format("SignUp parameters overriden SignUpEnabled: %s, timeout:%s, bootUrl:%s", s, s2, s3));
        }
    }
}
