// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads.mediation.customevent;

import java.util.HashMap;
import com.google.ads.mediation.NetworkExtras;

public final class CustomEventExtras implements NetworkExtras
{
    private final HashMap<String, Object> rQ;
    
    public CustomEventExtras() {
        this.rQ = new HashMap<String, Object>();
    }
    
    public Object getExtra(final String s) {
        return this.rQ.get(s);
    }
    
    public void setExtra(final String s, final Object o) {
        this.rQ.put(s, o);
    }
}
