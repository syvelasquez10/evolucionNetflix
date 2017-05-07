// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class zzj$zzd extends zzr$zza
{
    private zzj zzafM;
    private final int zzafN;
    
    public zzj$zzd(final zzj zzafM, final int zzafN) {
        this.zzafM = zzafM;
        this.zzafN = zzafN;
    }
    
    private void zzpj() {
        this.zzafM = null;
    }
    
    public void zza(final int n, final IBinder binder, final Bundle bundle) {
        zzx.zzb(this.zzafM, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzafM.zza(n, binder, bundle, this.zzafN);
        this.zzpj();
    }
    
    public void zzb(final int n, final Bundle bundle) {
        zzx.zzb(this.zzafM, "onAccountValidationComplete can be called only once per call to validateAccount");
        this.zzafM.zza(n, bundle, this.zzafN);
        this.zzpj();
    }
}
