// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.service.logging.logblob.LogBlobType;
import com.netflix.mediaclient.service.logging.logblob.BaseLogblob;

class CdnUrlDownloadEventReceiver$CdnDownloadLogBlob extends BaseLogblob
{
    private static final String BYTES_DOWNLOAD_IN_SESSION = "bytes";
    private static final String CDN_ID = "cdnid";
    private static final String DL_START_TIME = "downloadstarttime";
    private static final String DOWNLOADABLE_ID = "dlid";
    private static final String DOWNLOAD_DURATION = "duration";
    private static final String DXID = "dxid";
    private static final String OXID = "oxid";
    private static final String PLAYBACK_CONTEXT_ID = "playbackcontextid";
    private static final String START_BYTE_OFFSET = "startbyteoffset";
    private static final String TYPE = "offlinedlreport";
    private final boolean mShouldSendNow;
    final /* synthetic */ CdnUrlDownloadEventReceiver this$0;
    
    CdnUrlDownloadEventReceiver$CdnDownloadLogBlob(final CdnUrlDownloadEventReceiver this$0, final boolean mShouldSendNow) {
        this.this$0 = this$0;
        this.mShouldSendNow = mShouldSendNow;
    }
    
    @Override
    public String getType() {
        return LogBlobType.OFFLINE_CDN_URL_DOWNLOAD.getValue();
    }
    
    void populateJson(final CommonCdnLogBlobData commonCdnLogBlobData, final CdnUrl cdnUrl, final long n, final long n2, final long n3, final long n4) {
        this.mJson.put("offlinedlreport", (Object)LogBlobType.OFFLINE_CDN_URL_DOWNLOAD.getValue());
        this.mJson.put("oxid", (Object)commonCdnLogBlobData.mOxId);
        this.mJson.put("dxid", (Object)commonCdnLogBlobData.mDxId);
        this.mJson.put("downloadstarttime", n);
        this.mJson.put("startbyteoffset", n2);
        this.mJson.put("playbackcontextid", (Object)commonCdnLogBlobData.mManifestPlaybackContextId);
        this.mJson.put("cdnid", cdnUrl.mCdnId);
        this.mJson.put("dlid", (Object)commonCdnLogBlobData.mDownloadableId);
        this.mJson.put("bytes", n4);
        this.mJson.put("duration", n3);
    }
    
    @Override
    public boolean shouldSendNow() {
        return this.mShouldSendNow;
    }
}
