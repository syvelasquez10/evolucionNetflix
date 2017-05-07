// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzkq;

public final class zzc$zza
{
    public static zzkq<Integer> zzafD;
    public static zzkq<String> zzafE;
    public static zzkq<String> zzafF;
    public static zzkq<String> zzafG;
    public static zzkq<String> zzafH;
    public static zzkq<Long> zzafI;
    
    static {
        zzc$zza.zzafD = zzkq.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        zzc$zza.zzafE = zzkq.zzu("gms:common:stats:connections:ignored_calling_processes", "");
        zzc$zza.zzafF = zzkq.zzu("gms:common:stats:connections:ignored_calling_services", "");
        zzc$zza.zzafG = zzkq.zzu("gms:common:stats:connections:ignored_target_processes", "");
        zzc$zza.zzafH = zzkq.zzu("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
        zzc$zza.zzafI = zzkq.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
    }
}
