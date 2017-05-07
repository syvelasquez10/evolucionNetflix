// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import org.json.JSONObject;
import org.json.JSONArray;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.AccountConfigData;

public class AccountConfiguration
{
    AccountConfigData mAccountConfigData;
    Context mContext;
    
    public AccountConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mAccountConfigData = AccountConfigData.fromString(PreferenceUtils.getStringPref(mContext, "accountConfig", null));
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "streamingqoe", null);
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", null);
    }
    
    public JSONArray getCastBlacklist() {
        if (this.mAccountConfigData == null) {
            return null;
        }
        return this.mAccountConfigData.getCastBlacklistAsJsonArray();
    }
    
    public boolean getCastEnabled() {
        return this.mAccountConfigData != null && this.mAccountConfigData.getCastEnabled();
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
    
    public String getStreamingQoe() {
        return PreferenceUtils.getStringPref(this.mContext, "streamingqoe", null);
    }
    
    public int getVideoBufferSize() {
        if (this.mAccountConfigData == null) {
            return 0;
        }
        return this.mAccountConfigData.getVideoBufferSize();
    }
    
    public void persistAccountConfigOverride(final AccountConfigData mAccountConfigData) {
        String string = null;
        if (mAccountConfigData != null) {
            string = mAccountConfigData.toString();
        }
        PreferenceUtils.putStringPref(this.mContext, "accountConfig", string);
        this.mAccountConfigData = mAccountConfigData;
    }
    
    public void persistStreamingOverride(final String s) {
        PreferenceUtils.putStringPref(this.mContext, "streamingqoe", s);
    }
}
