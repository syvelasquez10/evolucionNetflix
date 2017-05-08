// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.platform;

import com.facebook.common.internal.Throwables;
import com.facebook.imagepipeline.common.TooManyBitmapsException;
import com.facebook.imagepipeline.nativecode.Bitmaps;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.image.EncodedImage;
import android.graphics.Bitmap;
import android.os.Build$VERSION;
import android.graphics.BitmapFactory$Options;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.BitmapCounterProvider;
import com.facebook.imagepipeline.memory.BitmapCounter;

abstract class DalvikPurgeableDecoder implements PlatformDecoder
{
    protected static final byte[] EOI;
    private final BitmapCounter mUnpooledBitmapsCounter;
    
    static {
        EOI = new byte[] { -1, -39 };
    }
    
    DalvikPurgeableDecoder() {
        this.mUnpooledBitmapsCounter = BitmapCounterProvider.get();
    }
    
    protected static boolean endsWithEOI(final CloseableReference<PooledByteBuffer> closeableReference, final int n) {
        final PooledByteBuffer pooledByteBuffer = closeableReference.get();
        return n >= 2 && pooledByteBuffer.read(n - 2) == -1 && pooledByteBuffer.read(n - 1) == -39;
    }
    
    private static BitmapFactory$Options getBitmapFactoryOptions(final int inSampleSize, final Bitmap$Config inPreferredConfig) {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inDither = true;
        bitmapFactory$Options.inPreferredConfig = inPreferredConfig;
        bitmapFactory$Options.inPurgeable = true;
        bitmapFactory$Options.inInputShareable = true;
        bitmapFactory$Options.inSampleSize = inSampleSize;
        if (Build$VERSION.SDK_INT >= 11) {
            bitmapFactory$Options.inMutable = true;
        }
        return bitmapFactory$Options;
    }
    
    abstract Bitmap decodeByteArrayAsPurgeable(final CloseableReference<PooledByteBuffer> p0, final BitmapFactory$Options p1);
    
    @Override
    public CloseableReference<Bitmap> decodeFromEncodedImage(EncodedImage byteBufferRef, final Bitmap$Config bitmap$Config) {
        final BitmapFactory$Options bitmapFactoryOptions = getBitmapFactoryOptions(byteBufferRef.getSampleSize(), bitmap$Config);
        byteBufferRef = (EncodedImage)byteBufferRef.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            return this.pinBitmap(this.decodeByteArrayAsPurgeable((CloseableReference<PooledByteBuffer>)byteBufferRef, bitmapFactoryOptions));
        }
        finally {
            CloseableReference.closeSafely((CloseableReference<?>)byteBufferRef);
        }
    }
    
    abstract Bitmap decodeJPEGByteArrayAsPurgeable(final CloseableReference<PooledByteBuffer> p0, final int p1, final BitmapFactory$Options p2);
    
    @Override
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(EncodedImage byteBufferRef, final Bitmap$Config bitmap$Config, final int n) {
        final BitmapFactory$Options bitmapFactoryOptions = getBitmapFactoryOptions(byteBufferRef.getSampleSize(), bitmap$Config);
        byteBufferRef = (EncodedImage)byteBufferRef.getByteBufferRef();
        Preconditions.checkNotNull(byteBufferRef);
        try {
            return this.pinBitmap(this.decodeJPEGByteArrayAsPurgeable((CloseableReference<PooledByteBuffer>)byteBufferRef, n, bitmapFactoryOptions));
        }
        finally {
            CloseableReference.closeSafely((CloseableReference<?>)byteBufferRef);
        }
    }
    
    public CloseableReference<Bitmap> pinBitmap(final Bitmap bitmap) {
        try {
            Bitmaps.pinBitmap(bitmap);
            if (!this.mUnpooledBitmapsCounter.increase(bitmap)) {
                bitmap.recycle();
                throw new TooManyBitmapsException();
            }
        }
        catch (Exception ex) {
            bitmap.recycle();
            throw Throwables.propagate(ex);
        }
        return CloseableReference.of(bitmap, this.mUnpooledBitmapsCounter.getReleaser());
    }
}
