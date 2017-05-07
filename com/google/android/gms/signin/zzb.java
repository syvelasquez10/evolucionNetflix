// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import com.google.android.gms.signin.internal.zzh;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.signin.internal.zzi;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.common.api.Api;

public final class zzb
{
    public static final Api<zze> API;
    public static final Api$zzc<zzi> zzQf;
    public static final Api$zza<zzi, zze> zzQg;
    static final Api$zza<zzi, Api$ApiOptions$NoOptions> zzaNZ;
    public static final Scope zzaOa;
    public static final Scope zzaOb;
    public static final zzc zzaOc;
    public static final Api<Api$ApiOptions$NoOptions> zzagz;
    public static final Api$zzc<zzi> zzanf;
    
    static {
        zzQf = new Api$zzc<zzi>();
        zzanf = new Api$zzc<zzi>();
        zzQg = new zzb$1();
        zzaNZ = new zzb$2();
        zzaOa = new Scope("profile");
        zzaOb = new Scope("email");
        API = new Api<zze>("SignIn.API", (Api$zza<C, zze>)zzb.zzQg, (Api$zzc<C>)zzb.zzQf);
        zzagz = new Api<Api$ApiOptions$NoOptions>("SignIn.INTERNAL_API", (Api$zza<C, Api$ApiOptions$NoOptions>)zzb.zzaNZ, (Api$zzc<C>)zzb.zzanf);
        zzaOc = new zzh();
    }
}
