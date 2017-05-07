// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

@zzgr
public class zzbx
{
    private boolean zzpA;
    private final Object zzpd;
    private SharedPreferences zzuj;
    
    public zzbx() {
        this.zzpd = new Object();
        this.zzpA = false;
        this.zzuj = null;
    }
    
    public <T> T zzd(final zzbu<T> zzbu) {
        synchronized (this.zzpd) {
            if (!this.zzpA) {
                return zzbu.zzde();
            }
            // monitorexit(this.zzpd)
            return zzbu.zza(this.zzuj);
        }
    }
}
