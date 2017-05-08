// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;

@DoNotStrip
public class AwakeTimeSinceBootClock
{
    @DoNotStrip
    private static final AwakeTimeSinceBootClock INSTANCE;
    
    static {
        INSTANCE = new AwakeTimeSinceBootClock();
    }
    
    @DoNotStrip
    public static AwakeTimeSinceBootClock get() {
        return AwakeTimeSinceBootClock.INSTANCE;
    }
    
    @DoNotStrip
    public long now() {
        return SystemClock.uptimeMillis();
    }
}
