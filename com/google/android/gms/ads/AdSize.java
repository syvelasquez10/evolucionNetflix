// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.ads;

import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzl;
import android.content.Context;

public final class AdSize
{
    public static final AdSize BANNER;
    public static final AdSize FLUID;
    public static final AdSize FULL_BANNER;
    public static final AdSize LARGE_BANNER;
    public static final AdSize LEADERBOARD;
    public static final AdSize MEDIUM_RECTANGLE;
    public static final AdSize SMART_BANNER;
    public static final AdSize WIDE_SKYSCRAPER;
    private final int zznQ;
    private final int zznR;
    private final String zznS;
    
    static {
        BANNER = new AdSize(320, 50, "320x50_mb");
        FULL_BANNER = new AdSize(468, 60, "468x60_as");
        LARGE_BANNER = new AdSize(320, 100, "320x100_as");
        LEADERBOARD = new AdSize(728, 90, "728x90_as");
        MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
        WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
        SMART_BANNER = new AdSize(-1, -2, "smart_banner");
        FLUID = new AdSize(-3, -4, "fluid");
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
    
    AdSize(final int zznQ, final int zznR, final String zznS) {
        if (zznQ < 0 && zznQ != -1 && zznQ != -3) {
            throw new IllegalArgumentException("Invalid width for AdSize: " + zznQ);
        }
        if (zznR < 0 && zznR != -2 && zznR != -4) {
            throw new IllegalArgumentException("Invalid height for AdSize: " + zznR);
        }
        this.zznQ = zznQ;
        this.zznR = zznR;
        this.zznS = zznS;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof AdSize)) {
                return false;
            }
            final AdSize adSize = (AdSize)o;
            if (this.zznQ != adSize.zznQ || this.zznR != adSize.zznR || !this.zznS.equals(adSize.zznS)) {
                return false;
            }
        }
        return true;
    }
    
    public int getHeight() {
        return this.zznR;
    }
    
    public int getHeightInPixels(final Context context) {
        switch (this.zznR) {
            default: {
                return zzl.zzcF().zzb(context, this.zznR);
            }
            case -2: {
                return AdSizeParcel.zzb(context.getResources().getDisplayMetrics());
            }
            case -4:
            case -3: {
                return -1;
            }
        }
    }
    
    public int getWidth() {
        return this.zznQ;
    }
    
    public int getWidthInPixels(final Context context) {
        switch (this.zznQ) {
            default: {
                return zzl.zzcF().zzb(context, this.zznQ);
            }
            case -1: {
                return AdSizeParcel.zza(context.getResources().getDisplayMetrics());
            }
            case -4:
            case -3: {
                return -1;
            }
        }
    }
    
    @Override
    public int hashCode() {
        return this.zznS.hashCode();
    }
    
    public boolean isFluid() {
        return this.zznQ == -3 && this.zznR == -4;
    }
    
    @Override
    public String toString() {
        return this.zznS;
    }
}
