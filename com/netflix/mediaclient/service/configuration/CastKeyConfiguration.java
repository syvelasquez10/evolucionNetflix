// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.configuration;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.PreferenceUtils;
import android.content.Context;
import com.netflix.mediaclient.service.webclient.model.leafs.CastKeyData;

public class CastKeyConfiguration
{
    private static String TAG;
    CastKeyData mCastKeyData;
    Context mContext;
    
    static {
        CastKeyConfiguration.TAG = "nf_cast";
    }
    
    public CastKeyConfiguration(final Context mContext) {
        this.mContext = mContext;
        this.mCastKeyData = CastKeyData.fromJsonString(PreferenceUtils.getStringPref(this.mContext, "castKeyData", null));
    }
    
    public void clear() {
        PreferenceUtils.putStringPref(this.mContext, "castKeyData", null);
    }
    
    public String getCastKey() {
        if (this.mCastKeyData != null) {
            return this.mCastKeyData.key;
        }
        return null;
    }
    
    public String getCastKeyId() {
        if (this.mCastKeyData != null) {
            return this.mCastKeyData.keyId;
        }
        return null;
    }
    
    public void persistCastConfigOverride(final CastKeyData mCastKeyData) {
        String jsonString;
        if (mCastKeyData == null) {
            jsonString = "";
        }
        else {
            jsonString = mCastKeyData.toJsonString();
        }
        if (Log.isLoggable()) {
            Log.d(CastKeyConfiguration.TAG, "Persisting castKeyData to config: " + jsonString);
        }
        PreferenceUtils.putStringPref(this.mContext, "castKeyData", jsonString);
        this.mCastKeyData = mCastKeyData;
    }
}
