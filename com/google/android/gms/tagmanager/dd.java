// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d;

abstract class dd extends cd
{
    public dd(final String s) {
        super(s);
    }
    
    @Override
    protected boolean a(final d.a a, final d.a a2, final Map<String, d.a> map) {
        final String j = di.j(a);
        final String i = di.j(a2);
        return j != di.pH() && i != di.pH() && this.a(j, i, map);
    }
    
    protected abstract boolean a(final String p0, final String p1, final Map<String, d.a> p2);
}
