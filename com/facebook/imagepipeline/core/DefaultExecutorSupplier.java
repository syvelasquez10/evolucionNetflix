// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.core;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.Executors;
import java.util.concurrent.Executor;

public class DefaultExecutorSupplier implements ExecutorSupplier
{
    private final Executor mBackgroundExecutor;
    private final Executor mDecodeExecutor;
    private final Executor mIoBoundExecutor;
    private final Executor mLightWeightBackgroundExecutor;
    
    public DefaultExecutorSupplier(final int n) {
        final PriorityThreadFactory priorityThreadFactory = new PriorityThreadFactory(10);
        this.mIoBoundExecutor = Executors.newFixedThreadPool(2);
        this.mDecodeExecutor = Executors.newFixedThreadPool(n, priorityThreadFactory);
        this.mBackgroundExecutor = Executors.newFixedThreadPool(n, priorityThreadFactory);
        this.mLightWeightBackgroundExecutor = Executors.newFixedThreadPool(1, priorityThreadFactory);
    }
    
    @Override
    public Executor forBackgroundTasks() {
        return this.mBackgroundExecutor;
    }
    
    @Override
    public Executor forDecode() {
        return this.mDecodeExecutor;
    }
    
    @Override
    public Executor forLightweightBackgroundTasks() {
        return this.mLightWeightBackgroundExecutor;
    }
    
    @Override
    public Executor forLocalStorageRead() {
        return this.mIoBoundExecutor;
    }
    
    @Override
    public Executor forLocalStorageWrite() {
        return this.mIoBoundExecutor;
    }
}
