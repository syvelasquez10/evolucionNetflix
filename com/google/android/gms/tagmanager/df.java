// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;

abstract class df extends aj
{
    public df(final String s, final String... array) {
        super(s, array);
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        this.z(map);
        return dh.lT();
    }
    
    public abstract void z(final Map<String, d.a> p0);
}
