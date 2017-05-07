// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzkq;

public final class zzc$zzb
{
    public static zzkq<Integer> zzafD;
    public static zzkq<Long> zzafI;
    
    static {
        zzc$zzb.zzafD = zzkq.zza("gms:common:stats:wakeLocks:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        zzc$zzb.zzafI = zzkq.zza("gms:common:stats:wakelocks:time_out_duration", Long.valueOf(600000L));
    }
}
