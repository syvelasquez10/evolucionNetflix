// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.views.view;

public class ColorUtil
{
    public static int getOpacityFromColor(int n) {
        n >>>= 24;
        if (n == 255) {
            return -1;
        }
        if (n == 0) {
            return -2;
        }
        return -3;
    }
    
    public static int multiplyColorAlpha(final int n, final int n2) {
        if (n2 == 255) {
            return n;
        }
        if (n2 == 0) {
            return n & 0xFFFFFF;
        }
        return ((n2 >> 7) + n2) * (n >>> 24) >> 8 << 24 | (n & 0xFFFFFF);
    }
}
