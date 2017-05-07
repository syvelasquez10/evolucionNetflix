// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.text.TextUtils;

class ap
{
    private final long XX;
    private String XY;
    private final long vi;
    private final long vj;
    
    ap(final long vi, final long vj, final long xx) {
        this.vi = vi;
        this.vj = vj;
        this.XX = xx;
    }
    
    void K(final String xy) {
        if (xy == null || TextUtils.isEmpty((CharSequence)xy.trim())) {
            return;
        }
        this.XY = xy;
    }
    
    long cP() {
        return this.vi;
    }
    
    long kD() {
        return this.XX;
    }
    
    String kE() {
        return this.XY;
    }
}
