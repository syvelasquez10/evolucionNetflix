// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.google.android.gms.internal.d;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class w extends df
{
    private static final String ID;
    private static final String VALUE;
    private static final String XL;
    private final DataLayer WK;
    
    static {
        ID = a.af.toString();
        VALUE = b.ew.toString();
        XL = b.bD.toString();
    }
    
    public w(final DataLayer wk) {
        super(w.ID, new String[] { w.VALUE });
        this.WK = wk;
    }
    
    private void a(final d.a a) {
        if (a != null && a != dh.lN()) {
            final String j = dh.j(a);
            if (j != dh.lS()) {
                this.WK.bv(j);
            }
        }
    }
    
    private void b(final d.a a) {
        if (a != null && a != dh.lN()) {
            final Object o = dh.o(a);
            if (o instanceof List) {
                for (final Map<String, Object> next : (List<Object>)o) {
                    if (next instanceof Map) {
                        this.WK.push(next);
                    }
                }
            }
        }
    }
    
    @Override
    public void z(final Map<String, d.a> map) {
        this.b(map.get(w.VALUE));
        this.a(map.get(w.XL));
    }
}
