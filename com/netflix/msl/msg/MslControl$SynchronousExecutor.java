// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.AbstractExecutorService;

class MslControl$SynchronousExecutor extends AbstractExecutorService
{
    private boolean shutdown;
    
    private MslControl$SynchronousExecutor() {
        this.shutdown = false;
    }
    
    @Override
    public boolean awaitTermination(final long n, final TimeUnit timeUnit) {
        return false;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        if (this.shutdown) {
            throw new RejectedExecutionException("Synchronous executor already shut down.");
        }
        runnable.run();
    }
    
    @Override
    public boolean isShutdown() {
        return this.shutdown;
    }
    
    @Override
    public boolean isTerminated() {
        return this.shutdown;
    }
    
    @Override
    public void shutdown() {
        this.shutdown = true;
    }
    
    @Override
    public List<Runnable> shutdownNow() {
        this.shutdown = true;
        return Collections.emptyList();
    }
}
