// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.cast;

import java.util.Iterator;
import com.google.android.gms.cast.internal.zzf;
import java.util.Collection;

public final class CastMediaControlIntent
{
    public static String categoryForCast(final String s) {
        if (s == null) {
            throw new IllegalArgumentException("applicationId cannot be null");
        }
        return zza("com.google.android.gms.cast.CATEGORY_CAST", s, null);
    }
    
    private static String zza(final String s, final String s2, final Collection<String> collection) {
        final StringBuilder sb = new StringBuilder(s);
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
            if (s2 == null) {
                sb.append("/");
            }
            sb.append("/");
            final Iterator<String> iterator = collection.iterator();
            int n = 1;
            while (iterator.hasNext()) {
                final String s3 = iterator.next();
                zzf.zzbD(s3);
                if (n != 0) {
                    n = 0;
                }
                else {
                    sb.append(",");
                }
                sb.append(zzf.zzbF(s3));
            }
        }
        return sb.toString();
    }
}
