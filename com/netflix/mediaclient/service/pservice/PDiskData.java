// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.pservice;

import java.util.Iterator;
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
    public static final Boolean ENABLE_VERBOSE_LOGGING;
    public static final String REPOSITORY_DIR = "preAppData";
    public static final String REPOSITORY_FILE_NAME = "preAppDiskDataFile";
    private static final String TAG = "nf_preapp_diskdata";
    @SerializedName("billboardList")
    public List<PVideo> billboardList;
    @SerializedName("cwList")
    public List<PVideo> cwList;
    @SerializedName("iqList")
    public List<PVideo> iqList;
    @SerializedName("listInfo")
    public Map<String, String> lomoMap;
    @SerializedName("nonMemberList")
    public List<PVideo> nonMemberList;
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
    
    static {
        ENABLE_VERBOSE_LOGGING = false;
    }
    
    public PDiskData() {
        this.urlMap = new HashMap<String, String>();
        this.lomoMap = new HashMap<String, String>();
        this.billboardList = new ArrayList<PVideo>();
        this.cwList = new ArrayList<PVideo>();
        this.iqList = new ArrayList<PVideo>();
        this.standardFirstList = new ArrayList<PVideo>();
        this.standardSecondList = new ArrayList<PVideo>();
        this.nonMemberList = new ArrayList<PVideo>();
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
        this.nonMemberList = pDiskData.nonMemberList;
        this.standardFirstList = pDiskData.standardFirstList;
        this.standardSecondList = pDiskData.standardSecondList;
        this.lomoMap = pDiskData.lomoMap;
    }
    
    public static PDiskData fromJsonString(final String s) {
        if (StringUtils.isEmpty(s)) {
            Log.w("nf_preapp_diskdata", "fromJsonString diskData is empty, retuning empty object");
            return new PDiskData();
        }
        return FalkorParseUtils.getGson().fromJson(s, PDiskData.class);
    }
    
    public static boolean isListEmpty(final List<PVideo> list) {
        return list == null || list.size() <= 0;
    }
    
    public static boolean isListNotEmpty(final List<PVideo> list) {
        return !isListEmpty(list);
    }
    
    public static boolean isMemberDataAvailable(final PDiskData pDiskData) {
        return pDiskData != null && (isListNotEmpty(pDiskData.billboardList) || isListNotEmpty(pDiskData.cwList) || isListNotEmpty(pDiskData.iqList) || isListNotEmpty(pDiskData.standardFirstList) || isListNotEmpty(pDiskData.standardSecondList));
    }
    
    public static boolean isNonMemberDataAvailable(final PDiskData pDiskData) {
        return isListNotEmpty(pDiskData.nonMemberList);
    }
    
    private String printList(final List<PVideo> list) {
        if (list == null) {
            return null;
        }
        final StringBuilder sb = new StringBuilder(list.size());
        final Iterator<PVideo> iterator = list.iterator();
        while (iterator.hasNext()) {
            sb.append(", " + iterator.next().id);
        }
        return sb.toString();
    }
    
    public void clearMemberlists() {
        this.lomoMap.clear();
        this.billboardList = null;
        this.cwList = null;
        this.iqList = null;
        this.standardFirstList = null;
        this.standardSecondList = null;
    }
    
    public List<PVideo> getVideoListByName(final PDiskData$ListType pDiskData$ListType) {
        switch (PDiskData$1.$SwitchMap$com$netflix$mediaclient$service$pservice$PDiskData$ListType[pDiskData$ListType.ordinal()]) {
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
            case 6: {
                return this.nonMemberList;
            }
        }
    }
    
    public void print() {
        Log.d("nf_preapp_diskdata", String.format("lomo: %s", this.lomoMap));
        Log.d("nf_preapp_diskdata", String.format("nm: %s", this.printList(this.nonMemberList)));
        Log.d("nf_preapp_diskdata", String.format("bb: %s", this.printList(this.billboardList)));
        Log.d("nf_preapp_diskdata", String.format("cw: %s", this.printList(this.cwList)));
        Log.d("nf_preapp_diskdata", String.format("iq: %s", this.printList(this.iqList)));
        Log.d("nf_preapp_diskdata", String.format("s1: %s", this.printList(this.standardFirstList)));
        Log.d("nf_preapp_diskdata", String.format("s2: %s", this.printList(this.standardSecondList)));
        int size;
        if (this.urlMap != null) {
            size = this.urlMap.size();
        }
        else {
            size = 0;
        }
        Log.d("nf_preapp_diskdata", String.format("urlMap count %d", size));
        if (PDiskData.ENABLE_VERBOSE_LOGGING) {
            Log.d("nf_preapp_diskdata", String.format("urlMap:%s", this.urlMap));
        }
    }
    
    public String toJsonString() {
        return FalkorParseUtils.getGson().toJson(this);
    }
}
