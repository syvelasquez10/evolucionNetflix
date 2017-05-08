// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import android.content.Context;
import android.app.Activity;
import java.util.ArrayList;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class StorageStateUtils$DeviceExternalStorageInfo
{
    @SerializedName("storageInfoList")
    public final List<StorageStateUtils$ExternalStorageInfo> mExternalStorageInfoList;
    @SerializedName("storageCount")
    public final int mNumberOfExternalStorage;
    
    public StorageStateUtils$DeviceExternalStorageInfo(final int mNumberOfExternalStorage) {
        this.mExternalStorageInfoList = new ArrayList<StorageStateUtils$ExternalStorageInfo>();
        this.mNumberOfExternalStorage = mNumberOfExternalStorage;
    }
    
    public boolean isPermissionProblem(final Activity activity) {
        return this.mExternalStorageInfoList != null && this.mExternalStorageInfoList.size() > 0 && !this.mExternalStorageInfoList.get(0).mIsWritable && AndroidUtils.isPermissionNotGranted((Context)activity, "android.permission.WRITE_EXTERNAL_STORAGE");
    }
}
