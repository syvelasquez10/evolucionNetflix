// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzx;

public final class Api<O extends Api$ApiOptions>
{
    private final String mName;
    private final Api$zzc<?> zzZM;
    private final Api$zza<?, O> zzaav;
    private final Api$zze<?, O> zzaaw;
    private final Api$zzf<?> zzaax;
    
    public Api(final String mName, final Api$zza<C, O> zzaav, final Api$zzc<C> zzZM) {
        zzx.zzb(zzaav, "Cannot construct an Api with a null ClientBuilder");
        zzx.zzb(zzZM, "Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.zzaav = zzaav;
        this.zzaaw = null;
        this.zzZM = zzZM;
        this.zzaax = null;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public Api$zza<?, O> zznv() {
        zzx.zza(this.zzaav != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzaav;
    }
    
    public Api$zze<?, O> zznw() {
        zzx.zza(this.zzaaw != null, "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.zzaaw;
    }
    
    public Api$zzc<?> zznx() {
        zzx.zza(this.zzZM != null, "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.zzZM;
    }
    
    public boolean zzny() {
        return this.zzaax != null;
    }
}
