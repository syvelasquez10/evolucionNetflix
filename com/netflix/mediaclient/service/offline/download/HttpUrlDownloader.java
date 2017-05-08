// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.offline.download;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import com.android.volley.VolleyError;
import java.io.IOException;
import com.netflix.mediaclient.Log;
import com.android.volley.Request$Priority;
import java.io.File;
import java.io.BufferedOutputStream;
import com.android.volley.toolbox.ProgressiveRequestListener;
import com.android.volley.toolbox.ProgressiveRequest;

class HttpUrlDownloader extends ProgressiveRequest implements ProgressiveRequestListener
{
    private static final String TAG = "nf_httpUrlDownloader";
    private static final boolean TEST_LOG_PROGRESS_EVERY_FIVE_SECONDS = false;
    private BufferedOutputStream mBufferedOutputStream;
    private final File mFile;
    private final String mFileName;
    private final String mHttpRange;
    private HttpUrlDownloader$HttpUrlDownloadListener mHttpUrlDownloadListener;
    private final HttpUrlDownloadStats mHttpUrlDownloadStats;
    private long mTestingLastLogTime;
    private final String mUrl;
    
    public HttpUrlDownloader(final String mUrl, final File mFile, final Request$Priority request$Priority, final HttpUrlDownloader$HttpUrlDownloadListener mHttpUrlDownloadListener) {
        super(mUrl, request$Priority);
        this.mHttpUrlDownloadStats = new HttpUrlDownloadStats();
        this.mUrl = mUrl;
        this.mFile = mFile;
        this.mFileName = this.mFile.getName();
        this.mHttpUrlDownloadListener = mHttpUrlDownloadListener;
        this.setProgressiveRequestListener(this);
        this.mHttpRange = "bytes=" + this.mFile.length() + "-";
    }
    
    private void flushAndCloseOutputStream() {
        if (this.mBufferedOutputStream == null) {
            return;
        }
        while (true) {
            try {
                this.mBufferedOutputStream.flush();
                this.mBufferedOutputStream.close();
                this.mBufferedOutputStream = null;
            }
            catch (IOException ex) {
                Log.e("nf_httpUrlDownloader", ex, "flushAndCloseOutputStream:", new Object[0]);
                continue;
            }
            break;
        }
    }
    
    private void sendNetworkError(final VolleyError volleyError) {
        if (Log.isLoggable()) {
            Log.e("nf_httpUrlDownloader", "sendNetworkError error=" + volleyError);
        }
        if (this.mHttpUrlDownloadListener != null) {
            this.mHttpUrlDownloadListener.onNetworkError(volleyError);
            this.mHttpUrlDownloadListener = null;
        }
    }
    
    private void sendProgress() {
        if (this.mHttpUrlDownloadListener != null) {
            this.mHttpUrlDownloadListener.onProgress(this);
        }
    }
    
    private void sendUrlDownloadDiskIOError() {
        if (this.mHttpUrlDownloadListener != null) {
            this.mHttpUrlDownloadListener.onUrlDownloadDiskIOError();
            this.mHttpUrlDownloadListener = null;
        }
    }
    
    private void sendUrlDownloadSessionEnd() {
        if (this.mHttpUrlDownloadListener != null) {
            this.mHttpUrlDownloadListener.onUrlDownloadSessionEnd();
            this.mHttpUrlDownloadListener = null;
        }
    }
    
    @Override
    public void cancel() {
        if (Log.isLoggable()) {
            Log.i("nf_httpUrlDownloader", "cancel " + this.hashCode());
        }
        this.mHttpUrlDownloadListener = null;
        super.cancel();
    }
    
    @Override
    public Map<String, String> getHeaders() {
        if (Log.isLoggable()) {
            Log.i("nf_httpUrlDownloader", "RangeHeader=" + this.mHttpRange);
        }
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("Range", this.mHttpRange);
        return hashMap;
    }
    
    public long getTotalBytesOnDisk() {
        return this.mHttpUrlDownloadStats.mFileSizeAtDownloadStartTime + this.mHttpUrlDownloadStats.mBytesDownloadedInSession;
    }
    
    @Override
    public void onCancelled() {
        Log.i("nf_httpUrlDownloader", "onCancelled");
        this.setProgressiveRequestListener(null);
        this.flushAndCloseOutputStream();
    }
    
    @Override
    public void onError(final VolleyError volleyError) {
        this.setProgressiveRequestListener(null);
        this.mHttpUrlDownloadStats.mOnErrorTime = System.currentTimeMillis();
        this.flushAndCloseOutputStream();
        this.sendNetworkError(volleyError);
    }
    
    @Override
    public void onNext(final byte[] array, final int n) {
        try {
            if (this.mBufferedOutputStream == null) {
                Log.i("nf_httpUrlDownloader", "onNext mBufferedOutputStream null. not writing");
                return;
            }
            if (this.isCanceled()) {
                Log.i("nf_httpUrlDownloader", "cancelled, closing file and returning");
                this.setProgressiveRequestListener(null);
                this.flushAndCloseOutputStream();
                return;
            }
        }
        catch (IOException ex) {
            Log.e("nf_httpUrlDownloader", ex, "onNext write to disk failed", new Object[0]);
            this.setProgressiveRequestListener(null);
            this.sendUrlDownloadDiskIOError();
            super.cancel();
            return;
        }
        if (n > 0) {
            this.mBufferedOutputStream.write(array, 0, n);
            final HttpUrlDownloadStats mHttpUrlDownloadStats = this.mHttpUrlDownloadStats;
            mHttpUrlDownloadStats.mBytesDownloadedInSession += n;
            this.sendProgress();
            return;
        }
        if (n < 0) {
            Log.i("nf_httpUrlDownloader", "onNext done count=" + n);
            this.setProgressiveRequestListener(null);
            this.flushAndCloseOutputStream();
            this.mHttpUrlDownloadStats.mOnCompleteTime = System.currentTimeMillis();
            if (Log.isLoggable()) {
                this.mHttpUrlDownloadStats.dumpStats();
            }
            this.sendUrlDownloadSessionEnd();
        }
    }
    
    @Override
    protected void onResponseStart(final long n) {
        if (Log.isLoggable()) {
            Log.i("nf_httpUrlDownloader", "onResponseStart responseContentLength=" + n);
        }
        Label_0113: {
            if (this.mBufferedOutputStream != null) {
                break Label_0113;
            }
            try {
                this.mBufferedOutputStream = new BufferedOutputStream(new FileOutputStream(this.mFile, true));
                if (Log.isLoggable()) {
                    Log.i("nf_httpUrlDownloader", "fileName=" + this.mFile.getAbsolutePath() + " fileSize=" + this.mFile.length());
                }
                this.mHttpUrlDownloadStats.mHttpResponseStartTime = System.currentTimeMillis();
                if (this.mHttpUrlDownloadListener != null) {
                    this.mHttpUrlDownloadListener.onHttpResponseStart(n);
                }
            }
            catch (FileNotFoundException ex) {
                if (Log.isLoggable()) {
                    Log.e("nf_httpUrlDownloader", ex, "start failed to create file=" + this.mFile.getAbsolutePath(), new Object[0]);
                }
                this.sendUrlDownloadDiskIOError();
            }
        }
    }
    
    public void start(final RequestQueue requestQueue) {
        this.mHttpUrlDownloadStats.mQueueStartTime = System.currentTimeMillis();
        this.mHttpUrlDownloadStats.mFileSizeAtDownloadStartTime = this.mFile.length();
        if (Log.isLoggable()) {
            Log.i("nf_httpUrlDownloader", "HttpUrlDownloader starting... url=" + this.mUrl);
        }
        requestQueue.add(this);
    }
}
