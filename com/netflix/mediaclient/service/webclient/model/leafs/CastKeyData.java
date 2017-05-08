// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class CastKeyData
{
    private static final String TAG = "nf_cast";
    @SerializedName("key")
    public String key;
    @SerializedName("keyId")
    public String keyId;
    
    public static CastKeyData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_cast", "Parsing CastKeyData from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, CastKeyData.class);
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_cast", "castKeyData as json: " + json);
        }
        return json;
    }
}
