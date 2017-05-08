// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.bridge;

import android.os.Looper;
import android.os.Handler;

public class UiThreadUtil
{
    private static Handler sMainHandler;
    
    public static void assertOnUiThread() {
        SoftAssertions.assertCondition(isOnUiThread(), "Expected to run on UI thread!");
    }
    
    public static boolean isOnUiThread() {
        return Looper.getMainLooper().getThread() == Thread.currentThread();
    }
    
    public static void runOnUiThread(final Runnable runnable) {
        synchronized (UiThreadUtil.class) {
            if (UiThreadUtil.sMainHandler == null) {
                UiThreadUtil.sMainHandler = new Handler(Looper.getMainLooper());
            }
            // monitorexit(UiThreadUtil.class)
            UiThreadUtil.sMainHandler.post(runnable);
        }
    }
}
