// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class dk extends aj
{
    private static final String ID;
    private static final String aoU;
    
    static {
        ID = a.aj.toString();
        aoU = b.bw.toString();
    }
    
    public dk() {
        super(dk.ID, new String[] { dk.aoU });
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        return di.u(di.j(map.get(dk.aoU)).toUpperCase());
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
