// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.internal.zzcx;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzgk;

@zzgk
public class zzk
{
    private static final Object zzpm;
    private static zzk zzsM;
    private final zza zzsN;
    private final zze zzsO;
    private final zzl zzsP;
    private final zzac zzsQ;
    private final zzcx zzsR;
    private final zzf zzsS;
    
    static {
        zzpm = new Object();
        zza(new zzk());
    }
    
    protected zzk() {
        this.zzsN = new zza();
        this.zzsO = new zze();
        this.zzsP = new zzl();
        this.zzsQ = new zzac();
        this.zzsR = new zzcx();
        this.zzsS = new zzf();
    }
    
    protected static void zza(final zzk zzsM) {
        synchronized (zzk.zzpm) {
            zzk.zzsM = zzsM;
        }
    }
    
    private static zzk zzcD() {
        synchronized (zzk.zzpm) {
            return zzk.zzsM;
        }
    }
    
    public static zza zzcE() {
        return zzcD().zzsN;
    }
}
