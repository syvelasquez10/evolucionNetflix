// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import com.google.android.gms.internal.d;

class dk
{
    private static by<d.a> a(final by<d.a> by) {
        try {
            return new by<d.a>(dh.r(cd(dh.j(by.getObject()))), by.kQ());
        }
        catch (UnsupportedEncodingException ex) {
            bh.b("Escape URI: unsupported encoding", ex);
            return by;
        }
    }
    
    private static by<d.a> a(final by<d.a> by, final int n) {
        if (!q(by.getObject())) {
            bh.w("Escaping can only be applied to strings.");
            return by;
        }
        switch (n) {
            default: {
                bh.w("Unsupported Value Escaping: " + n);
                return by;
            }
            case 12: {
                return a(by);
            }
        }
    }
    
    static by<d.a> a(by<d.a> a, final int... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            a = a(a, array[i]);
        }
        return a;
    }
    
    static String cd(final String s) throws UnsupportedEncodingException {
        return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    }
    
    private static boolean q(final d.a a) {
        return dh.o(a) instanceof String;
    }
}
