// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import java.util.Map;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;

class DecodeProducer$ProgressiveDecoder$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ DecodeProducer$ProgressiveDecoder this$1;
    final /* synthetic */ DecodeProducer val$this$0;
    
    DecodeProducer$ProgressiveDecoder$2(final DecodeProducer$ProgressiveDecoder this$1, final DecodeProducer val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void onIsIntermediateResultExpectedChanged() {
        if (this.this$1.mProducerContext.isIntermediateResultExpected()) {
            this.this$1.mJobScheduler.scheduleJob();
        }
    }
}
