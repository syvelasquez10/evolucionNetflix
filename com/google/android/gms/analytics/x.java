// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.text.TextUtils;

class x
{
    private String vh;
    private final long vi;
    private final long vj;
    private String vk;
    
    x(final String vh, final long vi, final long vj) {
        this.vk = "https:";
        this.vh = vh;
        this.vi = vi;
        this.vj = vj;
    }
    
    void J(final String vh) {
        this.vh = vh;
    }
    
    void K(final String s) {
        if (s != null && !TextUtils.isEmpty((CharSequence)s.trim()) && s.toLowerCase().startsWith("http:")) {
            this.vk = "http:";
        }
    }
    
    String cO() {
        return this.vh;
    }
    
    long cP() {
        return this.vi;
    }
    
    long cQ() {
        return this.vj;
    }
    
    String cR() {
        return this.vk;
    }
}
