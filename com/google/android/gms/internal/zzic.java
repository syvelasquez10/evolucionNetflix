// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.RejectedExecutionException;
import com.google.android.gms.ads.internal.util.client.zzb;
import java.util.concurrent.Future;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ExecutorService;

@zzgr
public final class zzic
{
    private static final ExecutorService zzIr;
    private static final ExecutorService zzIs;
    
    static {
        zzIr = Executors.newFixedThreadPool(10, zzay("Default"));
        zzIs = Executors.newFixedThreadPool(5, zzay("Loader"));
    }
    
    public static zziq<Void> zza(final int n, final Runnable runnable) {
        if (n == 1) {
            return zza(zzic.zzIs, (Callable<Void>)new zzic$1(runnable));
        }
        return zza(zzic.zzIr, (Callable<Void>)new zzic$2(runnable));
    }
    
    public static zziq<Void> zza(final Runnable runnable) {
        return zza(0, runnable);
    }
    
    public static <T> zziq<T> zza(final ExecutorService executorService, final Callable<T> callable) {
        final zzin<T> zzin = new zzin<T>();
        try {
            zzin.zzd(new zzic$4(zzin, executorService.submit(new zzic$3(zzin, callable))));
            return zzin;
        }
        catch (RejectedExecutionException ex) {
            zzb.zzd("Thread execution is rejected.", ex);
            zzin.cancel(true);
            return zzin;
        }
    }
    
    private static ThreadFactory zzay(final String s) {
        return new zzic$5(s);
    }
}
