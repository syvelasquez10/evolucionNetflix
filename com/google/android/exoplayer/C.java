// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Util;

public final class C
{
    public static final int CHANNEL_OUT_7POINT1_SURROUND;
    
    static {
        int channel_OUT_7POINT1_SURROUND;
        if (Util.SDK_INT < 23) {
            channel_OUT_7POINT1_SURROUND = 1020;
        }
        else {
            channel_OUT_7POINT1_SURROUND = 6396;
        }
        CHANNEL_OUT_7POINT1_SURROUND = channel_OUT_7POINT1_SURROUND;
    }
}
