// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class ce extends aj
{
    private static final String ID;
    private static final String YX;
    private static final String YY;
    
    static {
        ID = a.O.toString();
        YX = b.da.toString();
        YY = b.cZ.toString();
    }
    
    public ce() {
        super(ce.ID, new String[0]);
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(ce.YX);
        final d.a a2 = map.get(ce.YY);
        Label_0118: {
            if (a == null || a == dh.lT() || a2 == null || a2 == dh.lT()) {
                break Label_0118;
            }
            final dg k = dh.k(a);
            final dg i = dh.k(a2);
            if (k == dh.lR() || i == dh.lR()) {
                break Label_0118;
            }
            final double doubleValue = k.doubleValue();
            final double doubleValue2 = i.doubleValue();
            if (doubleValue > doubleValue2) {
                break Label_0118;
            }
            return dh.r(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
        }
        final double doubleValue2 = 2.147483647E9;
        final double doubleValue = 0.0;
        return dh.r(Math.round((doubleValue2 - doubleValue) * Math.random() + doubleValue));
    }
}
