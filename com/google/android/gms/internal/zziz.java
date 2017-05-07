// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public final class zziz extends zzod<zziz>
{
    private Map<Integer, Double> zzLb;
    
    public zziz() {
        this.zzLb = new HashMap<Integer, Double>(4);
    }
    
    @Override
    public String toString() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<Integer, Double> entry : this.zzLb.entrySet()) {
            hashMap.put("metric" + entry.getKey(), entry.getValue());
        }
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zziz zziz) {
        zziz.zzLb.putAll(this.zzLb);
    }
    
    public Map<Integer, Double> zzhI() {
        return Collections.unmodifiableMap((Map<? extends Integer, ? extends Double>)this.zzLb);
    }
}
