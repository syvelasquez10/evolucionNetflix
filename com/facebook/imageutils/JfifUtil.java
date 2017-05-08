// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageutils;

import com.facebook.common.internal.Preconditions;
import java.io.IOException;
import java.io.InputStream;

public class JfifUtil
{
    public static int getAutoRotateAngleFromOrientation(final int n) {
        return TiffUtil.getAutoRotateAngleFromOrientation(n);
    }
    
    public static int getOrientation(final InputStream inputStream) {
        try {
            final int moveToAPP1EXIF = moveToAPP1EXIF(inputStream);
            if (moveToAPP1EXIF == 0) {
                return 0;
            }
            return TiffUtil.readOrientationFromTIFF(inputStream, moveToAPP1EXIF);
        }
        catch (IOException ex) {
            return 0;
        }
    }
    
    private static boolean isSOFn(final int n) {
        switch (n) {
            default: {
                return false;
            }
            case 192:
            case 193:
            case 194:
            case 195:
            case 197:
            case 198:
            case 199:
            case 201:
            case 202:
            case 203:
            case 205:
            case 206:
            case 207: {
                return true;
            }
        }
    }
    
    private static int moveToAPP1EXIF(final InputStream inputStream) {
        if (moveToMarker(inputStream, 225)) {
            final int n = StreamProcessor.readPackedInt(inputStream, 2, false) - 2;
            if (n > 6) {
                final int packedInt = StreamProcessor.readPackedInt(inputStream, 4, false);
                final int packedInt2 = StreamProcessor.readPackedInt(inputStream, 2, false);
                if (packedInt == 1165519206 && packedInt2 == 0) {
                    return n - 4 - 2;
                }
            }
        }
        return 0;
    }
    
    public static boolean moveToMarker(final InputStream inputStream, final int n) {
        Preconditions.checkNotNull(inputStream);
        while (StreamProcessor.readPackedInt(inputStream, 1, false) == 255) {
            int i;
            for (i = 255; i == 255; i = StreamProcessor.readPackedInt(inputStream, 1, false)) {}
            if ((n == 192 && isSOFn(i)) || i == n) {
                return true;
            }
            if (i == 216 || i == 1) {
                continue;
            }
            if (i == 217 || i == 218) {
                return false;
            }
            inputStream.skip(StreamProcessor.readPackedInt(inputStream, 2, false) - 2);
        }
        return false;
    }
}
