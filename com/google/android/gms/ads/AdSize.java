// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.cs;
import com.google.android.gms.internal.x;
import android.content.Context;

public final class AdSize
{
    public static final int AUTO_HEIGHT = -2;
    public static final AdSize BANNER;
    public static final AdSize FULL_BANNER;
    public static final int FULL_WIDTH = -1;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final String dY;
    private final int v;
    private final int w;
    
    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
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
    
    AdSize(final int w, final int v, final String dy) {
        if (w < 0 && w != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + w);
        }
        if (v < 0 && v != -2) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + v);
        }
        this.w = w;
        this.v = v;
        this.dY = dy;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.w != adSize.w || this.v != adSize.v || !this.dY.equals(adSize.dY)) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.v;
    }
    
    public int getHeightInPixels(final Context context) {
        if (this.v == -2) {
            return x.b(context.getResources().getDisplayMetrics());
        }
        return cs.a(context, this.v);
    }
    
    public int getWidth() {
        return this.w;
    }
    
    public int getWidthInPixels(final Context context) {
        if (this.w == -1) {
            return x.a(context.getResources().getDisplayMetrics());
        }
        return cs.a(context, this.w);
    }
    
    @Override
    public int hashCode() {
        return this.dY.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.v == -2;
    }
    
    public boolean isFullWidth() {
        return this.w == -1;
    }
    
    @Override
    public String toString() {
        return this.dY;
    }
}
