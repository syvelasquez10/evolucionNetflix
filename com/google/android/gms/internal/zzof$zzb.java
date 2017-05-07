// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

class zzof$zzb implements ThreadFactory
{
    private static final AtomicInteger zzaIl;
    
    static {
        zzaIl = new AtomicInteger();
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new zzof$zzc(runnable, "measurement-" + zzof$zzb.zzaIl.incrementAndGet());
    }
}
