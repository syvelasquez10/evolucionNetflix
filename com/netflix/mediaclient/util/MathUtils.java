// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

public class MathUtils
{
    public static int ceiling(final int n, final int n2) {
        return (n + n2 - 1) / n2;
    }
    
    public static int constrain(final int n, final int n2, final int n3) {
        if (n < n2) {
            return n2;
        }
        if (n > n3) {
            return n3;
        }
        return n;
    }
    
    public static int divideInts(final int n, final int n2) {
        return (int)(n / n2 + 0.5f);
    }
    
    public static boolean isEven(final int n) {
        return (n & 0x1) == 0x0;
    }
}
