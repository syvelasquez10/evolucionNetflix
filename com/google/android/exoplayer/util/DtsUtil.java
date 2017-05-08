// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.nio.ByteBuffer;

public final class DtsUtil
{
    private static final int[] CHANNELS_BY_AMODE;
    private static final int[] SAMPLE_RATE_BY_SFREQ;
    private static final ParsableBitArray SCRATCH_BITS;
    private static final int[] TWICE_BITRATE_KBPS_BY_RATE;
    
    static {
        CHANNELS_BY_AMODE = new int[] { 1, 2, 2, 2, 2, 3, 3, 4, 4, 5, 6, 6, 6, 7, 8, 8 };
        SAMPLE_RATE_BY_SFREQ = new int[] { -1, 8000, 16000, 32000, -1, -1, 11025, 22050, 44100, -1, -1, 12000, 24000, 48000, -1, -1 };
        TWICE_BITRATE_KBPS_BY_RATE = new int[] { 64, 112, 128, 192, 224, 256, 384, 448, 512, 640, 768, 896, 1024, 1152, 1280, 1536, 1920, 2048, 2304, 2560, 2688, 2816, 2823, 2944, 3072, 3840, 4096, 6144, 7680 };
        SCRATCH_BITS = new ParsableBitArray();
    }
    
    public static int parseDtsAudioSampleCount(final ByteBuffer byteBuffer) {
        final int position = byteBuffer.position();
        return (((byteBuffer.get(position + 5) & 0xFC) >> 2 | (byteBuffer.get(position + 4) & 0x1) << 6) + 1) * 32;
    }
}
