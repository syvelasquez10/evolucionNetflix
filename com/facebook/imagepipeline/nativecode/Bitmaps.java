// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.nativecode;

import android.annotation.TargetApi;
import com.facebook.imageutils.BitmapUtil;
import android.graphics.Bitmap$Config;
import java.nio.ByteBuffer;
import com.facebook.common.internal.Preconditions;
import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;

@DoNotStrip
public class Bitmaps
{
    static {
        ImagePipelineNativeLoader.load();
    }
    
    public static void copyBitmap(final Bitmap bitmap, final Bitmap bitmap2) {
        final boolean b = true;
        Preconditions.checkArgument(bitmap2.getConfig() == bitmap.getConfig());
        Preconditions.checkArgument(bitmap.isMutable());
        Preconditions.checkArgument(bitmap.getWidth() == bitmap2.getWidth());
        Preconditions.checkArgument(bitmap.getHeight() == bitmap2.getHeight() && b);
        nativeCopyBitmap(bitmap, bitmap.getRowBytes(), bitmap2, bitmap2.getRowBytes(), bitmap.getHeight());
    }
    
    @DoNotStrip
    private static native void nativeCopyBitmap(final Bitmap p0, final int p1, final Bitmap p2, final int p3, final int p4);
    
    @DoNotStrip
    private static native ByteBuffer nativeGetByteBuffer(final Bitmap p0, final long p1, final long p2);
    
    @DoNotStrip
    private static native void nativePinBitmap(final Bitmap p0);
    
    @DoNotStrip
    private static native void nativeReleaseByteBuffer(final Bitmap p0);
    
    public static void pinBitmap(final Bitmap bitmap) {
        Preconditions.checkNotNull(bitmap);
        nativePinBitmap(bitmap);
    }
    
    @TargetApi(19)
    public static void reconfigureBitmap(final Bitmap bitmap, final int n, final int n2, final Bitmap$Config bitmap$Config) {
        Preconditions.checkArgument(bitmap.getAllocationByteCount() >= n * n2 * BitmapUtil.getPixelSizeForBitmapConfig(bitmap$Config));
        bitmap.reconfigure(n, n2, bitmap$Config);
    }
}
