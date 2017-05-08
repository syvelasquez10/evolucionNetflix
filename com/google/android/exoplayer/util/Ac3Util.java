// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import java.nio.ByteBuffer;
import java.util.List;
import com.google.android.exoplayer.MediaFormat;

public final class Ac3Util
{
    private static final int[] BITRATE_BY_HALF_FRMSIZECOD;
    private static final int[] BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD;
    private static final int[] CHANNEL_COUNT_BY_ACMOD;
    private static final int[] SAMPLE_RATE_BY_FSCOD;
    private static final int[] SAMPLE_RATE_BY_FSCOD2;
    private static final int[] SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1;
    
    static {
        BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD = new int[] { 1, 2, 3, 6 };
        SAMPLE_RATE_BY_FSCOD = new int[] { 48000, 44100, 32000 };
        SAMPLE_RATE_BY_FSCOD2 = new int[] { 24000, 22050, 16000 };
        CHANNEL_COUNT_BY_ACMOD = new int[] { 2, 1, 2, 3, 3, 4, 4, 5 };
        BITRATE_BY_HALF_FRMSIZECOD = new int[] { 32, 40, 48, 56, 64, 80, 96, 112, 128, 160, 192, 224, 256, 320, 384, 448, 512, 576, 640 };
        SYNCFRAME_SIZE_WORDS_BY_HALF_FRMSIZECOD_44_1 = new int[] { 69, 87, 104, 121, 139, 174, 208, 243, 278, 348, 417, 487, 557, 696, 835, 975, 1114, 1253, 1393 };
    }
    
    public static int getAc3SyncframeAudioSampleCount() {
        return 1536;
    }
    
    public static MediaFormat parseAc3AnnexFFormat(final ParsableByteArray parsableByteArray, final String s, final long n, final String s2) {
        final int n2 = Ac3Util.SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 0xC0) >> 6];
        final int unsignedByte = parsableByteArray.readUnsignedByte();
        int n3 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 0x38) >> 3];
        if ((unsignedByte & 0x4) != 0x0) {
            ++n3;
        }
        return MediaFormat.createAudioFormat(s, "audio/ac3", -1, -1, n, n3, n2, null, s2);
    }
    
    public static MediaFormat parseEAc3AnnexFFormat(final ParsableByteArray parsableByteArray, final String s, final long n, final String s2) {
        parsableByteArray.skipBytes(2);
        final int n2 = Ac3Util.SAMPLE_RATE_BY_FSCOD[(parsableByteArray.readUnsignedByte() & 0xC0) >> 6];
        final int unsignedByte = parsableByteArray.readUnsignedByte();
        int n3 = Ac3Util.CHANNEL_COUNT_BY_ACMOD[(unsignedByte & 0xE) >> 1];
        if ((unsignedByte & 0x1) != 0x0) {
            ++n3;
        }
        return MediaFormat.createAudioFormat(s, "audio/eac3", -1, -1, n, n3, n2, null, s2);
    }
    
    public static int parseEAc3SyncframeAudioSampleCount(final ByteBuffer byteBuffer) {
        int n;
        if ((byteBuffer.get(byteBuffer.position() + 4) & 0xC0) >> 6 == 3) {
            n = 6;
        }
        else {
            n = Ac3Util.BLOCKS_PER_SYNCFRAME_BY_NUMBLKSCOD[(byteBuffer.get(byteBuffer.position() + 4) & 0x30) >> 4];
        }
        return n * 256;
    }
}
