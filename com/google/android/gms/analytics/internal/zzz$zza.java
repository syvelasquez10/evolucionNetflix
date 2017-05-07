// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

class zzz$zza implements zzq$zza<zzaa>
{
    private final zzf zzLy;
    private final zzaa zzNS;
    
    public zzz$zza(final zzf zzLy) {
        this.zzLy = zzLy;
        this.zzNS = new zzaa();
    }
    
    @Override
    public void zzc(final String s, final boolean b) {
        if ("ga_dryRun".equals(s)) {
            final zzaa zzNS = this.zzNS;
            int zzNX;
            if (b) {
                zzNX = 1;
            }
            else {
                zzNX = 0;
            }
            zzNS.zzNX = zzNX;
            return;
        }
        this.zzLy.zzie().zzd("Bool xml configuration name not recognized", s);
    }
    
    @Override
    public void zzd(final String s, final int zzNW) {
        if ("ga_dispatchPeriod".equals(s)) {
            this.zzNS.zzNW = zzNW;
            return;
        }
        this.zzLy.zzie().zzd("Int xml configuration name not recognized", s);
    }
    
    public zzaa zzjX() {
        return this.zzNS;
    }
    
    @Override
    public void zzl(final String s, final String s2) {
    }
    
    @Override
    public void zzm(final String s, final String zzNV) {
        if ("ga_appName".equals(s)) {
            this.zzNS.zzNT = zzNV;
            return;
        }
        if ("ga_appVersion".equals(s)) {
            this.zzNS.zzNU = zzNV;
            return;
        }
        if ("ga_logLevel".equals(s)) {
            this.zzNS.zzNV = zzNV;
            return;
        }
        this.zzLy.zzie().zzd("String xml configuration name not recognized", s);
    }
}
