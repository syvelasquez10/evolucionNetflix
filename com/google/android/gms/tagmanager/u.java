// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class u extends aj
{
    private static final String ID;
    private static final String NAME;
    private static final String aoE;
    private final DataLayer anS;
    
    static {
        ID = a.C.toString();
        NAME = b.dB.toString();
        aoE = b.cr.toString();
    }
    
    public u(final DataLayer anS) {
        super(u.ID, new String[] { u.NAME });
        this.anS = anS;
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        final Object value = this.anS.get(di.j(map.get(u.NAME)));
        if (value != null) {
            return di.u(value);
        }
        final d$a d$a = map.get(u.aoE);
        if (d$a != null) {
            return d$a;
        }
        return di.pI();
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
