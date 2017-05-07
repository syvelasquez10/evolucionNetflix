// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast.internal;

import android.text.TextUtils;
import java.util.Locale;
import java.util.regex.Pattern;

public final class zzf
{
    private static final Pattern zzUO;
    
    static {
        zzUO = Pattern.compile("urn:x-cast:[-A-Za-z0-9_]+(\\.[-A-Za-z0-9_]+)*");
    }
    
    private static boolean zza(final char c) {
        return (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || c == '_' || c == '-';
    }
    
    public static <T> boolean zza(final T t, final T t2) {
        return (t == null && t2 == null) || (t != null && t2 != null && t.equals(t2));
    }
    
    public static String zzb(final Locale locale) {
        final StringBuilder sb = new StringBuilder(20);
        sb.append(locale.getLanguage());
        final String country = locale.getCountry();
        if (!TextUtils.isEmpty((CharSequence)country)) {
            sb.append('-').append(country);
        }
        final String variant = locale.getVariant();
        if (!TextUtils.isEmpty((CharSequence)variant)) {
            sb.append('-').append(variant);
        }
        return sb.toString();
    }
    
    public static void zzbD(final String s) {
        if (TextUtils.isEmpty((CharSequence)s)) {
            throw new IllegalArgumentException("Namespace cannot be null or empty");
        }
        if (s.length() > 128) {
            throw new IllegalArgumentException("Invalid namespace length");
        }
        if (!s.startsWith("urn:x-cast:")) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\"");
        }
        if (s.length() == "urn:x-cast:".length()) {
            throw new IllegalArgumentException("Namespace must begin with the prefix \"urn:x-cast:\" and have non-empty suffix");
        }
    }
    
    public static String zzbE(final String s) {
        return "urn:x-cast:" + s;
    }
    
    public static String zzbF(final String s) {
        if (zzf.zzUO.matcher(s).matches()) {
            return s;
        }
        final StringBuilder sb = new StringBuilder(s.length());
        for (int i = 0; i < s.length(); ++i) {
            final char char1 = s.charAt(i);
            if (zza(char1) || char1 == '.' || char1 == ':') {
                sb.append(char1);
            }
            else {
                sb.append(String.format("%%%04x", char1 & '\uffff'));
            }
        }
        return sb.toString();
    }
}
