// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class Settings$1 implements ThreadFactory
{
    private final AtomicInteger counter;
    
    Settings$1() {
        this.counter = new AtomicInteger(0);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "FacebookSdk #" + this.counter.incrementAndGet());
    }
}
