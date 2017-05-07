// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin;

import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.common.api.Api$ApiOptions$Optional;

public final class zze implements Api$ApiOptions$Optional
{
    public static final zze zzaOd;
    private final String zzRU;
    private final boolean zzaOe;
    private final boolean zzaOf;
    private final GoogleApiClient$ServerAuthCodeCallbacks zzaOg;
    
    static {
        zzaOd = new zze$zza().zzzr();
    }
    
    private zze(final boolean zzaOe, final boolean zzaOf, final String zzRU, final GoogleApiClient$ServerAuthCodeCallbacks zzaOg) {
        this.zzaOe = zzaOe;
        this.zzaOf = zzaOf;
        this.zzRU = zzRU;
        this.zzaOg = zzaOg;
    }
    
    public String zzlG() {
        return this.zzRU;
    }
    
    public boolean zzzo() {
        return this.zzaOe;
    }
    
    public boolean zzzp() {
        return this.zzaOf;
    }
    
    public GoogleApiClient$ServerAuthCodeCallbacks zzzq() {
        return this.zzaOg;
    }
}
