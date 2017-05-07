// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.service.webclient.model.leafs.ABTestConfigData;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;

public class AccountConfiguration
{
    private static String TAG;
    AccountConfigData mAccountConfigData;
    Context mContext;
    
    static {
        AccountConfiguration.TAG = "nf_configuration_account";
    }
    
    public AccountConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mAccountConfigData = AccountConfigData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "accountConfig", null));
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", null);
        PreferenceUtils.putIntPref(this.mContext, "user_bw_override", -1);
        PreferenceUtils.putIntPref(this.mContext, "user_bw_hd_override", -1);
    }
    
    public boolean enableHTTPSAuth() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableHTTPSAuth();
    }
    
    public boolean enableLowBitrateStreams() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableLowBitrateStreams();
    }
    
    public boolean enableWidevineL3ABTest() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableWidevineL3ABTest();
    }
    
    public ABTestConfigData getABTestConfiguration_6538() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getABTestConfiguration_6538();
    }
    
    public ABTestConfigData getABTestConfiguration_6725() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getABTestConfiguration_6725();
    }
    
    public DataSaveConfigData getBWSaveConfigData() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getBWSaveConfigData();
    }
    
    public boolean getCastEnabled() {
        return this.mAccountConfigData != null && this.mAccountConfigData.getCastEnabled();
    }
    
    public JSONArray getCastWhitelist() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getCastWhitelistAsJsonArray();
    }
    
    public JSONObject getJPlayerConfig() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getJPlayerThreadConfigAsJson();
    }
    
    public KubrickConfiguration getKubrickConfig() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getKubrickConfig();
    }
    
    public JSONArray getMdxBlacklist() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getMdxBlacklistAsJsonArray();
    }
    
    public String getPreAppPartnerExperience() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getPreAppPartnerExperience();
    }
    
    public String getPreAppWidgetExperience() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getPreAppWidgetExperience();
    }
    
    public int getVideoBufferSize() {
        if (this.mAccountConfigData == null) {
            return 0;
        }
        return this.mAccountConfigData.getVideoBufferSize();
    }
    
    public void persistAccountConfigOverride(final AccountConfigData mAccountConfigData) {
        String jsonString = null;
        if (mAccountConfigData != null) {
            jsonString = mAccountConfigData.toJsonString();
        }
        if (Log.isLoggable()) {
            Log.d(AccountConfiguration.TAG, "Persisting account config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", jsonString);
        this.mAccountConfigData = mAccountConfigData;
    }
    
    public boolean shouldDisableVoip() {
        return this.mAccountConfigData == null || !this.mAccountConfigData.isVoipEnabledOnAccount();
    }
    
    public boolean shouldForceBwSettingsInWifi() {
        return this.mAccountConfigData != null && this.mAccountConfigData.forceBwSettingsInWifi;
    }
    
    public boolean toDisableMcQueenV2() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableMcQueenV2();
    }
    
    public boolean toDisableSuspendPlay() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableSuspendPlay();
    }
}
