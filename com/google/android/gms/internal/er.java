// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.dynamic.e;
import android.view.View;
import com.google.android.gms.dynamic.d;

@ez
public final class er extends es$a
{
    private final aa sM;
    private final String sN;
    private final String sO;
    
    public er(final aa sm, final String sn, final String so) {
        this.sM = sm;
        this.sN = sn;
        this.sO = so;
    }
    
    public void ar() {
        this.sM.ar();
    }
    
    public void as() {
        this.sM.as();
    }
    
    public void c(final d d) {
        if (d == null) {
            return;
        }
        this.sM.b(e.f(d));
    }
    
    public String cv() {
        return this.sN;
    }
    
    public String cw() {
        return this.sO;
    }
}
