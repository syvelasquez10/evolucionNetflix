// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

public class BitmapCounterProvider
{
    public static final int MAX_BITMAP_TOTAL_SIZE;
    private static BitmapCounter sBitmapCounter;
    
    static {
        MAX_BITMAP_TOTAL_SIZE = getMaxSizeHardCap();
    }
    
    public static BitmapCounter get() {
        if (BitmapCounterProvider.sBitmapCounter == null) {
            BitmapCounterProvider.sBitmapCounter = new BitmapCounter(384, BitmapCounterProvider.MAX_BITMAP_TOTAL_SIZE);
        }
        return BitmapCounterProvider.sBitmapCounter;
    }
    
    private static int getMaxSizeHardCap() {
        final int n = (int)Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (n > 16777216L) {
            return n / 4 * 3;
        }
        return n / 2;
    }
}
