// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class p extends aj
{
    private static final String ID;
    private final String Sq;
    
    static {
        ID = a.D.toString();
    }
    
    public p(final String sq) {
        super(p.ID, new String[0]);
        this.Sq = sq;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        if (this.Sq == null) {
            return di.pI();
        }
        return di.u(this.Sq);
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
