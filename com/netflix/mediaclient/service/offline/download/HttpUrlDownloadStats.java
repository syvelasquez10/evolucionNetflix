// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.Log;

class HttpUrlDownloadStats
{
    private static final String TAG = "nf_httpUrlDownloadStat";
    public long mBytesDownloadedInSession;
    public long mFileSizeAtDownloadStartTime;
    public long mHttpResponseStartTime;
    public long mOnCompleteTime;
    public long mOnErrorTime;
    public long mQueueStartTime;
    
    HttpUrlDownloadStats() {
        this.mHttpResponseStartTime = 0L;
        this.mBytesDownloadedInSession = 0L;
        this.mFileSizeAtDownloadStartTime = 0L;
    }
    
    public void dumpStats() {
        Log.i("nf_httpUrlDownloadStat", "mQueueStartTime=" + this.mQueueStartTime);
        Log.i("nf_httpUrlDownloadStat", "mOnCompleteTime=" + this.mOnCompleteTime);
        Log.i("nf_httpUrlDownloadStat", "mOnErrorTime=" + this.mOnErrorTime);
        Log.i("nf_httpUrlDownloadStat", "mHttpResponseStartTime=" + this.mHttpResponseStartTime);
        long n;
        if (this.mOnCompleteTime > this.mOnErrorTime) {
            n = this.mOnCompleteTime;
        }
        else {
            n = this.mOnErrorTime;
        }
        final long n2 = n - this.mHttpResponseStartTime;
        if (n2 > 0L) {
            Log.i("nf_httpUrlDownloadStat", "dl speed in KB/sec=" + this.mBytesDownloadedInSession / n2);
            return;
        }
        Log.i("nf_httpUrlDownloadStat", "dl no speed data. took=" + n2);
    }
}
