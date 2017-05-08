// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import com.facebook.common.internal.Preconditions;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class PostprocessorProducer implements Producer<CloseableReference<CloseableImage>>
{
    private final PlatformBitmapFactory mBitmapFactory;
    private final Executor mExecutor;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    
    public PostprocessorProducer(final Producer<CloseableReference<CloseableImage>> producer, final PlatformBitmapFactory mBitmapFactory, final Executor executor) {
        this.mInputProducer = Preconditions.checkNotNull(producer);
        this.mBitmapFactory = mBitmapFactory;
        this.mExecutor = Preconditions.checkNotNull(executor);
    }
    
    @Override
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final ProducerListener listener = producerContext.getListener();
        final Postprocessor postprocessor = producerContext.getImageRequest().getPostprocessor();
        final PostprocessorProducer$PostprocessorConsumer postprocessorProducer$PostprocessorConsumer = new PostprocessorProducer$PostprocessorConsumer(consumer, listener, producerContext.getId(), postprocessor, producerContext);
        DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>> delegatingConsumer;
        if (postprocessor instanceof RepeatedPostprocessor) {
            delegatingConsumer = new PostprocessorProducer$RepeatedPostprocessorConsumer(this, postprocessorProducer$PostprocessorConsumer, (RepeatedPostprocessor)postprocessor, producerContext, null);
        }
        else {
            delegatingConsumer = new PostprocessorProducer$SingleUsePostprocessorConsumer(this, postprocessorProducer$PostprocessorConsumer, null);
        }
        this.mInputProducer.produceResults((Consumer<CloseableReference<CloseableImage>>)delegatingConsumer, producerContext);
    }
}
