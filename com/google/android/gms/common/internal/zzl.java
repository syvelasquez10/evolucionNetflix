// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ServiceConnection;
import android.content.Context;

public abstract class zzl
{
    private static final Object zzafW;
    private static zzl zzafX;
    
    static {
        zzafW = new Object();
    }
    
    public static zzl zzal(final Context context) {
        synchronized (zzl.zzafW) {
            if (zzl.zzafX == null) {
                zzl.zzafX = new zzm(context.getApplicationContext());
            }
            return zzl.zzafX;
        }
    }
    
    public abstract boolean zza(final String p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final String p0, final ServiceConnection p1, final String p2);
}
