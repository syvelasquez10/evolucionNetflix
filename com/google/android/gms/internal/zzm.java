// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzm<T>
{
    public final T result;
    public final zzb$zza zzag;
    public final zzr zzah;
    public boolean zzai;
    
    private zzm(final zzr zzah) {
        this.zzai = false;
        this.result = null;
        this.zzag = null;
        this.zzah = zzah;
    }
    
    private zzm(final T result, final zzb$zza zzag) {
        this.zzai = false;
        this.result = result;
        this.zzag = zzag;
        this.zzah = null;
    }
    
    public static <T> zzm<T> zza(final T t, final zzb$zza zzb$zza) {
        return new zzm<T>(t, zzb$zza);
    }
    
    public static <T> zzm<T> zzd(final zzr zzr) {
        return new zzm<T>(zzr);
    }
    
    public boolean isSuccess() {
        return this.zzah == null;
    }
}
