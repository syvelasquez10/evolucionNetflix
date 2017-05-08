// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.util.List;

class DeviceExternalStorageInfo
{
    @SerializedName("storageInfoList")
    public final List<ExternalStorageInfo> mExternalStorageInfoList;
    @SerializedName("storageCount")
    public final int mNumberOfExternalStorage;
    
    public DeviceExternalStorageInfo(final int mNumberOfExternalStorage) {
        this.mExternalStorageInfoList = new ArrayList<ExternalStorageInfo>();
        this.mNumberOfExternalStorage = mNumberOfExternalStorage;
    }
}
