// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.FutureTask;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import android.os.Message;
import android.os.Handler;

class ModernAsyncTask$InternalHandler extends Handler
{
    public void handleMessage(final Message message) {
        final ModernAsyncTask$AsyncTaskResult modernAsyncTask$AsyncTaskResult = (ModernAsyncTask$AsyncTaskResult)message.obj;
        switch (message.what) {
            default: {}
            case 1: {
                modernAsyncTask$AsyncTaskResult.mTask.finish(modernAsyncTask$AsyncTaskResult.mData[0]);
            }
            case 2: {
                modernAsyncTask$AsyncTaskResult.mTask.onProgressUpdate(modernAsyncTask$AsyncTaskResult.mData);
            }
        }
    }
}
