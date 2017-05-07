// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class ah extends aj
{
    private static final String ID;
    private final cs WL;
    
    static {
        ID = a.I.toString();
    }
    
    public ah(final cs wl) {
        super(ah.ID, new String[0]);
        this.WL = wl;
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final String lx = this.WL.lx();
        if (lx == null) {
            return dh.lT();
        }
        return dh.r(lx);
    }
}
