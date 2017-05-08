// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;

public class AddImageTransformMetaDataProducer implements Producer<EncodedImage>
{
    private final Producer<EncodedImage> mInputProducer;
    
    public AddImageTransformMetaDataProducer(final Producer<EncodedImage> mInputProducer) {
        this.mInputProducer = mInputProducer;
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        this.mInputProducer.produceResults((Consumer<EncodedImage>)new AddImageTransformMetaDataProducer$AddImageTransformMetaDataConsumer(consumer, null), producerContext);
    }
}
