// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import com.google.android.gms.internal.zzqx;
import java.util.concurrent.ExecutorService;

class zzi$zza extends zzd$zza
{
    private final ExecutorService zzaVm;
    private final zzqx zzaaT;
    
    public zzi$zza(final zzqx zzaaT, final ExecutorService zzaVm) {
        this.zzaaT = zzaaT;
        this.zzaVm = zzaVm;
    }
    
    private GoogleApiClient$ServerAuthCodeCallbacks zzCg() {
        return this.zzaaT.zzCg();
    }
    
    public void zza(final String s, final String s2, final zzf zzf) {
        this.zzaVm.submit(new zzi$zza$2(this, s, s2, zzf));
    }
    
    public void zza(final String s, final List<Scope> list, final zzf zzf) {
        this.zzaVm.submit(new zzi$zza$1(this, list, s, zzf));
    }
}
