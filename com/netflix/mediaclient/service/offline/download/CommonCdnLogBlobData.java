// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

class CommonCdnLogBlobData
{
    final String mDownloadableId;
    final DownloadableType mDownloadableType;
    final String mDxId;
    final String mManifestPlaybackContextId;
    final String mOxId;
    
    private CommonCdnLogBlobData(final String mOxId, final String mDxId, final String mDownloadableId, final DownloadableType mDownloadableType, final String mManifestPlaybackContextId) {
        this.mOxId = mOxId;
        this.mDxId = mDxId;
        this.mDownloadableType = mDownloadableType;
        this.mDownloadableId = mDownloadableId;
        this.mManifestPlaybackContextId = mManifestPlaybackContextId;
    }
    
    public static CommonCdnLogBlobData create(final OfflinePlayablePersistentData offlinePlayablePersistentData, final DownloadableInfo downloadableInfo, final String s) {
        return new CommonCdnLogBlobData(offlinePlayablePersistentData.mOxId, offlinePlayablePersistentData.mDxId, downloadableInfo.getDownloadableId(), downloadableInfo.getDownloadableType(), s);
    }
}
