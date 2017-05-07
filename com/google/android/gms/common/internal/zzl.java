// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ServiceConnection;
import android.content.Context;

public abstract class zzl
{
    private static final Object zzadT;
    private static zzl zzadU;
    
    static {
        zzadT = new Object();
    }
    
    public static zzl zzak(final Context context) {
        synchronized (zzl.zzadT) {
            if (zzl.zzadU == null) {
                zzl.zzadU = new zzm(context.getApplicationContext());
            }
            return zzl.zzadU;
        }
    }
    
    public abstract boolean zza(final String p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final String p0, final ServiceConnection p1, final String p2);
}
