// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import java.util.HashMap;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

class MetaRegistry
{
    @SerializedName("checksumMap")
    private final Map<Integer, String> mCheckSumMap;
    transient RegistryData mCurrentRegistryData;
    @SerializedName("isPaused")
    public boolean mDownloadsPausedByUser;
    @SerializedName("geoCountryCode")
    public String mGeoCountryCode;
    @SerializedName("writeCounter")
    public int mMetaRegistryWriteCounter;
    @SerializedName("primaryProfileGuid")
    public String mPrimaryProfileGuid;
    @SerializedName("activeRegId")
    public int mUserSelectedRegId;
    @SerializedName("version")
    private final int mVersion;
    
    public MetaRegistry(final int mVersion) {
        this.mCheckSumMap = new HashMap<Integer, String>();
        this.mDownloadsPausedByUser = false;
        this.mVersion = mVersion;
    }
    
    public String getCheckSumFor(final int n) {
        return this.mCheckSumMap.get(n);
    }
    
    boolean isCurrentlySelected(final RegistryData registryData) {
        return this.mCurrentRegistryData != null && registryData.mRegId == this.mCurrentRegistryData.mRegId;
    }
    
    void updateCheckSum(final int n, final String s) {
        this.mCheckSumMap.put(n, s);
    }
}
