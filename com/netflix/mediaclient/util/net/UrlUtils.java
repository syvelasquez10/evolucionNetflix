// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util.net;

public class UrlUtils
{
    public static String getHost(final String s) {
        if (s == null || s.length() == 0) {
            return "";
        }
        final int index = s.indexOf("//");
        int n;
        if (index == -1) {
            n = 0;
        }
        else {
            n = index + 2;
        }
        int n2 = s.indexOf(47, n);
        if (n2 < 0) {
            n2 = s.length();
        }
        final int index2 = s.indexOf(58, n);
        int n3 = n2;
        if (index2 > 0 && index2 < (n3 = n2)) {
            n3 = index2;
        }
        return s.substring(n, n3);
    }
}
