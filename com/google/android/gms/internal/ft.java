// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.concurrent.Future;
import java.util.Map;

@ez
public final class ft
{
    private gv md;
    private final Object mw;
    private String uq;
    private gk<fv> ur;
    public final by us;
    public final by ut;
    
    public ft(final String uq) {
        this.mw = new Object();
        this.ur = new gk<fv>();
        this.us = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                synchronized (ft.this.mw) {
                    if (ft.this.ur.isDone()) {
                        return;
                    }
                    final fv fv = new fv(1, map);
                    gs.W("Invalid " + fv.getType() + " request error: " + fv.cM());
                    ft.this.ur.a(fv);
                }
            }
        };
        this.ut = new by() {
            @Override
            public void a(final gv gv, final Map<String, String> map) {
                final fv fv;
                final String url;
                synchronized (ft.this.mw) {
                    if (ft.this.ur.isDone()) {
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
                    final String replaceAll = url.replaceAll("%40mediation_adapters%40", gf.a(gv2.getContext(), map.get("check_adapters"), ft.this.uq));
                    fv.setUrl(replaceAll);
                    gs.V("Ad request URL modified to " + replaceAll);
                }
                ft.this.ur.a(fv);
            }
            // monitorexit(o)
        };
        this.uq = uq;
    }
    
    public void b(final gv md) {
        this.md = md;
    }
    
    public Future<fv> cL() {
        return this.ur;
    }
}
