// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class NrmConfigData
{
    private static final String TAG = "nf_config_nrm";
    @SerializedName("isDeviceBound")
    public boolean isDeviceBound;
    @SerializedName("isUserBound")
    public boolean isUserBound;
    @SerializedName("netflixId")
    public String netflixId;
    @SerializedName("secureNetflixId")
    public String secureNetflixId;
    
    public static NrmConfigData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_config_nrm", "Parsing nrmInfo from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, NrmConfigData.class);
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_config_nrm", "NrmConfigData as json: " + json);
        }
        return json;
    }
}
