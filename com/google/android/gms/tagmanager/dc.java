// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d;

abstract class dc extends cc
{
    public dc(final String s) {
        super(s);
    }
    
    @Override
    protected boolean a(final d.a a, final d.a a2, final Map<String, d.a> map) {
        final String j = dh.j(a);
        final String i = dh.j(a2);
        return j != dh.lS() && i != dh.lS() && this.a(j, i, map);
    }
    
    protected abstract boolean a(final String p0, final String p1, final Map<String, d.a> p2);
}
