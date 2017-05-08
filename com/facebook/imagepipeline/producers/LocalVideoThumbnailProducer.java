// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>>
{
    private final Executor mExecutor;
    
    public LocalVideoThumbnailProducer(final Executor mExecutor) {
        this.mExecutor = mExecutor;
    }
    
    private static int calculateKind(final ImageRequest imageRequest) {
        if (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) {
            return 1;
        }
        return 3;
    }
    
    @Override
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final LocalVideoThumbnailProducer$1 localVideoThumbnailProducer$1 = new LocalVideoThumbnailProducer$1(this, consumer, producerContext.getListener(), "VideoThumbnailProducer", producerContext.getId(), producerContext.getImageRequest());
        producerContext.addCallbacks(new LocalVideoThumbnailProducer$2(this, localVideoThumbnailProducer$1));
        this.mExecutor.execute(localVideoThumbnailProducer$1);
    }
}
