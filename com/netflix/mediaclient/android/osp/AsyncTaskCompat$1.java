// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.android.osp;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class AsyncTaskCompat$1 implements ThreadFactory
{
    private final AtomicInteger mCount;
    
    AsyncTaskCompat$1() {
        this.mCount = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = new Thread(runnable, "AsyncTask #" + this.mCount.getAndIncrement());
        thread.setPriority(5);
        return thread;
    }
}
