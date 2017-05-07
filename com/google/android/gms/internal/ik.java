// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Locale;
import android.text.TextUtils;

public final class ik
{
    public static <T> boolean a(final T t, final T t2) {
        return (t == null && t2 == null) || (t != null && t2 != null && t.equals(t2));
    }
    
    public static void aF(final String s) {
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
    
    public static String b(final Locale locale) {
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
}
