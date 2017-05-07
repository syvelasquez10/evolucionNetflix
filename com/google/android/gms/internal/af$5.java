// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.view.View;
import java.util.Map;

class af$5 implements by
{
    final /* synthetic */ af mT;
    
    af$5(final af mt) {
        this.mT = mt;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        if (!this.mT.a(map)) {
            return;
        }
        this.mT.a((View)gv, map);
    }
}
