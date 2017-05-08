// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.image.CloseableImage;

final class BitmapCountingMemoryCacheFactory$1 implements ValueDescriptor<CloseableImage>
{
    @Override
    public int getSizeInBytes(final CloseableImage closeableImage) {
        return closeableImage.getSizeInBytes();
    }
}
