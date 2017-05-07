// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.Map;

class ft$2 implements by
{
    final /* synthetic */ ft uu;
    
    ft$2(final ft uu) {
        this.uu = uu;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final fv fv;
        final String url;
        synchronized (this.uu.mw) {
            if (this.uu.ur.isDone()) {
                return;
            }
            fv = new fv(-2, map);
            url = fv.getUrl();
            if (url == null) {
                gs.W("URL missing in loadAdUrl GMSG.");
                return;
            }
        }
        if (url.contains("%40mediation_adapters%40")) {
            final gv gv2;
            final String replaceAll = url.replaceAll("%40mediation_adapters%40", gf.a(gv2.getContext(), map.get("check_adapters"), this.uu.uq));
            fv.setUrl(replaceAll);
            gs.V("Ad request URL modified to " + replaceAll);
        }
        this.uu.ur.a(fv);
    }
    // monitorexit(o)
}
