// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Map;

public final class ao implements an
{
    private static boolean a(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int b(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return co.av();
            }
            if ("l".equalsIgnoreCase(s)) {
                return co.au();
            }
        }
        return -1;
    }
    
    @Override
    public void a(final cw cw, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            ct.v("Action missing from an open GMSG.");
            return;
        }
        final cx ac = cw.aC();
        if ("expand".equalsIgnoreCase(s)) {
            if (cw.aF()) {
                ct.v("Cannot expand WebView that is already expanded.");
                return;
            }
            ac.a(a(map), b(map));
        }
        else {
            if (!"webapp".equalsIgnoreCase(s)) {
                ac.a(new bj(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
                return;
            }
            final String s2 = map.get("u");
            if (s2 != null) {
                ac.a(a(map), b(map), s2);
                return;
            }
            ac.a(a(map), b(map), map.get("html"), map.get("baseurl"));
        }
    }
}
