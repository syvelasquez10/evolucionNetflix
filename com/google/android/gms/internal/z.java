// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import org.json.JSONObject;

public final class z
{
    private final String le;
    private final JSONObject lf;
    private final String lg;
    private final String lh;
    
    public z(final String lg, final dx dx, final String le, final JSONObject lf) {
        this.lh = dx.rq;
        this.lf = lf;
        this.lg = lg;
        this.le = le;
    }
    
    public String al() {
        return this.le;
    }
    
    public String am() {
        return this.lh;
    }
    
    public JSONObject an() {
        return this.lf;
    }
    
    public String ao() {
        return this.lg;
    }
}
