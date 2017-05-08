// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.Closeable;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.cache.common.CacheKey;
import android.util.Pair;

public class EncodedCacheKeyMultiplexProducer extends MultiplexProducer<Pair<CacheKey, ImageRequest$RequestLevel>, EncodedImage>
{
    private final CacheKeyFactory mCacheKeyFactory;
    
    public EncodedCacheKeyMultiplexProducer(final CacheKeyFactory mCacheKeyFactory, final Producer producer) {
        super(producer);
        this.mCacheKeyFactory = mCacheKeyFactory;
    }
    
    public EncodedImage cloneOrNull(final EncodedImage encodedImage) {
        return EncodedImage.cloneOrNull(encodedImage);
    }
    
    @Override
    protected Pair<CacheKey, ImageRequest$RequestLevel> getKey(final ProducerContext producerContext) {
        return (Pair<CacheKey, ImageRequest$RequestLevel>)Pair.create((Object)this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext()), (Object)producerContext.getLowestPermittedRequestLevel());
    }
}
