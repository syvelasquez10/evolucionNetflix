// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;

public class EncodedMemoryCacheProducer implements Producer<EncodedImage>
{
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<EncodedImage> mInputProducer;
    private final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache;
    
    public EncodedMemoryCacheProducer(final MemoryCache<CacheKey, PooledByteBuffer> mMemoryCache, final CacheKeyFactory mCacheKeyFactory, final Producer<EncodedImage> mInputProducer) {
        this.mMemoryCache = mMemoryCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mInputProducer = mInputProducer;
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        final Map<String, String> map = null;
        final Map<String, String> map2 = null;
        final Map<String, String> map3 = null;
        final String id = producerContext.getId();
        final ProducerListener listener = producerContext.getListener();
        listener.onProducerStart(id, "EncodedMemoryCacheProducer");
        final CacheKey encodedCacheKey = this.mCacheKeyFactory.getEncodedCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
        final CloseableReference<PooledByteBuffer> value = this.mMemoryCache.get(encodedCacheKey);
        if (value != null) {
            try {
                final EncodedImage encodedImage = new EncodedImage(value);
                Map<String, String> of = map3;
                try {
                    if (listener.requiresExtraMap(id)) {
                        of = ImmutableMap.of("cached_value_found", "true");
                    }
                    listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", of);
                    consumer.onProgressUpdate(1.0f);
                    consumer.onNewResult(encodedImage, true);
                    return;
                }
                finally {
                    EncodedImage.closeSafely(encodedImage);
                }
            }
            finally {
                CloseableReference.closeSafely(value);
            }
        }
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest$RequestLevel.ENCODED_MEMORY_CACHE.getValue()) {
            Map<String, String> of2 = map;
            if (listener.requiresExtraMap(id)) {
                of2 = ImmutableMap.of("cached_value_found", "false");
            }
            listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", of2);
            consumer.onNewResult(null, true);
            CloseableReference.closeSafely(value);
            return;
        }
        final EncodedMemoryCacheProducer$1 encodedMemoryCacheProducer$1 = new EncodedMemoryCacheProducer$1(this, consumer, encodedCacheKey);
        Map<String, String> of3 = map2;
        if (listener.requiresExtraMap(id)) {
            of3 = ImmutableMap.of("cached_value_found", "false");
        }
        listener.onProducerFinishWithSuccess(id, "EncodedMemoryCacheProducer", of3);
        this.mInputProducer.produceResults((Consumer<EncodedImage>)encodedMemoryCacheProducer$1, producerContext);
        CloseableReference.closeSafely(value);
    }
}
