// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public final class cg
{
    private final Object fx;
    private cw gv;
    private String hK;
    private String hL;
    public final an hM;
    public final an hN;
    private int hm;
    
    public cg(final String hk) {
        this.fx = new Object();
        this.hm = -2;
        this.hM = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                synchronized (cg.this.fx) {
                    ct.v("Invalid " + map.get("type") + " request error: " + map.get("errors"));
                    cg.this.hm = 1;
                    cg.this.fx.notify();
                }
            }
        };
        this.hN = new an() {
            @Override
            public void a(final cw cw, final Map<String, String> map) {
                synchronized (cg.this.fx) {
                    final String s = map.get("url");
                    if (s == null) {
                        ct.v("URL missing in loadAdUrl GMSG.");
                        return;
                    }
                    String replaceAll = s;
                    if (s.contains("%40mediation_adapters%40")) {
                        replaceAll = s.replaceAll("%40mediation_adapters%40", cl.b(cw.getContext(), map.get("check_adapters"), cg.this.hK));
                        ct.u("Ad request URL modified to " + replaceAll);
                    }
                    cg.this.hL = replaceAll;
                    cg.this.fx.notify();
                }
            }
        };
        this.hK = hk;
    }
    
    public String ap() {
        synchronized (this.fx) {
            while (this.hL == null && this.hm == -2) {
                try {
                    this.fx.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    ct.v("Ad request service was interrupted.");
                    return null;
                }
                break;
            }
            return this.hL;
        }
    }
    
    public void b(final cw gv) {
        synchronized (this.fx) {
            this.gv = gv;
        }
    }
    
    public int getErrorCode() {
        synchronized (this.fx) {
            return this.hm;
        }
    }
}
