// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.ui.kids.KidsUtils;
import com.netflix.mediaclient.ui.rating.Ratings;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.model.leafs.PreviewContentConfigData;
import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.service.webclient.model.leafs.DataSaveConfigData;
import com.netflix.mediaclient.util.NetflixPreference;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;

public class AccountConfiguration
{
    private static String TAG;
    private AccountConfigData mAccountConfigData;
    private Context mContext;
    
    static {
        AccountConfiguration.TAG = "nf_configuration_account";
    }
    
    public AccountConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.setAccountConfigData(AccountConfigData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "accountConfig", null)));
    }
    
    public void clear() {
        final NetflixPreference netflixPreference = new NetflixPreference(this.mContext);
        netflixPreference.putStringPref("accountConfig", (String)null);
        netflixPreference.putIntPref("bw_user_control_auto", -1);
        netflixPreference.putIntPref("bw_user_manual_setting", -1);
        netflixPreference.commit();
    }
    
    public boolean enableHTTPSAuth() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableHTTPSAuth();
    }
    
    public boolean enableLowBitrateStreams() {
        return this.mAccountConfigData != null && this.mAccountConfigData.enableLowBitrateStreams();
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
    
    public PreviewContentConfigData getPreviewContentConfigData() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getPreviewContentConfigData();
    }
    
    public String getUserPin() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getUserPin();
    }
    
    public int getVideoBufferSize() {
        if (this.mAccountConfigData == null) {
            return 0;
        }
        return this.mAccountConfigData.getVideoBufferSize();
    }
    
    public void persistAccountConfigOverride(final AccountConfigData accountConfigData) {
        if (accountConfigData == null) {
            Log.e(AccountConfiguration.TAG, "accountConfig obj is null - ignore overwrite");
            return;
        }
        final String jsonString = accountConfigData.toJsonString();
        if (Log.isLoggable()) {
            Log.d(AccountConfiguration.TAG, "Persisting account config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", jsonString);
        this.setAccountConfigData(accountConfigData);
    }
    
    public void setAccountConfigData(final AccountConfigData mAccountConfigData) {
        this.mAccountConfigData = mAccountConfigData;
        if (this.mAccountConfigData != null) {
            Ratings.setAndroidThumbActive(this.mAccountConfigData.isThumbRatingActive());
            KidsUtils.setMyListForKidsDisabled(this.mAccountConfigData.isMyListForKidsDisabled());
        }
    }
    
    public boolean shouldDisableVoip() {
        return this.mAccountConfigData == null || !this.mAccountConfigData.isVoipEnabledOnAccount();
    }
    
    public boolean toDisableMcQueenV2() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableMcQueenV2();
    }
    
    public boolean toDisableSuspendPlay() {
        return this.mAccountConfigData != null && this.mAccountConfigData.toDisableSuspendPlay();
    }
}
