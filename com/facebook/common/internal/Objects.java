// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

import java.util.Arrays;

public final class Objects
{
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
    
    public static int hashCode(final Object... array) {
        return Arrays.hashCode(array);
    }
    
    private static String simpleName(final Class<?> clazz) {
        final String replaceAll = clazz.getName().replaceAll("\\$[0-9]+", "\\$");
        int n;
        if ((n = replaceAll.lastIndexOf(36)) == -1) {
            n = replaceAll.lastIndexOf(46);
        }
        return replaceAll.substring(n + 1);
    }
    
    public static Objects$ToStringHelper toStringHelper(final Object o) {
        return new Objects$ToStringHelper(simpleName(o.getClass()), null);
    }
}
