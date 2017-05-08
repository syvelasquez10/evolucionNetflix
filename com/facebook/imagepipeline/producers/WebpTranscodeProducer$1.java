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
import com.facebook.common.util.TriState;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;

class WebpTranscodeProducer$1 extends StatefulProducerRunnable<EncodedImage>
{
    final /* synthetic */ WebpTranscodeProducer this$0;
    final /* synthetic */ EncodedImage val$encodedImageCopy;
    
    WebpTranscodeProducer$1(final WebpTranscodeProducer this$0, final Consumer consumer, final ProducerListener producerListener, final String s, final String s2, final EncodedImage val$encodedImageCopy) {
        this.this$0 = this$0;
        this.val$encodedImageCopy = val$encodedImageCopy;
        super(consumer, producerListener, s, s2);
    }
    
    @Override
    protected void disposeResult(final EncodedImage encodedImage) {
        EncodedImage.closeSafely(encodedImage);
    }
    
    @Override
    protected EncodedImage getResult() {
        final PooledByteBufferOutputStream outputStream = this.this$0.mPooledByteBufferFactory.newOutputStream();
        try {
            doTranscode(this.val$encodedImageCopy, outputStream);
            final CloseableReference<PooledByteBuffer> of = CloseableReference.of(outputStream.toByteBuffer());
            try {
                final EncodedImage encodedImage = new EncodedImage(of);
                encodedImage.copyMetaDataFrom(this.val$encodedImageCopy);
                return encodedImage;
            }
            finally {
                CloseableReference.closeSafely(of);
            }
        }
        finally {
            outputStream.close();
        }
    }
    
    @Override
    protected void onCancellation() {
        EncodedImage.closeSafely(this.val$encodedImageCopy);
        super.onCancellation();
    }
    
    @Override
    protected void onFailure(final Exception ex) {
        EncodedImage.closeSafely(this.val$encodedImageCopy);
        super.onFailure(ex);
    }
    
    @Override
    protected void onSuccess(final EncodedImage encodedImage) {
        EncodedImage.closeSafely(this.val$encodedImageCopy);
        super.onSuccess(encodedImage);
    }
}
