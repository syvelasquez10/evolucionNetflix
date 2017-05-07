// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;

public final class zzmp implements zzmn
{
    private static zzmp zzaik;
    
    public static zzmn zzqt() {
        synchronized (zzmp.class) {
            if (zzmp.zzaik == null) {
                zzmp.zzaik = new zzmp();
            }
            return zzmp.zzaik;
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
