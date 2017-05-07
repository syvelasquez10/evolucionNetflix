// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.signin.zze;
import java.util.concurrent.ExecutorService;

class zzi$zza extends zzd$zza
{
    private final ExecutorService zzaOo;
    private final zze zzade;
    
    public zzi$zza(final zze zzade, final ExecutorService zzaOo) {
        this.zzade = zzade;
        this.zzaOo = zzaOo;
    }
    
    private GoogleApiClient$ServerAuthCodeCallbacks zzzq() {
        return this.zzade.zzzq();
    }
    
    public void zza(final String s, final String s2, final zzf zzf) {
        this.zzaOo.submit(new zzi$zza$2(this, s, s2, zzf));
    }
    
    public void zza(final String s, final List<Scope> list, final zzf zzf) {
        this.zzaOo.submit(new zzi$zza$1(this, list, s, zzf));
    }
}
