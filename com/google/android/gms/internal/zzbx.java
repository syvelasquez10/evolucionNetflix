// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;

@zzgk
public class zzbx
{
    private final Object zzpc;
    private boolean zzpr;
    private SharedPreferences zztU;
    
    public zzbx() {
        this.zzpc = new Object();
        this.zzpr = false;
        this.zztU = null;
    }
    
    public <T> T zzd(final zzbu<T> zzbu) {
        synchronized (this.zzpc) {
            if (!this.zzpr) {
                return zzbu.zzdd();
            }
            // monitorexit(this.zzpc)
            return zzbu.zza(this.zztU);
        }
    }
}
