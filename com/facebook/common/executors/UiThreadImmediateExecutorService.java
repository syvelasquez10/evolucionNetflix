// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.executors;

import android.os.Handler;
import android.os.Looper;

public class UiThreadImmediateExecutorService extends HandlerExecutorServiceImpl
{
    private static UiThreadImmediateExecutorService sInstance;
    
    static {
        UiThreadImmediateExecutorService.sInstance = null;
    }
    
    private UiThreadImmediateExecutorService() {
        super(new Handler(Looper.getMainLooper()));
    }
    
    public static UiThreadImmediateExecutorService getInstance() {
        if (UiThreadImmediateExecutorService.sInstance == null) {
            UiThreadImmediateExecutorService.sInstance = new UiThreadImmediateExecutorService();
        }
        return UiThreadImmediateExecutorService.sInstance;
    }
    
    @Override
    public void execute(final Runnable runnable) {
        if (this.isHandlerThread()) {
            runnable.run();
            return;
        }
        super.execute(runnable);
    }
}
