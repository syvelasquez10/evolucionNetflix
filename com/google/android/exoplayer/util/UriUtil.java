// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import android.net.Uri;
import android.text.TextUtils;

public final class UriUtil
{
    private static int[] getUriIndices(final String s) {
        final int[] array = new int[4];
        if (TextUtils.isEmpty((CharSequence)s)) {
            array[0] = -1;
            return array;
        }
        int length = s.length();
        final int index = s.indexOf(35);
        if (index != -1) {
            length = index;
        }
        final int index2 = s.indexOf(63);
        int n;
        if (index2 == -1 || (n = index2) > length) {
            n = length;
        }
        final int index3 = s.indexOf(47);
        int n2;
        if (index3 == -1 || (n2 = index3) > n) {
            n2 = n;
        }
        int index4;
        if ((index4 = s.indexOf(58)) > n2) {
            index4 = -1;
        }
        int n3;
        if (index4 + 2 < n && s.charAt(index4 + 1) == '/' && s.charAt(index4 + 2) == '/') {
            n3 = 1;
        }
        else {
            n3 = 0;
        }
        int n4;
        if (n3 != 0) {
            final int index5 = s.indexOf(47, index4 + 3);
            if (index5 == -1 || (n4 = index5) > n) {
                n4 = n;
            }
        }
        else {
            n4 = index4 + 1;
        }
        array[0] = index4;
        array[1] = n4;
        array[2] = n;
        array[3] = length;
        return array;
    }
    
    private static String removeDotSegments(final StringBuilder sb, int n, int i) {
        if (n >= i) {
            return sb.toString();
        }
        int n2 = n;
        if (sb.charAt(n) == '/') {
            n2 = n + 1;
        }
        final int n3 = n2;
        n = n2;
        int n4 = i;
        i = n3;
        while (i <= n4) {
            int n5;
            if (i == n4) {
                n5 = i;
            }
            else {
                if (sb.charAt(i) != '/') {
                    ++i;
                    continue;
                }
                n5 = i + 1;
            }
            if (i == n + 1 && sb.charAt(n) == '.') {
                sb.delete(n, n5);
                n4 -= n5 - n;
                final int n6 = n;
                i = n;
                n = n6;
            }
            else if (i == n + 2 && sb.charAt(n) == '.' && sb.charAt(n + 1) == '.') {
                i = sb.lastIndexOf("/", n - 2) + 1;
                if (i > n2) {
                    n = i;
                }
                else {
                    n = n2;
                }
                sb.delete(n, n5);
                n4 -= n5 - n;
                n = i;
            }
            else {
                i = (n = i + 1);
            }
        }
        return sb.toString();
    }
    
    public static String resolve(String s, final String s2) {
        final StringBuilder sb = new StringBuilder();
        String s3 = s;
        if (s == null) {
            s3 = "";
        }
        if ((s = s2) == null) {
            s = "";
        }
        final int[] uriIndices = getUriIndices(s);
        if (uriIndices[0] != -1) {
            sb.append(s);
            removeDotSegments(sb, uriIndices[1], uriIndices[2]);
            return sb.toString();
        }
        final int[] uriIndices2 = getUriIndices(s3);
        if (uriIndices[3] == 0) {
            return sb.append(s3, 0, uriIndices2[3]).append(s).toString();
        }
        if (uriIndices[2] == 0) {
            return sb.append(s3, 0, uriIndices2[2]).append(s).toString();
        }
        if (uriIndices[1] != 0) {
            final int n = uriIndices2[0] + 1;
            sb.append(s3, 0, n).append(s);
            return removeDotSegments(sb, uriIndices[1] + n, n + uriIndices[2]);
        }
        if (uriIndices[1] != uriIndices[2] && s.charAt(uriIndices[1]) == '/') {
            sb.append(s3, 0, uriIndices2[1]).append(s);
            return removeDotSegments(sb, uriIndices2[1], uriIndices[2] + uriIndices2[1]);
        }
        if (uriIndices2[0] + 2 < uriIndices2[1] && uriIndices2[1] == uriIndices2[2]) {
            sb.append(s3, 0, uriIndices2[1]).append('/').append(s);
            return removeDotSegments(sb, uriIndices2[1], uriIndices[2] + uriIndices2[1] + 1);
        }
        final int lastIndex = s3.lastIndexOf(47, uriIndices2[2] - 1);
        int n2;
        if (lastIndex == -1) {
            n2 = uriIndices2[1];
        }
        else {
            n2 = lastIndex + 1;
        }
        sb.append(s3, 0, n2).append(s);
        return removeDotSegments(sb, uriIndices2[1], n2 + uriIndices[2]);
    }
    
    public static Uri resolveToUri(final String s, final String s2) {
        return Uri.parse(resolve(s, s2));
    }
}
