// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.common.internal;

public class Ints
{
    public static int max(final int... array) {
        int i = 1;
        Preconditions.checkArgument(array.length > 0);
        int n = array[0];
        while (i < array.length) {
            int n2;
            if (array[i] > (n2 = n)) {
                n2 = array[i];
            }
            ++i;
            n = n2;
        }
        return n;
    }
}
