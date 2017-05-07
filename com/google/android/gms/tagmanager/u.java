// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class u extends aj
{
    private static final String ID;
    private static final String NAME;
    private static final String XA;
    private final DataLayer WK;
    
    static {
        ID = a.C.toString();
        NAME = b.dc.toString();
        XA = b.cb.toString();
    }
    
    public u(final DataLayer wk) {
        super(u.ID, new String[] { u.NAME });
        this.WK = wk;
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final Object value = this.WK.get(dh.j(map.get(u.NAME)));
        if (value != null) {
            return dh.r(value);
        }
        final d.a a = map.get(u.XA);
        if (a != null) {
            return a;
        }
        return dh.lT();
    }
}
