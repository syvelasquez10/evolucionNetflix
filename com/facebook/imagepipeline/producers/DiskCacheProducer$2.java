// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$ImageType;
import java.util.concurrent.atomic.AtomicBoolean;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import java.util.concurrent.CancellationException;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import java.util.Map;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.image.EncodedImage;
import bolts.Continuation;

class DiskCacheProducer$2 implements Continuation<EncodedImage, Void>
{
    final /* synthetic */ DiskCacheProducer this$0;
    final /* synthetic */ Consumer val$consumer;
    final /* synthetic */ ProducerListener val$listener;
    final /* synthetic */ BufferedDiskCache val$preferredCache;
    final /* synthetic */ CacheKey val$preferredCacheKey;
    final /* synthetic */ ProducerContext val$producerContext;
    final /* synthetic */ String val$requestId;
    
    DiskCacheProducer$2(final DiskCacheProducer this$0, final ProducerListener val$listener, final String val$requestId, final Consumer val$consumer, final BufferedDiskCache val$preferredCache, final CacheKey val$preferredCacheKey, final ProducerContext val$producerContext) {
        this.this$0 = this$0;
        this.val$listener = val$listener;
        this.val$requestId = val$requestId;
        this.val$consumer = val$consumer;
        this.val$preferredCache = val$preferredCache;
        this.val$preferredCacheKey = val$preferredCacheKey;
        this.val$producerContext = val$producerContext;
    }
    
    @Override
    public Void then(final Task<EncodedImage> task) {
        if (isTaskCancelled(task)) {
            this.val$listener.onProducerFinishWithCancellation(this.val$requestId, "DiskCacheProducer", null);
            this.val$consumer.onCancellation();
            return null;
        }
        if (task.isFaulted()) {
            this.val$listener.onProducerFinishWithFailure(this.val$requestId, "DiskCacheProducer", task.getError(), null);
            this.this$0.maybeStartInputProducer(this.val$consumer, (Consumer<EncodedImage>)new DiskCacheProducer$DiskCacheConsumer(this.this$0, this.val$consumer, this.val$preferredCache, this.val$preferredCacheKey, null), this.val$producerContext);
            return null;
        }
        final EncodedImage encodedImage = task.getResult();
        if (encodedImage != null) {
            this.val$listener.onProducerFinishWithSuccess(this.val$requestId, "DiskCacheProducer", DiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, true));
            this.val$consumer.onProgressUpdate(1.0f);
            this.val$consumer.onNewResult(encodedImage, true);
            encodedImage.close();
            return null;
        }
        this.val$listener.onProducerFinishWithSuccess(this.val$requestId, "DiskCacheProducer", DiskCacheProducer.getExtraMap(this.val$listener, this.val$requestId, false));
        this.this$0.maybeStartInputProducer(this.val$consumer, (Consumer<EncodedImage>)new DiskCacheProducer$DiskCacheConsumer(this.this$0, this.val$consumer, this.val$preferredCache, this.val$preferredCacheKey, null), this.val$producerContext);
        return null;
    }
}
