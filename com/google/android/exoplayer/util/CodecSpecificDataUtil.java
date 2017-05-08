// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.util.Pair;

public final class CodecSpecificDataUtil
{
    private static final int[] AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE;
    private static final int[] AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE;
    private static final byte[] NAL_START_CODE;
    
    static {
        NAL_START_CODE = new byte[] { 0, 0, 0, 1 };
        AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE = new int[] { 96000, 88200, 64000, 48000, 44100, 32000, 24000, 22050, 16000, 12000, 11025, 8000, 7350 };
        AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE = new int[] { 0, 1, 2, 3, 4, 5, 6, 8, -1, -1, -1, 7, 8, -1, 8, -1 };
    }
    
    public static byte[] buildNalUnit(final byte[] array, final int n, final int n2) {
        final byte[] array2 = new byte[CodecSpecificDataUtil.NAL_START_CODE.length + n2];
        System.arraycopy(CodecSpecificDataUtil.NAL_START_CODE, 0, array2, 0, CodecSpecificDataUtil.NAL_START_CODE.length);
        System.arraycopy(array, n, array2, CodecSpecificDataUtil.NAL_START_CODE.length, n2);
        return array2;
    }
    
    public static Pair<Integer, Integer> parseAacAudioSpecificConfig(final byte[] array) {
        final boolean b = true;
        final ParsableBitArray parsableBitArray = new ParsableBitArray(array);
        final int bits = parsableBitArray.readBits(5);
        final int bits2 = parsableBitArray.readBits(4);
        int bits3;
        if (bits2 == 15) {
            bits3 = parsableBitArray.readBits(24);
        }
        else {
            Assertions.checkArgument(bits2 < 13);
            bits3 = CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[bits2];
        }
        final int bits4 = parsableBitArray.readBits(4);
        while (true) {
            Label_0187: {
                if (bits != 5 && bits != 29) {
                    break Label_0187;
                }
                final int bits5 = parsableBitArray.readBits(4);
                int bits6;
                if (bits5 == 15) {
                    bits6 = parsableBitArray.readBits(24);
                }
                else {
                    Assertions.checkArgument(bits5 < 13);
                    bits6 = CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_SAMPLING_RATE_TABLE[bits5];
                }
                bits3 = bits6;
                if (parsableBitArray.readBits(5) != 22) {
                    break Label_0187;
                }
                final int bits7 = parsableBitArray.readBits(4);
                final int n = CodecSpecificDataUtil.AUDIO_SPECIFIC_CONFIG_CHANNEL_COUNT_TABLE[bits7];
                Assertions.checkArgument(n != -1 && b);
                return (Pair<Integer, Integer>)Pair.create((Object)bits6, (Object)n);
            }
            int bits6 = bits3;
            final int bits7 = bits4;
            continue;
        }
    }
}
