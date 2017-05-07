// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;
import com.google.gson.annotations.SerializedName;
import com.google.gson.annotations.Expose;

public class AccountConfigData
{
    @Expose
    private static final String TAG = "nf_config";
    @SerializedName("castBlacklist")
    private String castBlacklist;
    @SerializedName("enableCast")
    private boolean enableCast;
    @Expose
    private JSONArray mCastBlacklistJSONArray;
    @Expose
    private JSONArray mMdxBlacklistTargetsJSONArray;
    @SerializedName("mdxBlacklistTargets")
    private String mdxBlacklistTargets;
    @SerializedName("videoBufferSize")
    private int videoBufferSize;
    
    public AccountConfigData() {
        this.videoBufferSize = 0;
        this.castBlacklist = null;
        this.mdxBlacklistTargets = null;
        this.mCastBlacklistJSONArray = null;
        this.mMdxBlacklistTargetsJSONArray = null;
    }
    
    public static AccountConfigData fromString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        final AccountConfigData accountConfigData = FalcorParseUtils.getGson().fromJson(s, AccountConfigData.class);
        accountConfigData.mCastBlacklistJSONArray = null;
        accountConfigData.mMdxBlacklistTargetsJSONArray = null;
        return accountConfigData;
    }
    
    public String getCastBlacklist() {
        return this.castBlacklist;
    }
    
    public JSONArray getCastBlacklistAsJsonArray() {
        Label_0038: {
            if (this.mCastBlacklistJSONArray != null) {
                break Label_0038;
            }
            JSONArray mCastBlacklistJSONArray = null;
            while (true) {
                if (!StringUtils.isNotEmpty(this.castBlacklist)) {
                    break Label_0033;
                }
                try {
                    mCastBlacklistJSONArray = new JSONArray(this.castBlacklist);
                    this.mCastBlacklistJSONArray = mCastBlacklistJSONArray;
                    return this.mCastBlacklistJSONArray;
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("castBlacklist bad json: %s", this.castBlacklist));
                    mCastBlacklistJSONArray = mCastBlacklistJSONArray;
                    continue;
                }
                break;
            }
        }
    }
    
    public boolean getCastEnabled() {
        return this.enableCast;
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
        return this.videoBufferSize;
    }
    
    @Override
    public String toString() {
        final String json = FalcorParseUtils.getGson().toJson(this);
        Log.d("nf_config", "AccountConfig=" + json);
        return json;
    }
}
