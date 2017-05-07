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
    private static final String anK;
    private static final String aot;
    private final a aou;
    
    static {
        ID = com.google.android.gms.internal.a.L.toString();
        aot = b.cV.toString();
        anK = b.bl.toString();
    }
    
    public s(final a aou) {
        super(s.ID, new String[] { s.aot });
        this.aou = aou;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final String j = di.j(map.get(s.aot));
        final HashMap<String, Object> hashMap = new HashMap<String, Object>();
        final d.a a = map.get(s.anK);
        if (a != null) {
            final Object o = di.o(a);
            if (!(o instanceof Map)) {
                bh.W("FunctionCallMacro: expected ADDITIONAL_PARAMS to be a map.");
                return di.pI();
            }
            for (final Map.Entry<Object, V> entry : ((Map<Object, V>)o).entrySet()) {
                hashMap.put(entry.getKey().toString(), entry.getValue());
            }
        }
        try {
            return di.u(this.aou.b(j, hashMap));
        }
        catch (Exception ex) {
            bh.W("Custom macro/tag " + j + " threw exception " + ex.getMessage());
            return di.pI();
        }
    }
    
    @Override
    public boolean nL() {
        return false;
    }
    
    public interface a
    {
        Object b(final String p0, final Map<String, Object> p1);
    }
}
