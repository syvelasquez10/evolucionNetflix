// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;

public class BitmapMemoryCacheGetProducer extends BitmapMemoryCacheProducer
{
    public BitmapMemoryCacheGetProducer(final MemoryCache<CacheKey, CloseableImage> memoryCache, final CacheKeyFactory cacheKeyFactory, final Producer<CloseableReference<CloseableImage>> producer) {
        super(memoryCache, cacheKeyFactory, producer);
    }
    
    @Override
    protected String getProducerName() {
        return "BitmapMemoryCacheGetProducer";
    }
    
    @Override
    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(final Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey) {
        return consumer;
    }
}
