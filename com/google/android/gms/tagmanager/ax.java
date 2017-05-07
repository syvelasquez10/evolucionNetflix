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
    private static final String WA;
    private final Context kI;
    
    static {
        ID = a.ab.toString();
        WA = b.bH.toString();
    }
    
    public ax(final Context ki) {
        super(ax.ID, new String[0]);
        this.kI = ki;
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        String j;
        if (map.get(ax.WA) != null) {
            j = dh.j(map.get(ax.WA));
        }
        else {
            j = null;
        }
        final String d = ay.d(this.kI, j);
        if (d != null) {
            return dh.r(d);
        }
        return dh.lT();
    }
}
