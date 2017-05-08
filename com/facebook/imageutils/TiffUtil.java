// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageutils;

import java.io.InputStream;
import com.facebook.common.logging.FLog;

class TiffUtil
{
    private static final Class<?> TAG;
    
    static {
        TAG = TiffUtil.class;
    }
    
    public static int getAutoRotateAngleFromOrientation(final int n) {
        switch (n) {
            default: {
                FLog.i(TiffUtil.TAG, "Unsupported orientation");
                return 0;
            }
            case 1: {
                return 0;
            }
            case 3: {
                return 180;
            }
            case 6: {
                return 90;
            }
            case 8: {
                return 270;
            }
        }
    }
    
    private static int getOrientationFromTiffEntry(final InputStream inputStream, int packedInt, final boolean b) {
        if (packedInt >= 10 && StreamProcessor.readPackedInt(inputStream, 2, b) == 3 && StreamProcessor.readPackedInt(inputStream, 4, b) == 1) {
            packedInt = StreamProcessor.readPackedInt(inputStream, 2, b);
            StreamProcessor.readPackedInt(inputStream, 2, b);
            return packedInt;
        }
        return 0;
    }
    
    private static int moveToTiffEntryWithTag(final InputStream inputStream, int n, final boolean b, final int n2) {
        if (n >= 14) {
            final int packedInt = StreamProcessor.readPackedInt(inputStream, 2, b);
            int n3;
            int packedInt2;
            int n4;
            for (n3 = n - 2, n = packedInt; n > 0 && n3 >= 12; n3 = n4 - 10, --n) {
                packedInt2 = StreamProcessor.readPackedInt(inputStream, 2, b);
                n4 = n3 - 2;
                if (packedInt2 == n2) {
                    return n4;
                }
                inputStream.skip(10L);
            }
        }
        return 0;
    }
    
    public static int readOrientationFromTIFF(final InputStream inputStream, int tiffHeader) {
        final TiffUtil$TiffHeader tiffUtil$TiffHeader = new TiffUtil$TiffHeader(null);
        tiffHeader = readTiffHeader(inputStream, tiffHeader, tiffUtil$TiffHeader);
        final int n = tiffUtil$TiffHeader.firstIfdOffset - 8;
        if (tiffHeader == 0 || n > tiffHeader) {
            return 0;
        }
        inputStream.skip(n);
        return getOrientationFromTiffEntry(inputStream, moveToTiffEntryWithTag(inputStream, tiffHeader - n, tiffUtil$TiffHeader.isLittleEndian, 274), tiffUtil$TiffHeader.isLittleEndian);
    }
    
    private static int readTiffHeader(final InputStream inputStream, int n, final TiffUtil$TiffHeader tiffUtil$TiffHeader) {
        if (n <= 8) {
            return 0;
        }
        tiffUtil$TiffHeader.byteOrder = StreamProcessor.readPackedInt(inputStream, 4, false);
        if (tiffUtil$TiffHeader.byteOrder != 1229531648 && tiffUtil$TiffHeader.byteOrder != 1296891946) {
            FLog.e(TiffUtil.TAG, "Invalid TIFF header");
            return 0;
        }
        tiffUtil$TiffHeader.isLittleEndian = (tiffUtil$TiffHeader.byteOrder == 1229531648);
        tiffUtil$TiffHeader.firstIfdOffset = StreamProcessor.readPackedInt(inputStream, 4, tiffUtil$TiffHeader.isLittleEndian);
        n = n - 4 - 4;
        if (tiffUtil$TiffHeader.firstIfdOffset < 8 || tiffUtil$TiffHeader.firstIfdOffset - 8 > n) {
            FLog.e(TiffUtil.TAG, "Invalid offset");
            return 0;
        }
        return n;
    }
}
