// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzof;
import com.google.android.gms.common.internal.zzx;
import android.content.Context;

public class zzg
{
    private final Context zzLR;
    private final Context zzqO;
    
    public zzg(Context applicationContext) {
        zzx.zzv(applicationContext);
        applicationContext = applicationContext.getApplicationContext();
        zzx.zzb(applicationContext, "Application context can't be null");
        this.zzqO = applicationContext;
        this.zzLR = applicationContext;
    }
    
    public Context getApplicationContext() {
        return this.zzqO;
    }
    
    protected zzof zzY(final Context context) {
        return zzof.zzaI(context);
    }
    
    protected zzu zza(final zzf zzf) {
        return new zzu(zzf);
    }
    
    protected zzk zzb(final zzf zzf) {
        return new zzk(zzf);
    }
    
    protected zza zzc(final zzf zzf) {
        return new zza(zzf);
    }
    
    protected zzn zzd(final zzf zzf) {
        return new zzn(zzf);
    }
    
    protected zzan zze(final zzf zzf) {
        return new zzan(zzf);
    }
    
    protected zzaf zzf(final zzf zzf) {
        return new zzaf(zzf);
    }
    
    protected zzr zzg(final zzf zzf) {
        return new zzr(zzf);
    }
    
    protected zzlm zzh(final zzf zzf) {
        return zzlo.zzpN();
    }
    
    protected GoogleAnalytics zzi(final zzf zzf) {
        return new GoogleAnalytics(zzf);
    }
    
    public Context zziq() {
        return this.zzLR;
    }
    
    zzl zzj(final zzf zzf) {
        return new zzl(zzf, this);
    }
    
    zzag zzk(final zzf zzf) {
        return new zzag(zzf);
    }
    
    protected zzb zzl(final zzf zzf) {
        return new zzb(zzf, this);
    }
    
    public zzj zzm(final zzf zzf) {
        return new zzj(zzf);
    }
    
    public zzah zzn(final zzf zzf) {
        return new zzah(zzf);
    }
    
    public zzi zzo(final zzf zzf) {
        return new zzi(zzf);
    }
    
    public zzv zzp(final zzf zzf) {
        return new zzv(zzf);
    }
    
    public zzai zzq(final zzf zzf) {
        return new zzai(zzf);
    }
}
