// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;

public final class zzja extends zzod<zzja>
{
    private final Map<String, Object> zzvs;
    
    public zzja() {
        this.zzvs = new HashMap<String, Object>();
    }
    
    private String zzaR(final String s) {
        zzx.zzcs(s);
        String substring = s;
        if (s != null) {
            substring = s;
            if (s.startsWith("&")) {
                substring = s.substring(1);
            }
        }
        zzx.zzh(substring, "Name can not be empty or \"&\"");
        return substring;
    }
    
    public void set(String zzaR, final String s) {
        zzaR = this.zzaR(zzaR);
        this.zzvs.put(zzaR, s);
    }
    
    @Override
    public String toString() {
        return zzod.zzA(this.zzvs);
    }
    
    @Override
    public void zza(final zzja zzja) {
        zzx.zzv(zzja);
        zzja.zzvs.putAll(this.zzvs);
    }
    
    public Map<String, Object> zzhJ() {
        return Collections.unmodifiableMap((Map<? extends String, ?>)this.zzvs);
    }
}
