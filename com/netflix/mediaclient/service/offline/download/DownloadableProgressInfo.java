// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

class DownloadableProgressInfo
{
    public long mBytesOnTheDisk;
    public long mTotalBytesToDownload;
    
    DownloadableProgressInfo() {
        this.mBytesOnTheDisk = 0L;
        this.mTotalBytesToDownload = -1L;
    }
}
