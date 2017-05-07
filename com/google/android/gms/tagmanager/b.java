// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import android.content.Context;

class b extends aj
{
    private static final String ID;
    private final a Wz;
    
    static {
        ID = com.google.android.gms.internal.a.u.toString();
    }
    
    public b(final Context context) {
        this(a.E(context));
    }
    
    b(final a wz) {
        super(b.ID, new String[0]);
        this.Wz = wz;
    }
    
    @Override
    public boolean jX() {
        return false;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        final String jt = this.Wz.jT();
        if (jt == null) {
            return dh.lT();
        }
        return dh.r(jt);
    }
}
