// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public final class de
{
    private dz lC;
    private final Object li;
    private int oS;
    private String pI;
    private String pJ;
    public final bb pK;
    public final bb pL;
    
    public de(final String pi) {
        this.li = new Object();
        this.oS = -2;
        this.pK = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                synchronized (de.this.li) {
                    dw.z("Invalid " + map.get("type") + " request error: " + map.get("errors"));
                    de.this.oS = 1;
                    de.this.li.notify();
                }
            }
        };
        this.pL = new bb() {
            @Override
            public void b(final dz dz, final Map<String, String> map) {
                synchronized (de.this.li) {
                    final String s = map.get("url");
                    if (s == null) {
                        dw.z("URL missing in loadAdUrl GMSG.");
                        return;
                    }
                    String replaceAll = s;
                    if (s.contains("%40mediation_adapters%40")) {
                        replaceAll = s.replaceAll("%40mediation_adapters%40", dn.b(dz.getContext(), map.get("check_adapters"), de.this.pI));
                        dw.y("Ad request URL modified to " + replaceAll);
                    }
                    de.this.pJ = replaceAll;
                    de.this.li.notify();
                }
            }
        };
        this.pI = pi;
    }
    
    public void b(final dz lc) {
        synchronized (this.li) {
            this.lC = lc;
        }
    }
    
    public String bj() {
        synchronized (this.li) {
            while (this.pJ == null && this.oS == -2) {
                try {
                    this.li.wait();
                    continue;
                }
                catch (InterruptedException ex) {
                    dw.z("Ad request service was interrupted.");
                    return null;
                }
                break;
            }
            return this.pJ;
        }
    }
    
    public int getErrorCode() {
        synchronized (this.li) {
            return this.oS;
        }
    }
}
