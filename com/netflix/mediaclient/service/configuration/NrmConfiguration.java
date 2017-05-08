// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmLanguagesData;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import android.content.Context;

public class NrmConfiguration
{
    private static String TAG;
    Context mContext;
    NrmConfigData mNrmConfigData;
    NrmLanguagesData mNrmLanguagesData;
    
    static {
        NrmConfiguration.TAG = "nf_config_nrm";
    }
    
    public NrmConfiguration(final Context mContext) {
        this.mContext = mContext;
        final String stringPref = PreferenceUtils.getStringPref(this.mContext, "nrmInfo", null);
        final String stringPref2 = PreferenceUtils.getStringPref(this.mContext, "nrmLanguages", null);
        this.mNrmConfigData = NrmConfigData.fromJsonString(stringPref);
        this.mNrmLanguagesData = NrmLanguagesData.fromJsonString(stringPref2);
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "nrmInfo", null);
        PreferenceUtils.putStringPref(this.mContext, "nrmLanguages", null);
    }
    
    public NrmConfigData getNrmConfigData() {
        return this.mNrmConfigData;
    }
    
    public NrmLanguagesData getNrmLanguagesData() {
        return this.mNrmLanguagesData;
    }
    
    public void persistNrmConfigOverride(final NrmConfigData mNrmConfigData) {
        String jsonString;
        if (mNrmConfigData == null) {
            jsonString = "";
        }
        else {
            jsonString = mNrmConfigData.toJsonString();
        }
        if (Log.isLoggable()) {
            Log.d(NrmConfiguration.TAG, "Persisting nrmConfigData to config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "nrmInfo", jsonString);
        this.mNrmConfigData = mNrmConfigData;
    }
    
    public void persistNrmLanguagesOverride(final NrmLanguagesData mNrmLanguagesData) {
        String jsonString;
        if (mNrmLanguagesData == null) {
            jsonString = "";
        }
        else {
            jsonString = mNrmLanguagesData.toJsonString();
        }
        if (Log.isLoggable()) {
            Log.d(NrmConfiguration.TAG, "Persisting nrmConfigData to config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "nrmLanguages", jsonString);
        this.mNrmLanguagesData = mNrmLanguagesData;
    }
}
