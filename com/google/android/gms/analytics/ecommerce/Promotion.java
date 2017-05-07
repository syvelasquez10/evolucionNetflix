// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import java.util.Iterator;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;

public class Promotion
{
    Map<String, String> zzLj;
    
    public Promotion() {
        this.zzLj = new HashMap<String, String>();
    }
    
    void put(final String s, final String s2) {
        zzx.zzb(s, "Name should be non-null");
        this.zzLj.put(s, s2);
    }
    
    public Promotion setCreative(final String s) {
        this.put("cr", s);
        return this;
    }
    
    public Promotion setId(final String s) {
        this.put("id", s);
        return this;
    }
    
    public Promotion setName(final String s) {
        this.put("nm", s);
        return this;
    }
    
    public Promotion setPosition(final String s) {
        this.put("ps", s);
        return this;
    }
    
    @Override
    public String toString() {
        return zzod.zzF(this.zzLj);
    }
    
    public Map<String, String> zzaV(final String s) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<String, String> entry : this.zzLj.entrySet()) {
            hashMap.put(s + entry.getKey(), entry.getValue());
        }
        return (Map<String, String>)hashMap;
    }
}
