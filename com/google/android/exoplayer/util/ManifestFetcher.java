// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.text.TextUtils;
import android.os.SystemClock;
import com.google.android.exoplayer.upstream.Loader$Loadable;
import java.io.IOException;
import com.google.android.exoplayer.upstream.UriDataSource;
import com.google.android.exoplayer.upstream.UriLoadable$Parser;
import com.google.android.exoplayer.upstream.Loader;
import android.os.Handler;
import com.google.android.exoplayer.upstream.UriLoadable;
import com.google.android.exoplayer.upstream.Loader$Callback;

public class ManifestFetcher<T> implements Loader$Callback
{
    private long currentLoadStartTimestamp;
    private UriLoadable<T> currentLoadable;
    private int enabledCount;
    private final Handler eventHandler;
    private final ManifestFetcher$EventListener eventListener;
    private ManifestFetcher$ManifestIOException loadException;
    private int loadExceptionCount;
    private long loadExceptionTimestamp;
    private Loader loader;
    private volatile T manifest;
    private volatile long manifestLoadCompleteTimestamp;
    private volatile long manifestLoadStartTimestamp;
    volatile String manifestUri;
    private final UriLoadable$Parser<T> parser;
    private final UriDataSource uriDataSource;
    
    private long getRetryDelayMillis(final long n) {
        return Math.min((n - 1L) * 1000L, 5000L);
    }
    
    private void notifyManifestError(final IOException ex) {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ManifestFetcher$3(this, ex));
        }
    }
    
    private void notifyManifestRefreshStarted() {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ManifestFetcher$1(this));
        }
    }
    
    private void notifyManifestRefreshed() {
        if (this.eventHandler != null && this.eventListener != null) {
            this.eventHandler.post((Runnable)new ManifestFetcher$2(this));
        }
    }
    
    public void disable() {
        final int enabledCount = this.enabledCount - 1;
        this.enabledCount = enabledCount;
        if (enabledCount == 0 && this.loader != null) {
            this.loader.release();
            this.loader = null;
        }
    }
    
    public void enable() {
        if (this.enabledCount++ == 0) {
            this.loadExceptionCount = 0;
            this.loadException = null;
        }
    }
    
    public T getManifest() {
        return this.manifest;
    }
    
    public long getManifestLoadStartTimestamp() {
        return this.manifestLoadStartTimestamp;
    }
    
    public void maybeThrowError() {
        if (this.loadException == null || this.loadExceptionCount <= 1) {
            return;
        }
        throw this.loadException;
    }
    
    @Override
    public void onLoadCanceled(final Loader$Loadable loader$Loadable) {
    }
    
    @Override
    public void onLoadCompleted(final Loader$Loadable loader$Loadable) {
        if (this.currentLoadable != loader$Loadable) {
            return;
        }
        this.manifest = this.currentLoadable.getResult();
        this.manifestLoadStartTimestamp = this.currentLoadStartTimestamp;
        this.manifestLoadCompleteTimestamp = SystemClock.elapsedRealtime();
        this.loadExceptionCount = 0;
        this.loadException = null;
        if (this.manifest instanceof ManifestFetcher$RedirectingManifest) {
            final String nextManifestUri = ((ManifestFetcher$RedirectingManifest)this.manifest).getNextManifestUri();
            if (!TextUtils.isEmpty((CharSequence)nextManifestUri)) {
                this.manifestUri = nextManifestUri;
            }
        }
        this.notifyManifestRefreshed();
    }
    
    @Override
    public void onLoadError(final Loader$Loadable loader$Loadable, final IOException ex) {
        if (this.currentLoadable != loader$Loadable) {
            return;
        }
        ++this.loadExceptionCount;
        this.loadExceptionTimestamp = SystemClock.elapsedRealtime();
        this.notifyManifestError(this.loadException = new ManifestFetcher$ManifestIOException(ex));
    }
    
    public void requestRefresh() {
        if (this.loadException == null || SystemClock.elapsedRealtime() >= this.loadExceptionTimestamp + this.getRetryDelayMillis(this.loadExceptionCount)) {
            if (this.loader == null) {
                this.loader = new Loader("manifestLoader");
            }
            if (!this.loader.isLoading()) {
                this.currentLoadable = new UriLoadable<T>(this.manifestUri, this.uriDataSource, this.parser);
                this.currentLoadStartTimestamp = SystemClock.elapsedRealtime();
                this.loader.startLoading(this.currentLoadable, this);
                this.notifyManifestRefreshStarted();
            }
        }
    }
}
