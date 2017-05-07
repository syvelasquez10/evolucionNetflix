// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.Iterator;
import android.text.TextUtils;
import java.util.Collection;

public final class CastMediaControlIntent
{
    private static String a(final String s, final String s2, final Collection<String> collection) {
        final StringBuffer sb = new StringBuffer(s);
        if (s2 != null) {
            final String upperCase = s2.toUpperCase();
            if (!upperCase.matches("[A-F0-9]+")) {
                throw new IllegalArgumentException("Invalid application ID: " + s2);
            }
            sb.append("/").append(upperCase);
        }
        if (collection != null) {
            if (collection.isEmpty()) {
                throw new IllegalArgumentException("Must specify at least one namespace");
            }
            for (final String s3 : collection) {
                if (TextUtils.isEmpty((CharSequence)s3) || s3.trim().equals("")) {
                    throw new IllegalArgumentException("Namespaces must not be null or empty");
                }
            }
            if (s2 == null) {
                sb.append("/");
            }
            sb.append("/").append(TextUtils.join((CharSequence)",", (Iterable)collection));
        }
        return sb.toString();
    }
    
    public static String categoryForCast(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        return a("com.google.android.gms.cast.CATEGORY_CAST", s, null);
    }
}
