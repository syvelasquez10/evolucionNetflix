// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import java.util.Map;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>>
{
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    
    public BitmapMemoryCacheProducer(final MemoryCache<CacheKey, CloseableImage> mMemoryCache, final CacheKeyFactory mCacheKeyFactory, final Producer<CloseableReference<CloseableImage>> mInputProducer) {
        this.mMemoryCache = mMemoryCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mInputProducer = mInputProducer;
    }
    
    protected String getProducerName() {
        return "BitmapMemoryCacheProducer";
    }
    
    @Override
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final Map<String, String> map = null;
        final ProducerListener listener = producerContext.getListener();
        final String id = producerContext.getId();
        listener.onProducerStart(id, this.getProducerName());
        final CacheKey bitmapCacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
        final CloseableReference<CloseableImage> value = this.mMemoryCache.get(bitmapCacheKey);
        if (value != null) {
            final boolean ofFullQuality = value.get().getQualityInfo().isOfFullQuality();
            if (ofFullQuality) {
                final String producerName = this.getProducerName();
                Map<String, String> of;
                if (listener.requiresExtraMap(id)) {
                    of = ImmutableMap.of("cached_value_found", "true");
                }
                else {
                    of = null;
                }
                listener.onProducerFinishWithSuccess(id, producerName, of);
                consumer.onProgressUpdate(1.0f);
            }
            consumer.onNewResult(value, ofFullQuality);
            value.close();
            if (ofFullQuality) {
                return;
            }
        }
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
            final String producerName2 = this.getProducerName();
            Map<String, String> of2;
            if (listener.requiresExtraMap(id)) {
                of2 = ImmutableMap.of("cached_value_found", "false");
            }
            else {
                of2 = null;
            }
            listener.onProducerFinishWithSuccess(id, producerName2, of2);
            consumer.onNewResult(null, true);
            return;
        }
        final Consumer<CloseableReference<CloseableImage>> wrapConsumer = this.wrapConsumer(consumer, bitmapCacheKey);
        final String producerName3 = this.getProducerName();
        Map<String, String> of3 = map;
        if (listener.requiresExtraMap(id)) {
            of3 = ImmutableMap.of("cached_value_found", "false");
        }
        listener.onProducerFinishWithSuccess(id, producerName3, of3);
        this.mInputProducer.produceResults(wrapConsumer, producerContext);
    }
    
    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(final Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey) {
        return (Consumer<CloseableReference<CloseableImage>>)new BitmapMemoryCacheProducer$1(this, consumer, cacheKey);
    }
}
