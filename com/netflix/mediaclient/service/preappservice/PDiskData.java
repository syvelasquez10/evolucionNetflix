// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.preappservice;

import com.netflix.mediaclient.service.webclient.volley.FalcorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.util.Map;
import com.netflix.mediaclient.service.webclient.model.branches.Video;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PDiskData
{
    public static final String REPOSITORY_DIR = "preAppData";
    public static final String REPOSITORY_FILE_NAME = "preAppDiskDataFile";
    private static final String TAG = "nf_preapp_diskdata";
    @SerializedName("billboardList")
    List<PVideo> billboardObjs;
    @SerializedName("continueWatchingList")
    List<PVideo> cwObjs;
    @SerializedName("iqList")
    List<Video.Summary> iqObjs;
    @SerializedName("recoList")
    List<Video.Summary> recoObjs;
    @SerializedName("urlMap")
    public Map<String, String> urlMap;
    
    public static PDiskData createEmptyObject() {
        return new PDiskData();
    }
    
    public static PDiskData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.w("nf_preapp_diskdata", "fromJsonString diskData is empty, retuning emtpy object");
            return new PDiskData();
        }
        return FalcorParseUtils.getGson().fromJson(s, PDiskData.class);
    }
    
    public String toJsonString() {
        return FalcorParseUtils.getGson().toJson(this);
    }
}
