// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$SingleUsePostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
{
    final /* synthetic */ PostprocessorProducer this$0;
    
    private PostprocessorProducer$SingleUsePostprocessorConsumer(final PostprocessorProducer this$0, final PostprocessorProducer$PostprocessorConsumer postprocessorProducer$PostprocessorConsumer) {
        this.this$0 = this$0;
        super(postprocessorProducer$PostprocessorConsumer);
    }
    
    protected void onNewResultImpl(final CloseableReference<CloseableImage> closeableReference, final boolean b) {
        if (!b) {
            return;
        }
        ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onNewResult(closeableReference, b);
    }
}
