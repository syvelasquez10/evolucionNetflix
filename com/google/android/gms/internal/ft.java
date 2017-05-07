// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;

@ez
public final class ft
{
    private gv md;
    private final Object mw;
    private String uq;
    private gk<fv> ur;
    public final by us;
    public final by ut;
    
    public ft(final String uq) {
        this.mw = new Object();
        this.ur = new gk<fv>();
        this.us = new ft$1(this);
        this.ut = new ft$2(this);
        this.uq = uq;
    }
    
    public void b(final gv md) {
        this.md = md;
    }
    
    public Future<fv> cL() {
        return this.ur;
    }
}
