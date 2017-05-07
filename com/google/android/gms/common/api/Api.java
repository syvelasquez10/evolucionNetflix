// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.api;

import java.util.List;
import java.util.Collection;
import java.util.Arrays;
import com.google.android.gms.common.internal.zzu;
import java.util.ArrayList;

public final class Api<O extends Api$ApiOptions>
{
    private final String mName;
    private final Api$ClientKey<?> zzVt;
    private final Api$zza<?, O> zzWh;
    private final Api$zzc<?, O> zzWi;
    private final Api$zzd<?> zzWj;
    private final ArrayList<Scope> zzWk;
    
    public Api(final String mName, final Api$zza<C, O> zzWh, final Api$ClientKey<C> zzVt, final Scope... array) {
        zzu.zzb(zzWh, "Cannot construct an Api with a null ClientBuilder");
        zzu.zzb(zzVt, "Cannot construct an Api with a null ClientKey");
        this.mName = mName;
        this.zzWh = zzWh;
        this.zzWi = null;
        this.zzVt = zzVt;
        this.zzWj = null;
        this.zzWk = new ArrayList<Scope>(Arrays.asList(array));
    }
    
    public String getName() {
        return this.mName;
    }
    
    public Api$zza<?, O> zzmn() {
        zzu.zza(this.zzWh != null, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
        return this.zzWh;
    }
    
    public Api$zzc<?, O> zzmo() {
        zzu.zza(this.zzWi != null, "This API was constructed with a ClientBuilder. Use getClientBuilder");
        return this.zzWi;
    }
    
    public List<Scope> zzmp() {
        return this.zzWk;
    }
    
    public Api$ClientKey<?> zzmq() {
        zzu.zza(this.zzVt != null, "This API was constructed with a SimpleClientKey. Use getSimpleClientKey");
        return this.zzVt;
    }
    
    public boolean zzmr() {
        return this.zzWj != null;
    }
}
