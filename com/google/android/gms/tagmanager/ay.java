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
    private static String apn;
    static Map<String, String> apo;
    
    static {
        ay.apo = new HashMap<String, String>();
    }
    
    static void cC(final String apn) {
        synchronized (ay.class) {
            ay.apn = apn;
        }
    }
    
    static void d(final Context context, final String s) {
        cz.a(context, "gtm_install_referrer", "referrer", s);
        f(context, s);
    }
    
    static String e(final Context context, final String s) {
        Label_0043: {
            if (ay.apn != null) {
                break Label_0043;
            }
            synchronized (ay.class) {
                if (ay.apn == null) {
                    final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        ay.apn = sharedPreferences.getString("referrer", "");
                    }
                    else {
                        ay.apn = "";
                    }
                }
                // monitorexit(ay.class)
                return x(ay.apn, s);
            }
        }
    }
    
    static String f(final Context context, final String s, final String s2) {
        String s3;
        if ((s3 = ay.apo.get(s)) == null) {
            final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            String string;
            if (sharedPreferences != null) {
                string = sharedPreferences.getString(s, "");
            }
            else {
                string = "";
            }
            ay.apo.put(s, string);
            s3 = string;
        }
        return x(s3, s2);
    }
    
    static void f(final Context context, final String s) {
        final String x = x(s, "conv");
        if (x != null && x.length() > 0) {
            ay.apo.put(x, s);
            cz.a(context, "gtm_click_referrers", x, s);
        }
    }
    
    static String x(final String s, final String s2) {
        if (s2 != null) {
            return Uri.parse("http://hostname/?" + s).getQueryParameter(s2);
        }
        if (s.length() > 0) {
            return s;
        }
        return null;
    }
}
