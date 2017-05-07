// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@zzgk
public final class zzht
{
    private static final ExecutorService zzHy;
    private static final ExecutorService zzHz;
    
    static {
        zzHy = Executors.newFixedThreadPool(10, zzav("Default"));
        zzHz = Executors.newFixedThreadPool(5, zzav("Loader"));
    }
    
    public static zzih<Void> zza(final int n, final Runnable runnable) {
        if (n == 1) {
            return zza(zzht.zzHz, (Callable<Void>)new zzht$1(runnable));
        }
        return zza(zzht.zzHy, (Callable<Void>)new zzht$2(runnable));
    }
    
    public static zzih<Void> zza(final Runnable runnable) {
        return zza(0, runnable);
    }
    
    public static <T> zzih<T> zza(final ExecutorService executorService, final Callable<T> callable) {
        final zzie<T> zzie = new zzie<T>();
        try {
            executorService.submit(new zzht$3(zzie, callable));
            return zzie;
        }
        catch (RejectedExecutionException ex) {
            zzb.zzd("Thread execution is rejected.", ex);
            zzie.cancel(true);
            return zzie;
        }
    }
    
    private static ThreadFactory zzav(final String s) {
        return new zzht$4(s);
    }
}
