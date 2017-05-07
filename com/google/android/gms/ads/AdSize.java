// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.internal.dv;
import com.google.android.gms.internal.ak;
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
    private final int kr;
    private final int ks;
    private final String kt;
    
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
    
    AdSize(final int kr, final int ks, final String kt) {
        if (kr < 0 && kr != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + kr);
        }
        if (ks < 0 && ks != -2) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + ks);
        }
        this.kr = kr;
        this.ks = ks;
        this.kt = kt;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.kr != adSize.kr || this.ks != adSize.ks || !this.kt.equals(adSize.kt)) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.ks;
    }
    
    public int getHeightInPixels(final Context context) {
        if (this.ks == -2) {
            return ak.b(context.getResources().getDisplayMetrics());
        }
        return dv.a(context, this.ks);
    }
    
    public int getWidth() {
        return this.kr;
    }
    
    public int getWidthInPixels(final Context context) {
        if (this.kr == -1) {
            return ak.a(context.getResources().getDisplayMetrics());
        }
        return dv.a(context, this.kr);
    }
    
    @Override
    public int hashCode() {
        return this.kt.hashCode();
    }
    
    public boolean isAutoHeight() {
        return this.ks == -2;
    }
    
    public boolean isFullWidth() {
        return this.kr == -1;
    }
    
    @Override
    public String toString() {
        return this.kt;
    }
}
