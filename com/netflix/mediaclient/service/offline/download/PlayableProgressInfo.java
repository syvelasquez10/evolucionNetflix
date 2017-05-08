// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import java.util.Iterator;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.util.FileUtils;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PlayableProgressInfo
{
    private static final String TAG = "nf_playableProgress";
    private long mActualSizeOfPlayableOnDiskInCompleteState;
    private long mBytesDownloadedSoFar;
    public final Map<String, DownloadableProgressInfo> mDownloadableProgressInfoMap;
    private int mPercentageDownloaded;
    private long mTotalBytesToDownload;
    
    public PlayableProgressInfo() {
        this.mPercentageDownloaded = 0;
        this.mActualSizeOfPlayableOnDiskInCompleteState = -1L;
        this.mDownloadableProgressInfoMap = new HashMap<String, DownloadableProgressInfo>();
    }
    
    public long getActualSizeOfPlayableOnDiskInCompleteState(final File file) {
        if (this.mActualSizeOfPlayableOnDiskInCompleteState == -1L) {
            this.mActualSizeOfPlayableOnDiskInCompleteState = FileUtils.getDirectorySizeInBytes(file);
        }
        return this.mActualSizeOfPlayableOnDiskInCompleteState;
    }
    
    public long getBytesDownloadedSoFar() {
        return this.mBytesDownloadedSoFar;
    }
    
    public int getPercentageDownloaded() {
        return this.mPercentageDownloaded;
    }
    
    public long getTotalBytesToDownload() {
        return this.mTotalBytesToDownload;
    }
    
    public void markComplete() {
        this.mPercentageDownloaded = 100;
    }
    
    public void updateProgressPercentage() {
        this.mBytesDownloadedSoFar = 0L;
        this.mTotalBytesToDownload = 0L;
        for (final Map.Entry<String, DownloadableProgressInfo> entry : this.mDownloadableProgressInfoMap.entrySet()) {
            final DownloadableProgressInfo downloadableProgressInfo = entry.getValue();
            if (downloadableProgressInfo.mTotalBytesToDownload > 0L) {
                this.mBytesDownloadedSoFar += downloadableProgressInfo.mBytesOnTheDisk;
                this.mTotalBytesToDownload += downloadableProgressInfo.mTotalBytesToDownload;
            }
            else {
                Log.e("nf_playableProgress", "mTotalBytesToDownload " + downloadableProgressInfo.mTotalBytesToDownload + " is not positive for " + entry.getKey());
            }
        }
        if (this.mTotalBytesToDownload > 0L) {
            this.mPercentageDownloaded = (int)(this.mBytesDownloadedSoFar * 100L / this.mTotalBytesToDownload);
            return;
        }
        this.mPercentageDownloaded = 0;
    }
}
