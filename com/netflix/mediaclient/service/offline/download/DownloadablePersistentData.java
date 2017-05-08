// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.google.gson.annotations.SerializedName;

public class DownloadablePersistentData
{
    @SerializedName("downloadableId")
    public final String mDownloadableId;
    @SerializedName("isComplete")
    public boolean mIsComplete;
    @SerializedName("size")
    public long mSizeOfDownloadable;
    
    public DownloadablePersistentData(final DownloadableInfo downloadableInfo) {
        this.mDownloadableId = downloadableInfo.getDownloadableId();
        this.mIsComplete = false;
        this.mSizeOfDownloadable = downloadableInfo.getSizeOfDownloadable();
    }
}
