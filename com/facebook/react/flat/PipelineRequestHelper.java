// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.react.flat;

import com.facebook.imagepipeline.image.CloseableBitmap;
import android.graphics.Bitmap;
import java.util.concurrent.Executor;
import com.facebook.common.executors.UiThreadImmediateExecutorService;
import com.facebook.imagepipeline.core.ImagePipelineFactory;
import com.facebook.infer.annotation.Assertions;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.datasource.DataSource;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.common.references.CloseableReference;
import com.facebook.datasource.DataSubscriber;

final class PipelineRequestHelper implements DataSubscriber<CloseableReference<CloseableImage>>
{
    private int mAttachCounter;
    private BitmapUpdateListener mBitmapUpdateListener;
    private DataSource<CloseableReference<CloseableImage>> mDataSource;
    private CloseableReference<CloseableImage> mImageRef;
    private final ImageRequest mImageRequest;
    
    PipelineRequestHelper(final ImageRequest mImageRequest) {
        this.mImageRequest = mImageRequest;
    }
    
    void attach(final BitmapUpdateListener mBitmapUpdateListener) {
        final boolean b = true;
        this.mBitmapUpdateListener = mBitmapUpdateListener;
        ++this.mAttachCounter;
        if (this.mAttachCounter != 1) {
            final Bitmap bitmap = this.getBitmap();
            if (bitmap != null) {
                mBitmapUpdateListener.onSecondaryAttach(bitmap);
            }
            return;
        }
        mBitmapUpdateListener.onImageLoadEvent(4);
        Assertions.assertCondition(this.mDataSource == null);
        Assertions.assertCondition(this.mImageRef == null && b);
        (this.mDataSource = ImagePipelineFactory.getInstance().getImagePipeline().fetchDecodedImage(this.mImageRequest, RCTImageView.getCallerContext())).subscribe(this, UiThreadImmediateExecutorService.getInstance());
    }
    
    void detach() {
        --this.mAttachCounter;
        if (this.mAttachCounter != 0) {
            return;
        }
        if (this.mDataSource != null) {
            this.mDataSource.close();
            this.mDataSource = null;
        }
        if (this.mImageRef != null) {
            this.mImageRef.close();
            this.mImageRef = null;
        }
        this.mBitmapUpdateListener = null;
    }
    
    Bitmap getBitmap() {
        if (this.mImageRef == null) {
            return null;
        }
        final CloseableImage closeableImage = this.mImageRef.get();
        if (!(closeableImage instanceof CloseableBitmap)) {
            this.mImageRef.close();
            this.mImageRef = null;
            return null;
        }
        return ((CloseableBitmap)closeableImage).getUnderlyingBitmap();
    }
    
    boolean isDetached() {
        return this.mAttachCounter == 0;
    }
    
    @Override
    public void onCancellation(final DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (this.mDataSource == dataSource) {
            this.mDataSource = null;
        }
        dataSource.close();
    }
    
    @Override
    public void onFailure(final DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (this.mDataSource == dataSource) {
            Assertions.assumeNotNull(this.mBitmapUpdateListener).onImageLoadEvent(1);
            Assertions.assumeNotNull(this.mBitmapUpdateListener).onImageLoadEvent(3);
            this.mDataSource = null;
        }
        dataSource.close();
    }
    
    @Override
    public void onNewResult(final DataSource<CloseableReference<CloseableImage>> dataSource) {
        if (!dataSource.isFinished()) {
            return;
        }
        try {
            if (this.mDataSource != dataSource) {
                return;
            }
            this.mDataSource = null;
            final CloseableReference<CloseableImage> mImageRef = dataSource.getResult();
            if (mImageRef == null) {
                return;
            }
            if (!(mImageRef.get() instanceof CloseableBitmap)) {
                mImageRef.close();
                return;
            }
            this.mImageRef = mImageRef;
            final Bitmap bitmap = this.getBitmap();
            if (bitmap == null) {
                return;
            }
            final BitmapUpdateListener bitmapUpdateListener = Assertions.assumeNotNull(this.mBitmapUpdateListener);
            bitmapUpdateListener.onBitmapReady(bitmap);
            bitmapUpdateListener.onImageLoadEvent(2);
            bitmapUpdateListener.onImageLoadEvent(3);
        }
        finally {
            dataSource.close();
        }
    }
    
    @Override
    public void onProgressUpdate(final DataSource<CloseableReference<CloseableImage>> dataSource) {
    }
}
