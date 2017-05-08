// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$RepeatedPostprocessorConsumer$1 extends BaseProducerContextCallbacks
{
    final /* synthetic */ PostprocessorProducer$RepeatedPostprocessorConsumer this$1;
    final /* synthetic */ PostprocessorProducer val$this$0;
    
    PostprocessorProducer$RepeatedPostprocessorConsumer$1(final PostprocessorProducer$RepeatedPostprocessorConsumer this$1, final PostprocessorProducer val$this$0) {
        this.this$1 = this$1;
        this.val$this$0 = val$this$0;
    }
    
    @Override
    public void onCancellationRequested() {
        if (this.this$1.close()) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this.this$1).getConsumer().onCancellation();
        }
    }
}
