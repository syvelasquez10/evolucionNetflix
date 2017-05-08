// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

import android.util.Log;
import java.nio.ByteBuffer;

public final class NalUnitUtil
{
    public static final float[] ASPECT_RATIO_IDC_VALUES;
    public static final byte[] NAL_START_CODE;
    private static int[] scratchEscapePositions;
    private static final Object scratchEscapePositionsLock;
    
    static {
        NAL_START_CODE = new byte[] { 0, 0, 0, 1 };
        ASPECT_RATIO_IDC_VALUES = new float[] { 1.0f, 1.0f, 1.0909091f, 0.90909094f, 1.4545455f, 1.2121212f, 2.1818182f, 1.8181819f, 2.909091f, 2.4242425f, 1.6363636f, 1.3636364f, 1.939394f, 1.6161616f, 1.3333334f, 1.5f, 2.0f };
        scratchEscapePositionsLock = new Object();
        NalUnitUtil.scratchEscapePositions = new int[10];
    }
    
    public static void discardToSps(final ByteBuffer byteBuffer) {
        final int position = byteBuffer.position();
        int n = 0;
        int n2 = 0;
        while (n + 1 < position) {
            final int n3 = byteBuffer.get(n) & 0xFF;
            int n4;
            if (n2 == 3) {
                n4 = n2;
                if (n3 == 1) {
                    n4 = n2;
                    if ((byteBuffer.get(n + 1) & 0x1F) == 0x7) {
                        final ByteBuffer duplicate = byteBuffer.duplicate();
                        duplicate.position(n - 3);
                        duplicate.limit(position);
                        byteBuffer.position(0);
                        byteBuffer.put(duplicate);
                        return;
                    }
                }
            }
            else {
                n4 = n2;
                if (n3 == 0) {
                    n4 = n2 + 1;
                }
            }
            n2 = n4;
            if (n3 != 0) {
                n2 = 0;
            }
            ++n;
        }
        byteBuffer.clear();
    }
    
    public static byte[] parseChildNalUnit(final ParsableByteArray parsableByteArray) {
        final int unsignedShort = parsableByteArray.readUnsignedShort();
        final int position = parsableByteArray.getPosition();
        parsableByteArray.skipBytes(unsignedShort);
        return CodecSpecificDataUtil.buildNalUnit(parsableByteArray.data, position, unsignedShort);
    }
    
    public static NalUnitUtil$SpsData parseSpsNalUnit(final ParsableBitArray parsableBitArray) {
        final int bits = parsableBitArray.readBits(8);
        parsableBitArray.skipBits(16);
        final int unsignedExpGolombCodedInt = parsableBitArray.readUnsignedExpGolombCodedInt();
        boolean bit = false;
        int n3;
        if (bits == 100 || bits == 110 || bits == 122 || bits == 244 || bits == 44 || bits == 83 || bits == 86 || bits == 118 || bits == 128 || bits == 138) {
            final int unsignedExpGolombCodedInt2 = parsableBitArray.readUnsignedExpGolombCodedInt();
            if (unsignedExpGolombCodedInt2 == 3) {
                bit = parsableBitArray.readBit();
            }
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.readUnsignedExpGolombCodedInt();
            parsableBitArray.skipBits(1);
            if (parsableBitArray.readBit()) {
                int n;
                if (unsignedExpGolombCodedInt2 != 3) {
                    n = 8;
                }
                else {
                    n = 12;
                }
                for (int i = 0; i < n; ++i) {
                    if (parsableBitArray.readBit()) {
                        int n2;
                        if (i < 6) {
                            n2 = 16;
                        }
                        else {
                            n2 = 64;
                        }
                        skipScalingList(parsableBitArray, n2);
                    }
                }
            }
            n3 = unsignedExpGolombCodedInt2;
        }
        else {
            bit = false;
            n3 = 1;
        }
        final int unsignedExpGolombCodedInt3 = parsableBitArray.readUnsignedExpGolombCodedInt();
        final int unsignedExpGolombCodedInt4 = parsableBitArray.readUnsignedExpGolombCodedInt();
        final boolean b = false;
        boolean b2 = false;
        int n4;
        if (unsignedExpGolombCodedInt4 == 0) {
            n4 = parsableBitArray.readUnsignedExpGolombCodedInt() + 4;
        }
        else {
            n4 = (b ? 1 : 0);
            if (unsignedExpGolombCodedInt4 == 1) {
                final boolean bit2 = parsableBitArray.readBit();
                parsableBitArray.readSignedExpGolombCodedInt();
                parsableBitArray.readSignedExpGolombCodedInt();
                final long n5 = parsableBitArray.readUnsignedExpGolombCodedInt();
                int n6 = 0;
                while (true) {
                    n4 = (b ? 1 : 0);
                    b2 = bit2;
                    if (n6 >= n5) {
                        break;
                    }
                    parsableBitArray.readUnsignedExpGolombCodedInt();
                    ++n6;
                }
            }
        }
        parsableBitArray.readUnsignedExpGolombCodedInt();
        parsableBitArray.skipBits(1);
        final int unsignedExpGolombCodedInt5 = parsableBitArray.readUnsignedExpGolombCodedInt();
        final int unsignedExpGolombCodedInt6 = parsableBitArray.readUnsignedExpGolombCodedInt();
        final boolean bit3 = parsableBitArray.readBit();
        int n7;
        if (bit3) {
            n7 = 1;
        }
        else {
            n7 = 0;
        }
        if (!bit3) {
            parsableBitArray.skipBits(1);
        }
        parsableBitArray.skipBits(1);
        final int n8 = (unsignedExpGolombCodedInt5 + 1) * 16;
        final int n9 = (2 - n7) * (unsignedExpGolombCodedInt6 + 1) * 16;
        int n17;
        int n18;
        if (parsableBitArray.readBit()) {
            final int unsignedExpGolombCodedInt7 = parsableBitArray.readUnsignedExpGolombCodedInt();
            final int unsignedExpGolombCodedInt8 = parsableBitArray.readUnsignedExpGolombCodedInt();
            final int unsignedExpGolombCodedInt9 = parsableBitArray.readUnsignedExpGolombCodedInt();
            final int unsignedExpGolombCodedInt10 = parsableBitArray.readUnsignedExpGolombCodedInt();
            int n13;
            int n14;
            if (n3 == 0) {
                final int n10 = 1;
                int n11;
                if (bit3) {
                    n11 = 1;
                }
                else {
                    n11 = 0;
                }
                final int n12 = 2 - n11;
                n13 = n10;
                n14 = n12;
            }
            else {
                if (n3 == 3) {
                    n13 = 1;
                }
                else {
                    n13 = 2;
                }
                int n15;
                if (n3 == 1) {
                    n15 = 2;
                }
                else {
                    n15 = 1;
                }
                int n16;
                if (bit3) {
                    n16 = 1;
                }
                else {
                    n16 = 0;
                }
                n14 = (2 - n16) * n15;
            }
            n17 = n8 - n13 * (unsignedExpGolombCodedInt7 + unsignedExpGolombCodedInt8);
            n18 = n9 - n14 * (unsignedExpGolombCodedInt9 + unsignedExpGolombCodedInt10);
        }
        else {
            n17 = n8;
            n18 = n9;
        }
        final float n19 = 1.0f;
        Label_0648: {
            if (!parsableBitArray.readBit() || !parsableBitArray.readBit()) {
                break Label_0648;
            }
            final int bits2 = parsableBitArray.readBits(8);
            float n20;
            if (bits2 == 255) {
                final int bits3 = parsableBitArray.readBits(16);
                final int bits4 = parsableBitArray.readBits(16);
                n20 = n19;
                if (bits3 != 0) {
                    n20 = n19;
                    if (bits4 != 0) {
                        n20 = bits3 / bits4;
                    }
                }
            }
            else {
                if (bits2 >= NalUnitUtil.ASPECT_RATIO_IDC_VALUES.length) {
                    Log.w("NalUnitUtil", "Unexpected aspect_ratio_idc value: " + bits2);
                    break Label_0648;
                }
                n20 = NalUnitUtil.ASPECT_RATIO_IDC_VALUES[bits2];
            }
            return new NalUnitUtil$SpsData(unsignedExpGolombCodedInt, n17, n18, n20, bit, bit3, unsignedExpGolombCodedInt3 + 4, unsignedExpGolombCodedInt4, n4, b2);
        }
        float n20 = 1.0f;
        return new NalUnitUtil$SpsData(unsignedExpGolombCodedInt, n17, n18, n20, bit, bit3, unsignedExpGolombCodedInt3 + 4, unsignedExpGolombCodedInt4, n4, b2);
    }
    
    private static void skipScalingList(final ParsableBitArray parsableBitArray, final int n) {
        int n2 = 8;
        int i = 0;
        int n3 = 8;
        while (i < n) {
            int n4;
            if ((n4 = n2) != 0) {
                n4 = (parsableBitArray.readSignedExpGolombCodedInt() + n3 + 256) % 256;
            }
            if (n4 != 0) {
                n3 = n4;
            }
            ++i;
            n2 = n4;
        }
    }
}
