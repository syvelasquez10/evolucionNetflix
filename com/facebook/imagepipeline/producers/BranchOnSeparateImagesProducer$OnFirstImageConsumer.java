// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.image.EncodedImage;

class BranchOnSeparateImagesProducer$OnFirstImageConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private ProducerContext mProducerContext;
    final /* synthetic */ BranchOnSeparateImagesProducer this$0;
    
    private BranchOnSeparateImagesProducer$OnFirstImageConsumer(final BranchOnSeparateImagesProducer this$0, final Consumer<EncodedImage> consumer, final ProducerContext mProducerContext) {
        this.this$0 = this$0;
        super(consumer);
        this.mProducerContext = mProducerContext;
    }
    
    @Override
    protected void onFailureImpl(final Throwable t) {
        this.this$0.mInputProducer2.produceResults(((DelegatingConsumer<I, EncodedImage>)this).getConsumer(), this.mProducerContext);
    }
    
    protected void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        final ImageRequest imageRequest = this.mProducerContext.getImageRequest();
        final boolean imageBigEnough = ThumbnailSizeChecker.isImageBigEnough(encodedImage, imageRequest.getResizeOptions());
        if (encodedImage != null && (imageBigEnough || imageRequest.getLocalThumbnailPreviewsEnabled())) {
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b && imageBigEnough);
        }
        if (b && !imageBigEnough) {
            EncodedImage.closeSafely(encodedImage);
            this.this$0.mInputProducer2.produceResults(((DelegatingConsumer<I, EncodedImage>)this).getConsumer(), this.mProducerContext);
        }
    }
}
