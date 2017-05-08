// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessorRunner;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$RepeatedPostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> implements RepeatedPostprocessorRunner
{
    private boolean mIsClosed;
    private CloseableReference<CloseableImage> mSourceImageRef;
    final /* synthetic */ PostprocessorProducer this$0;
    
    private PostprocessorProducer$RepeatedPostprocessorConsumer(final PostprocessorProducer this$0, final PostprocessorProducer$PostprocessorConsumer postprocessorProducer$PostprocessorConsumer, final RepeatedPostprocessor repeatedPostprocessor, final ProducerContext producerContext) {
        this.this$0 = this$0;
        super(postprocessorProducer$PostprocessorConsumer);
        this.mIsClosed = false;
        this.mSourceImageRef = null;
        repeatedPostprocessor.setCallback(this);
        producerContext.addCallbacks(new PostprocessorProducer$RepeatedPostprocessorConsumer$1(this, this$0));
    }
    
    private boolean close() {
        synchronized (this) {
            if (this.mIsClosed) {
                return false;
            }
            final CloseableReference<CloseableImage> mSourceImageRef = this.mSourceImageRef;
            this.mSourceImageRef = null;
            this.mIsClosed = true;
            // monitorexit(this)
            CloseableReference.closeSafely(mSourceImageRef);
            return true;
        }
    }
    
    private void setSourceImageRef(final CloseableReference<CloseableImage> closeableReference) {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            final CloseableReference<CloseableImage> mSourceImageRef = this.mSourceImageRef;
            this.mSourceImageRef = CloseableReference.cloneOrNull(closeableReference);
            // monitorexit(this)
            CloseableReference.closeSafely(mSourceImageRef);
        }
    }
    
    private void updateInternal() {
        // monitorenter(this)
        CloseableReference<CloseableImage> cloneOrNull;
        try {
            if (this.mIsClosed) {
                return;
            }
            cloneOrNull = CloseableReference.cloneOrNull(this.mSourceImageRef);
            // monitorexit(this)
            final PostprocessorProducer$RepeatedPostprocessorConsumer postprocessorProducer$RepeatedPostprocessorConsumer = this;
            final Consumer<CloseableReference<CloseableImage>> consumer = ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)postprocessorProducer$RepeatedPostprocessorConsumer).getConsumer();
            final CloseableReference<CloseableImage> closeableReference = cloneOrNull;
            final boolean b = false;
            consumer.onNewResult(closeableReference, b);
            return;
        }
        finally {
            final CloseableReference closeableReference2;
            cloneOrNull = (CloseableReference<CloseableImage>)closeableReference2;
        }
        // monitorexit(this)
        try {
            final PostprocessorProducer$RepeatedPostprocessorConsumer postprocessorProducer$RepeatedPostprocessorConsumer = this;
            final Consumer<CloseableReference<CloseableImage>> consumer = ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)postprocessorProducer$RepeatedPostprocessorConsumer).getConsumer();
            final CloseableReference<CloseableImage> closeableReference = cloneOrNull;
            final boolean b = false;
            consumer.onNewResult(closeableReference, b);
        }
        finally {
            CloseableReference.closeSafely(cloneOrNull);
        }
    }
    
    @Override
    protected void onCancellationImpl() {
        if (this.close()) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onCancellation();
        }
    }
    
    @Override
    protected void onFailureImpl(final Throwable t) {
        if (this.close()) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onFailure(t);
        }
    }
    
    protected void onNewResultImpl(final CloseableReference<CloseableImage> sourceImageRef, final boolean b) {
        if (!b) {
            return;
        }
        this.setSourceImageRef(sourceImageRef);
        this.updateInternal();
    }
}
