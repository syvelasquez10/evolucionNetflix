// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.executors;

import java.util.concurrent.Future;
import java.util.List;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.RunnableFuture;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import android.os.Handler;
import java.util.concurrent.AbstractExecutorService;

public class HandlerExecutorServiceImpl extends AbstractExecutorService implements HandlerExecutorService
{
    private final Handler mHandler;
    
    public HandlerExecutorServiceImpl(final Handler mHandler) {
        this.mHandler = mHandler;
    }
    
    @Override
    public boolean awaitTermination(final long n, final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void execute(final Runnable runnable) {
        this.mHandler.post(runnable);
    }
    
    public boolean isHandlerThread() {
        return Thread.currentThread() == this.mHandler.getLooper().getThread();
    }
    
    @Override
    public boolean isShutdown() {
        return false;
    }
    
    @Override
    public boolean isTerminated() {
        return false;
    }
    
    @Override
    protected <T> ScheduledFutureImpl<T> newTaskFor(final Runnable runnable, final T t) {
        return new ScheduledFutureImpl<T>(this.mHandler, runnable, t);
    }
    
    @Override
    protected <T> ScheduledFutureImpl<T> newTaskFor(final Callable<T> callable) {
        return new ScheduledFutureImpl<T>(this.mHandler, callable);
    }
    
    @Override
    public ScheduledFuture<?> schedule(final Runnable runnable, final long n, final TimeUnit timeUnit) {
        final ScheduledFutureImpl<Object> task = this.newTaskFor(runnable, (Object)null);
        this.mHandler.postDelayed((Runnable)task, timeUnit.toMillis(n));
        return task;
    }
    
    @Override
    public <V> ScheduledFuture<V> schedule(final Callable<V> callable, final long n, final TimeUnit timeUnit) {
        final ScheduledFutureImpl<V> task = this.newTaskFor(callable);
        this.mHandler.postDelayed((Runnable)task, timeUnit.toMillis(n));
        return task;
    }
    
    @Override
    public ScheduledFuture<?> scheduleAtFixedRate(final Runnable runnable, final long n, final long n2, final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ScheduledFuture<?> scheduleWithFixedDelay(final Runnable runnable, final long n, final long n2, final TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void shutdown() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public ScheduledFuture<?> submit(final Runnable runnable) {
        return this.submit(runnable, (Object)null);
    }
    
    @Override
    public <T> ScheduledFuture<T> submit(final Runnable runnable, final T t) {
        if (runnable == null) {
            throw new NullPointerException();
        }
        final ScheduledFutureImpl<T> task = this.newTaskFor(runnable, t);
        this.execute(task);
        return task;
    }
    
    @Override
    public <T> ScheduledFuture<T> submit(final Callable<T> callable) {
        if (callable == null) {
            throw new NullPointerException();
        }
        final ScheduledFutureImpl<T> task = this.newTaskFor(callable);
        this.execute(task);
        return task;
    }
}
