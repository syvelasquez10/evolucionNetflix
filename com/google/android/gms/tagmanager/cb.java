// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Map;
import com.google.android.gms.internal.a;
import com.google.android.gms.internal.d;

class cb extends aj
{
    private static final String ID;
    private static final d.a YM;
    
    static {
        ID = a.N.toString();
        YM = dh.r("Android");
    }
    
    public cb() {
        super(cb.ID, new String[0]);
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        return cb.YM;
    }
}
