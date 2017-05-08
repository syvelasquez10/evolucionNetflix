// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.executors;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Delayed;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;
import android.os.Handler;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.RunnableFuture;

public class ScheduledFutureImpl<V> implements RunnableFuture<V>, ScheduledFuture<V>
{
    private final Handler mHandler;
    private final FutureTask<V> mListenableFuture;
    
    public ScheduledFutureImpl(final Handler mHandler, final Runnable runnable, final V v) {
        this.mHandler = mHandler;
        this.mListenableFuture = new FutureTask<V>(runnable, v);
    }
    
    public ScheduledFutureImpl(final Handler mHandler, final Callable<V> callable) {
        this.mHandler = mHandler;
        this.mListenableFuture = new FutureTask<V>(callable);
    }
    
    @Override
    public boolean cancel(final boolean b) {
        return this.mListenableFuture.cancel(b);
    }
    
    @Override
    public int compareTo(final Delayed delayed) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public V get() {
        return this.mListenableFuture.get();
    }
    
    @Override
    public V get(final long n, final TimeUnit timeUnit) {
        return this.mListenableFuture.get(n, timeUnit);
    }
    
    @Override
    public long getDelay(final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public boolean isCancelled() {
        return this.mListenableFuture.isCancelled();
    }
    
    @Override
    public boolean isDone() {
        return this.mListenableFuture.isDone();
    }
    
    @Override
    public void run() {
        this.mListenableFuture.run();
    }
}
