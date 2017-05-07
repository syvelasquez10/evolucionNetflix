// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.util.Collections;
import java.util.HashMap;
import com.google.android.gms.common.internal.zzx;
import java.util.Map;

public class zzh
{
    private final long zzLS;
    private final String zzLT;
    private final boolean zzLU;
    private long zzLV;
    private final String zzLd;
    private final Map<String, String> zzvs;
    
    public zzh(final long zzLS, final String zzLd, final String zzLT, final boolean zzLU, final long zzLV, final Map<String, String> map) {
        zzx.zzcs(zzLd);
        zzx.zzcs(zzLT);
        this.zzLS = zzLS;
        this.zzLd = zzLd;
        this.zzLT = zzLT;
        this.zzLU = zzLU;
        this.zzLV = zzLV;
        if (map != null) {
            this.zzvs = new HashMap<String, String>(map);
            return;
        }
        this.zzvs = Collections.emptyMap();
    }
    
    public String getClientId() {
        return this.zzLd;
    }
    
    public long zziw() {
        return this.zzLS;
    }
    
    public String zzix() {
        return this.zzLT;
    }
    
    public boolean zziy() {
        return this.zzLU;
    }
    
    public long zziz() {
        return this.zzLV;
    }
    
    public Map<String, String> zzn() {
        return this.zzvs;
    }
    
    public void zzn(final long zzLV) {
        this.zzLV = zzLV;
    }
}
