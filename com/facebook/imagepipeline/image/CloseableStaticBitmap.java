// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.image;

import com.facebook.imageutils.BitmapUtil;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.ResourceReleaser;
import com.facebook.common.references.CloseableReference;
import android.graphics.Bitmap;

public class CloseableStaticBitmap extends CloseableBitmap
{
    private volatile Bitmap mBitmap;
    private CloseableReference<Bitmap> mBitmapReference;
    private final QualityInfo mQualityInfo;
    private final int mRotationAngle;
    
    public CloseableStaticBitmap(final Bitmap bitmap, final ResourceReleaser<Bitmap> resourceReleaser, final QualityInfo mQualityInfo, final int mRotationAngle) {
        this.mBitmap = Preconditions.checkNotNull(bitmap);
        this.mBitmapReference = CloseableReference.of(this.mBitmap, (ResourceReleaser<Bitmap>)Preconditions.checkNotNull((ResourceReleaser<T>)resourceReleaser));
        this.mQualityInfo = mQualityInfo;
        this.mRotationAngle = mRotationAngle;
    }
    
    public CloseableStaticBitmap(final CloseableReference<Bitmap> closeableReference, final QualityInfo mQualityInfo, final int mRotationAngle) {
        this.mBitmapReference = Preconditions.checkNotNull(closeableReference.cloneOrNull());
        this.mBitmap = this.mBitmapReference.get();
        this.mQualityInfo = mQualityInfo;
        this.mRotationAngle = mRotationAngle;
    }
    
    private CloseableReference<Bitmap> detachBitmapReference() {
        synchronized (this) {
            final CloseableReference<Bitmap> mBitmapReference = this.mBitmapReference;
            this.mBitmapReference = null;
            this.mBitmap = null;
            return mBitmapReference;
        }
    }
    
    @Override
    public void close() {
        final CloseableReference<Bitmap> detachBitmapReference = this.detachBitmapReference();
        if (detachBitmapReference != null) {
            detachBitmapReference.close();
        }
    }
    
    @Override
    public int getHeight() {
        final Bitmap mBitmap = this.mBitmap;
        if (mBitmap == null) {
            return 0;
        }
        return mBitmap.getHeight();
    }
    
    @Override
    public QualityInfo getQualityInfo() {
        return this.mQualityInfo;
    }
    
    public int getRotationAngle() {
        return this.mRotationAngle;
    }
    
    @Override
    public int getSizeInBytes() {
        return BitmapUtil.getSizeInBytes(this.mBitmap);
    }
    
    public Bitmap getUnderlyingBitmap() {
        return this.mBitmap;
    }
    
    @Override
    public int getWidth() {
        final Bitmap mBitmap = this.mBitmap;
        if (mBitmap == null) {
            return 0;
        }
        return mBitmap.getWidth();
    }
    
    @Override
    public boolean isClosed() {
        synchronized (this) {
            return this.mBitmapReference == null;
        }
    }
}
