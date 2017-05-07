// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import android.os.Handler;
import java.util.Iterator;
import java.io.OutputStream;
import java.util.Map;
import java.io.FilterOutputStream;

class ProgressOutputStream extends FilterOutputStream implements RequestOutputStream
{
    private long batchProgress;
    private RequestProgress currentRequestProgress;
    private long lastReportedProgress;
    private long maxProgress;
    private final Map<Request, RequestProgress> progressMap;
    private final RequestBatch requests;
    private final long threshold;
    
    ProgressOutputStream(final OutputStream outputStream, final RequestBatch requests, final Map<Request, RequestProgress> progressMap, final long maxProgress) {
        super(outputStream);
        this.requests = requests;
        this.progressMap = progressMap;
        this.maxProgress = maxProgress;
        this.threshold = Settings.getOnProgressThreshold();
    }
    
    private void addProgress(final long n) {
        if (this.currentRequestProgress != null) {
            this.currentRequestProgress.addProgress(n);
        }
        this.batchProgress += n;
        if (this.batchProgress >= this.lastReportedProgress + this.threshold || this.batchProgress >= this.maxProgress) {
            this.reportBatchProgress();
        }
    }
    
    private void reportBatchProgress() {
        if (this.batchProgress > this.lastReportedProgress) {
            for (final RequestBatch$Callback requestBatch$Callback : this.requests.getCallbacks()) {
                if (requestBatch$Callback instanceof RequestBatch$OnProgressCallback) {
                    final Handler callbackHandler = this.requests.getCallbackHandler();
                    final RequestBatch$OnProgressCallback requestBatch$OnProgressCallback = (RequestBatch$OnProgressCallback)requestBatch$Callback;
                    if (callbackHandler == null) {
                        requestBatch$OnProgressCallback.onBatchProgress(this.requests, this.batchProgress, this.maxProgress);
                    }
                    else {
                        callbackHandler.post((Runnable)new ProgressOutputStream$1(this, requestBatch$OnProgressCallback));
                    }
                }
            }
            this.lastReportedProgress = this.batchProgress;
        }
    }
    
    @Override
    public void close() {
        super.close();
        final Iterator<RequestProgress> iterator = this.progressMap.values().iterator();
        while (iterator.hasNext()) {
            iterator.next().reportProgress();
        }
        this.reportBatchProgress();
    }
    
    @Override
    public void setCurrentRequest(final Request request) {
        RequestProgress currentRequestProgress;
        if (request != null) {
            currentRequestProgress = this.progressMap.get(request);
        }
        else {
            currentRequestProgress = null;
        }
        this.currentRequestProgress = currentRequestProgress;
    }
    
    @Override
    public void write(final int n) {
        this.out.write(n);
        this.addProgress(1L);
    }
    
    @Override
    public void write(final byte[] array) {
        this.out.write(array);
        this.addProgress(array.length);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        this.out.write(array, n, n2);
        this.addProgress(n2);
    }
}
