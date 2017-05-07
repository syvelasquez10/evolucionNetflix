// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import android.os.Message;
import android.os.Handler;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import android.util.Log;
import java.util.concurrent.Callable;
import android.os.Process;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import com.netflix.mediaclient.service.configuration.ConfigurationAgent;
import java.util.concurrent.FutureTask;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;

public abstract class AsyncTaskCompat<Params, Progress, Result>
{
    private static final int CORE_POOL_SIZE = 4;
    private static final int KEEP_ALIVE = 1;
    private static final String LOG_TAG = "AsyncTask";
    private static final int MAXIMUM_POOL_SIZE;
    private static final int MESSAGE_POST_PROGRESS = 2;
    private static final int MESSAGE_POST_RESULT = 1;
    public static final Executor SERIAL_EXECUTOR;
    public static final Executor THREAD_POOL_EXECUTOR;
    private static volatile Executor sDefaultExecutor;
    private static final InternalHandler sHandler;
    private static final BlockingQueue<Runnable> sPoolWorkQueue;
    private static final ThreadFactory sThreadFactory;
    private final AtomicBoolean mCancelled;
    private final FutureTask<Result> mFuture;
    private volatile Status mStatus;
    private final AtomicBoolean mTaskInvoked;
    private final WorkerRunnable<Params, Result> mWorker;
    
    static {
        int maximum_POOL_SIZE;
        if (ConfigurationAgent.shouldUseLowMemConfig()) {
            maximum_POOL_SIZE = 8;
        }
        else {
            maximum_POOL_SIZE = 16;
        }
        MAXIMUM_POOL_SIZE = maximum_POOL_SIZE;
        sThreadFactory = new ThreadFactory() {
            private final AtomicInteger mCount = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "AsyncTask #" + this.mCount.getAndIncrement());
            }
        };
        sPoolWorkQueue = new LinkedBlockingQueue<Runnable>(10);
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, AsyncTaskCompat.MAXIMUM_POOL_SIZE, 1L, TimeUnit.SECONDS, AsyncTaskCompat.sPoolWorkQueue, AsyncTaskCompat.sThreadFactory, new ThreadPoolExecutor.DiscardOldestPolicy());
        SERIAL_EXECUTOR = Executors.newSingleThreadExecutor();
        sHandler = new InternalHandler();
        AsyncTaskCompat.sDefaultExecutor = AsyncTaskCompat.SERIAL_EXECUTOR;
    }
    
    public AsyncTaskCompat() {
        this.mStatus = Status.PENDING;
        this.mCancelled = new AtomicBoolean();
        this.mTaskInvoked = new AtomicBoolean();
        this.mWorker = (WorkerRunnable<Params, Result>)new WorkerRunnable<Params, Result>() {
            @Override
            public Result call() throws Exception {
                AsyncTaskCompat.this.mTaskInvoked.set(true);
                Process.setThreadPriority(10);
                return (Result)AsyncTaskCompat.this.postResult(AsyncTaskCompat.this.doInBackground(this.mParams));
            }
        };
        this.mFuture = new FutureTask<Result>(this.mWorker) {
            @Override
            protected void done() {
                try {
                    AsyncTaskCompat.this.postResultIfNotInvoked(((FutureTask<Object>)this).get());
                }
                catch (InterruptedException ex) {
                    Log.w("AsyncTask", (Throwable)ex);
                }
                catch (ExecutionException ex2) {
                    throw new RuntimeException("An error occured while executing doInBackground()", ex2.getCause());
                }
                catch (CancellationException ex3) {
                    AsyncTaskCompat.this.postResultIfNotInvoked(null);
                }
            }
        };
    }
    
    public static void execute(final Runnable runnable) {
        AsyncTaskCompat.sDefaultExecutor.execute(runnable);
    }
    
    private void finish(final Result result) {
        if (this.isCancelled()) {
            this.onCancelled(result);
        }
        else {
            this.onPostExecute(result);
        }
        this.mStatus = Status.FINISHED;
    }
    
    public static void init() {
        AsyncTaskCompat.sHandler.getLooper();
    }
    
    private Result postResult(final Result result) {
        AsyncTaskCompat.sHandler.obtainMessage(1, (Object)new AsyncTaskResult(this, new Object[] { result })).sendToTarget();
        return result;
    }
    
    private void postResultIfNotInvoked(final Result result) {
        if (!this.mTaskInvoked.get()) {
            this.postResult(result);
        }
    }
    
    public static void setDefaultExecutor(final Executor sDefaultExecutor) {
        AsyncTaskCompat.sDefaultExecutor = sDefaultExecutor;
    }
    
    public final boolean cancel(final boolean b) {
        this.mCancelled.set(true);
        return this.mFuture.cancel(b);
    }
    
    protected abstract Result doInBackground(final Params... p0);
    
    public final AsyncTaskCompat<Params, Progress, Result> execute(final Params... array) {
        return this.executeOnExecutor(AsyncTaskCompat.sDefaultExecutor, array);
    }
    
    public final AsyncTaskCompat<Params, Progress, Result> executeOnExecutor(final Executor executor, final Params... mParams) {
        if (this.mStatus != Status.PENDING) {
            switch (this.mStatus) {
                case RUNNING: {
                    throw new IllegalStateException("Cannot execute task: the task is already running.");
                }
                case FINISHED: {
                    throw new IllegalStateException("Cannot execute task: the task has already been executed (a task can be executed only once)");
                }
            }
        }
        this.mStatus = Status.RUNNING;
        this.onPreExecute();
        this.mWorker.mParams = mParams;
        executor.execute(this.mFuture);
        return this;
    }
    
    public final Result get() throws InterruptedException, ExecutionException {
        return this.mFuture.get();
    }
    
    public final Result get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.mFuture.get(n, timeUnit);
    }
    
    public final Status getStatus() {
        return this.mStatus;
    }
    
    public final boolean isCancelled() {
        return this.mCancelled.get();
    }
    
    protected void onCancelled() {
    }
    
    protected void onCancelled(final Result result) {
        this.onCancelled();
    }
    
    protected void onPostExecute(final Result result) {
    }
    
    protected void onPreExecute() {
    }
    
    protected void onProgressUpdate(final Progress... array) {
    }
    
    protected final void publishProgress(final Progress... array) {
        if (!this.isCancelled()) {
            AsyncTaskCompat.sHandler.obtainMessage(2, (Object)new AsyncTaskResult(this, (Object[])array)).sendToTarget();
        }
    }
    
    private static class AsyncTaskResult<Data>
    {
        final Data[] mData;
        final AsyncTaskCompat mTask;
        
        AsyncTaskResult(final AsyncTaskCompat mTask, final Data... mData) {
            this.mTask = mTask;
            this.mData = mData;
        }
    }
    
    private static class InternalHandler extends Handler
    {
        public void handleMessage(final Message message) {
            final AsyncTaskResult asyncTaskResult = (AsyncTaskResult)message.obj;
            switch (message.what) {
                default: {}
                case 1: {
                    asyncTaskResult.mTask.finish(asyncTaskResult.mData[0]);
                }
                case 2: {
                    asyncTaskResult.mTask.onProgressUpdate(asyncTaskResult.mData);
                }
            }
        }
    }
    
    public enum Status
    {
        FINISHED, 
        PENDING, 
        RUNNING;
    }
    
    private abstract static class WorkerRunnable<Params, Result> implements Callable<Result>
    {
        Params[] mParams;
    }
}
