// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Product
{
    Map<String, String> BK;
    
    public Product() {
        this.BK = new HashMap<String, String>();
    }
    
    public Map<String, String> aq(final String s) {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<String, String> entry : this.BK.entrySet()) {
            hashMap.put(s + entry.getKey(), entry.getValue());
        }
        return (Map<String, String>)hashMap;
    }
    
    void put(final String s, final String s2) {
        n.b(s, (Object)"Name should be non-null");
        this.BK.put(s, s2);
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
        this.put(com.google.android.gms.analytics.n.D(n), s);
        return this;
    }
    
    public Product setCustomMetric(final int n, final int n2) {
        this.put(com.google.android.gms.analytics.n.E(n), Integer.toString(n2));
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
}
