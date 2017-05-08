// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.ExecutorService;
import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.Executor;

final class AndroidExecutors
{
    static final int CORE_POOL_SIZE;
    private static final int CPU_COUNT;
    private static final AndroidExecutors INSTANCE;
    static final int MAX_POOL_SIZE;
    private final Executor uiThread;
    
    static {
        INSTANCE = new AndroidExecutors();
        CPU_COUNT = Runtime.getRuntime().availableProcessors();
        CORE_POOL_SIZE = AndroidExecutors.CPU_COUNT + 1;
        MAX_POOL_SIZE = AndroidExecutors.CPU_COUNT * 2 + 1;
    }
    
    private AndroidExecutors() {
        this.uiThread = new AndroidExecutors$UIThreadExecutor(null);
    }
    
    @SuppressLint({ "NewApi" })
    public static void allowCoreThreadTimeout(final ThreadPoolExecutor threadPoolExecutor, final boolean b) {
        if (Build$VERSION.SDK_INT >= 9) {
            threadPoolExecutor.allowCoreThreadTimeOut(b);
        }
    }
    
    public static ExecutorService newCachedThreadPool() {
        final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(AndroidExecutors.CORE_POOL_SIZE, AndroidExecutors.MAX_POOL_SIZE, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>());
        allowCoreThreadTimeout(threadPoolExecutor, true);
        return threadPoolExecutor;
    }
    
    public static Executor uiThread() {
        return AndroidExecutors.INSTANCE.uiThread;
    }
}
