// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class ap
{
    private final long AF;
    private final long AG;
    private final long apb;
    private String apc;
    
    ap(final long af, final long ag, final long apb) {
        this.AF = af;
        this.AG = ag;
        this.apb = apb;
    }
    
    void ak(final String apc) {
        if (apc == null || TextUtils.isEmpty((CharSequence)apc.trim())) {
            return;
        }
        this.apc = apc;
    }
    
    long eH() {
        return this.AF;
    }
    
    long or() {
        return this.apb;
    }
    
    String os() {
        return this.apc;
    }
}
