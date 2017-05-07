// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.signin.internal.zzh;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.signin.internal.zzi;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.common.api.Api;

public final class zzqu
{
    public static final Api<zzqx> API;
    public static final Api$zzc<zzi> zzRk;
    public static final Api$zza<zzi, zzqx> zzRl;
    public static final Scope zzTe;
    public static final Scope zzTf;
    static final Api$zza<zzi, Api$ApiOptions$NoOptions> zzaUX;
    public static final zzqv zzaUY;
    public static final Api<Api$ApiOptions$NoOptions> zzaiH;
    public static final Api$zzc<zzi> zzapF;
    
    static {
        zzRk = new Api$zzc<zzi>();
        zzapF = new Api$zzc<zzi>();
        zzRl = new zzqu$1();
        zzaUX = new zzqu$2();
        zzTe = new Scope("profile");
        zzTf = new Scope("email");
        API = new Api<zzqx>("SignIn.API", (Api$zza<C, zzqx>)zzqu.zzRl, (Api$zzc<C>)zzqu.zzRk);
        zzaiH = new Api<Api$ApiOptions$NoOptions>("SignIn.INTERNAL_API", (Api$zza<C, Api$ApiOptions$NoOptions>)zzqu.zzaUX, (Api$zzc<C>)zzqu.zzapF);
        zzaUY = new zzh();
    }
}
