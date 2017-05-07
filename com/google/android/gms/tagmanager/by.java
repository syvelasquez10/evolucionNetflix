// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d;

abstract class by extends cd
{
    public by(final String s) {
        super(s);
    }
    
    @Override
    protected boolean a(final d.a a, final d.a a2, final Map<String, d.a> map) {
        final dh k = di.k(a);
        final dh i = di.k(a2);
        return k != di.pG() && i != di.pG() && this.a(k, i, map);
    }
    
    protected abstract boolean a(final dh p0, final dh p1, final Map<String, d.a> p2);
}
