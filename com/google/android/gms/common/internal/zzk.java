// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.content.ServiceConnection;
import android.content.Context;

public abstract class zzk
{
    private static final Object zzaaI;
    private static zzk zzaaJ;
    
    static {
        zzaaI = new Object();
    }
    
    public static zzk zzah(final Context context) {
        synchronized (zzk.zzaaI) {
            if (zzk.zzaaJ == null) {
                zzk.zzaaJ = new zzl(context.getApplicationContext());
            }
            return zzk.zzaaJ;
        }
    }
    
    public abstract boolean zza(final String p0, final ServiceConnection p1, final String p2);
    
    public abstract void zzb(final String p0, final ServiceConnection p1, final String p2);
}
