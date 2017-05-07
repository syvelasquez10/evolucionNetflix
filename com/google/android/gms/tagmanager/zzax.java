// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import android.content.SharedPreferences;
import android.content.Context;
import android.net.Uri;
import java.util.HashMap;
import java.util.Map;

public class zzax
{
    private static String zzaQo;
    static Map<String, String> zzaQp;
    
    static {
        zzax.zzaQp = new HashMap<String, String>();
    }
    
    public static String zzF(final String s, final String s2) {
        if (s2 != null) {
            return Uri.parse("http://hostname/?" + s).getQueryParameter(s2);
        }
        if (s.length() > 0) {
            return s;
        }
        return null;
    }
    
    public static String zzg(final Context context, final String s, final String s2) {
        String s3;
        if ((s3 = zzax.zzaQp.get(s)) == null) {
            final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_click_referrers", 0);
            String string;
            if (sharedPreferences != null) {
                string = sharedPreferences.getString(s, "");
            }
            else {
                string = "";
            }
            zzax.zzaQp.put(s, string);
            s3 = string;
        }
        return zzF(s3, s2);
    }
    
    public static String zzk(final Context context, final String s) {
        Label_0043: {
            if (zzax.zzaQo != null) {
                break Label_0043;
            }
            synchronized (zzax.class) {
                if (zzax.zzaQo == null) {
                    final SharedPreferences sharedPreferences = context.getSharedPreferences("gtm_install_referrer", 0);
                    if (sharedPreferences != null) {
                        zzax.zzaQo = sharedPreferences.getString("referrer", "");
                    }
                    else {
                        zzax.zzaQo = "";
                    }
                }
                // monitorexit(zzax.class)
                return zzF(zzax.zzaQo, s);
            }
        }
    }
    
    public static void zzl(final Context context, final String s) {
        final String zzF = zzF(s, "conv");
        if (zzF != null && zzF.length() > 0) {
            zzax.zzaQp.put(zzF, s);
            zzcv.zzb(context, "gtm_click_referrers", zzF, s);
        }
    }
}
