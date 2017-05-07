// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class cf extends aj
{
    private static final String ID;
    private static final String aqa;
    private static final String aqb;
    
    static {
        ID = a.Q.toString();
        aqa = b.dz.toString();
        aqb = b.dy.toString();
    }
    
    public cf() {
        super(cf.ID, new String[0]);
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        final d$a d$a = map.get(cf.aqa);
        final d$a d$a2 = map.get(cf.aqb);
        Label_0118: {
            if (d$a == null || d$a == di.pI() || d$a2 == null || d$a2 == di.pI()) {
                break Label_0118;
            }
            final dh k = di.k(d$a);
            final dh i = di.k(d$a2);
            if (k == di.pG() || i == di.pG()) {
                break Label_0118;
            }
            final double doubleValue = k.doubleValue();
            final double doubleValue2 = i.doubleValue();
            if (doubleValue > doubleValue2) {
                break Label_0118;
            }
            return di.u(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
        }
        final double doubleValue2 = 2.147483647E9;
        final double doubleValue = 0.0;
        return di.u(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
