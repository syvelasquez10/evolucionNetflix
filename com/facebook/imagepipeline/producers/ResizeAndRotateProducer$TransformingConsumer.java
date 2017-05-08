// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.common.ResizeOptions;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import java.util.concurrent.Executor;
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

class ResizeAndRotateProducer$TransformingConsumer extends DelegatingConsumer<EncodedImage, EncodedImage>
{
    private boolean mIsCancelled;
    private final JobScheduler mJobScheduler;
    private final ProducerContext mProducerContext;
    final /* synthetic */ ResizeAndRotateProducer this$0;
    
    public ResizeAndRotateProducer$TransformingConsumer(final ResizeAndRotateProducer this$0, final Consumer<EncodedImage> consumer, final ProducerContext mProducerContext) {
        this.this$0 = this$0;
        super(consumer);
        this.mIsCancelled = false;
        this.mProducerContext = mProducerContext;
        this.mJobScheduler = new JobScheduler(this$0.mExecutor, new ResizeAndRotateProducer$TransformingConsumer$1(this, this$0), 100);
        this.mProducerContext.addCallbacks(new ResizeAndRotateProducer$TransformingConsumer$2(this, this$0, consumer));
    }
    
    private void doTransform(EncodedImage of, final boolean b) {
        Closeable inputStream = null;
        final EncodedImage encodedImage = null;
        final EncodedImage encodedImage2 = null;
        this.mProducerContext.getListener().onProducerStart(this.mProducerContext.getId(), "ResizeAndRotateProducer");
        final ImageRequest imageRequest = this.mProducerContext.getImageRequest();
        final PooledByteBufferOutputStream outputStream = this.this$0.mPooledByteBufferFactory.newOutputStream();
        Closeable closeable = encodedImage;
        while (true) {
            try {
                try {
                    final int access$700 = getScaleNumerator(imageRequest, (EncodedImage)of);
                    closeable = encodedImage;
                    final Map<String, String> extraMap = this.getExtraMap((EncodedImage)of, imageRequest, access$700);
                    inputStream = encodedImage2;
                    closeable = encodedImage;
                    try {
                        final Closeable closeable2 = closeable = (inputStream = ((EncodedImage)of).getInputStream());
                        JpegTranscoder.transcodeJpeg((InputStream)closeable2, outputStream, getRotationAngle(imageRequest, (EncodedImage)of), access$700, 85);
                        inputStream = closeable2;
                        closeable = closeable2;
                        of = (Exception)CloseableReference.of(outputStream.toByteBuffer());
                        try {
                            closeable = new EncodedImage((CloseableReference<PooledByteBuffer>)of);
                            ((EncodedImage)closeable).setImageFormat(ImageFormat.JPEG);
                            try {
                                ((EncodedImage)closeable).parseMetaData();
                                this.mProducerContext.getListener().onProducerFinishWithSuccess(this.mProducerContext.getId(), "ResizeAndRotateProducer", extraMap);
                                ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult((EncodedImage)closeable, b);
                                EncodedImage.closeSafely((EncodedImage)closeable);
                                closeable = closeable2;
                                CloseableReference.closeSafely((CloseableReference<?>)of);
                                return;
                            }
                            finally {
                                EncodedImage.closeSafely((EncodedImage)closeable);
                            }
                        }
                        finally {
                            inputStream = closeable2;
                            closeable = closeable2;
                            CloseableReference.closeSafely((CloseableReference<?>)of);
                            inputStream = closeable2;
                            closeable = closeable2;
                        }
                    }
                    catch (Exception ex) {}
                    closeable = inputStream;
                    this.mProducerContext.getListener().onProducerFinishWithFailure(this.mProducerContext.getId(), "ResizeAndRotateProducer", of, extraMap);
                    closeable = inputStream;
                    ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onFailure(of);
                }
                finally {
                    Closeables.closeQuietly((InputStream)closeable);
                    outputStream.close();
                }
            }
            catch (Exception of) {
                final Map<String, String> extraMap = null;
                continue;
            }
            break;
        }
    }
    
    private Map<String, String> getExtraMap(final EncodedImage encodedImage, final ImageRequest imageRequest, final int n) {
        if (!this.mProducerContext.getListener().requiresExtraMap(this.mProducerContext.getId())) {
            return null;
        }
        final String string = encodedImage.getWidth() + "x" + encodedImage.getHeight();
        String string2;
        if (imageRequest.getResizeOptions() != null) {
            string2 = imageRequest.getResizeOptions().width + "x" + imageRequest.getResizeOptions().height;
        }
        else {
            string2 = "Unspecified";
        }
        String string3;
        if (n > 0) {
            string3 = n + "/8";
        }
        else {
            string3 = "";
        }
        return ImmutableMap.of("Original size", string, "Requested size", string2, "Fraction", string3, "queueTime", String.valueOf(this.mJobScheduler.getQueuedTime()));
    }
    
    protected void onNewResultImpl(final EncodedImage encodedImage, final boolean b) {
        if (!this.mIsCancelled) {
            if (encodedImage == null) {
                if (b) {
                    ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(null, true);
                }
            }
            else {
                final TriState access$500 = shouldTransform(this.mProducerContext.getImageRequest(), encodedImage);
                if (b || access$500 != TriState.UNSET) {
                    if (access$500 != TriState.YES) {
                        ((DelegatingConsumer<I, EncodedImage>)this).getConsumer().onNewResult(encodedImage, b);
                        return;
                    }
                    if (this.mJobScheduler.updateJob(encodedImage, b) && (b || this.mProducerContext.isIntermediateResultExpected())) {
                        this.mJobScheduler.scheduleJob();
                    }
                }
            }
        }
    }
}
