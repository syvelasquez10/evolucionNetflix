// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.a;

class ca extends aj
{
    private static final String ID;
    
    static {
        ID = a.O.toString();
    }
    
    public ca() {
        super(ca.ID, new String[0]);
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        return di.u(Build$VERSION.RELEASE);
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
