// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.service.voip;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class WhistleVoipAgent$6 implements ThreadFactory
{
    private final AtomicInteger mCount;
    
    WhistleVoipAgent$6() {
        this.mCount = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "VoipTask #" + this.mCount.getAndIncrement());
    }
}
