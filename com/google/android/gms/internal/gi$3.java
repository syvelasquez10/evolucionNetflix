// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class gi$3 implements ThreadFactory
{
    private final AtomicInteger wl;
    
    gi$3() {
        this.wl = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "AdWorker #" + this.wl.getAndIncrement());
    }
}
