// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.net.Uri;
import android.content.SharedPreferences;
import android.content.Context;
import java.util.HashMap;
import java.util.Map;

class ay
{
    private static String Yk;
    static Map<String, String> Yl;
    
    static {
        ay.Yl = new HashMap<String, String>();
    }
    
    static void bF(final String yk) {
        synchronized (ay.class) {
            ay.Yk = yk;
        }
    }
    
    static void c(final Context context, final String s) {
        cy.a(context, "gtm_install_referrer", "referrer", s);
        e(context, s);
    }
    
    static String d(final Context context, final String s) {
        Label_0043: {
            if (ay.Yk != null) {
                break Label_0043;
            }
            synchronized (ay.class) {
                if (ay.Yk == null) {
                    final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        ay.Yk = sharedPreferences.getString("referrer", "");
                    }
                    else {
                        ay.Yk = "";
                    }
                }
                // monitorexit(ay.class)
                return m(ay.Yk, s);
            }
        }
    }
    
    static String e(final Context context, final String s, final String s2) {
        String s3;
        if ((s3 = ay.Yl.get(s)) == null) {
            final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            String string;
            if (sharedPreferences != null) {
                string = sharedPreferences.getString(s, "");
            }
            else {
                string = "";
            }
            ay.Yl.put(s, string);
            s3 = string;
        }
        return m(s3, s2);
    }
    
    static void e(final Context context, final String s) {
        final String m = m(s, "conv");
        if (m != null && m.length() > 0) {
            ay.Yl.put(m, s);
            cy.a(context, "gtm_click_referrers", m, s);
        }
    }
    
    static String m(final String s, final String s2) {
        if (s2 != null) {
            return Uri.parse("http://hostname/?" + s).getQueryParameter(s2);
        }
        if (s.length() > 0) {
            return s;
        }
        return null;
    }
}
