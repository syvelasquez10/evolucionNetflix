// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;

public class BranchOnSeparateImagesProducer implements Producer<EncodedImage>
{
    private final Producer<EncodedImage> mInputProducer1;
    private final Producer<EncodedImage> mInputProducer2;
    
    public BranchOnSeparateImagesProducer(final Producer<EncodedImage> mInputProducer1, final Producer<EncodedImage> mInputProducer2) {
        this.mInputProducer1 = mInputProducer1;
        this.mInputProducer2 = mInputProducer2;
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        this.mInputProducer1.produceResults((Consumer<EncodedImage>)new BranchOnSeparateImagesProducer$OnFirstImageConsumer(this, consumer, producerContext, null), producerContext);
    }
}
