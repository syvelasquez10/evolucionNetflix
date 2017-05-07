// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.internal.zzlo;
import android.app.Application;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.internal.zzof;
import android.content.Context;

public class zzf
{
    private static zzf zzLC;
    private final Context mContext;
    private final Context zzLD;
    private final zzr zzLE;
    private final zzaf zzLF;
    private final zzof zzLG;
    private final zzb zzLH;
    private final zzv zzLI;
    private final zzan zzLJ;
    private final zzai zzLK;
    private final GoogleAnalytics zzLL;
    private final zzn zzLM;
    private final zza zzLN;
    private final zzk zzLO;
    private final zzu zzLP;
    private final zzlm zzpO;
    
    protected zzf(final zzg zzg) {
        final Context applicationContext = zzg.getApplicationContext();
        zzx.zzb(applicationContext, "Application context can't be null");
        zzx.zzb(applicationContext instanceof Application, "getApplicationContext didn't return the application");
        final Context zziq = zzg.zziq();
        zzx.zzv(zziq);
        this.mContext = applicationContext;
        this.zzLD = zziq;
        this.zzpO = zzg.zzh(this);
        this.zzLE = zzg.zzg(this);
        final zzaf zzf = zzg.zzf(this);
        zzf.zza();
        this.zzLF = zzf;
        if (this.zzif().zzjk()) {
            this.zzie().zzba("Google Analytics " + zze.VERSION + " is starting up.");
        }
        else {
            this.zzie().zzba("Google Analytics " + zze.VERSION + " is starting up. " + "To enable debug logging on a device run:\n" + "  adb shell setprop log.tag.GAv4 DEBUG\n" + "  adb logcat -s GAv4");
        }
        final zzai zzq = zzg.zzq(this);
        zzq.zza();
        this.zzLK = zzq;
        final zzan zze = zzg.zze(this);
        zze.zza();
        this.zzLJ = zze;
        final zzb zzl = zzg.zzl(this);
        final zzn zzd = zzg.zzd(this);
        final zza zzc = zzg.zzc(this);
        final zzk zzb = zzg.zzb(this);
        final zzu zza = zzg.zza(this);
        final zzof zzY = zzg.zzY(applicationContext);
        zzY.zza(this.zzip());
        this.zzLG = zzY;
        final GoogleAnalytics zzi = zzg.zzi(this);
        zzd.zza();
        this.zzLM = zzd;
        zzc.zza();
        this.zzLN = zzc;
        zzb.zza();
        this.zzLO = zzb;
        zza.zza();
        this.zzLP = zza;
        final zzv zzp = zzg.zzp(this);
        zzp.zza();
        this.zzLI = zzp;
        zzl.zza();
        this.zzLH = zzl;
        if (this.zzif().zzjk()) {
            this.zzie().zzb("Device AnalyticsService version", com.google.android.gms.analytics.internal.zze.VERSION);
        }
        zzi.zza();
        this.zzLL = zzi;
        zzl.start();
    }
    
    public static zzf zzX(final Context context) {
        zzx.zzv(context);
        Label_0109: {
            if (zzf.zzLC != null) {
                break Label_0109;
            }
            synchronized (zzf.class) {
                if (zzf.zzLC == null) {
                    final zzlm zzpN = zzlo.zzpN();
                    final long elapsedRealtime = zzpN.elapsedRealtime();
                    final zzf zzf = com.google.android.gms.analytics.internal.zzf.zzLC = new zzf(new zzg(context.getApplicationContext()));
                    GoogleAnalytics.zzhx();
                    final long n = zzpN.elapsedRealtime() - elapsedRealtime;
                    final long longValue = zzy.zzNO.get();
                    if (n > longValue) {
                        zzf.zzie().zzc("Slow initialization (ms)", n, longValue);
                    }
                }
                return zzf.zzLC;
            }
        }
    }
    
    private void zza(final zzd zzd) {
        zzx.zzb(zzd, "Analytics service not created/initialized");
        zzx.zzb(zzd.isInitialized(), "Analytics service not initialized");
    }
    
    public Context getContext() {
        return this.mContext;
    }
    
    public zzan zzhA() {
        this.zza(this.zzLJ);
        return this.zzLJ;
    }
    
    public zzb zzhz() {
        this.zza(this.zzLH);
        return this.zzLH;
    }
    
    public void zzic() {
        zzof.zzic();
    }
    
    public zzlm zzid() {
        return this.zzpO;
    }
    
    public zzaf zzie() {
        this.zza(this.zzLF);
        return this.zzLF;
    }
    
    public zzr zzif() {
        return this.zzLE;
    }
    
    public zzof zzig() {
        zzx.zzv(this.zzLG);
        return this.zzLG;
    }
    
    public zzv zzih() {
        this.zza(this.zzLI);
        return this.zzLI;
    }
    
    public zzai zzii() {
        this.zza(this.zzLK);
        return this.zzLK;
    }
    
    public zzk zzil() {
        this.zza(this.zzLO);
        return this.zzLO;
    }
    
    public zzu zzim() {
        return this.zzLP;
    }
    
    protected Thread.UncaughtExceptionHandler zzip() {
        return new zzf$1(this);
    }
    
    public Context zziq() {
        return this.zzLD;
    }
    
    public zzaf zzir() {
        return this.zzLF;
    }
    
    public GoogleAnalytics zzis() {
        zzx.zzv(this.zzLL);
        zzx.zzb(this.zzLL.isInitialized(), "Analytics instance not initialized");
        return this.zzLL;
    }
    
    public zzai zzit() {
        if (this.zzLK == null || !this.zzLK.isInitialized()) {
            return null;
        }
        return this.zzLK;
    }
    
    public zza zziu() {
        this.zza(this.zzLN);
        return this.zzLN;
    }
    
    public zzn zziv() {
        this.zza(this.zzLM);
        return this.zzLM;
    }
}
