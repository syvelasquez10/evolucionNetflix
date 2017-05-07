// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.android.osp.AsyncTaskCompat;

public class BackgroundTask
{
    private static final String TAG = "BackgroundTask";
    private final BackgroundTask$NamedAsyncTask task;
    
    public BackgroundTask() {
        this.task = new BackgroundTask$NamedAsyncTask(null);
    }
    
    public void execute(final Runnable runnable) {
        ((AsyncTaskCompat<Runnable, Object, Object>)this.task).executeOnExecutor(AsyncTaskCompat.THREAD_POOL_EXECUTOR, runnable);
    }
    
    public void executeInSerial(final Runnable runnable) {
        ((AsyncTaskCompat<Runnable, Object, Object>)this.task).executeOnExecutor(AsyncTaskCompat.SERIAL_EXECUTOR, runnable);
    }
}
