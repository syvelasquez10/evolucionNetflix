// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class dd extends aj
{
    private static final String ID;
    
    static {
        ID = a.U.toString();
    }
    
    public dd() {
        super(dd.ID, new String[0]);
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        return dh.r(System.currentTimeMillis());
    }
}
