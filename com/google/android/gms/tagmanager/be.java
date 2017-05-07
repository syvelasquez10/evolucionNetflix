// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class be extends by
{
    private static final String ID;
    
    static {
        ID = a.au.toString();
    }
    
    public be() {
        super(be.ID);
    }
    
    @Override
    protected boolean a(final dh dh, final dh dh2, final Map<String, d.a> map) {
        return dh.a(dh2) < 0;
    }
}
