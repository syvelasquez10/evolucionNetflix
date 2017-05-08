// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import android.os.Looper;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.Util;
import java.util.concurrent.ExecutorService;

public final class Loader
{
    private Loader$LoadTask currentTask;
    private final ExecutorService downloadExecutorService;
    private boolean loading;
    
    public Loader(final String s) {
        this.downloadExecutorService = Util.newSingleThreadExecutor(s);
    }
    
    public void cancelLoading() {
        Assertions.checkState(this.loading);
        this.currentTask.quit();
    }
    
    public boolean isLoading() {
        return this.loading;
    }
    
    public void release() {
        this.release(null);
    }
    
    public void release(final Runnable runnable) {
        if (this.loading) {
            this.cancelLoading();
        }
        if (runnable != null) {
            this.downloadExecutorService.submit(runnable);
        }
        this.downloadExecutorService.shutdown();
    }
    
    public void startLoading(final Looper looper, final Loader$Loadable loader$Loadable, final Loader$Callback loader$Callback) {
        Assertions.checkState(!this.loading);
        this.loading = true;
        this.currentTask = new Loader$LoadTask(this, looper, loader$Loadable, loader$Callback);
        this.downloadExecutorService.submit(this.currentTask);
    }
    
    public void startLoading(final Loader$Loadable loader$Loadable, final Loader$Callback loader$Callback) {
        final Looper myLooper = Looper.myLooper();
        Assertions.checkState(myLooper != null);
        this.startLoading(myLooper, loader$Loadable, loader$Callback);
    }
}
