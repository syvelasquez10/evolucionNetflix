// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d$a;

class cc extends aj
{
    private static final String ID;
    private static final d$a apP;
    
    static {
        ID = a.P.toString();
        apP = di.u("Android");
    }
    
    public cc() {
        super(cc.ID, new String[0]);
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        return cc.apP;
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
