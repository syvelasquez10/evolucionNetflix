// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.log.ApmLogUtils;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;

public class ABTestConfiguration
{
    private static String TAG;
    ABTestConfigData mABTestConfigData;
    Context mContext;
    
    static {
        ABTestConfiguration.TAG = "nf_ab_test_config";
    }
    
    public ABTestConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mABTestConfigData = ABTestConfigData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "abTestConfig", null));
        if (this.mABTestConfigData != null) {
            ApmLogUtils.reportABConfigDataLoadedEvent(this.mContext);
        }
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "abTestConfig", null);
    }
    
    public void persistABTestConfigOverride(final ABTestConfigData mabTestConfigData) {
        if (mabTestConfigData == null) {
            Log.e(ABTestConfiguration.TAG, "abTestConfig object is null - ignore overwrite");
            return;
        }
        final String jsonString = mabTestConfigData.toJsonString();
        if (Log.isLoggable()) {
            Log.d(ABTestConfiguration.TAG, "Persisting ab test config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "abTestConfig", jsonString);
        this.mABTestConfigData = mabTestConfigData;
        ApmLogUtils.reportABConfigDataReceivedEvent(this.mContext);
    }
}
