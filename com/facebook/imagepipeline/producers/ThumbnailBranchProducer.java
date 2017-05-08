// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.EncodedImage;

public class ThumbnailBranchProducer implements Producer<EncodedImage>
{
    private final ThumbnailProducer<EncodedImage>[] mThumbnailProducers;
    
    public ThumbnailBranchProducer(final ThumbnailProducer<EncodedImage>... array) {
        this.mThumbnailProducers = (ThumbnailProducer<EncodedImage>[])Preconditions.checkNotNull(array);
        Preconditions.checkElementIndex(0, this.mThumbnailProducers.length);
    }
    
    private int findFirstProducerForSize(int i, final ResizeOptions resizeOptions) {
        while (i < this.mThumbnailProducers.length) {
            if (this.mThumbnailProducers[i].canProvideImageForSize(resizeOptions)) {
                return i;
            }
            ++i;
        }
        return -1;
    }
    
    private boolean produceResultsFromThumbnailProducer(int firstProducerForSize, final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        firstProducerForSize = this.findFirstProducerForSize(firstProducerForSize, producerContext.getImageRequest().getResizeOptions());
        if (firstProducerForSize == -1) {
            return false;
        }
        this.mThumbnailProducers[firstProducerForSize].produceResults((Consumer<EncodedImage>)new ThumbnailBranchProducer$ThumbnailConsumer(consumer, producerContext, firstProducerForSize), producerContext);
        return true;
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        if (producerContext.getImageRequest().getResizeOptions() == null) {
            consumer.onNewResult(null, true);
        }
        else if (!this.produceResultsFromThumbnailProducer(0, consumer, producerContext)) {
            consumer.onNewResult(null, true);
        }
    }
}
