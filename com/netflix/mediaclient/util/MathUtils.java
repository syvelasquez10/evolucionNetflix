// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.util.Random;
import android.graphics.Rect;

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
    
    public static int divideIntsWithRounding(final int n, final int n2) {
        return (int)(n / n2 + 0.5f);
    }
    
    public static void expandRect(final Rect rect, final int n) {
        rect.offset(-n, -n);
        rect.right += n * 2;
        rect.bottom += n * 2;
    }
    
    public static boolean isEven(final int n) {
        return (n & 0x1) == 0x0;
    }
    
    public static int randomInRange(final long n, final int n2, final int n3) {
        return new Random(n).nextInt(n3 - n2) + n2;
    }
}
