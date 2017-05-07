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
    private final a anH;
    
    static {
        ID = com.google.android.gms.internal.a.u.toString();
    }
    
    public b(final Context context) {
        this(a.V(context));
    }
    
    b(final a anH) {
        super(b.ID, new String[0]);
        this.anH = anH;
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final String nh = this.anH.nH();
        if (nh == null) {
            return di.pI();
        }
        return di.u(nh);
    }
    
    @Override
    public boolean nL() {
        return false;
    }
}
