// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.content.SharedPreferences;
import com.google.android.gms.ads.internal.zzp;

@zzgr
public abstract class zzbu<T>
{
    private final String zzue;
    private final T zzuf;
    
    private zzbu(final String zzue, final T zzuf) {
        this.zzue = zzue;
        this.zzuf = zzuf;
        zzp.zzbD().zza(this);
    }
    
    public static zzbu<String> zzP(final String s) {
        final zzbu<String> zzc = zzc(s, null);
        zzp.zzbD().zzb(zzc);
        return zzc;
    }
    
    public static zzbu<String> zzQ(final String s) {
        final zzbu<String> zzc = zzc(s, null);
        zzp.zzbD().zzc(zzc);
        return zzc;
    }
    
    public static zzbu<Integer> zza(final String s, final int n) {
        return new zzbu$2(s, Integer.valueOf(n));
    }
    
    public static zzbu<Boolean> zza(final String s, final Boolean b) {
        return new zzbu$1(s, b);
    }
    
    public static zzbu<Long> zzb(final String s, final long n) {
        return new zzbu$3(s, Long.valueOf(n));
    }
    
    public static zzbu<String> zzc(final String s, final String s2) {
        return new zzbu$4(s, s2);
    }
    
    public T get() {
        return zzp.zzbE().zzd(this);
    }
    
    public String getKey() {
        return this.zzue;
    }
    
    protected abstract T zza(final SharedPreferences p0);
    
    public T zzde() {
        return this.zzuf;
    }
}
