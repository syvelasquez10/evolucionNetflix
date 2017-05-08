// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.time;

import android.os.SystemClock;
import com.facebook.common.internal.DoNotStrip;

@DoNotStrip
public class RealtimeSinceBootClock
{
    private static final RealtimeSinceBootClock INSTANCE;
    
    static {
        INSTANCE = new RealtimeSinceBootClock();
    }
    
    @DoNotStrip
    public static RealtimeSinceBootClock get() {
        return RealtimeSinceBootClock.INSTANCE;
    }
    
    public long now() {
        return SystemClock.elapsedRealtime();
    }
}
