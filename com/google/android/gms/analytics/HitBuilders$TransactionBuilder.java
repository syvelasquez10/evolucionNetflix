// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Map;

@Deprecated
public class HitBuilders$TransactionBuilder extends HitBuilders$HitBuilder<HitBuilders$TransactionBuilder>
{
    public HitBuilders$TransactionBuilder() {
        t.eq().a(t$a.zL);
        this.set("&t", "transaction");
    }
    
    public HitBuilders$TransactionBuilder setAffiliation(final String s) {
        this.set("&ta", s);
        return this;
    }
    
    public HitBuilders$TransactionBuilder setCurrencyCode(final String s) {
        this.set("&cu", s);
        return this;
    }
    
    public HitBuilders$TransactionBuilder setRevenue(final double n) {
        this.set("&tr", Double.toString(n));
        return this;
    }
    
    public HitBuilders$TransactionBuilder setShipping(final double n) {
        this.set("&ts", Double.toString(n));
        return this;
    }
    
    public HitBuilders$TransactionBuilder setTax(final double n) {
        this.set("&tt", Double.toString(n));
        return this;
    }
    
    public HitBuilders$TransactionBuilder setTransactionId(final String s) {
        this.set("&ti", s);
        return this;
    }
}
