// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class be extends bx
{
    private static final String ID;
    
    static {
        ID = a.al.toString();
    }
    
    public be() {
        super(be.ID);
    }
    
    @Override
    protected boolean a(final dg dg, final dg dg2, final Map<String, d.a> map) {
        return dg.a(dg2) < 0;
    }
}
