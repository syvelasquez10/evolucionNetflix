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
import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.image.EncodedImage;

class DecodeProducer$ProgressiveDecoder$1 implements JobScheduler$JobRunnable
{
    final /* synthetic */ DecodeProducer$ProgressiveDecoder this$1;
    final /* synthetic */ ProducerContext val$producerContext;
    final /* synthetic */ DecodeProducer val$this$0;
    
    DecodeProducer$ProgressiveDecoder$1(final DecodeProducer$ProgressiveDecoder this$1, final DecodeProducer val$this$0, final ProducerContext val$producerContext) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
        this.val$producerContext = val$producerContext;
    }
    
    @Override
    public void run(final EncodedImage encodedImage, final boolean b) {
        if (encodedImage != null) {
            if (this.this$1.this$0.mDownsampleEnabled) {
                final ImageRequest imageRequest = this.val$producerContext.getImageRequest();
                if (this.this$1.this$0.mDownsampleEnabledForNetwork || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                    encodedImage.setSampleSize(DownsampleUtil.determineSampleSize(imageRequest, encodedImage));
                }
            }
            this.this$1.doDecode(encodedImage, b);
        }
    }
}
