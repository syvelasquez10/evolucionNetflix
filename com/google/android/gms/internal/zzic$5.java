// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzic$5 implements ThreadFactory
{
    private final AtomicInteger zzIw;
    final /* synthetic */ String zzIx;
    
    zzic$5(final String zzIx) {
        this.zzIx = zzIx;
        this.zzIw = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "AdWorker(" + this.zzIx + ") #" + this.zzIw.getAndIncrement());
    }
}
