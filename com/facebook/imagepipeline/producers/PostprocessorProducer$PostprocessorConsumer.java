// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.request.RepeatedPostprocessor;
import java.util.concurrent.Executor;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import android.graphics.Bitmap;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.common.internal.ImmutableMap;
import java.util.Map;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.request.Postprocessor;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;

class PostprocessorProducer$PostprocessorConsumer extends DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>
{
    private boolean mIsClosed;
    private boolean mIsDirty;
    private boolean mIsLast;
    private boolean mIsPostProcessingRunning;
    private final ProducerListener mListener;
    private final Postprocessor mPostprocessor;
    private final String mRequestId;
    private CloseableReference<CloseableImage> mSourceImageRef;
    final /* synthetic */ PostprocessorProducer this$0;
    
    public PostprocessorProducer$PostprocessorConsumer(final PostprocessorProducer this$0, final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerListener mListener, final String mRequestId, final Postprocessor mPostprocessor, final ProducerContext producerContext) {
        this.this$0 = this$0;
        super(consumer);
        this.mSourceImageRef = null;
        this.mIsLast = false;
        this.mIsDirty = false;
        this.mIsPostProcessingRunning = false;
        this.mListener = mListener;
        this.mRequestId = mRequestId;
        this.mPostprocessor = mPostprocessor;
        producerContext.addCallbacks(new PostprocessorProducer$PostprocessorConsumer$1(this, this$0));
    }
    
    private void clearRunningAndStartIfDirty() {
        synchronized (this) {
            this.mIsPostProcessingRunning = false;
            final boolean setRunningIfDirtyAndNotRunning = this.setRunningIfDirtyAndNotRunning();
            // monitorexit(this)
            if (setRunningIfDirtyAndNotRunning) {
                this.submitPostprocessing();
            }
        }
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
    
    private void doPostprocessing(final CloseableReference<CloseableImage> closeableReference, final boolean b) {
        Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
        if (!this.shouldPostprocess(closeableReference.get())) {
            this.maybeNotifyOnNewResult(closeableReference, b);
            return;
        }
        this.mListener.onProducerStart(this.mRequestId, "PostprocessorProducer");
        CloseableReference<?> postprocessInternal;
        final CloseableReference<?> closeableReference2 = postprocessInternal = null;
        try {
            final CloseableReference<CloseableImage> closeableReference3 = (CloseableReference<CloseableImage>)(postprocessInternal = this.postprocessInternal(closeableReference.get()));
            this.mListener.onProducerFinishWithSuccess(this.mRequestId, "PostprocessorProducer", this.getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
            postprocessInternal = closeableReference3;
            this.maybeNotifyOnNewResult(closeableReference3, b);
        }
        catch (Exception ex) {
            postprocessInternal = closeableReference2;
            this.mListener.onProducerFinishWithFailure(this.mRequestId, "PostprocessorProducer", ex, this.getExtraMap(this.mListener, this.mRequestId, this.mPostprocessor));
            postprocessInternal = closeableReference2;
            this.maybeNotifyOnFailure(ex);
            CloseableReference.closeSafely(null);
        }
        finally {
            CloseableReference.closeSafely(postprocessInternal);
        }
    }
    
    private Map<String, String> getExtraMap(final ProducerListener producerListener, final String s, final Postprocessor postprocessor) {
        if (!producerListener.requiresExtraMap(s)) {
            return null;
        }
        return ImmutableMap.of("Postprocessor", postprocessor.getName());
    }
    
    private boolean isClosed() {
        synchronized (this) {
            return this.mIsClosed;
        }
    }
    
    private void maybeNotifyOnCancellation() {
        if (this.close()) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onCancellation();
        }
    }
    
    private void maybeNotifyOnFailure(final Throwable t) {
        if (this.close()) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onFailure(t);
        }
    }
    
    private void maybeNotifyOnNewResult(final CloseableReference<CloseableImage> closeableReference, final boolean b) {
        if ((!b && !this.isClosed()) || (b && this.close())) {
            ((DelegatingConsumer<I, CloseableReference<CloseableImage>>)this).getConsumer().onNewResult(closeableReference, b);
        }
    }
    
    private CloseableReference<CloseableImage> postprocessInternal(final CloseableImage closeableImage) {
        final CloseableStaticBitmap closeableStaticBitmap = (CloseableStaticBitmap)closeableImage;
        final CloseableReference<Bitmap> process = this.mPostprocessor.process(closeableStaticBitmap.getUnderlyingBitmap(), this.this$0.mBitmapFactory);
        final int rotationAngle = closeableStaticBitmap.getRotationAngle();
        try {
            return (CloseableReference<CloseableImage>)CloseableReference.of(new CloseableStaticBitmap(process, closeableImage.getQualityInfo(), rotationAngle));
        }
        finally {
            CloseableReference.closeSafely(process);
        }
    }
    
    private boolean setRunningIfDirtyAndNotRunning() {
        boolean b = true;
        synchronized (this) {
            if (!this.mIsClosed && this.mIsDirty && !this.mIsPostProcessingRunning && CloseableReference.isValid(this.mSourceImageRef)) {
                this.mIsPostProcessingRunning = true;
            }
            else {
                b = false;
            }
            return b;
        }
    }
    
    private boolean shouldPostprocess(final CloseableImage closeableImage) {
        return closeableImage instanceof CloseableStaticBitmap;
    }
    
    private void submitPostprocessing() {
        this.this$0.mExecutor.execute(new PostprocessorProducer$PostprocessorConsumer$2(this));
    }
    
    private void updateSourceImageRef(final CloseableReference<CloseableImage> closeableReference, final boolean mIsLast) {
        synchronized (this) {
            if (this.mIsClosed) {
                return;
            }
            final CloseableReference<CloseableImage> mSourceImageRef = this.mSourceImageRef;
            this.mSourceImageRef = CloseableReference.cloneOrNull(closeableReference);
            this.mIsLast = mIsLast;
            this.mIsDirty = true;
            final boolean setRunningIfDirtyAndNotRunning = this.setRunningIfDirtyAndNotRunning();
            // monitorexit(this)
            CloseableReference.closeSafely(mSourceImageRef);
            if (setRunningIfDirtyAndNotRunning) {
                this.submitPostprocessing();
            }
        }
    }
    
    @Override
    protected void onCancellationImpl() {
        this.maybeNotifyOnCancellation();
    }
    
    @Override
    protected void onFailureImpl(final Throwable t) {
        this.maybeNotifyOnFailure(t);
    }
    
    protected void onNewResultImpl(final CloseableReference<CloseableImage> closeableReference, final boolean b) {
        if (!CloseableReference.isValid(closeableReference)) {
            if (b) {
                this.maybeNotifyOnNewResult(null, true);
            }
            return;
        }
        this.updateSourceImageRef(closeableReference, b);
    }
}
