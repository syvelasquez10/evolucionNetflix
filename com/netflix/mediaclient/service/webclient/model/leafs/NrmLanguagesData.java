// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.webclient.model.leafs;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import com.google.gson.annotations.SerializedName;

public class NrmLanguagesData
{
    private static final String TAG = "nf_languages_nrm";
    @SerializedName("default")
    public String defaultLanguage;
    @SerializedName("localizedNames")
    public String[] localizedNames;
    @SerializedName("tags")
    public String[] tags;
    
    public static NrmLanguagesData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            return null;
        }
        if (Log.isLoggable()) {
            Log.v("nf_languages_nrm", "Parsing nrmInfo from json: " + s);
        }
        return FalkorParseUtils.getGson().fromJson(s, NrmLanguagesData.class);
    }
    
    public String toJsonString() {
        final String json = FalkorParseUtils.getGson().toJson(this);
        if (Log.isLoggable()) {
            Log.d("nf_languages_nrm", "NrmConfigData as json: " + json);
        }
        return json;
    }
}
