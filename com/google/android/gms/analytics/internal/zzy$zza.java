// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzkq;

public final class zzy$zza<V>
{
    private final V zzNP;
    private final zzkq<V> zzNQ;
    private V zzNR;
    
    private zzy$zza(final zzkq<V> zzNQ, final V zzNP) {
        zzx.zzv(zzNQ);
        this.zzNQ = zzNQ;
        this.zzNP = zzNP;
    }
    
    static zzy$zza<Float> zza(final String s, final float n) {
        return zza(s, n, n);
    }
    
    static zzy$zza<Float> zza(final String s, final float n, final float n2) {
        return new zzy$zza<Float>(zzkq.zza(s, Float.valueOf(n2)), n);
    }
    
    static zzy$zza<Integer> zza(final String s, final int n, final int n2) {
        return new zzy$zza<Integer>(zzkq.zza(s, Integer.valueOf(n2)), n);
    }
    
    static zzy$zza<Long> zza(final String s, final long n, final long n2) {
        return new zzy$zza<Long>(zzkq.zza(s, Long.valueOf(n2)), n);
    }
    
    static zzy$zza<Boolean> zza(final String s, final boolean b, final boolean b2) {
        return new zzy$zza<Boolean>(zzkq.zzg(s, b2), b);
    }
    
    static zzy$zza<Long> zzc(final String s, final long n) {
        return zza(s, n, n);
    }
    
    static zzy$zza<String> zzd(final String s, final String s2, final String s3) {
        return new zzy$zza<String>(zzkq.zzu(s, s3), s2);
    }
    
    static zzy$zza<Boolean> zzd(final String s, final boolean b) {
        return zza(s, b, b);
    }
    
    static zzy$zza<Integer> zze(final String s, final int n) {
        return zza(s, n, n);
    }
    
    static zzy$zza<String> zzn(final String s, final String s2) {
        return zzd(s, s2, s2);
    }
    
    public V get() {
        if (this.zzNR != null) {
            return this.zzNR;
        }
        if (zzd.zzacF && zzkq.isInitialized()) {
            return this.zzNQ.zznN();
        }
        return this.zzNP;
    }
}
