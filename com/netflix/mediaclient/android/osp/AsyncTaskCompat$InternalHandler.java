// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import android.os.Message;
import android.os.Handler;

class AsyncTaskCompat$InternalHandler extends Handler
{
    public void handleMessage(final Message message) {
        final AsyncTaskCompat$AsyncTaskResult asyncTaskCompat$AsyncTaskResult = (AsyncTaskCompat$AsyncTaskResult)message.obj;
        switch (message.what) {
            default: {}
            case 1: {
                asyncTaskCompat$AsyncTaskResult.mTask.finish(asyncTaskCompat$AsyncTaskResult.mData[0]);
            }
            case 2: {
                asyncTaskCompat$AsyncTaskResult.mTask.onProgressUpdate(asyncTaskCompat$AsyncTaskResult.mData);
            }
        }
    }
}
