// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import android.os.Bundle;
import android.content.pm.ApplicationInfo;
import android.content.Context;
import android.content.pm.PackageManager$NameNotFoundException;

public class zzan extends zzd
{
    protected boolean zzKx;
    protected int zzMQ;
    protected String zzNT;
    protected String zzNU;
    protected int zzNW;
    protected boolean zzOL;
    protected boolean zzOM;
    protected boolean zzON;
    
    public zzan(final zzf zzf) {
        super(zzf);
    }
    
    private static int zzbt(String lowerCase) {
        lowerCase = lowerCase.toLowerCase();
        if ("verbose".equals(lowerCase)) {
            return 0;
        }
        if ("info".equals(lowerCase)) {
            return 1;
        }
        if ("warning".equals(lowerCase)) {
            return 2;
        }
        if ("error".equals(lowerCase)) {
            return 3;
        }
        return -1;
    }
    
    public int getLogLevel() {
        this.zzio();
        return this.zzMQ;
    }
    
    void zza(final zzaa zzaa) {
        this.zzaY("Loading global XML config values");
        if (zzaa.zzjY()) {
            this.zzb("XML config - app name", this.zzNT = zzaa.zzjZ());
        }
        if (zzaa.zzka()) {
            this.zzb("XML config - app version", this.zzNU = zzaa.zzkb());
        }
        if (zzaa.zzkc()) {
            final int zzbt = zzbt(zzaa.zzkd());
            if (zzbt >= 0) {
                this.zzMQ = zzbt;
                this.zza("XML config - log level", zzbt);
            }
        }
        if (zzaa.zzke()) {
            final int zzkf = zzaa.zzkf();
            this.zzNW = zzkf;
            this.zzOM = true;
            this.zzb("XML config - dispatch period (sec)", zzkf);
        }
        if (zzaa.zzkg()) {
            final boolean zzkh = zzaa.zzkh();
            this.zzKx = zzkh;
            this.zzON = true;
            this.zzb("XML config - dry run", zzkh);
        }
    }
    
    @Override
    protected void zzhB() {
        this.zzkW();
    }
    
    public String zzjZ() {
        this.zzio();
        return this.zzNT;
    }
    
    public int zzkV() {
        this.zzio();
        return this.zzNW;
    }
    
    protected void zzkW() {
        zzaa zzaa;
        while (true) {
            final Context context = this.getContext();
            ApplicationInfo applicationInfo;
            while (true) {
                try {
                    applicationInfo = context.getPackageManager().getApplicationInfo(context.getPackageName(), 129);
                    if (applicationInfo == null) {
                        this.zzbb("Couldn't get ApplicationInfo to load global config");
                        return;
                    }
                }
                catch (PackageManager$NameNotFoundException ex) {
                    this.zzd("PackageManager doesn't know about the app package", ex);
                    applicationInfo = null;
                    continue;
                }
                break;
            }
            final Bundle metaData = applicationInfo.metaData;
            if (metaData == null) {
                return;
            }
            final int int1 = metaData.getInt("com.google.android.gms.analytics.globalConfigResource");
            if (int1 <= 0) {
                return;
            }
            zzaa = new zzz(this.zzia()).zzac(int1);
            if (zzaa != null) {
                break;
            }
            return;
        }
        this.zza(zzaa);
    }
    
    public String zzkb() {
        this.zzio();
        return this.zzNU;
    }
    
    public boolean zzkc() {
        this.zzio();
        return this.zzOL;
    }
    
    public boolean zzke() {
        this.zzio();
        return this.zzOM;
    }
    
    public boolean zzkg() {
        this.zzio();
        return this.zzON;
    }
    
    public boolean zzkh() {
        this.zzio();
        return this.zzKx;
    }
}
