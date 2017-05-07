// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Iterator;
import java.util.Map;
import java.util.List;
import com.google.android.gms.internal.d$a;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class w extends dg
{
    private static final String ID;
    private static final String VALUE;
    private static final String aoP;
    private final DataLayer anS;
    
    static {
        ID = a.ah.toString();
        VALUE = b.ff.toString();
        aoP = b.bS.toString();
    }
    
    public w(final DataLayer anS) {
        super(w.ID, new String[] { w.VALUE });
        this.anS = anS;
    }
    
    private void a(final d$a d$a) {
        if (d$a != null && d$a != di.pC()) {
            final String j = di.j(d$a);
            if (j != di.pH()) {
                this.anS.cs(j);
            }
        }
    }
    
    private void b(final d$a d$a) {
        if (d$a != null && d$a != di.pC()) {
            final Object o = di.o(d$a);
            if (o instanceof List) {
                for (final Map<String, Object> next : (List<Object>)o) {
                    if (next instanceof Map) {
                        this.anS.push(next);
                    }
                }
            }
        }
    }
    
    @Override
    public void E(final Map<String, d$a> map) {
        this.b(map.get(w.VALUE));
        this.a(map.get(w.aoP));
    }
}
