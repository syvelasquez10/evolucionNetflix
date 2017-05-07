// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class bj extends aj
{
    private static final String ID;
    private static final String aoU;
    
    static {
        ID = a.ai.toString();
        aoU = b.bw.toString();
    }
    
    public bj() {
        super(bj.ID, new String[] { bj.aoU });
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        return di.u(di.j(map.get(bj.aoU)).toLowerCase());
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
