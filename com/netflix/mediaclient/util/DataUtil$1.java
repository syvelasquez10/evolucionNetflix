// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Comparator;

final class DataUtil$1 implements Comparator<String>
{
    @Override
    public int compare(final String s, final String s2) {
        if (s.length() < s2.length()) {
            return -1;
        }
        if (s.length() > s2.length()) {
            return 1;
        }
        return s.compareTo(s2);
    }
}
