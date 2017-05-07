// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class zzj$zzd extends zzr$zza
{
    private zzj zzadJ;
    private final int zzadK;
    
    public zzj$zzd(final zzj zzadJ, final int zzadK) {
        this.zzadJ = zzadJ;
        this.zzadK = zzadK;
    }
    
    private void zzoH() {
        this.zzadJ = null;
    }
    
    public void zza(final int n, final IBinder binder, final Bundle bundle) {
        zzx.zzb(this.zzadJ, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzadJ.zza(n, binder, bundle, this.zzadK);
        this.zzoH();
    }
    
    public void zzb(final int n, final Bundle bundle) {
        zzx.zzb(this.zzadJ, "onAccountValidationComplete can be called only once per call to validateAccount");
        this.zzadJ.zza(n, bundle, this.zzadK);
        this.zzoH();
    }
}
