// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common.futures;

import java.util.concurrent.TimeoutException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Future;

public class SimpleSettableFuture<T> implements Future<T>
{
    private Exception mException;
    private final CountDownLatch mReadyLatch;
    private T mResult;
    
    public SimpleSettableFuture() {
        this.mReadyLatch = new CountDownLatch(1);
    }
    
    private void checkNotSet() {
        if (this.mReadyLatch.getCount() == 0L) {
            throw new RuntimeException("Result has already been set!");
        }
    }
    
    @Override
    public boolean cancel(final boolean b) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public T get() {
        this.mReadyLatch.await();
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        return this.mResult;
    }
    
    @Override
    public T get(final long n, final TimeUnit timeUnit) {
        if (!this.mReadyLatch.await(n, timeUnit)) {
            throw new TimeoutException("Timed out waiting for result");
        }
        if (this.mException != null) {
            throw new ExecutionException(this.mException);
        }
        return this.mResult;
    }
    
    public T getOrThrow() {
        try {
            return this.get();
        }
        catch (InterruptedException ex) {}
        catch (ExecutionException value) {
            goto Label_0008;
        }
    }
    
    @Override
    public boolean isCancelled() {
        return false;
    }
    
    @Override
    public boolean isDone() {
        return this.mReadyLatch.getCount() == 0L;
    }
    
    public void set(final T mResult) {
        this.checkNotSet();
        this.mResult = mResult;
        this.mReadyLatch.countDown();
    }
    
    public void setException(final Exception mException) {
        this.checkNotSet();
        this.mException = mException;
        this.mReadyLatch.countDown();
    }
}
