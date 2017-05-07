// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.logging;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class LoggingAgent$1 implements ThreadFactory
{
    private final AtomicInteger mCount;
    
    LoggingAgent$1() {
        this.mCount = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "LoggingAgent #" + this.mCount.getAndIncrement());
    }
}
