// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.os.Build;
import com.google.android.gms.internal.d;
import java.util.Map;
import com.google.android.gms.internal.a;

class aa extends aj
{
    private static final String ID;
    
    static {
        ID = a.F.toString();
    }
    
    public aa() {
        super(aa.ID, new String[0]);
    }
    
    @Override
    public d.a C(final Map<String, d.a> map) {
        final String manufacturer = Build.MANUFACTURER;
        String s2;
        final String s = s2 = Build.MODEL;
        if (!s.startsWith(manufacturer)) {
            s2 = s;
            if (!manufacturer.equals("unknown")) {
                s2 = manufacturer + " " + s;
            }
        }
        return di.u(s2);
    }
    
    @Override
    public boolean nL() {
        return true;
    }
}
