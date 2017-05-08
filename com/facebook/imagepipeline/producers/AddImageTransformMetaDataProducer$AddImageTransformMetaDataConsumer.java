// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.EncodedImage;

class AddImageTransformMetaDataProducer$AddImageTransformMetaDataConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private AddImageTransformMetaDataProducer$AddImageTransformMetaDataConsumer(final Consumer<EncodedImage> consumer) {
        super(consumer);
    }
    
    protected void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (encodedImage == null) {
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(null, b);
            return;
        }
        if (!EncodedImage.isMetaDataAvailable(encodedImage)) {
            encodedImage.parseMetaData();
        }
        ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
    }
}
