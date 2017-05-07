// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.gr;
import com.google.android.gms.internal.ay;
import android.content.Context;

public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final AdSize FULL_BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final int lf;
    private final int lg;
    private final String lh;
    
    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
        LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        LEADERBOARD = new AdSize(728, 90, "728x90_as");
        MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
        WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
        SMART_BANNER = new AdSize(-1, -2, "smart_banner");
    }
    
    public AdSize(final int n, final int n2) {
        final StringBuilder sb = new StringBuilder();
        String value;
        if (n == -1) {
            value = "FULL";
        }
        else {
            value = String.valueOf(n);
        }
        final StringBuilder append = sb.append(value).append("x");
        String value2;
        if (n2 == -2) {
            value2 = "AUTO";
        }
        else {
            value2 = String.valueOf(n2);
        }
        this(n, n2, append.append(value2).append("_as").toString());
    }
    
    AdSize(final int lf, final int lg, final String lh) {
        if (lf < 0 && lf != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + lf);
        }
        if (lg < 0 && lg != -2) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + lg);
        }
        this.lf = lf;
        this.lg = lg;
        this.lh = lh;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.lf != adSize.lf || this.lg != adSize.lg || !this.lh.equals(adSize.lh)) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.lg;
    }
    
    public int getHeightInPixels(final Context context) {
        if (this.lg == -2) {
            return ay.b(context.getResources().getDisplayMetrics());
        }
        return gr.a(context, this.lg);
    }
    
    public int getWidth() {
        return this.lf;
    }
    
    public int getWidthInPixels(final Context context) {
        if (this.lf == -1) {
            return ay.a(context.getResources().getDisplayMetrics());
        }
        return gr.a(context, this.lf);
    }
    
    @Override
    public int hashCode() {
        return this.lh.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.lg == -2;
    }
    
    public boolean isFullWidth() {
        return this.lf == -1;
    }
    
    @Override
    public String toString() {
        return this.lh;
    }
}
