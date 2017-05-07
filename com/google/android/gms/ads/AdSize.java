// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.client.zzk;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import android.content.Context;

public final class AdSize
{
    public static final AdSize BANNER;
    public static final AdSize FULL_BANNER;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final int zznP;
    private final int zznQ;
    private final String zznR;
    
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
    
    AdSize(final int zznP, final int zznQ, final String zznR) {
        if (zznP < 0 && zznP != -1) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + zznP);
        }
        if (zznQ < 0 && zznQ != -2) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + zznQ);
        }
        this.zznP = zznP;
        this.zznQ = zznQ;
        this.zznR = zznR;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.zznP != adSize.zznP || this.zznQ != adSize.zznQ || !this.zznR.equals(adSize.zznR)) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zznQ;
    }
    
    public int getHeightInPixels(final Context context) {
        if (this.zznQ == -2) {
            return AdSizeParcel.zzb(context.getResources().getDisplayMetrics());
        }
        return zzk.zzcE().zzb(context, this.zznQ);
    }
    
    public int getWidth() {
        return this.zznP;
    }
    
    public int getWidthInPixels(final Context context) {
        if (this.zznP == -1) {
            return AdSizeParcel.zza(context.getResources().getDisplayMetrics());
        }
        return zzk.zzcE().zzb(context, this.zznP);
    }
    
    @Override
    public int hashCode() {
        return this.zznR.hashCode();
    }
    
    @Override
    public String toString() {
        return this.zznR;
    }
}
