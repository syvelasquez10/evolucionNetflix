// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.text.TextUtils;
import android.app.Activity;
import android.app.Application$ActivityLifecycleCallbacks;
import android.util.Log;
import android.os.Build$VERSION;
import android.content.Context;
import java.util.ArrayList;
import com.google.android.gms.common.internal.zzx;
import java.util.List;
import android.app.Application;

public class zzoj
{
    private static final zzoj$zza[] zzaIm;
    private static zzoj zzaIn;
    private final Application zzaIo;
    private zzoq zzaIp;
    private final List<zzoj$zza> zzaIq;
    private zzot zzaIr;
    
    static {
        zzaIm = new zzoj$zza[0];
    }
    
    private zzoj(final Application zzaIo) {
        zzx.zzv(zzaIo);
        this.zzaIo = zzaIo;
        this.zzaIq = new ArrayList<zzoj$zza>();
    }
    
    public static zzoj zzaJ(final Context context) {
        zzx.zzv(context);
        final Application application = (Application)context.getApplicationContext();
        zzx.zzv(application);
        synchronized (zzoj.class) {
            if (zzoj.zzaIn == null) {
                zzoj.zzaIn = new zzoj(application);
            }
            return zzoj.zzaIn;
        }
    }
    
    private zzoj$zza[] zzxz() {
        synchronized (this.zzaIq) {
            if (this.zzaIq.isEmpty()) {
                return zzoj.zzaIm;
            }
            return this.zzaIq.toArray(new zzoj$zza[this.zzaIq.size()]);
        }
    }
    
    public void zza(final zzoj$zza zzoj$zza) {
        zzx.zzv(zzoj$zza);
        synchronized (this.zzaIq) {
            this.zzaIq.remove(zzoj$zza);
            this.zzaIq.add(zzoj$zza);
        }
    }
    
    public void zzaj(final boolean b) {
        if (Build$VERSION.SDK_INT < 14) {
            Log.i("com.google.android.gms.measurement.ScreenViewService", "AutoScreeViewTracking is not supported on API 14 or earlier devices");
        }
        else if (this.zzxy() != b) {
            if (b) {
                this.zzaIr = new zzot(this);
                this.zzaIo.registerActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zzaIr);
                return;
            }
            this.zzaIo.unregisterActivityLifecycleCallbacks((Application$ActivityLifecycleCallbacks)this.zzaIr);
            this.zzaIr = null;
        }
    }
    
    public void zzb(final zzoq zzoq, final Activity activity) {
        final boolean b = false;
        zzx.zzv(zzoq);
        zzoj$zza[] zzxz = null;
        Label_0112: {
            if (!zzoq.isMutable()) {
                break Label_0112;
            }
            if (activity instanceof zzoi) {
                ((zzoi)activity).zzb(zzoq);
            }
            if (this.zzaIp != null) {
                zzoq.zzhT(this.zzaIp.zzbp());
                zzoq.zzdU(this.zzaIp.zzxT());
            }
            zzxz = this.zzxz();
            for (int i = 0; i < zzxz.length; ++i) {
                zzxz[i].zza(zzoq, activity);
            }
            zzoq.zzxX();
            if (!TextUtils.isEmpty((CharSequence)zzoq.zzxT())) {
                break Label_0112;
            }
            return;
        }
        if (this.zzaIp != null && this.zzaIp.zzbp() == zzoq.zzbp()) {
            this.zzaIp = zzoq;
            return;
        }
        this.zzxx();
        this.zzaIp = zzoq;
        zzoj$zza[] zzxz2 = zzxz;
        int j = b ? 1 : 0;
        if (zzxz == null) {
            zzxz2 = this.zzxz();
            j = (b ? 1 : 0);
        }
        while (j < zzxz2.length) {
            zzxz2[j].zza(zzoq);
            ++j;
        }
    }
    
    public zzoq zzxw() {
        return this.zzaIp;
    }
    
    public void zzxx() {
        this.zzaIp = null;
    }
    
    public boolean zzxy() {
        return this.zzaIr != null;
    }
}
