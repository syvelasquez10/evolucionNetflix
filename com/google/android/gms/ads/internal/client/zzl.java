// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.internal.client;

import com.google.android.gms.ads.internal.reward.client.zzf;
import com.google.android.gms.internal.zzda;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.internal.zzgr;

@zzgr
public class zzl
{
    private static final Object zzpy;
    private static zzl zztl;
    private final zza zztm;
    private final zze zztn;
    private final zzm zzto;
    private final zzad zztp;
    private final zzda zztq;
    private final zzf zztr;
    
    static {
        zzpy = new Object();
        zza(new zzl());
    }
    
    protected zzl() {
        this.zztm = new zza();
        this.zztn = new zze();
        this.zzto = new zzm();
        this.zztp = new zzad();
        this.zztq = new zzda();
        this.zztr = new zzf();
    }
    
    protected static void zza(final zzl zztl) {
        synchronized (zzl.zzpy) {
            zzl.zztl = zztl;
        }
    }
    
    private static zzl zzcE() {
        synchronized (zzl.zzpy) {
            return zzl.zztl;
        }
    }
    
    public static zza zzcF() {
        return zzcE().zztm;
    }
}
