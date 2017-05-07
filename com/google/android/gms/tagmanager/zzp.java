// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzqk$zza;
import com.google.android.gms.internal.zzlo;
import com.google.android.gms.internal.zzlm;
import com.google.android.gms.internal.zzaf$zzj;
import com.google.android.gms.internal.zzqk;
import android.os.Looper;
import android.content.Context;
import com.google.android.gms.common.api.zzb;

public class zzp extends zzb<ContainerHolder>
{
    private final Context mContext;
    private final Looper zzYV;
    private final String zzaOS;
    private final TagManager zzaPe;
    private final zzp$zzd zzaPh;
    private final zzcd zzaPi;
    private final int zzaPj;
    private zzp$zzf zzaPk;
    private zzqk zzaPl;
    private volatile zzo zzaPm;
    private zzaf$zzj zzaPo;
    private String zzaPp;
    private zzp$zze zzaPq;
    private final zzlm zzpO;
    
    zzp(final Context mContext, final TagManager zzaPe, final Looper looper, final String zzaOS, final int zzaPj, final zzp$zzf zzaPk, final zzp$zze zzaPq, final zzqk zzaPl, final zzlm zzpO, final zzcd zzaPi) {
        Looper mainLooper;
        if (looper == null) {
            mainLooper = Looper.getMainLooper();
        }
        else {
            mainLooper = looper;
        }
        super(mainLooper);
        this.mContext = mContext;
        this.zzaPe = zzaPe;
        Looper mainLooper2 = looper;
        if (looper == null) {
            mainLooper2 = Looper.getMainLooper();
        }
        this.zzYV = mainLooper2;
        this.zzaOS = zzaOS;
        this.zzaPj = zzaPj;
        this.zzaPk = zzaPk;
        this.zzaPq = zzaPq;
        this.zzaPl = zzaPl;
        this.zzaPh = new zzp$zzd(this, null);
        this.zzaPo = new zzaf$zzj();
        this.zzpO = zzpO;
        this.zzaPi = zzaPi;
        if (this.zzzK()) {
            this.zzey(zzcb.zzAv().zzAx());
        }
    }
    
    public zzp(final Context context, final TagManager tagManager, final Looper looper, final String s, final int n, final zzs zzs) {
        this(context, tagManager, looper, s, n, new zzcn(context, s), new zzcm(context, s, zzs), new zzqk(context), zzlo.zzpN(), new zzbe(30, 900000L, 5000L, "refreshing", zzlo.zzpN()));
        this.zzaPl.zzfj(zzs.zzzN());
    }
    
    private boolean zzzK() {
        final zzcb zzAv = zzcb.zzAv();
        return (zzAv.zzAw() == zzcb$zza.zzaRd || zzAv.zzAw() == zzcb$zza.zzaRe) && this.zzaOS.equals(zzAv.getContainerId());
    }
    
    public void load(final String s) {
        Integer value;
        if (this.zzaPj != -1) {
            value = this.zzaPj;
        }
        else {
            value = null;
        }
        this.zzaPl.zza(this.zzaOS, value, s, new zzp$1(this, s));
    }
    
    protected ContainerHolder zzbg(final Status status) {
        if (this.zzaPm != null) {
            return this.zzaPm;
        }
        if (status == Status.zzaaG) {
            zzbg.e("timer expired: setting result to failure");
        }
        return new zzo(status);
    }
    
    void zzey(final String zzaPp) {
        synchronized (this) {
            this.zzaPp = zzaPp;
            if (this.zzaPq != null) {
                this.zzaPq.zzeB(zzaPp);
            }
        }
    }
}
