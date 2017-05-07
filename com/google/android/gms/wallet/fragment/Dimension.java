// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.wallet.fragment;

import android.util.TypedValue;
import android.util.DisplayMetrics;

public class Dimension
{
    public static final int MATCH_PARENT = -1;
    public static final int UNIT_DIP = 1;
    public static final int UNIT_IN = 4;
    public static final int UNIT_MM = 5;
    public static final int UNIT_PT = 3;
    public static final int UNIT_PX = 0;
    public static final int UNIT_SP = 2;
    public static final int WRAP_CONTENT = -2;
    
    public static int a(final long n, final DisplayMetrics displayMetrics) {
        final int n2 = (int)(n >>> 32);
        final int n3 = (int)n;
        int n4 = 0;
        switch (n2) {
            default: {
                throw new IllegalStateException("Unexpected unit or type: " + n2);
            }
            case 129: {
                return n3;
            }
            case 128: {
                return TypedValue.complexToDimensionPixelSize(n3, displayMetrics);
            }
            case 0: {
                n4 = 0;
                break;
            }
            case 1: {
                n4 = 1;
                break;
            }
            case 2: {
                n4 = 2;
                break;
            }
            case 3: {
                n4 = 3;
                break;
            }
            case 4: {
                n4 = 4;
                break;
            }
            case 5: {
                n4 = 5;
                break;
            }
        }
        return Math.round(TypedValue.applyDimension(n4, Float.intBitsToFloat(n3), displayMetrics));
    }
    
    public static long a(final int n, final float n2) {
        switch (n) {
            default: {
                throw new IllegalArgumentException("Unrecognized unit: " + n);
            }
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5: {
                return o(n, Float.floatToIntBits(n2));
            }
        }
    }
    
    public static long a(final TypedValue typedValue) {
        switch (typedValue.type) {
            default: {
                throw new IllegalArgumentException("Unexpected dimension type: " + typedValue.type);
            }
            case 16: {
                return fD(typedValue.data);
            }
            case 5: {
                return o(128, typedValue.data);
            }
        }
    }
    
    public static long fD(final int n) {
        if (n >= 0) {
            return a(0, n);
        }
        if (n == -1 || n == -2) {
            return o(129, n);
        }
        throw new IllegalArgumentException("Unexpected dimension value: " + n);
    }
    
    private static long o(final int n, final int n2) {
        return n << 32 | (n2 & 0xFFFFFFFFL);
    }
}
