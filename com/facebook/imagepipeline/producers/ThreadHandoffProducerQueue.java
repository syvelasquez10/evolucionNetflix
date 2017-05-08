// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.ArrayDeque;
import com.facebook.common.internal.Preconditions;
import java.util.Deque;
import java.util.concurrent.Executor;

public class ThreadHandoffProducerQueue
{
    private final Executor mExecutor;
    private boolean mQueueing;
    private final Deque<Runnable> mRunnableList;
    
    public ThreadHandoffProducerQueue(final Executor executor) {
        this.mQueueing = false;
        this.mExecutor = Preconditions.checkNotNull(executor);
        this.mRunnableList = new ArrayDeque<Runnable>();
    }
    
    public void addToQueueOrExecute(final Runnable runnable) {
        synchronized (this) {
            if (this.mQueueing) {
                this.mRunnableList.add(runnable);
            }
            else {
                this.mExecutor.execute(runnable);
            }
        }
    }
    
    public void remove(final Runnable runnable) {
        synchronized (this) {
            this.mRunnableList.remove(runnable);
        }
    }
}
