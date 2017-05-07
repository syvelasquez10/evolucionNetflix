// 
// Decompiled by Procyon v0.5.30
// 

package com.google.ads;

import android.content.Context;

@Deprecated
public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize IAB_BANNER;
    public static final AdSize IAB_LEADERBOARD;
    public static final AdSize IAB_MRECT;
    public static final AdSize IAB_WIDE_SKYSCRAPER;
    public static final int LANDSCAPE_AD_HEIGHT = 32;
    public static final int LARGE_AD_HEIGHT = 90;
    public static final int PORTRAIT_AD_HEIGHT = 50;
    public static final AdSize SMART_BANNER;
    private final com.google.android.gms.ads.AdSize c;
    
    static {
        SMART_BANNER = new AdSize(-1, -2, "mb");
        BANNER = new AdSize(320, 50, "mb");
        IAB_MRECT = new AdSize(300, 250, "as");
        IAB_BANNER = new AdSize(468, 60, "as");
        IAB_LEADERBOARD = new AdSize(728, 90, "as");
        IAB_WIDE_SKYSCRAPER = new AdSize(160, 600, "as");
    }
    
    public AdSize(final int n, final int n2) {
        this(new com.google.android.gms.ads.AdSize(n, n2));
    }
    
    private AdSize(final int n, final int n2, final String s) {
        this(new com.google.android.gms.ads.AdSize(n, n2));
    }
    
    public AdSize(final com.google.android.gms.ads.AdSize c) {
        this.c = c;
    }
    
    @Override
    public boolean equals(final Object o) {
        return o instanceof AdSize && this.c.equals(((AdSize)o).c);
    }
    
    public AdSize findBestSize(final AdSize... array) {
        AdSize adSize = null;
        AdSize adSize2 = null;
        if (array != null) {
            float n = 0.0f;
            final int width = this.getWidth();
            final int height = this.getHeight();
            final int length = array.length;
            int n2 = 0;
        Label_0116_Outer:
            while (true) {
                adSize2 = adSize;
                if (n2 < length) {
                    final AdSize adSize3 = array[n2];
                    final int width2 = adSize3.getWidth();
                    final int height2 = adSize3.getHeight();
                    while (true) {
                        Label_0127: {
                            if (!this.isSizeAppropriate(width2, height2)) {
                                break Label_0127;
                            }
                            float n4;
                            final float n3 = n4 = width2 * height2 / (width * height);
                            if (n3 > 1.0f) {
                                n4 = 1.0f / n3;
                            }
                            if (n4 <= n) {
                                break Label_0127;
                            }
                            adSize = adSize3;
                            ++n2;
                            n = n4;
                            continue Label_0116_Outer;
                        }
                        float n4 = n;
                        continue;
                    }
                }
                break;
            }
        }
        return adSize2;
    }
    
    public int getHeight() {
        return this.c.getHeight();
    }
    
    public int getHeightInPixels(final Context context) {
        return this.c.getHeightInPixels(context);
    }
    
    public int getWidth() {
        return this.c.getWidth();
    }
    
    public int getWidthInPixels(final Context context) {
        return this.c.getWidthInPixels(context);
    }
    
    @Override
    public int hashCode() {
        return this.c.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.c.isAutoHeight();
    }
    
    public boolean isCustomAdSize() {
        return false;
    }
    
    public boolean isFullWidth() {
        return this.c.isFullWidth();
    }
    
    public boolean isSizeAppropriate(final int n, final int n2) {
        final int width = this.getWidth();
        final int height = this.getHeight();
        return n <= width * 1.25f && n >= width * 0.8f && n2 <= height * 1.25f && n2 >= height * 0.8f;
    }
    
    @Override
    public String toString() {
        return this.c.toString();
    }
}
