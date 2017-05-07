// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.signin.internal;

import com.google.android.gms.common.api.Scope;
import java.util.List;
import com.google.android.gms.common.api.GoogleApiClient$ServerAuthCodeCallbacks;
import java.util.concurrent.ExecutorService;
import com.google.android.gms.internal.zzpt;

class zzh$zza extends zzd$zza
{
    private final zzpt zzZT;
    private final ExecutorService zzaKa;
    
    public zzh$zza(final zzpt zzZT, final ExecutorService zzaKa) {
        this.zzZT = zzZT;
        this.zzaKa = zzaKa;
    }
    
    private GoogleApiClient$ServerAuthCodeCallbacks zzxZ() {
        return this.zzZT.zzxZ();
    }
    
    public void zza(final String s, final String s2, final zzf zzf) {
        this.zzaKa.submit(new zzh$zza$2(this, s, s2, zzf));
    }
    
    public void zza(final String s, final List<Scope> list, final zzf zzf) {
        this.zzaKa.submit(new zzh$zza$1(this, list, s, zzf));
    }
}
