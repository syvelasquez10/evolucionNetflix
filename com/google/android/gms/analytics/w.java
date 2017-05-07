// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import android.text.TextUtils;

class w
{
    private String AE;
    private final long AF;
    private final long AG;
    private String AH;
    
    w(final String ae, final long af, final long ag) {
        this.AH = "https:";
        this.AE = ae;
        this.AF = af;
        this.AG = ag;
    }
    
    void aj(final String ae) {
        this.AE = ae;
    }
    
    void ak(final String s) {
        if (s != null && !TextUtils.isEmpty((CharSequence)s.trim()) && s.toLowerCase().startsWith("http:")) {
            this.AH = "http:";
        }
    }
    
    String eG() {
        return this.AE;
    }
    
    long eH() {
        return this.AF;
    }
    
    long eI() {
        return this.AG;
    }
    
    String eJ() {
        return this.AH;
    }
}
