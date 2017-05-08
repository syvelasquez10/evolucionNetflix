// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.animated.factory;

import com.facebook.imagepipeline.image.CloseableImage;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

public interface AnimatedImageFactory
{
    CloseableImage decodeGif(final EncodedImage p0, final ImageDecodeOptions p1, final Bitmap$Config p2);
    
    CloseableImage decodeWebP(final EncodedImage p0, final ImageDecodeOptions p1, final Bitmap$Config p2);
}
