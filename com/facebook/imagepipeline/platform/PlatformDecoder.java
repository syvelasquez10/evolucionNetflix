// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.image.EncodedImage;

public interface PlatformDecoder
{
    CloseableReference<Bitmap> decodeFromEncodedImage(final EncodedImage p0, final Bitmap$Config p1);
    
    CloseableReference<Bitmap> decodeJPEGFromEncodedImage(final EncodedImage p0, final Bitmap$Config p1, final int p2);
}
