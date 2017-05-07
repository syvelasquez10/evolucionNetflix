// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import com.google.android.gms.common.internal.zzx;

public final class Api<O extends Api$ApiOptions>
{
    private final String mName;
    private final Api$zzc<?> zzXW;
    private final Api$zza<?, O> zzYL;
    private final Api$zze<?, O> zzYM;
    private final Api$zzf<?> zzYN;
    
    public Api(final String mName, final Api$zza<C, O> zzYL, final Api$zzc<C> zzXW) {
        zzx.zzb(zzYL, "Cannot construct an Api with a null ClientBuilder");
        zzx.zzb(zzXW, "Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.zzYL = zzYL;
        this.zzYM = null;
        this.zzXW = zzXW;
        this.zzYN = null;
    }
    
    public String getName() {
        return this.mName;
    }
    
    public Api$zza<?, O> zznb() {
        zzx.zza(this.zzYL != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzYL;
    }
    
    public Api$zze<?, O> zznc() {
        zzx.zza(this.zzYM != null, "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.zzYM;
    }
    
    public Api$zzc<?> zznd() {
        zzx.zza(this.zzXW != null, "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.zzXW;
    }
    
    public boolean zzne() {
        return this.zzYN != null;
    }
}
