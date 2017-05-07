// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import android.content.Context;

@zzgk
public class zzhy
{
    private static zzl zzIf;
    public static final zzhy$zza<Void> zzIg;
    private static final Object zzpm;
    
    static {
        zzpm = new Object();
        zzIg = new zzhy$1();
    }
    
    public zzhy(final Context context) {
        zzhy.zzIf = zzP(context);
    }
    
    private static zzl zzP(final Context context) {
        synchronized (zzhy.zzpm) {
            if (zzhy.zzIf == null) {
                zzhy.zzIf = zzac.zza(context.getApplicationContext());
            }
            return zzhy.zzIf;
        }
    }
    
    public zzih<String> zzb(final String s, final Map<String, String> map) {
        final zzhy$zzc<String> zzhy$zzc = new zzhy$zzc<String>(this, null);
        zzhy.zzIf.zze((zzk<Object>)new zzhy$3(this, s, zzhy$zzc, new zzhy$2(this, s, zzhy$zzc), map));
        return zzhy$zzc;
    }
}
