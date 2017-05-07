// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;
import android.content.Context;

class ax extends aj
{
    private static final String ID;
    private static final String anI;
    private final Context lB;
    
    static {
        ID = a.ad.toString();
        anI = b.bW.toString();
    }
    
    public ax(final Context lb) {
        super(ax.ID, new String[0]);
        this.lB = lb;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        String j;
        if (map.get(ax.anI) != null) {
            j = di.j(map.get(ax.anI));
        }
        else {
            j = null;
        }
        final String e = ay.e(this.lB, j);
        if (e != null) {
            return di.u(e);
        }
        return di.pI();
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
