// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.n;
import java.util.HashMap;
import java.util.Map;

public class ProductAction
{
    public static final String ACTION_ADD = "add";
    public static final String ACTION_CHECKOUT = "checkout";
    public static final String ACTION_CHECKOUT_OPTION = "checkout_option";
    @Deprecated
    public static final String ACTION_CHECKOUT_OPTIONS = "checkout_options";
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_DETAIL = "detail";
    public static final String ACTION_PURCHASE = "purchase";
    public static final String ACTION_REFUND = "refund";
    public static final String ACTION_REMOVE = "remove";
    Map<String, String> BK;
    
    public ProductAction(final String s) {
        this.BK = new HashMap<String, String>();
        this.put("&pa", s);
    }
    
    public Map<String, String> build() {
        return new HashMap<String, String>(this.BK);
    }
    
    void put(final String s, final String s2) {
        n.b(s, (Object)"Name should be non-null");
        this.BK.put(s, s2);
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
    
    public ProductAction setProductListSource(final String s) {
        this.put("&pls", s);
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
}
