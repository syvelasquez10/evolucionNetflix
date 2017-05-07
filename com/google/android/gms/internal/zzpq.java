// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.signin.internal.zzg;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.signin.internal.zzh;
import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Api;

public final class zzpq
{
    public static final Api<zzpt> API;
    public static final Api$ClientKey<zzh> zzNX;
    public static final Api$zza<zzh, zzpt> zzNY;
    static final Api$zza<zzh, Api$ApiOptions$NoOptions> zzaJN;
    public static final zzpr zzaJO;
    public static final Api<Api$ApiOptions$NoOptions> zzacZ;
    public static final Api$ClientKey<zzh> zzajy;
    
    static {
        zzNX = new Api$ClientKey<zzh>();
        zzajy = new Api$ClientKey<zzh>();
        zzNY = new zzpq$1();
        zzaJN = new zzpq$2();
        API = new Api<zzpt>("SignIn.API", (Api$zza<C, zzpt>)zzpq.zzNY, (Api$ClientKey<C>)zzpq.zzNX, new Scope[0]);
        zzacZ = new Api<Api$ApiOptions$NoOptions>("SignIn.INTERNAL_API", (Api$zza<C, Api$ApiOptions$NoOptions>)zzpq.zzaJN, (Api$ClientKey<C>)zzpq.zzajy, new Scope[0]);
        zzaJO = new zzg();
    }
}
