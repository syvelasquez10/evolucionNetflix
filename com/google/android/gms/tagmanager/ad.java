// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class ad extends dc
{
    private static final String ID;
    
    static {
        ID = a.ai.toString();
    }
    
    public ad() {
        super(ad.ID);
    }
    
    @Override
    protected boolean a(final String s, final String s2, final Map<String, d.a> map) {
        return s.endsWith(s2);
    }
}
