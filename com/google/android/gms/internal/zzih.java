// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import android.content.Context;

@zzgr
public class zzih
{
    private static zzl zzIZ;
    public static final zzih$zza<Void> zzJa;
    private static final Object zzpy;
    
    static {
        zzpy = new Object();
        zzJa = new zzih$1();
    }
    
    public zzih(final Context context) {
        zzih.zzIZ = zzP(context);
    }
    
    private static zzl zzP(final Context context) {
        synchronized (zzih.zzpy) {
            if (zzih.zzIZ == null) {
                zzih.zzIZ = zzac.zza(context.getApplicationContext());
            }
            return zzih.zzIZ;
        }
    }
    
    public zziq<String> zza(final String s, final Map<String, String> map) {
        final zzih$zzc<String> zzih$zzc = new zzih$zzc<String>(this, null);
        zzih.zzIZ.zze((zzk<Object>)new zzih$3(this, s, zzih$zzc, new zzih$2(this, s, zzih$zzc), map));
        return zzih$zzc;
    }
}
