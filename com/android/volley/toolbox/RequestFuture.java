// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.ExecutionException;
import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import java.util.concurrent.Future;

public class RequestFuture<T> implements Future<T>, Listener<T>, ErrorListener
{
    private VolleyError mException;
    private Request<?> mRequest;
    private T mResult;
    private boolean mResultReceived;
    
    private RequestFuture() {
        this.mResultReceived = false;
    }
    
    private T doGet(final Long n) throws InterruptedException, ExecutionException, TimeoutException {
        synchronized (this) {
            if (this.mException != null) {
                throw new ExecutionException(this.mException);
            }
        }
        T t;
        if (this.mResultReceived) {
            t = this.mResult;
        }
        else {
            final Long n2;
            if (n2 == null) {
                this.wait(0L);
            }
            else if (n2 > 0L) {
                this.wait(n2);
            }
            if (this.mException != null) {
                throw new ExecutionException(this.mException);
            }
            if (!this.mResultReceived) {
                throw new TimeoutException();
            }
            t = this.mResult;
        }
        // monitorexit(this)
        return t;
    }
    
    public static <E> RequestFuture<E> newFuture() {
        return new RequestFuture<E>();
    }
    
    @Override
    public boolean cancel(final boolean b) {
        boolean b2 = false;
        synchronized (this) {
            if (this.mRequest != null && !this.isDone()) {
                this.mRequest.cancel();
                b2 = true;
            }
            return b2;
        }
    }
    
    @Override
    public T get() throws InterruptedException, ExecutionException {
        try {
            return this.doGet(null);
        }
        catch (TimeoutException ex) {
            throw new AssertionError((Object)ex);
        }
    }
    
    @Override
    public T get(final long n, final TimeUnit timeUnit) throws InterruptedException, ExecutionException, TimeoutException {
        return this.doGet(TimeUnit.MILLISECONDS.convert(n, timeUnit));
    }
    
    @Override
    public boolean isCancelled() {
        return this.mRequest != null && this.mRequest.isCanceled();
    }
    
    @Override
    public boolean isDone() {
        synchronized (this) {
            return this.mResultReceived || this.mException != null || this.isCancelled();
        }
    }
    
    @Override
    public void onErrorResponse(final VolleyError mException) {
        synchronized (this) {
            this.mException = mException;
            this.notifyAll();
        }
    }
    
    @Override
    public void onResponse(final T mResult) {
        synchronized (this) {
            this.mResultReceived = true;
            this.mResult = mResult;
            this.notifyAll();
        }
    }
    
    public void setRequest(final Request<?> mRequest) {
        this.mRequest = mRequest;
    }
}
