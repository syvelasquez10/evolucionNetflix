// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.yoga;

public class YogaMeasureOutput
{
    public static long make(final float n, final float n2) {
        return Float.floatToRawIntBits(n2) | Float.floatToRawIntBits(n) << 32;
    }
    
    public static long make(final int n, final int n2) {
        return make(n, (float)n2);
    }
}
