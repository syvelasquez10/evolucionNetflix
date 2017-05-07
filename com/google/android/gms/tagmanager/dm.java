// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.net.URLEncoder;
import java.io.UnsupportedEncodingException;
import com.google.android.gms.internal.d$a;

class dm
{
    private static bz<d$a> a(final bz<d$a> bz) {
        try {
            return new bz<d$a>(di.u(db(di.j(bz.getObject()))), bz.oE());
        }
        catch (UnsupportedEncodingException ex) {
            bh.b("Escape URI: unsupported encoding", ex);
            return bz;
        }
    }
    
    private static bz<d$a> a(final bz<d$a> bz, final int n) {
        if (!q(bz.getObject())) {
            bh.T("Escaping can only be applied to strings.");
            return bz;
        }
        switch (n) {
            default: {
                bh.T("Unsupported Value Escaping: " + n);
                return bz;
            }
            case 12: {
                return a(bz);
            }
        }
    }
    
    static bz<d$a> a(bz<d$a> a, final int... array) {
        for (int length = array.length, i = 0; i < length; ++i) {
            a = a(a, array[i]);
        }
        return a;
    }
    
    static String db(final String s) {
        return URLEncoder.encode(s, "UTF-8").replaceAll("\\+", "%20");
    }
    
    private static boolean q(final d$a d$a) {
        return di.o(d$a) instanceof String;
    }
}
