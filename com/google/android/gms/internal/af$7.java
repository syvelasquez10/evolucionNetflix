// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

class af$7 implements by
{
    final /* synthetic */ af mT;
    
    af$7(final af mt) {
        this.mT = mt;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        if (this.mT.a(map) && map.containsKey("isVisible")) {
            this.mT.d(Boolean.valueOf("1".equals(map.get("isVisible")) || "true".equals(map.get("isVisible"))));
        }
    }
}
