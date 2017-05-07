// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.analytics;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.MessageDigest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import android.text.TextUtils;
import java.util.Locale;

class aj
{
    private static final char[] BJ;
    
    static {
        BJ = new char[] { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };
    }
    
    static String C(final boolean b) {
        if (b) {
            return "1";
        }
        return "0";
    }
    
    public static double a(final String s, final double n) {
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
    
    static String a(final Locale locale) {
        if (locale != null && !TextUtils.isEmpty((CharSequence)locale.getLanguage())) {
            final StringBuilder sb = new StringBuilder();
            sb.append(locale.getLanguage().toLowerCase());
            if (!TextUtils.isEmpty((CharSequence)locale.getCountry())) {
                sb.append("-").append(locale.getCountry().toLowerCase());
            }
            return sb.toString();
        }
        return null;
    }
    
    public static void a(final Map<String, String> map, final String s, final l l) {
        if (!map.containsKey(s)) {
            map.put(s, l.getValue(s));
        }
    }
    
    public static void a(final Map<String, String> map, final String s, final String s2) {
        if (!map.containsKey(s)) {
            map.put(s, s2);
        }
    }
    
    public static Map<String, String> an(final String s) {
        final HashMap<String, String> hashMap = new HashMap<String, String>();
        final String[] split = s.split("&");
        for (int length = split.length, i = 0; i < length; ++i) {
            final String[] split2 = split[i].split("=");
            if (split2.length > 1) {
                hashMap.put(split2[0], split2[1]);
            }
            else if (split2.length == 1 && split2[0].length() != 0) {
                hashMap.put(split2[0], null);
            }
        }
        return hashMap;
    }
    
    public static String ao(String decode) {
        if (TextUtils.isEmpty((CharSequence)decode)) {
            return null;
        }
        String s = decode;
        if (decode.contains("?")) {
            final String[] split = decode.split("[\\?]");
            s = decode;
            if (split.length > 1) {
                s = split[1];
            }
        }
        while (true) {
            Label_0200: {
                if (!s.contains("%3D")) {
                    break Label_0200;
                }
                try {
                    decode = URLDecoder.decode(s, "UTF-8");
                    final Map<String, String> an = an(decode);
                    final String[] array = { "dclid", "utm_source", "gclid", "utm_campaign", "utm_medium", "utm_term", "utm_content", "utm_id", "gmob_t" };
                    final StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < array.length; ++i) {
                        if (!TextUtils.isEmpty((CharSequence)an.get(array[i]))) {
                            if (sb.length() > 0) {
                                sb.append("&");
                            }
                            sb.append(array[i]).append("=").append(an.get(array[i]));
                        }
                    }
                    return sb.toString();
                }
                catch (UnsupportedEncodingException ex) {
                    return null;
                }
            }
            decode = s;
            if (!s.contains("=")) {
                return null;
            }
            continue;
        }
    }
    
    public static MessageDigest ap(final String s) {
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
    
    public static boolean e(final String s, final boolean b) {
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
