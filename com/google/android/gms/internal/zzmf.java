// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

public class zzmf
{
    public static final int[] EMPTY_INTS;
    public static final long[] EMPTY_LONGS;
    public static final Object[] EMPTY_OBJECTS;
    
    static {
        EMPTY_INTS = new int[0];
        EMPTY_LONGS = new long[0];
        EMPTY_OBJECTS = new Object[0];
    }
    
    public static int binarySearch(final int[] array, int i, final int n) {
        final int n2 = 0;
        final int n3 = i - 1;
        i = n2;
        int n4 = n3;
        while (i <= n4) {
            final int n5 = i + n4 >>> 1;
            final int n6 = array[n5];
            if (n6 < n) {
                i = n5 + 1;
            }
            else {
                if (n6 <= n) {
                    return n5;
                }
                n4 = n5 - 1;
            }
        }
        return ~i;
    }
    
    public static boolean equal(final Object o, final Object o2) {
        return o == o2 || (o != null && o.equals(o2));
    }
}
