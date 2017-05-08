// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import com.netflix.mediaclient.service.webclient.model.leafs.NrmConfigData;
import android.content.Context;

public class NrmConfiguration
{
    private static String TAG;
    Context mContext;
    NrmConfigData mNrmConfigData;
    
    static {
        NrmConfiguration.TAG = "nf_config_nrm";
    }
    
    public NrmConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mNrmConfigData = NrmConfigData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "nrmInfo", null));
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "nrmInfo", null);
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
}
