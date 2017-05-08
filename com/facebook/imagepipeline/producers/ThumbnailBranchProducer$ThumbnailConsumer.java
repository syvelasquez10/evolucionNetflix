// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.imagepipeline.image.EncodedImage;

class ThumbnailBranchProducer$ThumbnailConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private final ProducerContext mProducerContext;
    private final int mProducerIndex;
    private final ResizeOptions mResizeOptions;
    final /* synthetic */ ThumbnailBranchProducer this$0;
    
    public ThumbnailBranchProducer$ThumbnailConsumer(final ThumbnailBranchProducer this$0, final Consumer<EncodedImage> consumer, final ProducerContext mProducerContext, final int mProducerIndex) {
        this.this$0 = this$0;
        super(consumer);
        this.mProducerContext = mProducerContext;
        this.mProducerIndex = mProducerIndex;
        this.mResizeOptions = this.mProducerContext.getImageRequest().getResizeOptions();
    }
    
    @Override
    protected void onFailureImpl(final Throwable t) {
        if (!this.this$0.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, this.getConsumer(), this.mProducerContext)) {
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onFailure(t);
        }
    }
    
    protected void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (encodedImage != null && (!b || ThumbnailSizeChecker.isImageBigEnough(encodedImage, this.mResizeOptions))) {
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
        }
        else if (b) {
            EncodedImage.closeSafely(encodedImage);
            if (!this.this$0.produceResultsFromThumbnailProducer(this.mProducerIndex + 1, this.getConsumer(), this.mProducerContext)) {
                ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(null, true);
            }
        }
    }
}
