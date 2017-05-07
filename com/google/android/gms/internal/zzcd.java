// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

@zzgr
public abstract class zzcd
{
    @zzgr
    public static final zzcd zzvK;
    @zzgr
    public static final zzcd zzvL;
    @zzgr
    public static final zzcd zzvM;
    
    static {
        zzvK = new zzcd$1();
        zzvL = new zzcd$2();
        zzvM = new zzcd$3();
    }
    
    public final void zza(final Map<String, String> map, final String s, final String s2) {
        map.put(s, this.zzd(map.get(s), s2));
    }
    
    public abstract String zzd(final String p0, final String p1);
}
