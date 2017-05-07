// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

@ez
public final class cd implements by
{
    private final bz pL;
    private final v pM;
    
    public cd(final bz pl, final v pm) {
        this.pL = pl;
        this.pM = pm;
    }
    
    private static boolean b(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int c(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return gj.dn();
            }
            if ("l".equalsIgnoreCase(s)) {
                return gj.dm();
            }
        }
        return -1;
    }
    
    @Override
    public void a(final gv gv, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            gs.W("Action missing from an open GMSG.");
        }
        else {
            if (this.pM != null && !this.pM.av()) {
                this.pM.d(map.get("u"));
                return;
            }
            final gw dv = gv.dv();
            if ("expand".equalsIgnoreCase(s)) {
                if (gv.dz()) {
                    gs.W("Cannot expand WebView that is already expanded.");
                    return;
                }
                dv.a(b(map), c(map));
            }
            else if ("webapp".equalsIgnoreCase(s)) {
                final String s2 = map.get("u");
                if (s2 != null) {
                    dv.a(b(map), c(map), s2);
                    return;
                }
                dv.a(b(map), c(map), map.get("html"), map.get("baseurl"));
            }
            else {
                if (!"in_app_purchase".equalsIgnoreCase(s)) {
                    dv.a(new dj(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
                    return;
                }
                final String s3 = map.get("product_id");
                final String s4 = map.get("report_urls");
                if (this.pL != null) {
                    if (s4 != null && !s4.isEmpty()) {
                        this.pL.a(s3, new ArrayList<String>(Arrays.asList(s4.split(" "))));
                        return;
                    }
                    this.pL.a(s3, new ArrayList<String>());
                }
            }
        }
    }
}
