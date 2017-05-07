// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.stats;

import com.google.android.gms.internal.zzlr;

public final class zzc$zza
{
    public static zzlr<Integer> zzahH;
    public static zzlr<String> zzahI;
    public static zzlr<String> zzahJ;
    public static zzlr<String> zzahK;
    public static zzlr<String> zzahL;
    public static zzlr<Long> zzahM;
    
    static {
        zzc$zza.zzahH = zzlr.zza("gms:common:stats:connections:level", Integer.valueOf(zzd.LOG_LEVEL_OFF));
        zzc$zza.zzahI = zzlr.zzu("gms:common:stats:connections:ignored_calling_processes", "");
        zzc$zza.zzahJ = zzlr.zzu("gms:common:stats:connections:ignored_calling_services", "");
        zzc$zza.zzahK = zzlr.zzu("gms:common:stats:connections:ignored_target_processes", "");
        zzc$zza.zzahL = zzlr.zzu("gms:common:stats:connections:ignored_target_services", "com.google.android.gms.auth.GetToken");
        zzc$zza.zzahM = zzlr.zza("gms:common:stats:connections:time_out_duration", Long.valueOf(600000L));
    }
}
