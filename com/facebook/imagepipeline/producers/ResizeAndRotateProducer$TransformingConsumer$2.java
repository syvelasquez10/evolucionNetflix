// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.common.util.TriState;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.imagepipeline.request.ImageRequest;
import java.io.Closeable;
import com.facebook.common.internal.Closeables;
import com.facebook.imageformat.ImageFormat;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.io.OutputStream;
import java.io.InputStream;
import com.facebook.imagepipeline.nativecode.JpegTranscoder;
import com.facebook.imagepipeline.image.EncodedImage;

class ResizeAndRotateProducer$TransformingConsumer$2 extends BaseProducerContextCallbacks
{
    final /* synthetic */ ResizeAndRotateProducer$TransformingConsumer this$1;
    final /* synthetic */ Consumer val$consumer;
    final /* synthetic */ ResizeAndRotateProducer val$this$0;
    
    ResizeAndRotateProducer$TransformingConsumer$2(final ResizeAndRotateProducer$TransformingConsumer this$1, final ResizeAndRotateProducer val$this$0, final Consumer val$consumer) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
        this.val$consumer = val$consumer;
    }
    
    @Override
    public void onCancellationRequested() {
        this.this$1.mJobScheduler.clearJob();
        this.this$1.mIsCancelled = true;
        this.val$consumer.onCancellation();
    }
    
    @Override
    public void onIsIntermediateResultExpectedChanged() {
        if (this.this$1.mProducerContext.isIntermediateResultExpected()) {
            this.this$1.mJobScheduler.scheduleJob();
        }
    }
}
