// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

@Deprecated
public class HitBuilders$ItemBuilder extends HitBuilders$HitBuilder<HitBuilders$ItemBuilder>
{
    public HitBuilders$ItemBuilder() {
        t.eq().a(t$a.Ae);
        this.set("&t", "item");
    }
    
    public HitBuilders$ItemBuilder setCategory(final String s) {
        this.set("&iv", s);
        return this;
    }
    
    public HitBuilders$ItemBuilder setCurrencyCode(final String s) {
        this.set("&cu", s);
        return this;
    }
    
    public HitBuilders$ItemBuilder setName(final String s) {
        this.set("&in", s);
        return this;
    }
    
    public HitBuilders$ItemBuilder setPrice(final double n) {
        this.set("&ip", Double.toString(n));
        return this;
    }
    
    public HitBuilders$ItemBuilder setQuantity(final long n) {
        this.set("&iq", Long.toString(n));
        return this;
    }
    
    public HitBuilders$ItemBuilder setSku(final String s) {
        this.set("&ic", s);
        return this;
    }
    
    public HitBuilders$ItemBuilder setTransactionId(final String s) {
        this.set("&ti", s);
        return this;
    }
}
