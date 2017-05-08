// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

import java.io.IOException;
import android.os.Environment;
import java.net.HttpURLConnection;
import java.net.URL;
import com.netflix.mediaclient.Log;
import java.io.File;
import org.chromium.net.CronetEngine$Builder;
import android.content.Context;
import java.util.concurrent.atomic.AtomicBoolean;
import org.chromium.net.CronetEngine;

public final class CronetHttpURLConnectionFactory
{
    private static final String CRONET_CACHE_DIR = "cronet";
    private static final String TAG = "nf_net_cronet";
    private static CronetHttpURLConnectionFactory sInstance;
    private CronetEngine mCronetEngine;
    private AtomicBoolean mNetLogInProgress;
    
    private CronetHttpURLConnectionFactory(final Context context) {
        this.mNetLogInProgress = new AtomicBoolean(false);
        this.mCronetEngine = this.createAndConfigureBuilder(context).build();
    }
    
    private CronetEngine$Builder createAndConfigureBuilder(final Context context) {
        final CronetEngine$Builder cronetEngine$Builder = new CronetEngine$Builder(context);
        cronetEngine$Builder.enableHttpCache(1, 102400L);
        final File file = new File(context.getCacheDir(), "cronet");
        Log.d("nf_net_cronet", "Set cache to %s, it exists %b, absolute path %s", file.getPath(), file.exists(), file.getAbsolutePath());
        if (!file.exists()) {
            Log.d("nf_net_cronet", "Cache created %b", file.mkdir());
        }
        cronetEngine$Builder.setStoragePath(file.getPath());
        cronetEngine$Builder.enableHttpCache(3, 1048576L);
        return cronetEngine$Builder;
    }
    
    public static CronetHttpURLConnectionFactory getInstance(final Context context) {
        Label_0029: {
            if (CronetHttpURLConnectionFactory.sInstance != null) {
                break Label_0029;
            }
            synchronized (CronetHttpURLConnectionFactory.class) {
                if (CronetHttpURLConnectionFactory.sInstance == null) {
                    CronetHttpURLConnectionFactory.sInstance = new CronetHttpURLConnectionFactory(context);
                }
                return CronetHttpURLConnectionFactory.sInstance;
            }
        }
    }
    
    public boolean isNetLogInProgress() {
        return this.mNetLogInProgress.get();
    }
    
    HttpURLConnection openConnection(final URL url) {
        return (HttpURLConnection)this.mCronetEngine.openConnection(url);
    }
    
    public void startNetLog() {
        synchronized (this) {
            if (!this.mNetLogInProgress.get()) {
                try {
                    final File tempFile = File.createTempFile("cronet", ".log", Environment.getExternalStorageDirectory());
                    if (Log.isLoggable()) {
                        Log.d("nf_net_cronet", "Logging cronet netlog to: " + tempFile.getCanonicalPath());
                    }
                    this.mCronetEngine.startNetLogToFile(tempFile.toString(), true);
                    this.mNetLogInProgress.set(true);
                }
                catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
    
    public void stopNetLog() {
        synchronized (this) {
            if (this.mNetLogInProgress.get()) {
                Log.d("nf_net_cronet", "Stopping cronet logging");
                this.mCronetEngine.stopNetLog();
                this.mNetLogInProgress.set(false);
            }
        }
    }
}
