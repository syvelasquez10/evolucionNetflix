// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Collection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public final class bd implements bb
{
    private final bc mP;
    
    public bd(final bc mp) {
        this.mP = mp;
    }
    
    private static boolean a(final Map<String, String> map) {
        return "1".equals(map.get("custom_close"));
    }
    
    private static int b(final Map<String, String> map) {
        final String s = map.get("o");
        if (s != null) {
            if ("p".equalsIgnoreCase(s)) {
                return dq.bA();
            }
            if ("l".equalsIgnoreCase(s)) {
                return dq.bz();
            }
        }
        return -1;
    }
    
    @Override
    public void b(final dz dz, final Map<String, String> map) {
        final String s = map.get("a");
        if (s == null) {
            dw.z("Action missing from an open GMSG.");
        }
        else {
            final ea bi = dz.bI();
            if ("expand".equalsIgnoreCase(s)) {
                if (dz.bL()) {
                    dw.z("Cannot expand WebView that is already expanded.");
                    return;
                }
                bi.a(a(map), b(map));
            }
            else if ("webapp".equalsIgnoreCase(s)) {
                final String s2 = map.get("u");
                if (s2 != null) {
                    bi.a(a(map), b(map), s2);
                    return;
                }
                bi.a(a(map), b(map), map.get("html"), map.get("baseurl"));
            }
            else {
                if (!"in_app_purchase".equalsIgnoreCase(s)) {
                    bi.a(new cb(map.get("i"), map.get("u"), map.get("m"), map.get("p"), map.get("c"), map.get("f"), map.get("e")));
                    return;
                }
                final String s3 = map.get("product_id");
                final String s4 = map.get("report_urls");
                if (this.mP != null) {
                    if (s4 != null && !s4.isEmpty()) {
                        this.mP.a(s3, new ArrayList<String>(Arrays.asList(s4.split(" "))));
                        return;
                    }
                    this.mP.a(s3, new ArrayList<String>());
                }
            }
        }
    }
}
