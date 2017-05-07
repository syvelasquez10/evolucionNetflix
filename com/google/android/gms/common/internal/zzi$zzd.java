// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;

public final class zzi$zzd extends zzo$zza
{
    private zzi zzaay;
    private final int zzaaz;
    
    public zzi$zzd(final zzi zzaay, final int zzaaz) {
        this.zzaay = zzaay;
        this.zzaaz = zzaaz;
    }
    
    private void zznQ() {
        this.zzaay = null;
    }
    
    public void zza(final int n, final IBinder binder, final Bundle bundle) {
        zzu.zzb(this.zzaay, "onPostInitComplete can be called only once per call to getRemoteService");
        this.zzaay.zza(n, binder, bundle, this.zzaaz);
        this.zznQ();
    }
    
    public void zzb(final int n, final Bundle bundle) {
        zzu.zzb(this.zzaay, "onAccountValidationComplete can be called only once per call to validateAccount");
        this.zzaay.zza(n, bundle, this.zzaaz);
        this.zznQ();
    }
}
