// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public final class zziy extends zzod<zziy>
{
    private Map<Integer, String> zzLa;
    
    public zziy() {
        this.zzLa = new HashMap<Integer, String>(4);
    }
    
    @Override
    public String toString() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<Integer, String> entry : this.zzLa.entrySet()) {
            hashMap.put("dimension" + entry.getKey(), entry.getValue());
        }
        return zzod.zzA(hashMap);
    }
    
    @Override
    public void zza(final zziy zziy) {
        zziy.zzLa.putAll(this.zzLa);
    }
    
    public Map<Integer, String> zzhH() {
        return Collections.unmodifiableMap((Map<? extends Integer, ? extends String>)this.zzLa);
    }
}
