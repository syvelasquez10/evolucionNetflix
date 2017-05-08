// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.executors;

import java.util.concurrent.atomic.AtomicInteger;

public abstract class StatefulRunnable<T> implements Runnable
{
    protected final AtomicInteger mState;
    
    public StatefulRunnable() {
        this.mState = new AtomicInteger(0);
    }
    
    public void cancel() {
        if (this.mState.compareAndSet(0, 2)) {
            this.onCancellation();
        }
    }
    
    protected void disposeResult(final T t) {
    }
    
    protected abstract T getResult();
    
    protected void onCancellation() {
    }
    
    protected void onFailure(final Exception ex) {
    }
    
    protected void onSuccess(final T t) {
    }
    
    @Override
    public final void run() {
        if (!this.mState.compareAndSet(0, 1)) {
            return;
        }
        Exception result = null;
        try {
            result = this.getResult();
            this.mState.set(3);
            final StatefulRunnable statefulRunnable = this;
            final Exception ex = result;
            statefulRunnable.onSuccess((T)ex);
            return;
        }
        catch (Exception result) {
            this.mState.set(4);
            this.onFailure(result);
            return;
        }
        try {
            final StatefulRunnable statefulRunnable = this;
            final Exception ex = result;
            statefulRunnable.onSuccess((T)ex);
        }
        finally {
            this.disposeResult((T)result);
        }
    }
}
