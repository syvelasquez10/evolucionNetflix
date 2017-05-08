// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.image;

import android.util.Pair;
import com.facebook.imageutils.JfifUtil;
import com.facebook.imageutils.BitmapUtil;
import com.facebook.imageformat.ImageFormatChecker;
import com.facebook.imagepipeline.memory.PooledByteBufferInputStream;
import java.io.InputStream;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import java.io.FileInputStream;
import com.facebook.common.internal.Supplier;
import com.facebook.imageformat.ImageFormat;
import java.io.Closeable;

public class EncodedImage implements Closeable
{
    private int mHeight;
    private ImageFormat mImageFormat;
    private final Supplier<FileInputStream> mInputStreamSupplier;
    private final CloseableReference<PooledByteBuffer> mPooledByteBufferRef;
    private int mRotationAngle;
    private int mSampleSize;
    private int mStreamSize;
    private int mWidth;
    
    public EncodedImage(final Supplier<FileInputStream> mInputStreamSupplier) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkNotNull(mInputStreamSupplier);
        this.mPooledByteBufferRef = null;
        this.mInputStreamSupplier = mInputStreamSupplier;
    }
    
    public EncodedImage(final Supplier<FileInputStream> supplier, final int mStreamSize) {
        this(supplier);
        this.mStreamSize = mStreamSize;
    }
    
    public EncodedImage(final CloseableReference<PooledByteBuffer> closeableReference) {
        this.mImageFormat = ImageFormat.UNKNOWN;
        this.mRotationAngle = -1;
        this.mWidth = -1;
        this.mHeight = -1;
        this.mSampleSize = 1;
        this.mStreamSize = -1;
        Preconditions.checkArgument(CloseableReference.isValid(closeableReference));
        this.mPooledByteBufferRef = closeableReference.clone();
        this.mInputStreamSupplier = null;
    }
    
    public static EncodedImage cloneOrNull(final EncodedImage encodedImage) {
        if (encodedImage != null) {
            return encodedImage.cloneOrNull();
        }
        return null;
    }
    
    public static void closeSafely(final EncodedImage encodedImage) {
        if (encodedImage != null) {
            encodedImage.close();
        }
    }
    
    public static boolean isMetaDataAvailable(final EncodedImage encodedImage) {
        return encodedImage.mRotationAngle >= 0 && encodedImage.mWidth >= 0 && encodedImage.mHeight >= 0;
    }
    
    public static boolean isValid(final EncodedImage encodedImage) {
        return encodedImage != null && encodedImage.isValid();
    }
    
    public EncodedImage cloneOrNull() {
        EncodedImage encodedImage;
        if (this.mInputStreamSupplier != null) {
            encodedImage = new EncodedImage(this.mInputStreamSupplier, this.mStreamSize);
        }
        else {
            final CloseableReference<PooledByteBuffer> cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
            if (cloneOrNull == null) {
                encodedImage = null;
            }
            else {
                try {
                    final EncodedImage encodedImage2 = new EncodedImage(cloneOrNull);
                }
                finally {
                    CloseableReference.closeSafely(cloneOrNull);
                }
            }
            CloseableReference.closeSafely(cloneOrNull);
        }
        if (encodedImage != null) {
            encodedImage.copyMetaDataFrom(this);
        }
        return encodedImage;
    }
    
    @Override
    public void close() {
        CloseableReference.closeSafely(this.mPooledByteBufferRef);
    }
    
    public void copyMetaDataFrom(final EncodedImage encodedImage) {
        this.mImageFormat = encodedImage.getImageFormat();
        this.mWidth = encodedImage.getWidth();
        this.mHeight = encodedImage.getHeight();
        this.mRotationAngle = encodedImage.getRotationAngle();
        this.mSampleSize = encodedImage.getSampleSize();
        this.mStreamSize = encodedImage.getSize();
    }
    
    public CloseableReference<PooledByteBuffer> getByteBufferRef() {
        return CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
    }
    
    public int getHeight() {
        return this.mHeight;
    }
    
    public ImageFormat getImageFormat() {
        return this.mImageFormat;
    }
    
    public InputStream getInputStream() {
        if (this.mInputStreamSupplier != null) {
            return this.mInputStreamSupplier.get();
        }
        final CloseableReference<PooledByteBuffer> cloneOrNull = CloseableReference.cloneOrNull(this.mPooledByteBufferRef);
        if (cloneOrNull != null) {
            try {
                return new PooledByteBufferInputStream(cloneOrNull.get());
            }
            finally {
                CloseableReference.closeSafely(cloneOrNull);
            }
        }
        return null;
    }
    
    public int getRotationAngle() {
        return this.mRotationAngle;
    }
    
    public int getSampleSize() {
        return this.mSampleSize;
    }
    
    public int getSize() {
        if (this.mPooledByteBufferRef != null && this.mPooledByteBufferRef.get() != null) {
            return this.mPooledByteBufferRef.get().size();
        }
        return this.mStreamSize;
    }
    
    public int getWidth() {
        return this.mWidth;
    }
    
    public boolean isCompleteAt(final int n) {
        if (this.mImageFormat == ImageFormat.JPEG && this.mInputStreamSupplier == null) {
            Preconditions.checkNotNull(this.mPooledByteBufferRef);
            final PooledByteBuffer pooledByteBuffer = this.mPooledByteBufferRef.get();
            return pooledByteBuffer.read(n - 2) == -1 && pooledByteBuffer.read(n - 1) == -39;
        }
        return true;
    }
    
    public boolean isValid() {
        synchronized (this) {
            return CloseableReference.isValid(this.mPooledByteBufferRef) || this.mInputStreamSupplier != null;
        }
    }
    
    public void parseMetaData() {
        final ImageFormat imageFormat_WrapIOException = ImageFormatChecker.getImageFormat_WrapIOException(this.getInputStream());
        this.mImageFormat = imageFormat_WrapIOException;
        if (!ImageFormat.isWebpFormat(imageFormat_WrapIOException)) {
            final Pair<Integer, Integer> decodeDimensions = BitmapUtil.decodeDimensions(this.getInputStream());
            if (decodeDimensions != null) {
                this.mWidth = (int)decodeDimensions.first;
                this.mHeight = (int)decodeDimensions.second;
                if (imageFormat_WrapIOException != ImageFormat.JPEG) {
                    this.mRotationAngle = 0;
                    return;
                }
                if (this.mRotationAngle == -1) {
                    this.mRotationAngle = JfifUtil.getAutoRotateAngleFromOrientation(JfifUtil.getOrientation(this.getInputStream()));
                }
            }
        }
    }
    
    public void setHeight(final int mHeight) {
        this.mHeight = mHeight;
    }
    
    public void setImageFormat(final ImageFormat mImageFormat) {
        this.mImageFormat = mImageFormat;
    }
    
    public void setRotationAngle(final int mRotationAngle) {
        this.mRotationAngle = mRotationAngle;
    }
    
    public void setSampleSize(final int mSampleSize) {
        this.mSampleSize = mSampleSize;
    }
    
    public void setWidth(final int mWidth) {
        this.mWidth = mWidth;
    }
}
