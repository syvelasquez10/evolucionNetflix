// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class DecodeProducer$LocalImagesProgressiveDecoder extends DecodeProducer$ProgressiveDecoder
{
    final /* synthetic */ DecodeProducer this$0;
    
    public DecodeProducer$LocalImagesProgressiveDecoder(final DecodeProducer this$0, final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        this.this$0 = this$0;
        super(consumer, producerContext);
    }
    
    @Override
    protected int getIntermediateImageEndOffset(final EncodedImage encodedImage) {
        return encodedImage.getSize();
    }
    
    @Override
    protected QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.of(0, false, false);
    }
    
    @Override
    protected boolean updateDecodeJob(final EncodedImage encodedImage, boolean updateDecodeJob) {
        // monitorenter(this)
        if (!updateDecodeJob) {
            updateDecodeJob = false;
        }
        else {
            try {
                updateDecodeJob = super.updateDecodeJob(encodedImage, updateDecodeJob);
            }
            finally {
            }
            // monitorexit(this)
        }
        // monitorexit(this)
        return updateDecodeJob;
    }
}
