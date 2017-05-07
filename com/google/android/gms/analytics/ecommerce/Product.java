// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import java.util.Iterator;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.analytics.zzc;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;

public class Product
{
    Map<String, String> zzLj;
    
    public Product() {
        this.zzLj = new HashMap<String, String>();
    }
    
    void put(final String s, final String s2) {
        zzx.zzb(s, "Name should be non-null");
        this.zzLj.put(s, s2);
    }
    
    public Product setBrand(final String s) {
        this.put("br", s);
        return this;
    }
    
    public Product setCategory(final String s) {
        this.put("ca", s);
        return this;
    }
    
    public Product setCouponCode(final String s) {
        this.put("cc", s);
        return this;
    }
    
    public Product setCustomDimension(final int n, final String s) {
        this.put(zzc.zzZ(n), s);
        return this;
    }
    
    public Product setCustomMetric(final int n, final int n2) {
        this.put(zzc.zzaa(n), Integer.toString(n2));
        return this;
    }
    
    public Product setId(final String s) {
        this.put("id", s);
        return this;
    }
    
    public Product setName(final String s) {
        this.put("nm", s);
        return this;
    }
    
    public Product setPosition(final int n) {
        this.put("ps", Integer.toString(n));
        return this;
    }
    
    public Product setPrice(final double n) {
        this.put("pr", Double.toString(n));
        return this;
    }
    
    public Product setQuantity(final int n) {
        this.put("qt", Integer.toString(n));
        return this;
    }
    
    public Product setVariant(final String s) {
        this.put("va", s);
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
