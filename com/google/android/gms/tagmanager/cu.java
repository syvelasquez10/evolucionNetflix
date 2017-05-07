// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class cu extends aj
{
    private static final String ID;
    
    static {
        ID = a.S.toString();
    }
    
    public cu() {
        super(cu.ID, new String[0]);
    }
    
    @Override
    public boolean jX() {
        return true;
    }
    
    @Override
    public d.a x(final Map<String, d.a> map) {
        return dh.r(Build$VERSION.SDK_INT);
    }
}
