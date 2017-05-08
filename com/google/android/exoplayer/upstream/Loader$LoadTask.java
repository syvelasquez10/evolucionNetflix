// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Util;
import java.util.concurrent.ExecutorService;
import android.util.Log;
import com.google.android.exoplayer.util.Assertions;
import com.google.android.exoplayer.util.TraceUtil;
import java.io.IOException;
import android.os.Message;
import android.os.Looper;
import android.annotation.SuppressLint;
import android.os.Handler;

@SuppressLint({ "HandlerLeak" })
final class Loader$LoadTask extends Handler implements Runnable
{
    private final Loader$Callback callback;
    private volatile Thread executorThread;
    private final Loader$Loadable loadable;
    final /* synthetic */ Loader this$0;
    
    public Loader$LoadTask(final Loader this$0, final Looper looper, final Loader$Loadable loadable, final Loader$Callback callback) {
        this.this$0 = this$0;
        super(looper);
        this.loadable = loadable;
        this.callback = callback;
    }
    
    private void onFinished() {
        this.this$0.loading = false;
        this.this$0.currentTask = null;
    }
    
    public void handleMessage(final Message message) {
        if (message.what == 2) {
            throw (Error)message.obj;
        }
        this.onFinished();
        if (this.loadable.isLoadCanceled()) {
            this.callback.onLoadCanceled(this.loadable);
            return;
        }
        switch (message.what) {
            default: {}
            case 0: {
                this.callback.onLoadCompleted(this.loadable);
            }
            case 1: {
                this.callback.onLoadError(this.loadable, (IOException)message.obj);
            }
        }
    }
    
    public void quit() {
        this.loadable.cancelLoad();
        if (this.executorThread != null) {
            this.executorThread.interrupt();
        }
    }
    
    public void run() {
        try {
            this.executorThread = Thread.currentThread();
            if (!this.loadable.isLoadCanceled()) {
                TraceUtil.beginSection(this.loadable.getClass().getSimpleName() + ".load()");
                this.loadable.load();
                TraceUtil.endSection();
            }
            this.sendEmptyMessage(0);
        }
        catch (IOException ex) {
            this.obtainMessage(1, (Object)ex).sendToTarget();
        }
        catch (InterruptedException ex3) {
            Assertions.checkState(this.loadable.isLoadCanceled());
            this.sendEmptyMessage(0);
        }
        catch (Exception ex2) {
            Log.e("LoadTask", "Unexpected exception loading stream", (Throwable)ex2);
            this.obtainMessage(1, (Object)new Loader$UnexpectedLoaderException(ex2)).sendToTarget();
        }
        catch (Error error) {
            Log.e("LoadTask", "Unexpected error loading stream", (Throwable)error);
            this.obtainMessage(2, (Object)error).sendToTarget();
            throw error;
        }
    }
}
