// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public abstract class zzlr<T>
{
    private static zzlr$zza zzadc;
    private static int zzadd;
    private static String zzade;
    private static final Object zzpy;
    private T zzOX;
    protected final String zzue;
    protected final T zzuf;
    
    static {
        zzpy = new Object();
        zzlr.zzadc = null;
        zzlr.zzadd = 0;
        zzlr.zzade = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    }
    
    protected zzlr(final String zzue, final T zzuf) {
        this.zzOX = null;
        this.zzue = zzue;
        this.zzuf = zzuf;
    }
    
    public static boolean isInitialized() {
        return zzlr.zzadc != null;
    }
    
    public static zzlr<Integer> zza(final String s, final Integer n) {
        return new zzlr$3(s, n);
    }
    
    public static zzlr<Long> zza(final String s, final Long n) {
        return new zzlr$2(s, n);
    }
    
    public static int zzoo() {
        return zzlr.zzadd;
    }
    
    public static zzlr<String> zzu(final String s, final String s2) {
        return new zzlr$5(s, s2);
    }
    
    public final T get() {
        if (this.zzOX != null) {
            return this.zzOX;
        }
        return this.zzbY(this.zzue);
    }
    
    protected abstract T zzbY(final String p0);
}
