// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.image.EncodedImage;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.platform.PlatformDecoder;
import android.annotation.TargetApi;

@TargetApi(11)
public class HoneycombBitmapFactory extends PlatformBitmapFactory
{
    private final EmptyJpegGenerator mJpegGenerator;
    private final PlatformDecoder mPurgeableDecoder;
    
    public HoneycombBitmapFactory(final EmptyJpegGenerator mJpegGenerator, final PlatformDecoder mPurgeableDecoder) {
        this.mJpegGenerator = mJpegGenerator;
        this.mPurgeableDecoder = mPurgeableDecoder;
    }
    
    @Override
    public CloseableReference<Bitmap> createBitmap(final int n, final int n2, final Bitmap$Config bitmap$Config) {
        final CloseableReference<PooledByteBuffer> generate = this.mJpegGenerator.generate((short)n, (short)n2);
        try {
            final EncodedImage encodedImage = new EncodedImage(generate);
            encodedImage.setImageFormat(ImageFormat.JPEG);
            try {
                final CloseableReference<Bitmap> decodeJPEGFromEncodedImage = this.mPurgeableDecoder.decodeJPEGFromEncodedImage(encodedImage, bitmap$Config, generate.get().size());
                decodeJPEGFromEncodedImage.get().eraseColor(0);
                return decodeJPEGFromEncodedImage;
            }
            finally {
                EncodedImage.closeSafely(encodedImage);
            }
        }
        finally {
            generate.close();
        }
    }
}
