// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

public final class ch
{
    private String hP;
    private String hQ;
    private String hR;
    private List<String> hS;
    private List<String> hT;
    private long hU;
    private boolean hV;
    private final long hW;
    private List<String> hX;
    private long hY;
    private int mOrientation;
    
    public ch() {
        this.hU = -1L;
        this.hV = false;
        this.hW = -1L;
        this.hY = -1L;
        this.mOrientation = -1;
    }
    
    private static long a(Map<String, List<String>> s, final String s2) {
        final List<String> list = ((Map<String, List<String>>)s).get(s2);
        if (list != null && !list.isEmpty()) {
            s = list.get(0);
            try {
                return (long)(Float.parseFloat(s) * 1000.0f);
            }
            catch (NumberFormatException ex) {
                ct.v("Could not parse float from " + s2 + " header: " + s);
            }
        }
        return -1L;
    }
    
    private static List<String> b(final Map<String, List<String>> map, final String s) {
        final List<String> list = map.get(s);
        if (list != null && !list.isEmpty()) {
            final String s2 = list.get(0);
            if (s2 != null) {
                return Arrays.asList(s2.trim().split("\\s+"));
            }
        }
        return null;
    }
    
    private void e(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Ad-Size");
        if (list != null && !list.isEmpty()) {
            this.hP = list.get(0);
        }
    }
    
    private void f(final Map<String, List<String>> map) {
        final List<String> b = b(map, "X-Afma-Click-Tracking-Urls");
        if (b != null) {
            this.hS = b;
        }
    }
    
    private void g(final Map<String, List<String>> map) {
        final List<String> b = b(map, "X-Afma-Tracking-Urls");
        if (b != null) {
            this.hT = b;
        }
    }
    
    private void h(final Map<String, List<String>> map) {
        final long a = a(map, "X-Afma-Interstitial-Timeout");
        if (a != -1L) {
            this.hU = a;
        }
    }
    
    private void i(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Mediation");
        if (list != null && !list.isEmpty()) {
            this.hV = Boolean.valueOf(list.get(0));
        }
    }
    
    private void j(final Map<String, List<String>> map) {
        final List<String> b = b(map, "X-Afma-Manual-Tracking-Urls");
        if (b != null) {
            this.hX = b;
        }
    }
    
    private void k(final Map<String, List<String>> map) {
        final long a = a(map, "X-Afma-Refresh-Rate");
        if (a != -1L) {
            this.hY = a;
        }
    }
    
    private void l(final Map<String, List<String>> map) {
        final List<String> list = map.get("X-Afma-Orientation");
        if (list != null && !list.isEmpty()) {
            final String s = list.get(0);
            if ("portrait".equalsIgnoreCase(s)) {
                this.mOrientation = co.av();
            }
            else if ("landscape".equalsIgnoreCase(s)) {
                this.mOrientation = co.au();
            }
        }
    }
    
    public void a(final String hq, final Map<String, List<String>> map, final String hr) {
        this.hQ = hq;
        this.hR = hr;
        this.d(map);
    }
    
    public cb aq() {
        return new cb(this.hQ, this.hR, this.hS, this.hT, this.hU, this.hV, -1L, this.hX, this.hY, this.mOrientation, this.hP);
    }
    
    public void d(final Map<String, List<String>> map) {
        this.e(map);
        this.f(map);
        this.g(map);
        this.h(map);
        this.i(map);
        this.j(map);
        this.k(map);
        this.l(map);
    }
}
