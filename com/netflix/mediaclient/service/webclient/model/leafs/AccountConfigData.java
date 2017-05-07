// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.JsonUtils;
import org.json.JSONObject;
import com.netflix.mediaclient.util.StringUtils;
import org.json.JSONArray;

public class AccountConfigData
{
    private static final String FIELD_MDX_BLACKLIST_TARGETS = "mdxBlacklistTargets";
    private static final String FIELD_VIDEO_BUFFER_SIZE = "videoBufferSize";
    private static final String TAG = "nf_config";
    private JSONArray mMdxBlacklistTargetsJSONArray;
    private String mdxBlacklistTargets;
    private int videoBufferSize;
    
    public AccountConfigData() {
        this.videoBufferSize = 0;
        this.mdxBlacklistTargets = null;
        this.mMdxBlacklistTargetsJSONArray = null;
    }
    
    public static AccountConfigData fromString(final String s) {
        final AccountConfigData accountConfigData = new AccountConfigData();
        if (s == null) {
            return new AccountConfigData();
        }
        while (true) {
            while (true) {
                try {
                    if (StringUtils.isEmpty(s)) {
                        final JSONObject jsonObject = new JSONObject();
                        accountConfigData.mdxBlacklistTargets = JsonUtils.getString(jsonObject, "mdxBlacklistTargets", null);
                        accountConfigData.videoBufferSize = JsonUtils.getInt(jsonObject, "videoBufferSize", 0);
                        return accountConfigData;
                    }
                }
                catch (JSONException ex) {
                    Log.d("nf_config", String.format("failed to create json string= %s, exception %s", s, ex));
                    return accountConfigData;
                }
                final JSONObject jsonObject = new JSONObject(s);
                continue;
            }
        }
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
        final JSONObject jsonObject = new JSONObject();
        while (true) {
            try {
                jsonObject.put("mdxBlacklistTargets", (Object)this.getMdxBlacklist());
                jsonObject.put("videoBufferSize", this.getVideoBufferSize());
                Log.d("nf_config", "AccountConfig=" + jsonObject.toString());
                return jsonObject.toString();
            }
            catch (JSONException ex) {
                Log.d("nf_config", "failed in json string " + ex);
                continue;
            }
            break;
        }
    }
}
