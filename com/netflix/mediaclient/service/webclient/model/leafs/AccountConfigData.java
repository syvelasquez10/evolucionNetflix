// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.configuration.KidsOnPhoneConfiguration;
import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import org.json.JSONArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class AccountConfigData
{
    private static final String FIELD_KIDS_ON_PHONE = "kidsOnPhoneConfig";
    @Expose
    private static final String TAG = "nf_config";
    @SerializedName("castWhitelistTargets")
    private final String castWhitelist;
    @SerializedName("enableCast")
    private boolean enableCast;
    @SerializedName("JPlayerConfig")
    private String jPlayerConfig;
    @SerializedName("kidsOnPhoneConfig")
    private KidsOnPhoneConfigData kidsOnPhoneConfig;
    @Expose
    private JSONArray mCastWhitelistJSONArray;
    @Expose
    private JSONObject mJPlayerConfigJSON;
    @Expose
    private JSONArray mMdxBlacklistTargetsJSONArray;
    @SerializedName("mdxBlacklistTargets")
    private final String mdxBlacklistTargets;
    @SerializedName("videoBufferSize")
    private final int videoBufferSize;
    
    public AccountConfigData() {
        this.videoBufferSize = 0;
        this.castWhitelist = null;
        this.mdxBlacklistTargets = null;
        this.mCastWhitelistJSONArray = null;
        this.mMdxBlacklistTargetsJSONArray = null;
        this.kidsOnPhoneConfig = KidsOnPhoneConfigData.DEFAULT_KIDS_CONFIG;
        this.mJPlayerConfigJSON = null;
    }
    
    public static AccountConfigData fromString(String string) {
        if (StringUtils.isEmpty(string)) {
            return null;
        }
        AccountConfigData accountConfigData;
        while (true) {
            accountConfigData = FalcorParseUtils.getGson().fromJson(string, AccountConfigData.class);
            accountConfigData.mCastWhitelistJSONArray = null;
            accountConfigData.mMdxBlacklistTargetsJSONArray = null;
            accountConfigData.mJPlayerConfigJSON = null;
            while (true) {
                try {
                    if (StringUtils.isEmpty(string)) {
                        final JSONObject jsonObject = new JSONObject();
                        string = JsonUtils.getString(jsonObject, "kidsOnPhoneConfig", null);
                        if (string != null) {
                            accountConfigData.kidsOnPhoneConfig = FalcorParseUtils.getGson().fromJson(string, KidsOnPhoneConfigData.class);
                            return accountConfigData;
                        }
                        break;
                    }
                }
                catch (JSONException ex) {
                    Log.handleException("nf_config", (Exception)ex);
                    return accountConfigData;
                }
                final JSONObject jsonObject = new JSONObject(string);
                continue;
            }
        }
        accountConfigData.kidsOnPhoneConfig = KidsOnPhoneConfigData.DEFAULT_KIDS_CONFIG;
        return accountConfigData;
    }
    
    public String getCastBlacklist() {
        return this.castWhitelist;
    }
    
    public boolean getCastEnabled() {
        return this.enableCast;
    }
    
    public JSONArray getCastWhitelistAsJsonArray() {
        Label_0038: {
            if (this.mCastWhitelistJSONArray != null) {
                break Label_0038;
            }
            JSONArray mCastWhitelistJSONArray = null;
            while (true) {
                if (!StringUtils.isNotEmpty(this.castWhitelist)) {
                    break Label_0033;
                }
                try {
                    mCastWhitelistJSONArray = new JSONArray(this.castWhitelist);
                    this.mCastWhitelistJSONArray = mCastWhitelistJSONArray;
                    return this.mCastWhitelistJSONArray;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("castWhitelist bad json: %s", this.castWhitelist));
                    mCastWhitelistJSONArray = mCastWhitelistJSONArray;
                    continue;
                }
                break;
            }
        }
    }
    
    public JSONObject getJPlayerThreadConfigAsJson() {
        Label_0038: {
            if (this.mJPlayerConfigJSON != null) {
                break Label_0038;
            }
            JSONObject mjPlayerConfigJSON = null;
            while (true) {
                if (!StringUtils.isNotEmpty(this.jPlayerConfig)) {
                    break Label_0033;
                }
                try {
                    mjPlayerConfigJSON = new JSONObject(this.jPlayerConfig);
                    this.mJPlayerConfigJSON = mjPlayerConfigJSON;
                    return this.mJPlayerConfigJSON;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("jPlayerThreadConfig bad json: %s", this.jPlayerConfig));
                    mjPlayerConfigJSON = mjPlayerConfigJSON;
                    continue;
                }
                break;
            }
        }
    }
    
    public KidsOnPhoneConfiguration getKidsOnPhone() {
        return this.kidsOnPhoneConfig;
    }
    
    public String getMdxBlacklist() {
        return this.mdxBlacklistTargets;
    }
    
    public JSONArray getMdxBlacklistAsJsonArray() {
        Label_0038: {
            if (this.mMdxBlacklistTargetsJSONArray != null) {
                break Label_0038;
            }
            JSONArray mMdxBlacklistTargetsJSONArray = null;
            while (true) {
                if (!StringUtils.isNotEmpty(this.mdxBlacklistTargets)) {
                    break Label_0033;
                }
                try {
                    mMdxBlacklistTargetsJSONArray = new JSONArray(this.mdxBlacklistTargets);
                    this.mMdxBlacklistTargetsJSONArray = mMdxBlacklistTargetsJSONArray;
                    return this.mMdxBlacklistTargetsJSONArray;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("mdxBlacklistTargets bad json: %s", this.mdxBlacklistTargets));
                    mMdxBlacklistTargetsJSONArray = mMdxBlacklistTargetsJSONArray;
                    continue;
                }
                break;
            }
        }
    }
    
    public int getVideoBufferSize() {
        return 0;
    }
    
    @Override
    public String toString() {
        final String json = FalcorParseUtils.getGson().toJson(this);
        Log.d("nf_config", "AccountConfig=" + json);
        return json;
    }
}
