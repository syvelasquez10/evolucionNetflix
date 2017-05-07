// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.util.Iterator;
import java.util.HashMap;
import java.util.Map;

class aa
{
    private final Map<String, Integer> AU;
    private final Map<String, String> AV;
    private final boolean AW;
    private final String AX;
    
    aa(final String ax, final boolean aw) {
        this.AU = new HashMap<String, Integer>();
        this.AV = new HashMap<String, String>();
        this.AW = aw;
        this.AX = ax;
    }
    
    void e(final String s, final int n) {
        if (!this.AW) {
            return;
        }
        Integer value;
        if ((value = this.AU.get(s)) == null) {
            value = 0;
        }
        this.AU.put(s, value + n);
    }
    
    String eM() {
        if (!this.AW) {
            return "";
        }
        final StringBuilder sb = new StringBuilder();
        sb.append(this.AX);
        for (final String s : this.AU.keySet()) {
            sb.append("&").append(s).append("=").append(this.AU.get(s));
        }
        for (final String s2 : this.AV.keySet()) {
            sb.append("&").append(s2).append("=").append(this.AV.get(s2));
        }
        return sb.toString();
    }
}
