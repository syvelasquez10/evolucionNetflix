// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
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
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "abTestConfig", null);
    }
    
    public ABTestConfig$Cell getCoppola1TestCell() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getCoppola1ABTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getCoppola1ABTestConfig().getCell();
    }
    
    public ABTestConfig$Cell getMotionBBTestConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getMotionBBTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getMotionBBTestConfig().getCell();
    }
    
    public ABTestConfig$Cell getPhoneOrientationConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getPhoneOrientationConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getPhoneOrientationConfig().getCell();
    }
    
    public ABTestConfig$Cell getPushNotificationOptInConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getPushOptInConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getPushOptInConfig().getCell();
    }
    
    public ABTestConfig$Cell getVoiceSearchABTestConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getVoiceSearchABTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getVoiceSearchABTestConfig().getCell();
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
    }
}
