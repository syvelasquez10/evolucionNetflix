// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Handler;

class RequestProgress
{
    private final Handler callbackHandler;
    private long lastReportedProgress;
    private long maxProgress;
    private long progress;
    private final Request request;
    private final long threshold;
    
    RequestProgress(final Handler callbackHandler, final Request request) {
        this.request = request;
        this.callbackHandler = callbackHandler;
        this.threshold = Settings.getOnProgressThreshold();
    }
    
    void addProgress(final long n) {
        this.progress += n;
        if (this.progress >= this.lastReportedProgress + this.threshold || this.progress >= this.maxProgress) {
            this.reportProgress();
        }
    }
    
    void addToMax(final long n) {
        this.maxProgress += n;
    }
    
    long getMaxProgress() {
        return this.maxProgress;
    }
    
    long getProgress() {
        return this.progress;
    }
    
    void reportProgress() {
        if (this.progress > this.lastReportedProgress) {
            final Request$Callback callback = this.request.getCallback();
            if (this.maxProgress > 0L && callback instanceof Request$OnProgressCallback) {
                final long progress = this.progress;
                final long maxProgress = this.maxProgress;
                final Request$OnProgressCallback request$OnProgressCallback = (Request$OnProgressCallback)callback;
                if (this.callbackHandler == null) {
                    request$OnProgressCallback.onProgress(progress, maxProgress);
                }
                else {
                    this.callbackHandler.post((Runnable)new RequestProgress$1(this, request$OnProgressCallback, progress, maxProgress));
                }
                this.lastReportedProgress = this.progress;
            }
        }
    }
}
