// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import java.util.HashMap;
import com.google.ads.mediation.NetworkExtras;

public final class CustomEventExtras implements NetworkExtras
{
    private final HashMap<String, Object> ji;
    
    public CustomEventExtras() {
        this.ji = new HashMap<String, Object>();
    }
    
    public Object getExtra(final String s) {
        return this.ji.get(s);
    }
    
    public void setExtra(final String s, final Object o) {
        this.ji.put(s, o);
    }
}
