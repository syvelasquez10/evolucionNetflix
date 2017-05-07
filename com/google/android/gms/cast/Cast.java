// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import com.google.android.gms.common.api.Api$ClientKey;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.cast.internal.zzk;
import com.google.android.gms.cast.internal.zze;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.common.api.Api;

public final class Cast
{
    public static final Api<Cast$CastOptions> API;
    public static final Cast$CastApi CastApi;
    private static final Api$zza<zze, Cast$CastOptions> zzNY;
    
    static {
        zzNY = new Cast$1();
        API = new Api<Cast$CastOptions>("Cast.API", (Api$zza<C, Cast$CastOptions>)Cast.zzNY, (Api$ClientKey<C>)zzk.zzNX, new Scope[0]);
        CastApi = new Cast$CastApi$zza();
    }
}
