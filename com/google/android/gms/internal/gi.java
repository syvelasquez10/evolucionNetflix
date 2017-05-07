// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.RejectedExecutionException;
import android.os.Process;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadFactory;

@ez
public final class gi
{
    private static final ThreadFactory wh;
    private static final ExecutorService wi;
    
    static {
        wh = new ThreadFactory() {
            private final AtomicInteger wl = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "AdWorker #" + this.wl.getAndIncrement());
            }
        };
        wi = Executors.newFixedThreadPool(10, gi.wh);
    }
    
    public static Future<Void> a(final Runnable runnable) {
        return submit((Callable<Void>)new Callable<Void>() {
            public Void dk() {
                runnable.run();
                return null;
            }
        });
    }
    
    public static <T> Future<T> submit(final Callable<T> callable) {
        try {
            return gi.wi.submit((Callable<T>)new Callable<T>() {
                @Override
                public T call() throws Exception {
                    try {
                        Process.setThreadPriority(10);
                        return callable.call();
                    }
                    catch (Exception ex) {
                        gb.e(ex);
                        return null;
                    }
                }
            });
        }
        catch (RejectedExecutionException ex) {
            gs.d("Thread execution is rejected.", ex);
            return new gl<T>(null);
        }
    }
}
