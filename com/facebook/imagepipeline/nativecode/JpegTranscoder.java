// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.nativecode;

import com.facebook.common.internal.Preconditions;
import java.io.OutputStream;
import java.io.InputStream;
import com.facebook.common.internal.DoNotStrip;

@DoNotStrip
public class JpegTranscoder
{
    static {
        ImagePipelineNativeLoader.load();
    }
    
    public static boolean isRotationAngleAllowed(final int n) {
        return n >= 0 && n <= 270 && n % 90 == 0;
    }
    
    @DoNotStrip
    private static native void nativeTranscodeJpeg(final InputStream p0, final OutputStream p1, final int p2, final int p3, final int p4);
    
    public static void transcodeJpeg(final InputStream inputStream, final OutputStream outputStream, final int n, final int n2, final int n3) {
        final boolean b = false;
        Preconditions.checkArgument(n2 >= 1);
        Preconditions.checkArgument(n2 <= 16);
        Preconditions.checkArgument(n3 >= 0);
        Preconditions.checkArgument(n3 <= 100);
        Preconditions.checkArgument(isRotationAngleAllowed(n));
        boolean b2 = false;
        Label_0082: {
            if (n2 == 8) {
                b2 = b;
                if (n == 0) {
                    break Label_0082;
                }
            }
            b2 = true;
        }
        Preconditions.checkArgument(b2, (Object)"no transformation requested");
        nativeTranscodeJpeg(Preconditions.checkNotNull(inputStream), Preconditions.checkNotNull(outputStream), n, n2, n3);
    }
}
