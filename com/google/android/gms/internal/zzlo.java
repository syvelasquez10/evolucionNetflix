// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;

public final class zzlo implements zzlm
{
    private static zzlo zzagd;
    
    public static zzlm zzpN() {
        synchronized (zzlo.class) {
            if (zzlo.zzagd == null) {
                zzlo.zzagd = new zzlo();
            }
            return zzlo.zzagd;
        }
    }
    
    @Override
    public long currentTimeMillis() {
        return System.currentTimeMillis();
    }
    
    @Override
    public long elapsedRealtime() {
        return SystemClock.elapsedRealtime();
    }
}
