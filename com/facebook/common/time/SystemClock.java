// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.time;

public class SystemClock implements Clock
{
    private static final SystemClock INSTANCE;
    
    static {
        INSTANCE = new SystemClock();
    }
    
    public static SystemClock get() {
        return SystemClock.INSTANCE;
    }
    
    @Override
    public long now() {
        return System.currentTimeMillis();
    }
}
