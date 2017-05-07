// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics.internal;

import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import android.content.pm.ActivityInfo;
import android.content.BroadcastReceiver;
import android.content.pm.ServiceInfo;
import android.content.pm.PackageManager$NameNotFoundException;
import android.content.ComponentName;
import android.app.Service;
import android.content.Context;
import java.util.Map;
import java.util.Locale;
import java.util.Iterator;
import java.util.List;
import java.net.URISyntaxException;
import org.apache.http.NameValuePair;
import java.util.HashMap;
import org.apache.http.client.utils.URLEncodedUtils;
import java.net.URI;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzol;

public class zzam
{
    private static final char[] zzOK;
    
    static {
        zzOK = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    public static double zza(final String s, final double n) {
        if (s == null) {
            return n;
        }
        try {
            return Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            return n;
        }
    }
    
    public static zzol zza(zzaf zzaf, final String s) {
        zzx.zzv(zzaf);
        if (TextUtils.isEmpty((CharSequence)s)) {
            return null;
        }
        try {
            final List parse = URLEncodedUtils.parse(new URI("?" + s), "UTF-8");
            zzaf = (zzaf)new HashMap(parse.size());
            for (final NameValuePair nameValuePair : parse) {
                ((Map<String, String>)zzaf).put(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
        catch (URISyntaxException ex) {
            zzaf.zzd("No valid campaign data found", ex);
            return null;
        }
        final zzol zzol = new zzol();
        zzol.zzdL(((Map<K, String>)zzaf).get((Object)"utm_content"));
        zzol.zzdJ(((Map<K, String>)zzaf).get((Object)"utm_medium"));
        zzol.setName(((Map<K, String>)zzaf).get((Object)"utm_campaign"));
        zzol.zzdI(((Map<K, String>)zzaf).get((Object)"utm_source"));
        zzol.zzdK(((Map<K, String>)zzaf).get((Object)"utm_term"));
        zzol.zzdM(((Map<K, String>)zzaf).get((Object)"utm_id"));
        zzol.zzdN(((Map<K, String>)zzaf).get((Object)"anid"));
        zzol.zzdO(((Map<K, String>)zzaf).get((Object)"gclid"));
        zzol.zzdP(((Map<K, String>)zzaf).get((Object)"dclid"));
        zzol.zzdQ(((Map<K, String>)zzaf).get((Object)"aclid"));
        return zzol;
    }
    
    public static String zza(final Locale locale) {
        if (locale != null) {
            final String language = locale.getLanguage();
            if (!TextUtils.isEmpty((CharSequence)language)) {
                final StringBuilder sb = new StringBuilder();
                sb.append(language.toLowerCase());
                if (!TextUtils.isEmpty((CharSequence)locale.getCountry())) {
                    sb.append("-").append(locale.getCountry().toLowerCase());
                }
                return sb.toString();
            }
        }
        return null;
    }
    
    public static void zza(final Map<String, String> map, final String s, final Map<String, String> map2) {
        zzb(map, s, map2.get(s));
    }
    
    public static boolean zza(final double n, final String s) {
        return n > 0.0 && n < 100.0 && zzbr(s) % 10000 >= 100.0 * n;
    }
    
    public static boolean zza(final Context context, final Class<? extends Service> clazz) {
        try {
            final ServiceInfo serviceInfo = context.getPackageManager().getServiceInfo(new ComponentName(context, (Class)clazz), 4);
            if (serviceInfo != null && serviceInfo.enabled) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static boolean zza(final Context context, final Class<? extends BroadcastReceiver> clazz, final boolean b) {
        try {
            final ActivityInfo receiverInfo = context.getPackageManager().getReceiverInfo(new ComponentName(context, (Class)clazz), 2);
            if (receiverInfo != null && receiverInfo.enabled && (!b || receiverInfo.exported)) {
                return true;
            }
        }
        catch (PackageManager$NameNotFoundException ex) {}
        return false;
    }
    
    public static void zzb(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null && !map.containsKey(s)) {
            map.put(s, s2);
        }
    }
    
    public static void zzb(final Map<String, String> map, final String s, final boolean b) {
        if (!map.containsKey(s)) {
            String s2;
            if (b) {
                s2 = "1";
            }
            else {
                s2 = "0";
            }
            map.put(s, s2);
        }
    }
    
    public static long zzbo(final String s) {
        if (s == null) {
            return 0L;
        }
        try {
            return Long.parseLong(s);
        }
        catch (NumberFormatException ex) {
            return 0L;
        }
    }
    
    public static MessageDigest zzbq(final String s) {
        for (int i = 0; i < 2; ++i) {
            try {
                final MessageDigest instance = MessageDigest.getInstance(s);
                if (instance != null) {
                    return instance;
                }
            }
            catch (NoSuchAlgorithmException ex) {}
        }
        return null;
    }
    
    public static int zzbr(final String s) {
        int n = 1;
        if (!TextUtils.isEmpty((CharSequence)s)) {
            final int length = s.length();
            int n2 = 0;
            int n3 = length - 1;
            while (true) {
                n = n2;
                if (n3 < 0) {
                    break;
                }
                final char char1 = s.charAt(n3);
                final int n4 = (n2 << 6 & 0xFFFFFFF) + char1 + (char1 << 14);
                final int n5 = 0xFE00000 & n4;
                n2 = n4;
                if (n5 != 0) {
                    n2 = (n4 ^ n5 >> 21);
                }
                --n3;
            }
        }
        return n;
    }
    
    public static boolean zzbs(final String s) {
        return TextUtils.isEmpty((CharSequence)s) || !s.startsWith("http:");
    }
    
    public static void zzc(final Map<String, String> map, final String s, final String s2) {
        if (s2 != null && TextUtils.isEmpty((CharSequence)map.get(s))) {
            map.put(s, s2);
        }
    }
    
    public static boolean zze(final String s, final boolean b) {
        boolean b2 = b;
        if (s != null) {
            if (!s.equalsIgnoreCase("true") && !s.equalsIgnoreCase("yes") && !s.equalsIgnoreCase("1")) {
                if (!s.equalsIgnoreCase("false") && !s.equalsIgnoreCase("no")) {
                    b2 = b;
                    if (!s.equalsIgnoreCase("0")) {
                        return b2;
                    }
                }
                return false;
            }
            b2 = true;
        }
        return b2;
    }
}
