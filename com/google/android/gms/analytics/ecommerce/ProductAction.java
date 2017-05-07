// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import java.util.Iterator;
import com.google.android.gms.internal.zzod;
import com.google.android.gms.common.internal.zzx;
import java.util.HashMap;
import java.util.Map;

public class ProductAction
{
    Map<String, String> zzLj;
    
    public ProductAction(final String s) {
        this.zzLj = new HashMap<String, String>();
        this.put("&pa", s);
    }
    
    public Map<String, String> build() {
        return new HashMap<String, String>(this.zzLj);
    }
    
    void put(final String s, final String s2) {
        zzx.zzb(s, "Name should be non-null");
        this.zzLj.put(s, s2);
    }
    
    public ProductAction setCheckoutOptions(final String s) {
        this.put("&col", s);
        return this;
    }
    
    public ProductAction setCheckoutStep(final int n) {
        this.put("&cos", Integer.toString(n));
        return this;
    }
    
    public ProductAction setProductActionList(final String s) {
        this.put("&pal", s);
        return this;
    }
    
    public ProductAction setTransactionAffiliation(final String s) {
        this.put("&ta", s);
        return this;
    }
    
    public ProductAction setTransactionCouponCode(final String s) {
        this.put("&tcc", s);
        return this;
    }
    
    public ProductAction setTransactionId(final String s) {
        this.put("&ti", s);
        return this;
    }
    
    public ProductAction setTransactionRevenue(final double n) {
        this.put("&tr", Double.toString(n));
        return this;
    }
    
    public ProductAction setTransactionShipping(final double n) {
        this.put("&ts", Double.toString(n));
        return this;
    }
    
    public ProductAction setTransactionTax(final double n) {
        this.put("&tt", Double.toString(n));
        return this;
    }
    
    @Override
    public String toString() {
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        for (final Map.Entry<String, String> entry : this.zzLj.entrySet()) {
            if (entry.getKey().startsWith("&")) {
                hashMap.put(entry.getKey().substring(1), entry.getValue());
            }
            else {
                hashMap.put(entry.getKey(), entry.getValue());
            }
        }
        return zzod.zzF(hashMap);
    }
}
