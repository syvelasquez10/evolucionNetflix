// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.d$a;

abstract class dd extends cd
{
    public dd(final String s) {
        super(s);
    }
    
    @Override
    protected boolean a(final d$a d$a, final d$a d$a2, final Map<String, d$a> map) {
        final String j = di.j(d$a);
        final String i = di.j(d$a2);
        return j != di.pH() && i != di.pH() && this.a(j, i, map);
    }
    
    protected abstract boolean a(final String p0, final String p1, final Map<String, d$a> p2);
}
