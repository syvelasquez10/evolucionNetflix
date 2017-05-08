// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.kubrick.details;

import com.netflix.mediaclient.util.DeviceUtils;
import android.content.Context;

public enum BarkerHelper$ArtworkFormat
{
    REGULAR(720, 16, 9), 
    SHORT(0, 16, 8), 
    TALL(960, 4, 3);
    
    private int mHRatio;
    private int mStartRangeDp;
    private int mWRatio;
    
    private BarkerHelper$ArtworkFormat(final int mStartRangeDp, final int mwRatio, final int mhRatio) {
        this.mWRatio = mwRatio;
        this.mHRatio = mhRatio;
        this.mStartRangeDp = mStartRangeDp;
    }
    
    public static BarkerHelper$ArtworkFormat getFormatForDevice(final Context context) {
        final int screenHeightInPixels = DeviceUtils.getScreenHeightInPixels(context);
        final BarkerHelper$ArtworkFormat[] values = values();
        final int length = values.length;
        BarkerHelper$ArtworkFormat barkerHelper$ArtworkFormat = null;
        BarkerHelper$ArtworkFormat barkerHelper$ArtworkFormat2;
        for (int i = 0; i < length; ++i, barkerHelper$ArtworkFormat = barkerHelper$ArtworkFormat2) {
            barkerHelper$ArtworkFormat2 = values[i];
            if (screenHeightInPixels <= barkerHelper$ArtworkFormat2.mStartRangeDp) {
                break;
            }
        }
        return barkerHelper$ArtworkFormat;
    }
    
    public int getHeightRatio() {
        return this.mHRatio;
    }
    
    public int getRatio() {
        return this.getWidthRatio() / this.getHeightRatio();
    }
    
    public int getWidthRatio() {
        return this.mWRatio;
    }
}
