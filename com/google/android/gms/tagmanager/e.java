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
    private static final String WA;
    private static final String WB;
    private final Context kI;
    
    static {
        ID = a.W.toString();
        WA = b.bH.toString();
        WB = b.bK.toString();
    }
    
    public e(final Context ki) {
        super(e.ID, new String[] { e.WB });
        this.kI = ki;
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final d.a a = map.get(e.WB);
        if (a == null) {
            return dh.lT();
        }
        final String j = dh.j(a);
        final d.a a2 = map.get(e.WA);
        String i;
        if (a2 != null) {
            i = dh.j(a2);
        }
        else {
            i = null;
        }
        final String e = ay.e(this.kI, j, i);
        if (e != null) {
            return dh.r(e);
        }
        return dh.lT();
    }
}
