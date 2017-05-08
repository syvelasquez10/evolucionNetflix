// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.ImageRequest;
import java.util.Map;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class PostprocessedBitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>>
{
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    
    public PostprocessedBitmapMemoryCacheProducer(final MemoryCache<CacheKey, CloseableImage> mMemoryCache, final CacheKeyFactory mCacheKeyFactory, final Producer<CloseableReference<CloseableImage>> mInputProducer) {
        this.mMemoryCache = mMemoryCache;
        this.mCacheKeyFactory = mCacheKeyFactory;
        this.mInputProducer = mInputProducer;
    }
    
    protected String getProducerName() {
        return "PostprocessedBitmapMemoryCacheProducer";
    }
    
    @Override
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final Map<String, String> map = null;
        final Map<String, String> map2 = null;
        final ProducerListener listener = producerContext.getListener();
        final String id = producerContext.getId();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        final Object callerContext = producerContext.getCallerContext();
        final Postprocessor postprocessor = imageRequest.getPostprocessor();
        if (postprocessor == null || postprocessor.getPostprocessorCacheKey() == null) {
            this.mInputProducer.produceResults(consumer, producerContext);
            return;
        }
        listener.onProducerStart(id, this.getProducerName());
        final CacheKey postprocessedBitmapCacheKey = this.mCacheKeyFactory.getPostprocessedBitmapCacheKey(imageRequest, callerContext);
        final CloseableReference<CloseableImage> value = this.mMemoryCache.get(postprocessedBitmapCacheKey);
        if (value != null) {
            final String producerName = this.getProducerName();
            Map<String, String> of = map2;
            if (listener.requiresExtraMap(id)) {
                of = ImmutableMap.of("cached_value_found", "true");
            }
            listener.onProducerFinishWithSuccess(id, producerName, of);
            consumer.onProgressUpdate(1.0f);
            consumer.onNewResult(value, true);
            value.close();
            return;
        }
        final PostprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer postprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer = new PostprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer(consumer, postprocessedBitmapCacheKey, postprocessor instanceof RepeatedPostprocessor, this.mMemoryCache);
        final String producerName2 = this.getProducerName();
        Map<String, String> of2 = map;
        if (listener.requiresExtraMap(id)) {
            of2 = ImmutableMap.of("cached_value_found", "false");
        }
        listener.onProducerFinishWithSuccess(id, producerName2, of2);
        this.mInputProducer.produceResults((Consumer<CloseableReference<CloseableImage>>)postprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer, producerContext);
    }
}
