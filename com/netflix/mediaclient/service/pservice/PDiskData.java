// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import com.netflix.mediaclient.service.webclient.volley.FalkorParseUtils;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PDiskData
{
    public static final String REPOSITORY_DIR = "preAppData";
    public static final String REPOSITORY_FILE_NAME = "preAppDiskDataFile";
    private static final String TAG = "nf_preapp_diskdata";
    @SerializedName("billboardList")
    public List<PVideo> billboardList;
    @SerializedName("cwList")
    public List<PVideo> cwList;
    @SerializedName("iqList")
    public List<PVideo> iqList;
    @SerializedName("preAppPartnerExperience")
    public String preAppPartnerExperience;
    @SerializedName("preAppWidgetExperience")
    public String preAppWidgetExperience;
    @SerializedName("standardFirstList")
    public List<PVideo> standardFirstList;
    @SerializedName("standardSecondList")
    public List<PVideo> standardSecondList;
    @SerializedName("urlMap")
    public Map<String, String> urlMap;
    
    public PDiskData() {
        this.urlMap = new HashMap<String, String>();
        this.billboardList = new ArrayList<PVideo>();
        this.cwList = new ArrayList<PVideo>();
        this.iqList = new ArrayList<PVideo>();
        this.standardFirstList = new ArrayList<PVideo>();
        this.standardSecondList = new ArrayList<PVideo>();
        this.preAppPartnerExperience = "default";
        this.preAppWidgetExperience = "default";
    }
    
    public PDiskData(final PDiskData pDiskData) {
        this.preAppPartnerExperience = pDiskData.preAppPartnerExperience;
        this.preAppWidgetExperience = pDiskData.preAppWidgetExperience;
        this.urlMap = pDiskData.urlMap;
        this.billboardList = pDiskData.billboardList;
        this.cwList = pDiskData.cwList;
        this.iqList = pDiskData.iqList;
        this.standardFirstList = pDiskData.standardFirstList;
        this.standardSecondList = pDiskData.standardSecondList;
    }
    
    public static PDiskData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.w("nf_preapp_diskdata", "fromJsonString diskData is empty, retuning emtpy object");
            return new PDiskData();
        }
        return FalkorParseUtils.getGson().fromJson(s, PDiskData.class);
    }
    
    public List<PVideo> getListByName(final PDiskData$ListName pDiskData$ListName) {
        switch (PDiskData$1.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListName[pDiskData$ListName.ordinal()]) {
            default: {
                return null;
            }
            case 1: {
                return this.billboardList;
            }
            case 2: {
                return this.cwList;
            }
            case 3: {
                return this.iqList;
            }
            case 4: {
                return this.standardFirstList;
            }
            case 5: {
                return this.standardSecondList;
            }
        }
    }
    
    public String toJsonString() {
        return FalkorParseUtils.getGson().toJson(this);
    }
}
