// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build$VERSION;
import com.google.android.gms.internal.d$a;
import java.util.Map;
import com.google.android.gms.internal.a;

class cv extends aj
{
    private static final String ID;
    
    static {
        ID = a.U.toString();
    }
    
    public cv() {
        super(cv.ID, new String[0]);
    }
    
    @Override
    public d$a C(final Map<String, d$a> map) {
        return di.u(Build$VERSION.SDK_INT);
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
