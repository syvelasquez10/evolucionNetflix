// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.registry;

import com.netflix.mediaclient.util.FileUtils;
import com.netflix.mediaclient.Log;
import android.os.Environment;
import android.os.Build$VERSION;
import android.os.StatFs;
import android.os.storage.StorageManager;
import android.content.Context;
import java.io.File;

class OfflineStorageVolumeInfo
{
    private static final String TAG = "offlineStorageVolInfo";
    private final File mDownloadDir;
    private long mFreeSpace;
    private boolean mIsEmulated;
    private boolean mIsRemovable;
    private long mNetflixUsedSpace;
    private long mTotalSpace;
    
    public OfflineStorageVolumeInfo(final Context context, final StorageManager storageManager, final File mDownloadDir, final StatFs statFs, final boolean b) {
        this.mDownloadDir = mDownloadDir;
        this.updateSpaceInfo(statFs);
        this.mIsRemovable = !b;
        if (Build$VERSION.SDK_INT >= 21) {
            this.mIsRemovable = Environment.isExternalStorageRemovable(mDownloadDir);
            this.mIsEmulated = Environment.isExternalStorageEmulated(mDownloadDir);
        }
        this.dump();
    }
    
    private void dump() {
        Log.i("offlineStorageVolInfo", "\nmDownloadDirPath=%s mTotalSpace=%d mFreeSpace=%d mNetflixUsedSpace=%d mIsRemovable=%b mIsEmulated=%b", this.mDownloadDir.getAbsolutePath(), this.mTotalSpace, this.mFreeSpace, this.mNetflixUsedSpace, this.mIsRemovable, this.mIsEmulated);
    }
    
    public File getDownloadDir() {
        return this.mDownloadDir;
    }
    
    public long getFreeSpace() {
        return this.mFreeSpace;
    }
    
    public long getNetflixUsedSpace() {
        return this.mNetflixUsedSpace;
    }
    
    public long getTotalSpace() {
        return this.mTotalSpace;
    }
    
    public boolean isRemovable() {
        return this.mIsRemovable;
    }
    
    void updateSpaceInfo(final StatFs statFs) {
        this.mTotalSpace = statFs.getTotalBytes();
        this.mFreeSpace = statFs.getFreeBytes();
        this.mNetflixUsedSpace = FileUtils.getDirectorySizeInBytes(this.mDownloadDir.getParentFile());
    }
}
