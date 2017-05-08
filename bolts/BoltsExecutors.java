// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import java.util.Locale;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

final class BoltsExecutors
{
    private static final BoltsExecutors INSTANCE;
    private final ExecutorService background;
    private final Executor immediate;
    private final ScheduledExecutorService scheduled;
    
    static {
        INSTANCE = new BoltsExecutors();
    }
    
    private BoltsExecutors() {
        ExecutorService background;
        if (!isAndroidRuntime()) {
            background = Executors.newCachedThreadPool();
        }
        else {
            background = AndroidExecutors.newCachedThreadPool();
        }
        this.background = background;
        this.scheduled = Executors.newSingleThreadScheduledExecutor();
        this.immediate = new BoltsExecutors$ImmediateExecutor(null);
    }
    
    public static ExecutorService background() {
        return BoltsExecutors.INSTANCE.background;
    }
    
    static Executor immediate() {
        return BoltsExecutors.INSTANCE.immediate;
    }
    
    private static boolean isAndroidRuntime() {
        final String property = System.getProperty("java.runtime.name");
        return property != null && property.toLowerCase(Locale.US).contains("android");
    }
}
