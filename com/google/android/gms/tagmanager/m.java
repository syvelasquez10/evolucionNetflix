// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.b;
import com.google.android.gms.internal.a;

class m extends aj
{
    private static final String ID;
    private static final String VALUE;
    
    static {
        ID = a.A.toString();
        VALUE = b.ew.toString();
    }
    
    public m() {
        super(m.ID, new String[] { m.VALUE });
    }
    
    public static String ka() {
        return m.ID;
    }
    
    public static String kb() {
        return m.VALUE;
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        return map.get(m.VALUE);
    }
}
