// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal;

import com.google.android.gms.internal.zzmp;
import android.os.Build$VERSION;
import com.google.android.gms.internal.zzdu;
import com.google.android.gms.internal.zzej;
import com.google.android.gms.internal.zzii;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.internal.zzbx;
import com.google.android.gms.internal.zzbv;
import com.google.android.gms.internal.zzbw;
import com.google.android.gms.internal.zzgz;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzmn;
import com.google.android.gms.internal.zzhu;
import com.google.android.gms.internal.zzie;
import com.google.android.gms.internal.zzjb;
import com.google.android.gms.internal.zzid;
import com.google.android.gms.internal.zzgg;
import com.google.android.gms.ads.internal.overlay.zze;
import com.google.android.gms.ads.internal.request.zza;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzp
{
    private static zzp zzpN;
    private static final Object zzpy;
    private final zza zzpO;
    private final com.google.android.gms.ads.internal.overlay.zza zzpP;
    private final zze zzpQ;
    private final zzgg zzpR;
    private final zzid zzpS;
    private final zzjb zzpT;
    private final zzie zzpU;
    private final zzhu zzpV;
    private final zzmn zzpW;
    private final zzcb zzpX;
    private final zzgz zzpY;
    private final zzbw zzpZ;
    private final zzbv zzqa;
    private final zzbx zzqb;
    private final zzi zzqc;
    private final zzii zzqd;
    private final zzej zzqe;
    private final zzdu zzqf;
    
    static {
        zzpy = new Object();
        zza(new zzp());
    }
    
    protected zzp() {
        this.zzpO = new zza();
        this.zzpP = new com.google.android.gms.ads.internal.overlay.zza();
        this.zzpQ = new zze();
        this.zzpR = new zzgg();
        this.zzpS = new zzid();
        this.zzpT = new zzjb();
        this.zzpU = zzie.zzM(Build$VERSION.SDK_INT);
        this.zzpV = new zzhu(this.zzpS);
        this.zzpW = new zzmp();
        this.zzpX = new zzcb();
        this.zzpY = new zzgz();
        this.zzpZ = new zzbw();
        this.zzqa = new zzbv();
        this.zzqb = new zzbx();
        this.zzqc = new zzi();
        this.zzqd = new zzii();
        this.zzqe = new zzej();
        this.zzqf = new zzdu();
    }
    
    protected static void zza(final zzp zzpN) {
        synchronized (zzp.zzpy) {
            zzp.zzpN = zzpN;
        }
    }
    
    public static zzbv zzbD() {
        return zzbq().zzqa;
    }
    
    public static zzbx zzbE() {
        return zzbq().zzqb;
    }
    
    public static zzi zzbF() {
        return zzbq().zzqc;
    }
    
    public static zzdu zzbI() {
        return zzbq().zzqf;
    }
    
    private static zzp zzbq() {
        synchronized (zzp.zzpy) {
            return zzp.zzpN;
        }
    }
    
    public static com.google.android.gms.ads.internal.overlay.zza zzbs() {
        return zzbq().zzpP;
    }
    
    public static zze zzbt() {
        return zzbq().zzpQ;
    }
    
    public static zzid zzbv() {
        return zzbq().zzpS;
    }
    
    public static zzjb zzbw() {
        return zzbq().zzpT;
    }
    
    public static zzie zzbx() {
        return zzbq().zzpU;
    }
    
    public static zzhu zzby() {
        return zzbq().zzpV;
    }
    
    public static zzmn zzbz() {
        return zzbq().zzpW;
    }
}
