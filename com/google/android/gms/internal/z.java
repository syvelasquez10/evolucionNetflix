// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.HashMap;

@ez
class z implements ac
{
    private gv mi;
    
    public z(final gv mi) {
        this.mi = mi;
    }
    
    @Override
    public void a(final af af, final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("isVisible", s);
        this.mi.a("onAdVisibilityChanged", hashMap);
    }
}
