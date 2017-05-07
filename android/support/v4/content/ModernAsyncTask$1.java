// 
// Decompiled by Procyon v0.5.30
// 

package android.support.v4.content;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.ThreadFactory;

final class ModernAsyncTask$1 implements ThreadFactory
{
    private final AtomicInteger mCount;
    
    ModernAsyncTask$1() {
        this.mCount = new AtomicInteger(1);
    }
    
    @Override
    public Thread newThread(final Runnable runnable) {
        return new Thread(runnable, "ModernAsyncTask #" + this.mCount.getAndIncrement());
    }
}
