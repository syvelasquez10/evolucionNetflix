// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import android.content.Context;

class e extends aj
{
    private static final String ID;
    private static final String anI;
    private static final String anJ;
    private final Context lB;
    
    static {
        ID = a.Y.toString();
        anI = b.bW.toString();
        anJ = b.bZ.toString();
    }
    
    public e(final Context lb) {
        super(e.ID, new String[] { e.anJ });
        this.lB = lb;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final d.a a = map.get(e.anJ);
        if (a == null) {
            return di.pI();
        }
        final String j = di.j(a);
        final d.a a2 = map.get(e.anI);
        String i;
        if (a2 != null) {
            i = di.j(a2);
        }
        else {
            i = null;
        }
        final String f = ay.f(this.lB, j, i);
        if (f != null) {
            return di.u(f);
        }
        return di.pI();
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
