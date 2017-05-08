// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache;

import android.os.Looper;
import android.os.Handler;

class FalkorCache$LruBackup$LruLooper extends Thread
{
    private static final int EVICT_LIST = 1;
    private Handler handler;
    
    private FalkorCache$LruBackup$LruLooper() {
        super("FalkorCache.LruBackup");
    }
    
    @Override
    public void run() {
        Looper.prepare();
        this.handler = new FalkorCache$LruBackup$LruHandler(null);
        Looper.loop();
    }
}
