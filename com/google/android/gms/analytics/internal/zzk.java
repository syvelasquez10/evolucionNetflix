// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzok;

public class zzk extends zzd
{
    private final zzok zzMm;
    
    zzk(final zzf zzf) {
        super(zzf);
        this.zzMm = new zzok();
    }
    
    @Override
    protected void zzhB() {
        this.zzig().zzxu().zza(this.zzMm);
        this.zzhw();
    }
    
    public void zzhw() {
        final zzan zzhA = this.zzhA();
        final String zzjZ = zzhA.zzjZ();
        if (zzjZ != null) {
            this.zzMm.setAppName(zzjZ);
        }
        final String zzkb = zzhA.zzkb();
        if (zzkb != null) {
            this.zzMm.setAppVersion(zzkb);
        }
    }
    
    public zzok zziL() {
        this.zzio();
        return this.zzMm;
    }
}
