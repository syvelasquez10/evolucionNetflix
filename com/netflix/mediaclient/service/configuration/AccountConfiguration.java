// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import org.json.JSONObject;
import org.json.JSONArray;
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
    }
    
    public boolean enableHTTPSAuth() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableHTTPSAuth();
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
    
    public KidsOnPhoneConfiguration getKidsOnPhoneConfiguration() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getKidsOnPhone();
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
    
    public int getSearchTest() {
        if (this.mAccountConfigData == null) {
            return 1;
        }
        return this.mAccountConfigData.getSearchTest();
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
        if (Log.isLoggable(AccountConfiguration.TAG, 3)) {
            Log.d(AccountConfiguration.TAG, "Persisting account config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", jsonString);
        this.mAccountConfigData = mAccountConfigData;
    }
    
    public boolean shouldUseLegacyBrowseVolleyClient() {
        if (this.mAccountConfigData == null) {
            Log.w(AccountConfiguration.TAG, "shouldUseLegacyBrowseVolleyClient - account config data is null");
            return false;
        }
        return this.mAccountConfigData.shouldUseLegacyBrowseVolleyClient();
    }
    
    public boolean toDisableMcQueenV2() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableMcQueenV2();
    }
    
    public boolean toDisableSuspendPlay() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableSuspendPlay();
    }
}
