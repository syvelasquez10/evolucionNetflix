// 
// Decompiled by Procyon v0.5.30
// 

package bolts;

import android.os.Handler;
import android.os.Looper;
import java.util.concurrent.Executor;

class AndroidExecutors$UIThreadExecutor implements Executor
{
    @Override
    public void execute(final Runnable runnable) {
        new Handler(Looper.getMainLooper()).post(runnable);
    }
}
