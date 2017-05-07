// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class zzkf<T>
{
    private static zzkf$zza zzYi;
    private static int zzYj;
    private static String zzYk;
    private static final Object zzoW;
    private T zzLS;
    protected final String zztw;
    protected final T zztx;
    
    static {
        zzoW = new Object();
        zzkf.zzYi = null;
        zzkf.zzYj = 0;
        zzkf.zzYk = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }
    
    protected zzkf(final String zztw, final T zztx) {
        this.zzLS = null;
        this.zztw = zztw;
        this.zztx = zztx;
    }
    
    public static boolean isInitialized() {
        return zzkf.zzYi != null;
    }
    
    public static zzkf<Integer> zza(final String s, final Integer n) {
        return new zzkf$3(s, n);
    }
    
    public static zzkf<Long> zza(final String s, final Long n) {
        return new zzkf$2(s, n);
    }
    
    public static zzkf<Boolean> zzg(final String s, final boolean b) {
        return new zzkf$1(s, Boolean.valueOf(b));
    }
    
    public static int zzmW() {
        return zzkf.zzYj;
    }
    
    public static zzkf<String> zzs(final String s, final String s2) {
        return new zzkf$5(s, s2);
    }
    
    public final T get() {
        if (this.zzLS != null) {
            return this.zzLS;
        }
        return this.zzbP(this.zztw);
    }
    
    protected abstract T zzbP(final String p0);
}
