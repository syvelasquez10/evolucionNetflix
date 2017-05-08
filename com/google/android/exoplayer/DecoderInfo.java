// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import com.google.android.exoplayer.util.Util;
import android.media.MediaCodecInfo$CodecCapabilities;
import android.annotation.TargetApi;

@TargetApi(16)
public final class DecoderInfo
{
    public final boolean adaptive;
    public final MediaCodecInfo$CodecCapabilities capabilities;
    public final String name;
    
    DecoderInfo(final String name, final MediaCodecInfo$CodecCapabilities capabilities) {
        this.name = name;
        this.capabilities = capabilities;
        this.adaptive = isAdaptive(capabilities);
    }
    
    private static boolean isAdaptive(final MediaCodecInfo$CodecCapabilities mediaCodecInfo$CodecCapabilities) {
        return mediaCodecInfo$CodecCapabilities != null && Util.SDK_INT >= 19 && isAdaptiveV19(mediaCodecInfo$CodecCapabilities);
    }
    
    @TargetApi(19)
    private static boolean isAdaptiveV19(final MediaCodecInfo$CodecCapabilities mediaCodecInfo$CodecCapabilities) {
        return mediaCodecInfo$CodecCapabilities.isFeatureSupported("adaptive-playback");
    }
}
