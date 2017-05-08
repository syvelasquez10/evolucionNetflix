// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import org.json.JSONException;
import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONObject;
import com.google.gson.annotations.Expose;
import org.json.JSONArray;
import com.google.gson.annotations.SerializedName;

public class AccountConfigData
{
    private static final KubrickConfigData DEFAULT_KUBRICK_CONFIG;
    private static final String TAG = "nf_config";
    @SerializedName("castWhitelistTargets")
    private final String castWhitelist;
    @SerializedName("dataSaveConfig")
    private DataSaveConfigData dataSaveConfig;
    @SerializedName("disableMcQueenV2")
    private boolean disableMcQueenV2;
    @SerializedName("disableSuspendPlay")
    private boolean disableSuspendPlay;
    @SerializedName("enableCast")
    private boolean enableCast;
    @SerializedName("enableHTTPSAuth")
    private boolean enableHTTPSAuth;
    @SerializedName("enableLowBitrateStreams")
    private boolean enableLowBitrateStreams;
    @SerializedName("falkorCacheDisabled")
    private boolean falkorCacheDisabled;
    @SerializedName("JPlayerConfig")
    private String jPlayerConfig;
    @Expose
    private JSONArray mCastWhitelistJSONArray;
    @Expose
    private JSONObject mJPlayerConfigJSON;
    @Expose
    private JSONArray mMdxBlacklistTargetsJSONArray;
    @SerializedName("mdxBlacklistTargets")
    private final String mdxBlacklistTargets;
    @SerializedName("myListForKidsDisabled")
    private boolean myListForKidsDisabled;
    @SerializedName("preAppPartnerExperience")
    private String preAppPartnerExperience;
    @SerializedName("preAppWidgetExperience")
    private String preAppWidgetExperience;
    @SerializedName("previewContent")
    public PreviewContentConfigData previewContent;
    @SerializedName("thumbRatingActive")
    private boolean thumbRatingActive;
    @SerializedName("userPin")
    private String userPin;
    @SerializedName("videoBufferSize")
    private final int videoBufferSize;
    @SerializedName("voipEnabledOnAccount")
    private boolean voipEnabledOnAccount;
    
    static {
        DEFAULT_KUBRICK_CONFIG = new KubrickConfigData();
    }
    
    public AccountConfigData() {
        this.videoBufferSize = 0;
        this.enableLowBitrateStreams = true;
        this.castWhitelist = null;
        this.mdxBlacklistTargets = null;
        this.mCastWhitelistJSONArray = null;
        this.mMdxBlacklistTargetsJSONArray = null;
        this.mJPlayerConfigJSON = null;
        this.previewContent = PreviewContentConfigData.getDefault();
    }
    
    public static AccountConfigData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_config", "Parsing AccountConfig from json: " + s);
        }
        final AccountConfigData accountConfigData = FalkorParseUtils.getGson().fromJson(s, AccountConfigData.class);
        accountConfigData.mCastWhitelistJSONArray = null;
        accountConfigData.mMdxBlacklistTargetsJSONArray = null;
        accountConfigData.mJPlayerConfigJSON = null;
        return accountConfigData;
    }
    
    public boolean enableHTTPSAuth() {
        return this.enableHTTPSAuth;
    }
    
    public boolean enableLowBitrateStreams() {
        return this.enableLowBitrateStreams;
    }
    
    public DataSaveConfigData getBWSaveConfigData() {
        return this.dataSaveConfig;
    }
    
    public String getCastBlacklist() {
        return this.castWhitelist;
    }
    
    public boolean getCastEnabled() {
        return this.enableCast;
    }
    
    public JSONArray getCastWhitelistAsJsonArray() {
        while (true) {
            Label_0034: {
                if (this.mCastWhitelistJSONArray != null) {
                    break Label_0034;
                }
                if (!StringUtils.isNotEmpty(this.castWhitelist)) {
                    break Label_0034;
                }
                try {
                    final JSONArray mCastWhitelistJSONArray = new JSONArray(this.castWhitelist);
                    this.mCastWhitelistJSONArray = mCastWhitelistJSONArray;
                    return this.mCastWhitelistJSONArray;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("castWhitelist bad json: %s", this.castWhitelist));
                }
            }
            final JSONArray mCastWhitelistJSONArray = null;
            continue;
        }
    }
    
    public JSONObject getJPlayerThreadConfigAsJson() {
        while (true) {
            Label_0034: {
                if (this.mJPlayerConfigJSON != null) {
                    break Label_0034;
                }
                if (!StringUtils.isNotEmpty(this.jPlayerConfig)) {
                    break Label_0034;
                }
                try {
                    final JSONObject mjPlayerConfigJSON = new JSONObject(this.jPlayerConfig);
                    this.mJPlayerConfigJSON = mjPlayerConfigJSON;
                    return this.mJPlayerConfigJSON;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("jPlayerThreadConfig bad json: %s", this.jPlayerConfig));
                }
            }
            final JSONObject mjPlayerConfigJSON = null;
            continue;
        }
    }
    
    public String getMdxBlacklist() {
        return this.mdxBlacklistTargets;
    }
    
    public JSONArray getMdxBlacklistAsJsonArray() {
        while (true) {
            Label_0034: {
                if (this.mMdxBlacklistTargetsJSONArray != null) {
                    break Label_0034;
                }
                if (!StringUtils.isNotEmpty(this.mdxBlacklistTargets)) {
                    break Label_0034;
                }
                try {
                    final JSONArray mMdxBlacklistTargetsJSONArray = new JSONArray(this.mdxBlacklistTargets);
                    this.mMdxBlacklistTargetsJSONArray = mMdxBlacklistTargetsJSONArray;
                    return this.mMdxBlacklistTargetsJSONArray;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("mdxBlacklistTargets bad json: %s", this.mdxBlacklistTargets));
                }
            }
            final JSONArray mMdxBlacklistTargetsJSONArray = null;
            continue;
        }
    }
    
    public String getPreAppPartnerExperience() {
        return this.preAppPartnerExperience;
    }
    
    public String getPreAppWidgetExperience() {
        return this.preAppWidgetExperience;
    }
    
    public PreviewContentConfigData getPreviewContentConfigData() {
        return this.previewContent;
    }
    
    public String getUserPin() {
        return this.userPin;
    }
    
    public int getVideoBufferSize() {
        return 0;
    }
    
    public boolean isFalkorCacheDisabled() {
        return this.falkorCacheDisabled;
    }
    
    public boolean isMyListForKidsDisabled() {
        return this.myListForKidsDisabled;
    }
    
    public boolean isThumbRatingActive() {
        return this.thumbRatingActive;
    }
    
    public boolean isVoipEnabledOnAccount() {
        return this.voipEnabledOnAccount;
    }
    
    public boolean toDisableMcQueenV2() {
        return this.disableMcQueenV2;
    }
    
    public boolean toDisableSuspendPlay() {
        return this.disableSuspendPlay;
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config", "AccountConfig as json: " + json);
        }
        return json;
    }
}
