// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

@ez
public final class fu
{
    private int mOrientation;
    private String pn;
    private List<String> uA;
    private long uB;
    private boolean uC;
    private final long uD;
    private long uE;
    private boolean uF;
    private boolean uG;
    private boolean uH;
    private boolean uI;
    private List<String> ua;
    private String uv;
    private String uw;
    private List<String> ux;
    private String uy;
    private String uz;
    
    public fu() {
        this.uB = -1L;
        this.uC = false;
        this.uD = -1L;
        this.uE = -1L;
        this.mOrientation = -1;
        this.uF = false;
        this.uG = false;
        this.uH = false;
        this.uI = false;
    }
    
    static String a(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    static long b(Map<String, List<String>> s, final String s2) {
        final List<String> list = ((Map<String, List<String>>)s).get(s2);
        if (list != null && !list.isEmpty()) {
            s = list.get(0);
            try {
                return (long)(Float.parseFloat(s) * 1000.0f);
            }
            catch (NumberFormatException ex) {
                gs.W("Could not parse float from " + s2 + " header: " + s);
            }
        }
        return -1L;
    }
    
    static List<String> c(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            final String s2 = list.get(0);
            if (s2 != null) {
                return Arrays.asList(s2.trim().split("\\s+"));
            }
        }
        return null;
    }
    
    private boolean d(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        return list != null && !list.isEmpty() && Boolean.valueOf(list.get(0));
    }
    
    private void f(final Map<String, List<String>> map) {
        this.uv = a(map, "X-Afma-Ad-Size");
    }
    
    private void g(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Click-Tracking-Urls");
        if (c != null) {
            this.ux = c;
        }
    }
    
    private void h(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.uy = list.get(0);
        }
    }
    
    private void i(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Tracking-Urls");
        if (c != null) {
            this.uA = c;
        }
    }
    
    private void j(final Map<String, List<String>> map) {
        final long b = b(map, "X-Afma-Interstitial-Timeout");
        if (b != -1L) {
            this.uB = b;
        }
    }
    
    private void k(final Map<String, List<String>> map) {
        this.uz = a(map, "X-Afma-ActiveView");
    }
    
    private void l(final Map<String, List<String>> map) {
        this.uG |= this.d(map, "X-Afma-Native");
    }
    
    private void m(final Map<String, List<String>> map) {
        this.uF |= this.d(map, "X-Afma-Custom-Rendering-Allowed");
    }
    
    private void n(final Map<String, List<String>> map) {
        this.uC |= this.d(map, "X-Afma-Mediation");
    }
    
    private void o(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Manual-Tracking-Urls");
        if (c != null) {
            this.ua = c;
        }
    }
    
    private void p(final Map<String, List<String>> map) {
        final long b = b(map, "X-Afma-Refresh-Rate");
        if (b != -1L) {
            this.uE = b;
        }
    }
    
    private void q(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            final String s = list.get(0);
            if ("portrait".equalsIgnoreCase(s)) {
                this.mOrientation = gj.dn();
            }
            else if ("landscape".equalsIgnoreCase(s)) {
                this.mOrientation = gj.dm();
            }
        }
    }
    
    private void r(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Use-HTTPS");
        if (list != null && !list.isEmpty()) {
            this.uH = Boolean.valueOf(list.get(0));
        }
    }
    
    private void s(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Content-Url-Opted-Out");
        if (list != null && !list.isEmpty()) {
            this.uI = Boolean.valueOf(list.get(0));
        }
    }
    
    public void a(final String uw, final Map<String, List<String>> map, final String pn) {
        this.uw = uw;
        this.pn = pn;
        this.e(map);
    }
    
    public void e(final Map<String, List<String>> map) {
        this.f(map);
        this.g(map);
        this.h(map);
        this.i(map);
        this.j(map);
        this.n(map);
        this.o(map);
        this.p(map);
        this.q(map);
        this.k(map);
        this.r(map);
        this.m(map);
        this.l(map);
        this.s(map);
    }
    
    public fk i(final long n) {
        return new fk(this.uw, this.pn, this.ux, this.uA, this.uB, this.uC, -1L, this.ua, this.uE, this.mOrientation, this.uv, n, this.uy, this.uz, this.uF, this.uG, this.uH, this.uI);
    }
}
