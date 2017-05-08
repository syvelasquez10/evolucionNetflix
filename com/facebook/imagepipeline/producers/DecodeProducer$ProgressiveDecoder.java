// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.decoder.ProgressiveJpegParser;
import com.facebook.common.util.UriUtil;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import android.graphics.Bitmap;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import java.util.Map;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.common.ImageDecodeOptions;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;

abstract class DecodeProducer$ProgressiveDecoder extends DelegatingConsumer<EncodedImage, CloseableReference<CloseableImage>>
{
    private final ImageDecodeOptions mImageDecodeOptions;
    private boolean mIsFinished;
    private final JobScheduler mJobScheduler;
    private final ProducerContext mProducerContext;
    private final ProducerListener mProducerListener;
    final /* synthetic */ DecodeProducer this$0;
    
    public DecodeProducer$ProgressiveDecoder(final DecodeProducer this$0, final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext mProducerContext) {
        this.this$0 = this$0;
        super(consumer);
        this.mProducerContext = mProducerContext;
        this.mProducerListener = mProducerContext.getListener();
        this.mImageDecodeOptions = mProducerContext.getImageRequest().getImageDecodeOptions();
        this.mIsFinished = false;
        this.mJobScheduler = new JobScheduler(this$0.mExecutor, new DecodeProducer$ProgressiveDecoder$1(this, this$0, mProducerContext), this.mImageDecodeOptions.minDecodeIntervalMs);
        this.mProducerContext.addCallbacks(new DecodeProducer$ProgressiveDecoder$2(this, this$0));
    }
    
    private void doDecode(final EncodedImage encodedImage, final boolean b) {
        if (this.isFinished() || !EncodedImage.isValid(encodedImage)) {
            return;
        }
        try {
            final long queuedTime = this.mJobScheduler.getQueuedTime();
            while (true) {
                while (true) {
                    Label_0033: {
                        if (b) {
                            final int n = encodedImage.getSize();
                            break Label_0033;
                        }
                        Label_0129: {
                            break Label_0129;
                            while (true) {
                                this.mProducerListener.onProducerStart(this.mProducerContext.getId(), "DecodeProducer");
                                try {
                                    final int n;
                                    Object o = null;
                                    final CloseableImage decodeImage = this.this$0.mImageDecoder.decodeImage(encodedImage, n, (QualityInfo)o, this.mImageDecodeOptions);
                                    o = this.getExtraMap(decodeImage, queuedTime, (QualityInfo)o, b);
                                    this.mProducerListener.onProducerFinishWithSuccess(this.mProducerContext.getId(), "DecodeProducer", (Map<String, String>)o);
                                    this.handleResult(decodeImage, b);
                                    return;
                                    n = this.getIntermediateImageEndOffset(encodedImage);
                                    break;
                                    o = this.getQualityInfo();
                                }
                                catch (Exception ex) {
                                    final Object o;
                                    this.mProducerListener.onProducerFinishWithFailure(this.mProducerContext.getId(), "DecodeProducer", ex, this.getExtraMap(null, queuedTime, (QualityInfo)o, b));
                                    this.handleError(ex);
                                    return;
                                }
                            }
                        }
                    }
                    if (b) {
                        final Object o = ImmutableQualityInfo.FULL_QUALITY;
                        continue;
                    }
                    break;
                }
                continue;
            }
        }
        finally {
            EncodedImage.closeSafely(encodedImage);
        }
    }
    
    private Map<String, String> getExtraMap(final CloseableImage closeableImage, final long n, final QualityInfo qualityInfo, final boolean b) {
        if (!this.mProducerListener.requiresExtraMap(this.mProducerContext.getId())) {
            return null;
        }
        final String value = String.valueOf(n);
        final String value2 = String.valueOf(qualityInfo.isOfGoodEnoughQuality());
        final String value3 = String.valueOf(b);
        final String value4 = String.valueOf(this.mProducerContext.getImageRequest().getImageType());
        if (closeableImage instanceof CloseableStaticBitmap) {
            final Bitmap underlyingBitmap = ((CloseableStaticBitmap)closeableImage).getUnderlyingBitmap();
            return ImmutableMap.of("bitmapSize", underlyingBitmap.getWidth() + "x" + underlyingBitmap.getHeight(), "queueTime", value, "hasGoodQuality", value2, "isFinal", value3, "imageType", value4);
        }
        return ImmutableMap.of("queueTime", value, "hasGoodQuality", value2, "isFinal", value3, "imageType", value4);
    }
    
    private void handleCancellation() {
        this.maybeFinish(true);
        ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onCancellation();
    }
    
    private void handleError(final Throwable t) {
        this.maybeFinish(true);
        ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onFailure(t);
    }
    
    private void handleResult(CloseableImage of, final boolean b) {
        of = (CloseableImage)CloseableReference.of(of);
        try {
            this.maybeFinish(b);
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onNewResult((CloseableReference<CloseableImage>)of, b);
        }
        finally {
            CloseableReference.closeSafely((CloseableReference<?>)of);
        }
    }
    
    private boolean isFinished() {
        synchronized (this) {
            return this.mIsFinished;
        }
    }
    
    private void maybeFinish(final boolean b) {
        // monitorenter(this)
        if (!b) {
            return;
        }
        try {
            if (this.mIsFinished) {
                return;
            }
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onProgressUpdate(1.0f);
            this.mIsFinished = true;
            // monitorexit(this)
            this.mJobScheduler.clearJob();
        }
        finally {
        }
        // monitorexit(this)
    }
    
    protected abstract int getIntermediateImageEndOffset(final EncodedImage p0);
    
    protected abstract QualityInfo getQualityInfo();
    
    public void onCancellationImpl() {
        this.handleCancellation();
    }
    
    public void onFailureImpl(final Throwable t) {
        this.handleError(t);
    }
    
    public void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (b && !EncodedImage.isValid(encodedImage)) {
            this.handleError(new NullPointerException("Encoded image is not valid."));
        }
        else if (this.updateDecodeJob(encodedImage, b) && (b || this.mProducerContext.isIntermediateResultExpected())) {
            this.mJobScheduler.scheduleJob();
        }
    }
    
    @Override
    protected void onProgressUpdateImpl(final float n) {
        super.onProgressUpdateImpl(0.99f * n);
    }
    
    protected boolean updateDecodeJob(final EncodedImage encodedImage, final boolean b) {
        return this.mJobScheduler.updateJob(encodedImage, b);
    }
}
