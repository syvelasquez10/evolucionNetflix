// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import android.os.Build$VERSION;
import android.app.ActivityManager;
import com.facebook.common.internal.Supplier;

public class DefaultBitmapMemoryCacheParamsSupplier implements Supplier<MemoryCacheParams>
{
    private final ActivityManager mActivityManager;
    
    public DefaultBitmapMemoryCacheParamsSupplier(final ActivityManager mActivityManager) {
        this.mActivityManager = mActivityManager;
    }
    
    private int getMaxCacheSize() {
        final int min = Math.min(this.mActivityManager.getMemoryClass() * 1048576, Integer.MAX_VALUE);
        if (min < 33554432) {
            return 4194304;
        }
        if (min < 67108864) {
            return 6291456;
        }
        if (Build$VERSION.SDK_INT < 11) {
            return 8388608;
        }
        return min / 4;
    }
    
    @Override
    public MemoryCacheParams get() {
        return new MemoryCacheParams(this.getMaxCacheSize(), 256, Integer.MAX_VALUE, Integer.MAX_VALUE, Integer.MAX_VALUE);
    }
}
