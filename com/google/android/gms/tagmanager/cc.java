// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;
import com.google.android.gms.internal.d;
import com.google.android.gms.internal.b;

abstract class cc extends aj
{
    private static final String XQ;
    private static final String YN;
    
    static {
        XQ = b.bi.toString();
        YN = b.bj.toString();
    }
    
    public cc(final String s) {
        super(s, new String[] { cc.XQ, cc.YN });
    }
    
    protected abstract boolean a(final d.a p0, final d.a p1, final Map<String, d.a> p2);
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final Iterator<d.a> iterator = map.values().iterator();
        while (iterator.hasNext()) {
            if (iterator.next() == dh.lT()) {
                return dh.r(false);
            }
        }
        final d.a a = map.get(cc.XQ);
        final d.a a2 = map.get(cc.YN);
        return dh.r(a != null && a2 != null && this.a(a, a2, map));
    }
}
