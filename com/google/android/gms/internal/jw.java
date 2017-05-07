// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.SystemClock;

public final class jw implements ju
{
    private static jw MK;
    
    public static ju hA() {
        synchronized (jw.class) {
            if (jw.MK == null) {
                jw.MK = new jw();
            }
            return jw.MK;
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
