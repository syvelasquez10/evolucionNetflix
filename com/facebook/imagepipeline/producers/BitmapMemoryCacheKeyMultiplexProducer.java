// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.io.Closeable;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.cache.common.CacheKey;
import android.util.Pair;

public class BitmapMemoryCacheKeyMultiplexProducer extends MultiplexProducer<Pair<CacheKey, ImageRequest$RequestLevel>, CloseableReference<CloseableImage>>
{
    private final CacheKeyFactory mCacheKeyFactory;
    
    public BitmapMemoryCacheKeyMultiplexProducer(final CacheKeyFactory mCacheKeyFactory, final Producer producer) {
        super(producer);
        this.mCacheKeyFactory = mCacheKeyFactory;
    }
    
    public CloseableReference<CloseableImage> cloneOrNull(final CloseableReference<CloseableImage> closeableReference) {
        return CloseableReference.cloneOrNull(closeableReference);
    }
    
    @Override
    protected Pair<CacheKey, ImageRequest$RequestLevel> getKey(final ProducerContext producerContext) {
        return (Pair<CacheKey, ImageRequest$RequestLevel>)Pair.create((Object)this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext()), (Object)producerContext.getLowestPermittedRequestLevel());
    }
}
