// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.Map;

class ft$1 implements by
{
    final /* synthetic */ ft uu;
    
    ft$1(final ft uu) {
        this.uu = uu;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        synchronized (this.uu.mw) {
            if (this.uu.ur.isDone()) {
                return;
            }
            final fv fv = new fv(1, map);
            gs.W("Invalid " + fv.getType() + " request error: " + fv.cM());
            this.uu.ur.a(fv);
        }
    }
}
