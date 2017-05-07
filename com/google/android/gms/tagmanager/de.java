// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class de extends aj
{
    private static final String ID;
    
    static {
        ID = a.W.toString();
    }
    
    public de() {
        super(de.ID, new String[0]);
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        return di.u(System.currentTimeMillis());
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
