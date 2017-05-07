// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.Comparator;

final class b$1 implements Comparator<byte[]>
{
    public int c(final byte[] array, final byte[] array2) {
        for (int min = Math.min(array.length, array2.length), i = 0; i < min; ++i) {
            final int n = array[i] & 0xFF;
            final int n2 = array2[i] & 0xFF;
            if (n != n2) {
                return n - n2;
            }
        }
        return array.length - array2.length;
    }
}
