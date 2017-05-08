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
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
import com.facebook.common.util.TriState;
import com.facebook.imagepipeline.image.EncodedImage;

class WebpTranscodeProducer$WebpTranscodeConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private final ProducerContext mContext;
    private TriState mShouldTranscodeWhenFinished;
    final /* synthetic */ WebpTranscodeProducer this$0;
    
    public WebpTranscodeProducer$WebpTranscodeConsumer(final WebpTranscodeProducer this$0, final Consumer<EncodedImage> consumer, final ProducerContext mContext) {
        this.this$0 = this$0;
        super(consumer);
        this.mContext = mContext;
        this.mShouldTranscodeWhenFinished = TriState.UNSET;
    }
    
    protected void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (this.mShouldTranscodeWhenFinished == TriState.UNSET && encodedImage != null) {
            this.mShouldTranscodeWhenFinished = shouldTranscode(encodedImage);
        }
        if (this.mShouldTranscodeWhenFinished == TriState.NO) {
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
        }
        else if (b) {
            if (this.mShouldTranscodeWhenFinished == TriState.YES && encodedImage != null) {
                this.this$0.transcodeLastResult(encodedImage, this.getConsumer(), this.mContext);
                return;
            }
            ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
        }
    }
}
