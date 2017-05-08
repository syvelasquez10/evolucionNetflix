// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.logging.FLog;

public abstract class BaseConsumer<T> implements Consumer<T>
{
    private boolean mIsFinished;
    
    public BaseConsumer() {
        this.mIsFinished = false;
    }
    
    @Override
    public void onCancellation() {
        synchronized (this) {
            if (!this.mIsFinished) {
                this.mIsFinished = true;
                try {
                    this.onCancellationImpl();
                }
                catch (Exception ex) {
                    this.onUnhandledException(ex);
                }
            }
        }
    }
    
    protected abstract void onCancellationImpl();
    
    @Override
    public void onFailure(final Throwable t) {
        synchronized (this) {
            if (!this.mIsFinished) {
                this.mIsFinished = true;
                try {
                    this.onFailureImpl(t);
                }
                catch (Exception ex) {
                    this.onUnhandledException(ex);
                }
            }
        }
    }
    
    protected abstract void onFailureImpl(final Throwable p0);
    
    @Override
    public void onNewResult(final T t, final boolean mIsFinished) {
        synchronized (this) {
            if (!this.mIsFinished) {
                this.mIsFinished = mIsFinished;
                try {
                    this.onNewResultImpl(t, mIsFinished);
                }
                catch (Exception ex) {
                    this.onUnhandledException(ex);
                }
            }
        }
    }
    
    protected abstract void onNewResultImpl(final T p0, final boolean p1);
    
    @Override
    public void onProgressUpdate(final float n) {
        synchronized (this) {
            if (!this.mIsFinished) {
                try {
                    this.onProgressUpdateImpl(n);
                }
                catch (Exception ex) {
                    this.onUnhandledException(ex);
                }
            }
        }
    }
    
    protected void onProgressUpdateImpl(final float n) {
    }
    
    protected void onUnhandledException(final Exception ex) {
        FLog.wtf(this.getClass(), "unhandled exception", ex);
    }
}
