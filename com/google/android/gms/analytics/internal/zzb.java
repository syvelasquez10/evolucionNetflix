// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzof;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.analytics.AnalyticsService;
import com.google.android.gms.analytics.AnalyticsReceiver;
import com.google.android.gms.common.internal.zzx;

public class zzb extends zzd
{
    private final zzl zzLq;
    
    public zzb(final zzf zzf, final zzg zzg) {
        super(zzf);
        zzx.zzv(zzg);
        this.zzLq = zzg.zzj(zzf);
    }
    
    void onServiceConnected() {
        this.zzic();
        this.zzLq.onServiceConnected();
    }
    
    public void start() {
        this.zzLq.start();
    }
    
    public void zzI(final boolean b) {
        this.zza("Network connectivity status changed", b);
        this.zzig().zzf(new zzb$2(this, b));
    }
    
    public long zza(final zzh zzh) {
        this.zzio();
        zzx.zzv(zzh);
        this.zzic();
        final long zza = this.zzLq.zza(zzh, true);
        if (zza == 0L) {
            this.zzLq.zzc(zzh);
        }
        return zza;
    }
    
    public void zza(final zzab zzab) {
        zzx.zzv(zzab);
        this.zzio();
        this.zzb("Hit delivery requested", zzab);
        this.zzig().zzf(new zzb$4(this, zzab));
    }
    
    public void zza(final zzw zzw) {
        this.zzio();
        this.zzig().zzf(new zzb$6(this, zzw));
    }
    
    public void zza(final String s, final Runnable runnable) {
        zzx.zzh(s, "campaign param can't be empty");
        this.zzig().zzf(new zzb$3(this, s, runnable));
    }
    
    @Override
    protected void zzhB() {
        this.zzLq.zza();
    }
    
    public void zzhV() {
        this.zzio();
        final Context context = this.getContext();
        if (AnalyticsReceiver.zzV(context) && AnalyticsService.zzW(context)) {
            final Intent intent = new Intent(context, (Class)AnalyticsService.class);
            intent.setAction("com.google.android.gms.analytics.ANALYTICS_DISPATCH");
            context.startService(intent);
            return;
        }
        this.zza((zzw)null);
    }
    
    public void zzhX() {
        this.zzio();
        zzof.zzic();
        this.zzLq.zzhX();
    }
    
    public void zzhY() {
        this.zzaY("Radio powered up");
        this.zzhV();
    }
    
    void zzhZ() {
        this.zzic();
        this.zzLq.zzhZ();
    }
}
