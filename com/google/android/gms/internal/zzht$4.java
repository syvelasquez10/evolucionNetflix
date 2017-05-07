// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class zzht$4 implements ThreadFactory
{
    private final AtomicInteger zzHC;
    final /* synthetic */ String zzHD;
    
    zzht$4(final String zzHD) {
        this.zzHD = zzHD;
        this.zzHC = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "AdWorker(" + this.zzHD + ") #" + this.zzHC.getAndIncrement());
    }
}
