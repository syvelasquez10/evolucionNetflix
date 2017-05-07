// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.ecommerce;

import com.google.android.gms.common.internal.n;
import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

public class Promotion
{
    public static final String ACTION_CLICK = "click";
    public static final String ACTION_VIEW = "view";
    Map<String, String> BK;
    
    public Promotion() {
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
}
