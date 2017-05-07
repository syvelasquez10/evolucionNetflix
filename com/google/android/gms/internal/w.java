// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;
import java.util.HashMap;

class w implements y
{
    private dz kU;
    
    public w(final dz ku) {
        this.kU = ku;
    }
    
    @Override
    public void a(final ab ab, final boolean b) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        String s;
        if (b) {
            s = "1";
        }
        else {
            s = "0";
        }
        hashMap.put("isVisible", s);
        this.kU.a("onAdVisibilityChanged", hashMap);
    }
}
