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
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;

public final class dp
{
    private static final ThreadFactory ra;
    private static final ThreadPoolExecutor rb;
    
    static {
        ra = new ThreadFactory() {
            private final AtomicInteger rd = new AtomicInteger(1);
            
            @Override
            public Thread newThread(final Runnable runnable) {
                return new Thread(runnable, "AdWorker #" + this.rd.getAndIncrement());
            }
        };
        rb = new ThreadPoolExecutor(0, 10, 65L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(true), dp.ra);
    }
    
    public static void execute(final Runnable runnable) {
        try {
            dp.rb.execute(new Runnable() {
                @Override
                public void run() {
                    Process.setThreadPriority(10);
                    runnable.run();
                }
            });
        }
        catch (RejectedExecutionException ex) {
            dw.c("Too many background threads already running. Aborting task.  Current pool size: " + getPoolSize(), ex);
        }
    }
    
    public static int getPoolSize() {
        return dp.rb.getPoolSize();
    }
}
