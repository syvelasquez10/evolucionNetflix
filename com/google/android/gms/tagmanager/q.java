// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.a;

class q extends dd
{
    private static final String ID;
    
    static {
        ID = a.as.toString();
    }
    
    public q() {
        super(q.ID);
    }
    
    @Override
    protected boolean a(final String s, final String s2, final Map<String, d$a> map) {
        return s.contains(s2);
    }
}
