// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.concurrent.CountDownLatch;
import android.support.v4.util.TimeUtils;
import java.io.PrintWriter;
import java.io.FileDescriptor;
import android.os.SystemClock;
import android.content.Context;
import android.os.Handler;

public abstract class AsyncTaskLoader<D> extends Loader<D>
{
    static final boolean DEBUG = false;
    static final String TAG = "AsyncTaskLoader";
    volatile LoadTask mCancellingTask;
    Handler mHandler;
    long mLastLoadCompleteTime;
    volatile LoadTask mTask;
    long mUpdateThrottle;
    
    public AsyncTaskLoader(final Context context) {
        super(context);
        this.mLastLoadCompleteTime = -10000L;
    }
    
    public boolean cancelLoad() {
        if (this.mTask != null) {
            if (this.mCancellingTask != null) {
                if (this.mTask.waiting) {
                    this.mTask.waiting = false;
                    this.mHandler.removeCallbacks((Runnable)this.mTask);
                }
                this.mTask = null;
            }
            else {
                if (this.mTask.waiting) {
                    this.mTask.waiting = false;
                    this.mHandler.removeCallbacks((Runnable)this.mTask);
                    this.mTask = null;
                    return false;
                }
                final boolean cancel = this.mTask.cancel(false);
                if (cancel) {
                    this.mCancellingTask = this.mTask;
                }
                this.mTask = null;
                return cancel;
            }
        }
        return false;
    }
    
    void dispatchOnCancelled(final LoadTask loadTask, final D n) {
        this.onCanceled(n);
        if (this.mCancellingTask == loadTask) {
            this.rollbackContentChanged();
            this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
            this.mCancellingTask = null;
            this.executePendingTask();
        }
    }
    
    void dispatchOnLoadComplete(final LoadTask loadTask, final D n) {
        if (this.mTask != loadTask) {
            this.dispatchOnCancelled(loadTask, n);
            return;
        }
        if (this.isAbandoned()) {
            this.onCanceled(n);
            return;
        }
        this.commitContentChanged();
        this.mLastLoadCompleteTime = SystemClock.uptimeMillis();
        this.mTask = null;
        this.deliverResult(n);
    }
    
    @Override
    public void dump(final String s, final FileDescriptor fileDescriptor, final PrintWriter printWriter, final String[] array) {
        super.dump(s, fileDescriptor, printWriter, array);
        if (this.mTask != null) {
            printWriter.print(s);
            printWriter.print("mTask=");
            printWriter.print(this.mTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mTask.waiting);
        }
        if (this.mCancellingTask != null) {
            printWriter.print(s);
            printWriter.print("mCancellingTask=");
            printWriter.print(this.mCancellingTask);
            printWriter.print(" waiting=");
            printWriter.println(this.mCancellingTask.waiting);
        }
        if (this.mUpdateThrottle != 0L) {
            printWriter.print(s);
            printWriter.print("mUpdateThrottle=");
            TimeUtils.formatDuration(this.mUpdateThrottle, printWriter);
            printWriter.print(" mLastLoadCompleteTime=");
            TimeUtils.formatDuration(this.mLastLoadCompleteTime, SystemClock.uptimeMillis(), printWriter);
            printWriter.println();
        }
    }
    
    void executePendingTask() {
        if (this.mCancellingTask == null && this.mTask != null) {
            if (this.mTask.waiting) {
                this.mTask.waiting = false;
                this.mHandler.removeCallbacks((Runnable)this.mTask);
            }
            if (this.mUpdateThrottle <= 0L || SystemClock.uptimeMillis() >= this.mLastLoadCompleteTime + this.mUpdateThrottle) {
                ((ModernAsyncTask<Void, Object, Object>)this.mTask).executeOnExecutor(ModernAsyncTask.THREAD_POOL_EXECUTOR, (Void[])null);
                return;
            }
            this.mTask.waiting = true;
            this.mHandler.postAtTime((Runnable)this.mTask, this.mLastLoadCompleteTime + this.mUpdateThrottle);
        }
    }
    
    public abstract D loadInBackground();
    
    public void onCanceled(final D n) {
    }
    
    @Override
    protected void onForceLoad() {
        super.onForceLoad();
        this.cancelLoad();
        this.mTask = new LoadTask();
        this.executePendingTask();
    }
    
    protected D onLoadInBackground() {
        return this.loadInBackground();
    }
    
    public void setUpdateThrottle(final long mUpdateThrottle) {
        this.mUpdateThrottle = mUpdateThrottle;
        if (mUpdateThrottle != 0L) {
            this.mHandler = new Handler();
        }
    }
    
    public void waitForLoader() {
        final LoadTask mTask = this.mTask;
        if (mTask == null) {
            return;
        }
        try {
            mTask.done.await();
        }
        catch (InterruptedException ex) {}
    }
    
    final class LoadTask extends ModernAsyncTask<Void, Void, D> implements Runnable
    {
        private CountDownLatch done;
        D result;
        boolean waiting;
        
        LoadTask() {
            this.done = new CountDownLatch(1);
        }
        
        @Override
        protected D doInBackground(final Void... array) {
            return this.result = AsyncTaskLoader.this.onLoadInBackground();
        }
        
        @Override
        protected void onCancelled() {
            try {
                AsyncTaskLoader.this.dispatchOnCancelled(this, this.result);
            }
            finally {
                this.done.countDown();
            }
        }
        
        @Override
        protected void onPostExecute(final D n) {
            try {
                AsyncTaskLoader.this.dispatchOnLoadComplete(this, n);
            }
            finally {
                this.done.countDown();
            }
        }
        
        @Override
        public void run() {
            this.waiting = false;
            AsyncTaskLoader.this.executePendingTask();
        }
    }
}
