// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.nativecode.WebpTranscoder;
import com.facebook.imageformat.ImageFormat;
import java.io.InputStream;
import java.io.OutputStream;
import com.facebook.imagepipeline.nativecode.WebpTranscoderFactory;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.common.util.TriState;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.image.EncodedImage;

public class WebpTranscodeProducer implements Producer<EncodedImage>
{
    private final Executor mExecutor;
    private final Producer<EncodedImage> mInputProducer;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    public WebpTranscodeProducer(final Executor executor, final PooledByteBufferFactory pooledByteBufferFactory, final Producer<EncodedImage> producer) {
        this.mExecutor = Preconditions.checkNotNull(executor);
        this.mPooledByteBufferFactory = Preconditions.checkNotNull(pooledByteBufferFactory);
        this.mInputProducer = Preconditions.checkNotNull(producer);
    }
    
    private static void doTranscode(final EncodedImage encodedImage, final PooledByteBufferOutputStream pooledByteBufferOutputStream) {
        final InputStream inputStream = encodedImage.getInputStream();
        switch (WebpTranscodeProducer$2.$SwitchMap$com$facebook$imageformat$ImageFormat[ImageFormatChecker.getImageFormat_WrapIOException(inputStream).ordinal()]) {
            default: {
                throw new IllegalArgumentException("Wrong image format");
            }
            case 1:
            case 3: {
                WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToJpeg(inputStream, pooledByteBufferOutputStream, 80);
            }
            case 2:
            case 4: {
                WebpTranscoderFactory.getWebpTranscoder().transcodeWebpToPng(inputStream, pooledByteBufferOutputStream);
            }
        }
    }
    
    private static TriState shouldTranscode(final EncodedImage encodedImage) {
        Preconditions.checkNotNull(encodedImage);
        final ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(encodedImage.getInputStream());
        switch (WebpTranscodeProducer$2.$SwitchMap$com$facebook$imageformat$ImageFormat[imageFormat_WrapIOException.ordinal()]) {
            default: {
                return TriState.NO;
            }
            case 1:
            case 2:
            case 3:
            case 4: {
                final WebpTranscoder webpTranscoder = WebpTranscoderFactory.getWebpTranscoder();
                if (webpTranscoder == null) {
                    return TriState.NO;
                }
                return TriState.valueOf(!webpTranscoder.isWebpNativelySupported(imageFormat_WrapIOException));
            }
            case 5: {
                return TriState.UNSET;
            }
        }
    }
    
    private void transcodeLastResult(EncodedImage cloneOrNull, final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        Preconditions.checkNotNull(cloneOrNull);
        cloneOrNull = EncodedImage.cloneOrNull(cloneOrNull);
        this.mExecutor.execute(new WebpTranscodeProducer$1(this, consumer, producerContext.getListener(), "WebpTranscodeProducer", producerContext.getId(), cloneOrNull));
    }
    
    @Override
    public void produceResults(final Consumer<EncodedImage> consumer, final ProducerContext producerContext) {
        this.mInputProducer.produceResults((Consumer<EncodedImage>)new WebpTranscodeProducer$WebpTranscodeConsumer(consumer, producerContext), producerContext);
    }
}
