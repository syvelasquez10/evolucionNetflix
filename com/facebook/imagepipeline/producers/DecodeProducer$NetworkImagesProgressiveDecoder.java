// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;

class DecodeProducer$NetworkImagesProgressiveDecoder extends DecodeProducer$ProgressiveDecoder
{
    private int mLastScheduledScanNumber;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final ProgressiveJpegParser mProgressiveJpegParser;
    final /* synthetic */ DecodeProducer this$0;
    
    public DecodeProducer$NetworkImagesProgressiveDecoder(final DecodeProducer this$0, final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext, final ProgressiveJpegParser progressiveJpegParser, final ProgressiveJpegConfig progressiveJpegConfig) {
        this.this$0 = this$0;
        super(consumer, producerContext);
        this.mProgressiveJpegParser = Preconditions.checkNotNull(progressiveJpegParser);
        this.mProgressiveJpegConfig = Preconditions.checkNotNull(progressiveJpegConfig);
        this.mLastScheduledScanNumber = 0;
    }
    
    @Override
    protected int getIntermediateImageEndOffset(final EncodedImage encodedImage) {
        return this.mProgressiveJpegParser.getBestScanEndOffset();
    }
    
    @Override
    protected QualityInfo getQualityInfo() {
        return this.mProgressiveJpegConfig.getQualityInfo(this.mProgressiveJpegParser.getBestScanNumber());
    }
    
    @Override
    protected boolean updateDecodeJob(final EncodedImage encodedImage, final boolean b) {
        final boolean b2 = false;
        synchronized (this) {
            final boolean updateDecodeJob = super.updateDecodeJob(encodedImage, b);
            if (b || !EncodedImage.isValid(encodedImage)) {
                return updateDecodeJob;
            }
            boolean b3;
            if (!this.mProgressiveJpegParser.parseMoreData(encodedImage)) {
                b3 = b2;
            }
            else {
                final int bestScanNumber = this.mProgressiveJpegParser.getBestScanNumber();
                b3 = b2;
                if (bestScanNumber > this.mLastScheduledScanNumber) {
                    b3 = b2;
                    if (bestScanNumber >= this.mProgressiveJpegConfig.getNextScanNumberToDecode(this.mLastScheduledScanNumber)) {
                        this.mLastScheduledScanNumber = bestScanNumber;
                        return updateDecodeJob;
                    }
                }
            }
            return b3;
            b3 = updateDecodeJob;
            return b3;
        }
    }
}
