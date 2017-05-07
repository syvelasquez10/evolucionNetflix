// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;

abstract class dg extends aj
{
    public dg(final String s, final String... array) {
        super(s, array);
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        this.E(map);
        return di.pI();
    }
    
    public abstract void E(final Map<String, d$a> p0);
    
    @Override
    public boolean nL() {
        return false;
    }
}
