// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$ImageType;
import java.util.concurrent.atomic.AtomicBoolean;
import bolts.Continuation;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.concurrent.CancellationException;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import bolts.Task;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.image.EncodedImage;

class DiskCacheProducer$DiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private final BufferedDiskCache mCache;
    private final CacheKey mCacheKey;
    final /* synthetic */ DiskCacheProducer this$0;
    
    private DiskCacheProducer$DiskCacheConsumer(final DiskCacheProducer this$0, final Consumer<EncodedImage> consumer, final BufferedDiskCache mCache, final CacheKey mCacheKey) {
        this.this$0 = this$0;
        super(consumer);
        this.mCache = mCache;
        this.mCacheKey = mCacheKey;
    }
    
    public void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (encodedImage != null && b) {
            if (this.this$0.mChooseCacheByImageSize) {
                final int size = encodedImage.getSize();
                if (size > 0 && size < this.this$0.mForceSmallCacheThresholdBytes) {
                    this.this$0.mSmallImageBufferedDiskCache.put(this.mCacheKey, encodedImage);
                }
                else {
                    this.this$0.mDefaultBufferedDiskCache.put(this.mCacheKey, encodedImage);
                }
            }
            else {
                this.mCache.put(this.mCacheKey, encodedImage);
            }
        }
        ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
    }
}
