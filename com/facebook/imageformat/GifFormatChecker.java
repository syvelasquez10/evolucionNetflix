// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageformat;

import java.io.IOException;
import java.io.InputStream;
import com.facebook.common.internal.Preconditions;

public final class GifFormatChecker
{
    private static final byte[] FRAME_HEADER_END_1;
    private static final byte[] FRAME_HEADER_END_2;
    private static final byte[] FRAME_HEADER_START;
    
    static {
        FRAME_HEADER_START = new byte[] { 0, 33, -7, 4 };
        FRAME_HEADER_END_1 = new byte[] { 0, 44 };
        FRAME_HEADER_END_2 = new byte[] { 0, 33 };
    }
    
    static boolean circularBufferMatchesBytePattern(final byte[] array, final int n, final byte[] array2) {
        Preconditions.checkNotNull(array);
        Preconditions.checkNotNull(array2);
        Preconditions.checkArgument(n >= 0);
        if (array2.length <= array.length) {
            for (int i = 0; i < array2.length; ++i) {
                if (array[(i + n) % array.length] != array2[i]) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }
    
    public static boolean isAnimated(final InputStream inputStream) {
        while (true) {
            final byte[] array = new byte[10];
            while (true) {
                int n = 0;
                Label_0107: {
                    try {
                        inputStream.read(array, 0, 10);
                        n = 0;
                        Block_7: {
                            int n3;
                            for (int n2 = 0; inputStream.read(array, n2, 1) > 0; n2 = (n2 + 1) % array.length, n = n3) {
                                n3 = n;
                                if (circularBufferMatchesBytePattern(array, n2 + 1, GifFormatChecker.FRAME_HEADER_START)) {
                                    if (circularBufferMatchesBytePattern(array, n2 + 9, GifFormatChecker.FRAME_HEADER_END_1)) {
                                        break Label_0107;
                                    }
                                    n3 = n;
                                    if (circularBufferMatchesBytePattern(array, n2 + 9, GifFormatChecker.FRAME_HEADER_END_2)) {
                                        break Block_7;
                                    }
                                }
                            }
                            return false;
                        }
                        break Label_0107;
                    }
                    catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    return false;
                }
                int n3;
                if ((n3 = n + 1) > 1) {
                    return true;
                }
                continue;
            }
        }
    }
}
