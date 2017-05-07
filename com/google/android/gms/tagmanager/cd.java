// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;

abstract class cd extends aj
{
    private static final String aoU;
    private static final String apQ;
    
    static {
        aoU = b.bw.toString();
        apQ = b.bx.toString();
    }
    
    public cd(final String s) {
        super(s, new String[] { cd.aoU, cd.apQ });
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final Iterator<d.a> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == di.pI()) {
                return di.u(false);
            }
        }
        final d.a a = map.get(cd.aoU);
        final d.a a2 = map.get(cd.apQ);
        return di.u(a != null && a2 != null && this.a(a, a2, map));
    }
    
    protected abstract boolean a(final d.a p0, final d.a p1, final Map<String, d.a> p2);
    
    @Override
    public boolean nL() {
        return true;
    }
}
