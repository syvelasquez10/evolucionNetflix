// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzkq<T>
{
    private static zzkq$zza zzaaX;
    private static int zzaaY;
    private static String zzaaZ;
    private static final Object zzpm;
    private T zzNR;
    protected final String zztP;
    protected final T zztQ;
    
    static {
        zzpm = new Object();
        zzkq.zzaaX = null;
        zzkq.zzaaY = 0;
        zzkq.zzaaZ = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }
    
    protected zzkq(final String zztP, final T zztQ) {
        this.zzNR = null;
        this.zztP = zztP;
        this.zztQ = zztQ;
    }
    
    public static boolean isInitialized() {
        return zzkq.zzaaX != null;
    }
    
    public static zzkq<Float> zza(final String s, final Float n) {
        return new zzkq$4(s, n);
    }
    
    public static zzkq<Integer> zza(final String s, final Integer n) {
        return new zzkq$3(s, n);
    }
    
    public static zzkq<Long> zza(final String s, final Long n) {
        return new zzkq$2(s, n);
    }
    
    public static zzkq<Boolean> zzg(final String s, final boolean b) {
        return new zzkq$1(s, Boolean.valueOf(b));
    }
    
    public static int zznM() {
        return zzkq.zzaaY;
    }
    
    public static zzkq<String> zzu(final String s, final String s2) {
        return new zzkq$5(s, s2);
    }
    
    public final T get() {
        if (this.zzNR != null) {
            return this.zzNR;
        }
        return this.zzbX(this.zztP);
    }
    
    protected abstract T zzbX(final String p0);
    
    public final T zznN() {
        final long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            return this.get();
        }
        finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
