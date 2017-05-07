// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.app;

import com.netflix.mediaclient.Log;
import com.netflix.mediaclient.android.osp.AsyncTaskCompat;

public class BackgroundTask
{
    private static final String TAG = "BackgroundTask";
    private final NamedAsyncTask task;
    
    public BackgroundTask() {
        this.task = new NamedAsyncTask();
    }
    
    public void execute(final Runnable runnable) {
        ((AsyncTaskCompat<Runnable, Object, Object>)this.task).executeOnExecutor(AsyncTaskCompat.THREAD_POOL_EXECUTOR, runnable);
    }
    
    public void executeInSerial(final Runnable runnable) {
        ((AsyncTaskCompat<Runnable, Object, Object>)this.task).executeOnExecutor(AsyncTaskCompat.SERIAL_EXECUTOR, runnable);
    }
    
    private static class NamedAsyncTask extends AsyncTaskCompat<Runnable, Void, Void>
    {
        @Override
        protected Void doInBackground(final Runnable... array) {
            try {
                for (int length = array.length, i = 0; i < length; ++i) {
                    final Runnable runnable = array[i];
                    Thread.currentThread().setName(runnable.getClass().getName());
                    runnable.run();
                }
            }
            catch (Exception ex) {
                Log.e("BackgroundTask", "Failed to execute!", ex);
            }
            return null;
        }
    }
}
