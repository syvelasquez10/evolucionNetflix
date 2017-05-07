// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzlk$zza implements ThreadFactory
{
    private final ThreadFactory zzacE;
    private AtomicInteger zzacF;
    
    private zzlk$zza() {
        this.zzacE = Executors.defaultThreadFactory();
        this.zzacF = new AtomicInteger(0);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        final Thread thread = this.zzacE.newThread(runnable);
        thread.setName("GAC_Executor[" + this.zzacF.getAndIncrement() + "]");
        return thread;
    }
}
