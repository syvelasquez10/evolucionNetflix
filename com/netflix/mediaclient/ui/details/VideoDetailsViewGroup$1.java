// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.ui.details;

import java.util.EnumMap;

final class VideoDetailsViewGroup$1 extends EnumMap<VideoDetailsViewGroup$SupportedCapabilities, Integer>
{
    VideoDetailsViewGroup$1(final Class clazz) {
        super(clazz);
        this.put(VideoDetailsViewGroup$SupportedCapabilities.HD, Integer.valueOf(2131297088));
        this.put(VideoDetailsViewGroup$SupportedCapabilities.UHD, Integer.valueOf(2131297075));
        this.put(VideoDetailsViewGroup$SupportedCapabilities._5dot1, Integer.valueOf(2131297072));
        this.put(VideoDetailsViewGroup$SupportedCapabilities.HDR10, Integer.valueOf(2131297074));
        this.put(VideoDetailsViewGroup$SupportedCapabilities.DOLBY_VISION, Integer.valueOf(2131297073));
    }
}
