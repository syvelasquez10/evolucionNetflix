// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.Future;

@ez
public class gl<T> implements Future<T>
{
    private final T wq;
    
    public gl(final T wq) {
        this.wq = wq;
    }
    
    @Override
    public boolean cancel(final boolean b) {
        return false;
    }
    
    @Override
    public T get() {
        return this.wq;
    }
    
    @Override
    public T get(final long n, final TimeUnit timeUnit) {
        return this.wq;
    }
    
    @Override
    public boolean isCancelled() {
        return false;
    }
    
    @Override
    public boolean isDone() {
        return true;
    }
}
