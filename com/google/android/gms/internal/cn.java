// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.RejectedExecutionException;
import android.os.Process;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;

public final class cn
{
    private static final ThreadPoolExecutor iA;
    private static final ThreadFactory iz;
    
    static {
        iz = new ThreadFactory() {
            private final AtomicInteger iC = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "AdWorker #" + this.iC.getAndIncrement());
            }
        };
        iA = new ThreadPoolExecutor(0, 10, 65L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(true), cn.iz);
    }
    
    public static void execute(final Runnable runnable) {
        try {
            cn.iA.execute(new Runnable() {
                @Override
                public void run() {
                    Process.setThreadPriority(10);
                    runnable.run();
                }
            });
        }
        catch (RejectedExecutionException ex) {
            ct.b("Too many background threads already running. Aborting task.", ex);
        }
    }
}
