// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.fitness.data.DataSource;

public class kv
{
    private static final ThreadLocal<String> To;
    
    static {
        To = new ThreadLocal<String>();
    }
    
    public static String bq(final String s) {
        return s(s, kv.To.get());
    }
    
    public static DataSource c(final DataSource dataSource) {
        if (dataSource.iJ()) {
            final String s = kv.To.get();
            if (!iU() && !s.equals(dataSource.getAppPackageName())) {
                return dataSource.iK();
            }
        }
        return dataSource;
    }
    
    public static boolean iU() {
        final String s = kv.To.get();
        return s == null || s.startsWith("com.google");
    }
    
    private static String s(final String s, final String s2) {
        if (s == null || s2 == null) {
            return s;
        }
        final byte[] array = new byte[s.length() + s2.length()];
        System.arraycopy(s.getBytes(), 0, array, 0, s.length());
        System.arraycopy(s2.getBytes(), 0, array, s.length(), s2.length());
        return Integer.toHexString(kb.a(array, 0, array.length, 0));
    }
}
