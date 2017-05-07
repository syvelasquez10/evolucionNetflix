// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class db extends dc
{
    private static final String ID;
    
    static {
        ID = a.ah.toString();
    }
    
    public db() {
        super(db.ID);
    }
    
    @Override
    protected boolean a(final String s, final String s2, final Map<String, d.a> map) {
        return s.startsWith(s2);
    }
}
