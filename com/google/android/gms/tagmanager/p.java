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
    private final String Xl;
    
    static {
        ID = a.D.toString();
    }
    
    public p(final String xl) {
        super(p.ID, new String[0]);
        this.Xl = xl;
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        if (this.Xl == null) {
            return dh.lT();
        }
        return dh.r(this.Xl);
    }
}
