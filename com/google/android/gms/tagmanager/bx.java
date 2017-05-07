// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d;

abstract class bx extends cc
{
    public bx(final String s) {
        super(s);
    }
    
    @Override
    protected boolean a(final d.a a, final d.a a2, final Map<String, d.a> map) {
        final dg k = dh.k(a);
        final dg i = dh.k(a2);
        return k != dh.lR() && i != dh.lR() && this.a(k, i, map);
    }
    
    protected abstract boolean a(final dg p0, final dg p1, final Map<String, d.a> p2);
}
