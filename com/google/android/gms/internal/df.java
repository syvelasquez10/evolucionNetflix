// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

public final class df
{
    private int mOrientation;
    private String pN;
    private String pO;
    private String pP;
    private List<String> pQ;
    private String pR;
    private String pS;
    private List<String> pT;
    private long pU;
    private boolean pV;
    private final long pW;
    private List<String> pX;
    private long pY;
    
    public df() {
        this.pU = -1L;
        this.pV = false;
        this.pW = -1L;
        this.pY = -1L;
        this.mOrientation = -1;
    }
    
    private static String a(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            return list.get(0);
        }
        return null;
    }
    
    private static long b(Map<String, List<String>> s, final String s2) {
        final List<String> list = ((Map<String, List<String>>)s).get(s2);
        if (list != null && !list.isEmpty()) {
            s = list.get(0);
            try {
                return (long)(Float.parseFloat(s) * 1000.0f);
            }
            catch (NumberFormatException ex) {
                dw.z("Could not parse float from " + s2 + " header: " + s);
            }
        }
        return -1L;
    }
    
    private static List<String> c(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            final String s2 = list.get(0);
            if (s2 != null) {
                return Arrays.asList(s2.trim().split("\\s+"));
            }
        }
        return null;
    }
    
    private void f(final Map<String, List<String>> map) {
        this.pN = a(map, "X-Afma-Ad-Size");
    }
    
    private void g(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Click-Tracking-Urls");
        if (c != null) {
            this.pQ = c;
        }
    }
    
    private void h(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Debug-Dialog");
        if (list != null && !list.isEmpty()) {
            this.pR = list.get(0);
        }
    }
    
    private void i(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Tracking-Urls");
        if (c != null) {
            this.pT = c;
        }
    }
    
    private void j(final Map<String, List<String>> map) {
        final long b = b(map, "X-Afma-Interstitial-Timeout");
        if (b != -1L) {
            this.pU = b;
        }
    }
    
    private void k(final Map<String, List<String>> map) {
        this.pS = a(map, "X-Afma-ActiveView");
    }
    
    private void l(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Mediation");
        if (list != null && !list.isEmpty()) {
            this.pV = Boolean.valueOf(list.get(0));
        }
    }
    
    private void m(final Map<String, List<String>> map) {
        final List<String> c = c(map, "X-Afma-Manual-Tracking-Urls");
        if (c != null) {
            this.pX = c;
        }
    }
    
    private void n(final Map<String, List<String>> map) {
        final long b = b(map, "X-Afma-Refresh-Rate");
        if (b != -1L) {
            this.pY = b;
        }
    }
    
    private void o(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            final String s = list.get(0);
            if ("portrait".equalsIgnoreCase(s)) {
                this.mOrientation = dq.bA();
            }
            else if ("landscape".equalsIgnoreCase(s)) {
                this.mOrientation = dq.bz();
            }
        }
    }
    
    public void a(final String po, final Map<String, List<String>> map, final String pp) {
        this.pO = po;
        this.pP = pp;
        this.e(map);
    }
    
    public void e(final Map<String, List<String>> map) {
        this.f(map);
        this.g(map);
        this.h(map);
        this.i(map);
        this.j(map);
        this.l(map);
        this.m(map);
        this.n(map);
        this.o(map);
        this.k(map);
    }
    
    public cz g(final long n) {
        return new cz(this.pO, this.pP, this.pQ, this.pT, this.pU, this.pV, -1L, this.pX, this.pY, this.mOrientation, this.pN, n, this.pR, this.pS);
    }
}
