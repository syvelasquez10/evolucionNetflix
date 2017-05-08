// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class PostprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
{
    private final CacheKey mCacheKey;
    private final boolean mIsRepeatedProcessor;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;
    
    public PostprocessedBitmapMemoryCacheProducer$CachedPostprocessorConsumer(final Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey mCacheKey, final boolean mIsRepeatedProcessor, final MemoryCache<CacheKey, CloseableImage> mMemoryCache) {
        super(consumer);
        this.mCacheKey = mCacheKey;
        this.mIsRepeatedProcessor = mIsRepeatedProcessor;
        this.mMemoryCache = mMemoryCache;
    }
    
    protected void onNewResultImpl(CloseableReference<CloseableImage> closeableReference, final boolean b) {
        if (closeableReference == null) {
            if (b) {
                ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onNewResult(null, true);
            }
        }
        else if (b || this.mIsRepeatedProcessor) {
            final CloseableReference<CloseableImage> cache = this.mMemoryCache.cache(this.mCacheKey, closeableReference);
            try {
                ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onProgressUpdate(1.0f);
                final Consumer<CloseableReference<CloseableImage>> consumer = ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer();
                if (cache != null) {
                    closeableReference = cache;
                }
                consumer.onNewResult(closeableReference, b);
            }
            finally {
                CloseableReference.closeSafely(cache);
            }
        }
    }
}
