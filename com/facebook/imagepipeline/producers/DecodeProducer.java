// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.common.util.UriUtil;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

public class DecodeProducer implements Producer<CloseableReference<CloseableImage>>
{
    private final ByteArrayPool mByteArrayPool;
    private final boolean mDownsampleEnabled;
    private final boolean mDownsampleEnabledForNetwork;
    private final Executor mExecutor;
    private final ImageDecoder mImageDecoder;
    private final Producer<EncodedImage> mInputProducer;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    
    public DecodeProducer(final ByteArrayPool byteArrayPool, final Executor executor, final ImageDecoder imageDecoder, final ProgressiveJpegConfig progressiveJpegConfig, final boolean mDownsampleEnabled, final boolean mDownsampleEnabledForNetwork, final Producer<EncodedImage> producer) {
        this.mByteArrayPool = Preconditions.checkNotNull(byteArrayPool);
        this.mExecutor = Preconditions.checkNotNull(executor);
        this.mImageDecoder = Preconditions.checkNotNull(imageDecoder);
        this.mProgressiveJpegConfig = Preconditions.checkNotNull(progressiveJpegConfig);
        this.mDownsampleEnabled = mDownsampleEnabled;
        this.mDownsampleEnabledForNetwork = mDownsampleEnabledForNetwork;
        this.mInputProducer = Preconditions.checkNotNull(producer);
    }
    
    @Override
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        DecodeProducer$ProgressiveDecoder decodeProducer$ProgressiveDecoder;
        if (!UriUtil.isNetworkUri(producerContext.getImageRequest().getSourceUri())) {
            decodeProducer$ProgressiveDecoder = new DecodeProducer$LocalImagesProgressiveDecoder(consumer, producerContext);
        }
        else {
            decodeProducer$ProgressiveDecoder = new DecodeProducer$NetworkImagesProgressiveDecoder(consumer, producerContext, new ProgressiveJpegParser(this.mByteArrayPool), this.mProgressiveJpegConfig);
        }
        this.mInputProducer.produceResults((Consumer<EncodedImage>)decodeProducer$ProgressiveDecoder, producerContext);
    }
}
