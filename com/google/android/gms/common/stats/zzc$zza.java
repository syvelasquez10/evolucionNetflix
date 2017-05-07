// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzkf;

public final class zzc$zza
{
    public static zzkf<Integer> zzacs;
    public static zzkf<String> zzact;
    public static zzkf<String> zzacu;
    public static zzkf<String> zzacv;
    public static zzkf<String> zzacw;
    public static zzkf<Long> zzacx;
    
    static {
        zzc$zza.zzacs = zzkf.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.zzacy));
        zzc$zza.zzact = zzkf.zzs("gms:common:stats:connections:ignored_calling_processes", "");
        zzc$zza.zzacu = zzkf.zzs("gms:common:stats:connections:ignored_calling_services", "");
        zzc$zza.zzacv = zzkf.zzs("gms:common:stats:connections:ignored_target_processes", "");
        zzc$zza.zzacw = zzkf.zzs("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
        zzc$zza.zzacx = zzkf.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
    }
}
