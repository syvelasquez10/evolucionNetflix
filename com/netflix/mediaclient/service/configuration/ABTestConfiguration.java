// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfig$Cell;
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
    
    public ABTestConfig$Cell getAimLowPrefetchDPConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getAimLowPrefetchDPConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getAimLowPrefetchDPConfig().getCell();
    }
    
    public ABTestConfig$Cell getAimLowPrefetchLolomoConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getAimLowPrefetchLolomoConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getAimLowPrefetchLolomoConfig().getCell();
    }
    
    public ABTestConfig$Cell getAimLowTextPlaceholderConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getAimLowTextPlaceholderConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getAimLowTextPlaceholderConfig().getCell();
    }
    
    public ABTestConfig$Cell getBrandLoveSurveyConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getBrandLoveSurveyConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getBrandLoveSurveyConfig().getCell();
    }
    
    public ABTestConfig$Cell getCWProgressBarConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getCWProgressBarConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getCWProgressBarConfig().getCell();
    }
    
    public ABTestConfig$Cell getCoppola1TestCell() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getCoppola1ABTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getCoppola1ABTestConfig().getCell();
    }
    
    public ABTestConfig$Cell getCoppola2TestCell() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getCoppola2ABTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getCoppola2ABTestConfig().getCell();
    }
    
    public ABTestConfig$Cell getDisplayPageRefreshConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getDisplayPageRefreshConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getDisplayPageRefreshConfig().getCell();
    }
    
    public ABTestConfig$Cell getMemento2Config() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getMemento2Config() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getMemento2Config().getCell();
    }
    
    public ABTestConfig$Cell getMementoConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getMementoConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getMementoConfig().getCell();
    }
    
    public ABTestConfig$Cell getMotionBBTestConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getMotionBBTestConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getMotionBBTestConfig().getCell();
    }
    
    public ABTestConfig$Cell getOfflineTutorialConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getOfflineTutorialConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getOfflineTutorialConfig().getCell();
    }
    
    public ABTestConfig$Cell getOnRampConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getOnRampConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getOnRampConfig().getCell();
    }
    
    public ABTestConfig$Cell getPhoneOrientationConfig() {
        if (this.mABTestConfigData == null || this.mABTestConfigData.getPhoneOrientationConfig() == null) {
            return ABTestConfig$Cell.CELL_ONE;
        }
        return this.mABTestConfigData.getPhoneOrientationConfig().getCell();
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
        ApmLogUtils.reportABConfigDataReceivedEvent(this.mContext);
    }
}
