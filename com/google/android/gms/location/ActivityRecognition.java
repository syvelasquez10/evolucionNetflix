// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.location;

import com.google.android.gms.location.internal.zza;
import com.google.android.gms.common.api.Api$zza;
import com.google.android.gms.location.internal.zzl;
import com.google.android.gms.common.api.Api$zzc;
import com.google.android.gms.common.api.Api$ApiOptions$NoOptions;
import com.google.android.gms.common.api.Api;

public class ActivityRecognition
{
    public static final Api<Api$ApiOptions$NoOptions> API;
    public static final ActivityRecognitionApi ActivityRecognitionApi;
    private static final Api$zzc<zzl> zzRk;
    private static final Api$zza<zzl, Api$ApiOptions$NoOptions> zzRl;
    
    static {
        zzRk = new Api$zzc<zzl>();
        zzRl = new ActivityRecognition$1();
        API = new Api<Api$ApiOptions$NoOptions>("ActivityRecognition.API", (Api$zza<C, Api$ApiOptions$NoOptions>)ActivityRecognition.zzRl, (Api$zzc<C>)ActivityRecognition.zzRk);
        ActivityRecognitionApi = new zza();
    }
}
