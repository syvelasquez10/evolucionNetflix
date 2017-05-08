// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import org.json.JSONException;
import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.app.BackgroundTask;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import com.netflix.mediaclient.servicemgr.LogblobLogging;

class CdnUrlDownloadEventReceiver
{
    private static final String TAG = "nf_cdnUrlDownloadEvent";
    private final CommonCdnLogBlobData mCommonLogBlobData;
    private CdnUrl mCurrentCdnUrl;
    private long mDlStartBytes;
    private long mDlStartTime;
    private final LogblobLogging mLogblobLogging;
    
    CdnUrlDownloadEventReceiver(final CommonCdnLogBlobData mCommonLogBlobData, final IClientLogging clientLogging) {
        this.mCommonLogBlobData = mCommonLogBlobData;
        this.mLogblobLogging = clientLogging.getLogblobLogging();
    }
    
    private void sendDlReportLogBlob(long n, final boolean b) {
        final long n2 = System.currentTimeMillis() - this.mDlStartTime;
        n -= this.mDlStartBytes;
        if (n2 > 0L && n >= 0L) {
            final CdnUrlDownloadEventReceiver$CdnDownloadLogBlob cdnUrlDownloadEventReceiver$CdnDownloadLogBlob = new CdnUrlDownloadEventReceiver$CdnDownloadLogBlob(this, b);
            try {
                cdnUrlDownloadEventReceiver$CdnDownloadLogBlob.populateJson(this.mCommonLogBlobData, this.mCurrentCdnUrl, this.mDlStartTime, this.mDlStartBytes, n2, n);
                new BackgroundTask().execute(new CdnUrlDownloadEventReceiver$1(this, cdnUrlDownloadEventReceiver$CdnDownloadLogBlob));
                return;
            }
            catch (JSONException ex) {
                Log.e("nf_cdnUrlDownloadEvent", (Throwable)ex, "onDownloadComplete jsonException", new Object[0]);
                return;
            }
            catch (Exception ex2) {
                Log.e("nf_cdnUrlDownloadEvent", ex2, "onDownloadComplete exception", new Object[0]);
                return;
            }
        }
        Log.i("nf_cdnUrlDownloadEvent", "onDownloadComplete not sending dl report.");
    }
    
    void onDownloadComplete(final long n) {
        if (this.mCurrentCdnUrl == null) {
            Log.i("nf_cdnUrlDownloadEvent", "onDownloadComplete  didn't receive onDownloadStart. Not an error, ignoring");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_cdnUrlDownloadEvent", "onDownloadComplete  mDownloadableType=" + this.mCommonLogBlobData.mDownloadableType + ", mDownloadableId=" + this.mCommonLogBlobData.mDownloadableId + ", cdnId=" + this.mCurrentCdnUrl.mCdnId + ", bytesOnTheDisk=" + n);
        }
        this.sendDlReportLogBlob(n, true);
        this.mCurrentCdnUrl = null;
    }
    
    void onDownloadStart(final CdnUrl mCurrentCdnUrl, final long mDlStartBytes) {
        this.mCurrentCdnUrl = mCurrentCdnUrl;
        if (Log.isLoggable()) {
            Log.i("nf_cdnUrlDownloadEvent", "onDownloadStart  mDownloadableType=" + this.mCommonLogBlobData.mDownloadableType + ", mDownloadableId=" + this.mCommonLogBlobData.mDownloadableId + ", cdnId=" + this.mCurrentCdnUrl.mCdnId + ", bytesOnTheDisk=" + mDlStartBytes);
        }
        this.mDlStartTime = System.currentTimeMillis();
        this.mDlStartBytes = mDlStartBytes;
    }
    
    void onDownloadStop(final long n) {
        if (this.mCurrentCdnUrl == null) {
            Log.i("nf_cdnUrlDownloadEvent", "onDownloadStop  didn't receive onDownloadStart. Not an error, ignoring");
            return;
        }
        if (Log.isLoggable()) {
            Log.i("nf_cdnUrlDownloadEvent", "onDownloadStop  mDownloadableType=" + this.mCommonLogBlobData.mDownloadableType + ", mDownloadableId=" + this.mCommonLogBlobData.mDownloadableId + ", cdnId=" + this.mCurrentCdnUrl.mCdnId + ", bytesOnTheDisk=" + n);
        }
        this.sendDlReportLogBlob(n, false);
        this.mCurrentCdnUrl = null;
    }
}
