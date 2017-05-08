// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.netflix.mediaclient.util.LogUtils;
import com.netflix.mediaclient.service.offline.utils.OfflineUtils;
import com.netflix.mediaclient.util.ConnectivityUtils;
import com.netflix.mediaclient.servicemgr.ErrorLogging;
import com.netflix.mediaclient.util.VolleyUtils;
import com.android.volley.VolleyError;
import com.netflix.mediaclient.android.app.Status;
import com.netflix.mediaclient.android.app.NetflixStatus;
import com.netflix.mediaclient.StatusCode;
import java.util.concurrent.TimeUnit;
import com.netflix.mediaclient.Log;
import com.android.volley.Request$Priority;
import com.netflix.mediaclient.servicemgr.IClientLogging;
import android.os.Looper;
import com.android.volley.RequestQueue;
import java.util.List;
import java.io.File;
import android.content.Context;
import android.os.Handler;

public class CdnUrlDownloader implements HttpUrlDownloader$HttpUrlDownloadListener
{
    private static final int MAX_PRIMARY_CDN_NETWORK_ERRORS;
    private static final long[] PrimaryCdnBackOffTimes;
    private static final String TAG = "nf_cdnUrlDownloader";
    private final Handler mBackGroundHandler;
    private final Runnable mCdnRetryRunnable;
    private final CdnUrlDownloadEventReceiver mCdnUrlDownloadEventReceiver;
    private final CdnUrlDownloadListener mCdnUrlDownloadListener;
    private final Context mContext;
    private int mCurrentUrlIndex;
    private final File mDownloadableFile;
    private final DownloadableProgressInfo mDownloadableProgressInfo;
    private HttpUrlDownloader mHttpUrlDownloader;
    private final DownloadablePersistentData mMediaUrlDownloadPersistentData;
    private int mPrimaryCdnUrlNetworkErrors;
    private final List<CdnUrl> mRankedCdnUrlList;
    private final RequestQueue mRequestQueue;
    
    static {
        PrimaryCdnBackOffTimes = new long[] { 30000L, 60000L };
        MAX_PRIMARY_CDN_NETWORK_ERRORS = CdnUrlDownloader.PrimaryCdnBackOffTimes.length;
    }
    
    public CdnUrlDownloader(final Context mContext, final Looper looper, final DownloadablePersistentData mMediaUrlDownloadPersistentData, final DownloadableInfo downloadableInfo, final DownloadableProgressInfo mDownloadableProgressInfo, final File mDownloadableFile, final RequestQueue mRequestQueue, final CommonCdnLogBlobData commonCdnLogBlobData, final IClientLogging clientLogging, final CdnUrlDownloadListener mCdnUrlDownloadListener) {
        this.mCdnRetryRunnable = new CdnUrlDownloader$1(this);
        this.mContext = mContext;
        this.mBackGroundHandler = new Handler(looper);
        this.mMediaUrlDownloadPersistentData = mMediaUrlDownloadPersistentData;
        this.mDownloadableProgressInfo = mDownloadableProgressInfo;
        this.mDownloadableFile = mDownloadableFile;
        this.mRequestQueue = mRequestQueue;
        this.mCdnUrlDownloadListener = mCdnUrlDownloadListener;
        this.mDownloadableProgressInfo.mBytesOnTheDisk = this.mDownloadableFile.length();
        CdnUrl.sortByRank(this.mRankedCdnUrlList = downloadableInfo.getCdnUrls());
        this.mCdnUrlDownloadEventReceiver = new CdnUrlDownloadEventReceiver(commonCdnLogBlobData, clientLogging);
    }
    
    private void doStartDownload(final String s) {
        (this.mHttpUrlDownloader = new HttpUrlDownloader(s, this.mDownloadableFile, Request$Priority.NORMAL, this)).start(this.mRequestQueue);
    }
    
    private void doStopDownload() {
        this.mBackGroundHandler.removeCallbacksAndMessages((Object)null);
        if (this.mHttpUrlDownloader != null) {
            Log.i("nf_cdnUrlDownloader", "doStopDownload");
            this.mCdnUrlDownloadEventReceiver.onDownloadStop(this.mDownloadableProgressInfo.mBytesOnTheDisk);
            this.mHttpUrlDownloader.cancel();
            this.mHttpUrlDownloader = null;
        }
    }
    
    private void handleCdnUrlNetworkError() {
        if (this.mCurrentUrlIndex == 0 && this.mPrimaryCdnUrlNetworkErrors < CdnUrlDownloader.MAX_PRIMARY_CDN_NETWORK_ERRORS) {
            if (Log.isLoggable()) {
                Log.e("nf_cdnUrlDownloader", "handleCdnUrlNetworkError back-off by " + TimeUnit.MILLISECONDS.toSeconds(CdnUrlDownloader.PrimaryCdnBackOffTimes[this.mPrimaryCdnUrlNetworkErrors]));
            }
            this.mBackGroundHandler.removeCallbacks(this.mCdnRetryRunnable);
            this.mBackGroundHandler.postDelayed(this.mCdnRetryRunnable, CdnUrlDownloader.PrimaryCdnBackOffTimes[this.mPrimaryCdnUrlNetworkErrors]);
            ++this.mPrimaryCdnUrlNetworkErrors;
            return;
        }
        ++this.mCurrentUrlIndex;
        if (this.mCurrentUrlIndex < this.mRankedCdnUrlList.size()) {
            this.mBackGroundHandler.removeCallbacks(this.mCdnRetryRunnable);
            this.mBackGroundHandler.post(this.mCdnRetryRunnable);
            return;
        }
        Log.e("nf_cdnUrlDownloader", "onCdnRetryRunnable all CDN URL exhausted");
        this.mCdnUrlDownloadListener.onNetworkError(this, new NetflixStatus(StatusCode.DL_ALL_CDN_URLS_FAILED));
    }
    
    private void onCdnRetryRunnable() {
        if (this.mCurrentUrlIndex < this.mRankedCdnUrlList.size()) {
            final CdnUrl cdnUrl = this.mRankedCdnUrlList.get(this.mCurrentUrlIndex);
            if (Log.isLoggable()) {
                Log.i("nf_cdnUrlDownloader", "onCdnRetryRunnable using rank=" + cdnUrl.mRank + " url=" + cdnUrl.mUrl);
            }
            this.doStartDownload(cdnUrl.mUrl);
            return;
        }
        this.mCdnUrlDownloadListener.onNetworkError(this, new NetflixStatus(StatusCode.DL_ALL_CDN_URLS_FAILED));
    }
    
    public String getDownloadableId() {
        return this.mMediaUrlDownloadPersistentData.mDownloadableId;
    }
    
    public boolean isDownloadComplete() {
        return this.mMediaUrlDownloadPersistentData.mIsComplete;
    }
    
    public boolean isDownloading() {
        return !this.mMediaUrlDownloadPersistentData.mIsComplete && this.mHttpUrlDownloader != null;
    }
    
    @Override
    public void onHttpResponseStart(final long n) {
        if (Log.isLoggable()) {
            Log.i("nf_cdnUrlDownloader", "onHttpResponseStart httpContentLength=" + n);
        }
        if (this.mDownloadableProgressInfo.mBytesOnTheDisk == 0L && n > 0L && n != this.mMediaUrlDownloadPersistentData.mSizeOfDownloadable && Log.isLoggable()) {
            Log.e("nf_cdnUrlDownloader", "onHttpResponseStart size mismatch size=" + this.mMediaUrlDownloadPersistentData.mSizeOfDownloadable + " httpContentLength=" + n);
        }
        if (this.mCurrentUrlIndex >= 0 && this.mCurrentUrlIndex < this.mRankedCdnUrlList.size()) {
            this.mCdnUrlDownloadEventReceiver.onDownloadStart(this.mRankedCdnUrlList.get(this.mCurrentUrlIndex), this.mDownloadableProgressInfo.mBytesOnTheDisk);
        }
        else if (Log.isLoggable()) {
            Log.e("nf_cdnUrlDownloader", "Incorrect mCurrentUrlIndex=" + this.mCurrentUrlIndex);
        }
    }
    
    @Override
    public void onNetworkError(final VolleyError volleyError) {
        while (true) {
            Label_0277: {
                int statusCode = 0;
                Label_0194: {
                    synchronized (this) {
                        if (Log.isLoggable()) {
                            Log.e("nf_cdnUrlDownloader", "onNetworkError error=" + volleyError);
                        }
                        statusCode = -1;
                        if (volleyError.networkResponse != null) {
                            final int n = statusCode = volleyError.networkResponse.statusCode;
                            if (Log.isLoggable()) {
                                Log.e("nf_cdnUrlDownloader", "onError httpStatusCode=" + n);
                                statusCode = n;
                            }
                        }
                        final NetflixStatus status = VolleyUtils.getStatus(volleyError, null);
                        this.doStopDownload();
                        if (ConnectivityUtils.isConnected(this.mContext)) {
                            if (OfflineUtils.cdnUrlExpiredOrMoved(statusCode)) {
                                Log.e("nf_cdnUrlDownloader", "cdnUrlExpiredOrMoved httpStatusCode=%d", statusCode);
                                this.mCdnUrlDownloadListener.onCdnUrlExpiredOrMoved(this, status);
                            }
                            else {
                                if (!OfflineUtils.isCdnUrlGeoCheckError(statusCode)) {
                                    break Label_0194;
                                }
                                Log.e("nf_cdnUrlDownloader", "isCdnUrlGeoCheckError httpStatusCode=%d", statusCode);
                                this.mCdnUrlDownloadListener.onCdnUrlGeoCheckFailure(this, status);
                            }
                            return;
                        }
                        break Label_0277;
                    }
                }
                if (statusCode == 416) {
                    this.doStopDownload();
                    final boolean delete = this.mDownloadableFile.delete();
                    if (Log.isLoggable()) {
                        Log.e("nf_cdnUrlDownloader", "onNetworkError deleting " + this.mDownloadableFile.getName() + " deleted=" + delete);
                    }
                    LogUtils.reportErrorSafely("http 416 error", null);
                }
                this.handleCdnUrlNetworkError();
                return;
            }
            Log.i("nf_cdnUrlDownloader", "onNetworkError lost connectivity.. not trying next CDN url.");
            final Status status2;
            this.mCdnUrlDownloadListener.onNetworkError(this, status2);
        }
    }
    
    @Override
    public void onProgress(final HttpUrlDownloader httpUrlDownloader) {
        this.mDownloadableProgressInfo.mBytesOnTheDisk = httpUrlDownloader.getTotalBytesOnDisk();
    }
    
    @Override
    public void onUrlDownloadDiskIOError() {
        synchronized (this) {
            Log.i("nf_cdnUrlDownloader", "onUrlDownloadDiskIOError");
            this.doStopDownload();
            this.mCdnUrlDownloadListener.onUrlDownloadDiskIOError(this);
        }
    }
    
    @Override
    public void onUrlDownloadSessionEnd() {
        synchronized (this) {
            if (this.mDownloadableFile.length() >= this.mMediaUrlDownloadPersistentData.mSizeOfDownloadable) {
                Log.i("nf_cdnUrlDownloader", "onUrlDownloadSessionEnd download finished.");
                this.mMediaUrlDownloadPersistentData.mIsComplete = true;
                this.mCdnUrlDownloadEventReceiver.onDownloadComplete(this.mDownloadableProgressInfo.mBytesOnTheDisk);
            }
            else {
                Log.i("nf_cdnUrlDownloader", "onUrlDownloadSessionEnd not finished yet.");
            }
            this.mCdnUrlDownloadListener.onCdnUrlDownloadSessionEnd(this);
            this.doStopDownload();
        }
    }
    
    public void startDownload() {
        synchronized (this) {
            Log.i("nf_cdnUrlDownloader", "startDownload");
            this.mDownloadableProgressInfo.mBytesOnTheDisk = this.mDownloadableFile.length();
            this.mCurrentUrlIndex = 0;
            this.mPrimaryCdnUrlNetworkErrors = 0;
            final String mUrl = this.mRankedCdnUrlList.get(0).mUrl;
            this.doStopDownload();
            this.doStartDownload(mUrl);
        }
    }
    
    public void stopDownload() {
        synchronized (this) {
            this.doStopDownload();
        }
    }
}
