// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.image.EncodedImage;

class LocalFetchProducer$1 extends StatefulProducerRunnable<EncodedImage>
{
    final /* synthetic */ LocalFetchProducer this$0;
    final /* synthetic */ ImageRequest val$imageRequest;
    
    LocalFetchProducer$1(final LocalFetchProducer this$0, final Consumer consumer, final ProducerListener producerListener, final String s, final String s2, final ImageRequest val$imageRequest) {
        this.this$0 = this$0;
        this.val$imageRequest = val$imageRequest;
        super(consumer, producerListener, s, s2);
    }
    
    @Override
    protected void disposeResult(final EncodedImage encodedImage) {
        EncodedImage.closeSafely(encodedImage);
    }
    
    @Override
    protected EncodedImage getResult() {
        final EncodedImage encodedImage = this.this$0.getEncodedImage(this.val$imageRequest);
        if (encodedImage == null) {
            return null;
        }
        encodedImage.parseMetaData();
        return encodedImage;
    }
}
