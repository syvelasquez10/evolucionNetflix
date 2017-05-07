// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzcc
{
    public static zzce zza(final zzcg zzcg, final long n) {
        if (zzcg == null) {
            return null;
        }
        return zzcg.zzb(n);
    }
    
    public static boolean zza(final zzcg zzcg, final zzce zzce, final long n, final String... array) {
        return zzcg != null && zzce != null && zzcg.zza(zzce, n, array);
    }
    
    public static boolean zza(final zzcg zzcg, final zzce zzce, final String... array) {
        return zzcg != null && zzce != null && zzcg.zza(zzce, array);
    }
    
    public static zzce zzb(final zzcg zzcg) {
        if (zzcg == null) {
            return null;
        }
        return zzcg.zzdn();
    }
}
