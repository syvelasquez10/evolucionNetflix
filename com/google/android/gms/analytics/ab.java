// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

class ab
{
    private final Map<String, Integer> vt;
    private final Map<String, String> vu;
    private final boolean vv;
    private final String vw;
    
    ab(final String vw, final boolean vv) {
        this.vt = new HashMap<String, Integer>();
        this.vu = new HashMap<String, String>();
        this.vv = vv;
        this.vw = vw;
    }
    
    void c(final String s, final int n) {
        if (!this.vv) {
            return;
        }
        Integer value;
        if ((value = this.vt.get(s)) == null) {
            value = 0;
        }
        this.vt.put(s, value + n);
    }
    
    String cU() {
        if (!this.vv) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(this.vw);
        for (final String s : this.vt.keySet()) {
            sb.append("&").append(s).append("=").append(this.vt.get(s));
        }
        for (final String s2 : this.vu.keySet()) {
            sb.append("&").append(s2).append("=").append(this.vu.get(s2));
        }
        return sb.toString();
    }
}
