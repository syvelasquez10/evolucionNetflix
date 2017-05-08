// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.common;

public class SystemClock
{
    public static long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    public static long nanoTime() {
        return System.nanoTime();
    }
    
    public static long uptimeMillis() {
        return android.os.SystemClock.uptimeMillis();
    }
}
