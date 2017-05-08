// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.executors;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.AbstractExecutorService;

public class CallerThreadExecutor extends AbstractExecutorService
{
    private static final CallerThreadExecutor sInstance;
    
    static {
        sInstance = new CallerThreadExecutor();
    }
    
    public static CallerThreadExecutor getInstance() {
        return CallerThreadExecutor.sInstance;
    }
    
    @Override
    public boolean awaitTermination(final long n, final TimeUnit timeUnit) {
        return true;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        runnable.run();
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
    public void shutdown() {
    }
    
    @Override
    public List<Runnable> shutdownNow() {
        this.shutdown();
        return Collections.emptyList();
    }
}
