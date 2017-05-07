// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.HashMap;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class s extends aj
{
    private static final String ID;
    private static final String WC;
    private static final String Xn;
    private final a Xo;
    
    static {
        ID = com.google.android.gms.internal.a.J.toString();
        Xn = b.cy.toString();
        WC = b.aX.toString();
    }
    
    public s(final a xo) {
        super(s.ID, new String[] { s.Xn });
        this.Xo = xo;
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final String j = dh.j(map.get(s.Xn));
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final d.a a = map.get(s.WC);
        if (a != null) {
            final Object o = dh.o(a);
            if (!(o instanceof Map)) {
                bh.z("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return dh.lT();
            }
            for (final Map.Entry<Object, V> entry : ((Map<Object, V>)o).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return dh.r(this.Xo.b(j, hashMap));
        }
        catch (Exception ex) {
            bh.z("Custom macro/tag " + j + " threw exception " + ex.getMessage());
            return dh.lT();
        }
    }
    
    public interface a
    {
        Object b(final String p0, final Map<String, Object> p1);
    }
}
