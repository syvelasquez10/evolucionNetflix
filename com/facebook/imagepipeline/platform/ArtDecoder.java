// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.platform;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.imageutils.BitmapUtil;
import java.io.InputStream;
import com.facebook.common.streams.TailAppendingInputStream;
import com.facebook.common.streams.LimitedInputStream;
import com.facebook.common.internal.Preconditions;
import android.graphics.Bitmap;
import com.facebook.common.references.CloseableReference;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.image.EncodedImage;
import java.nio.ByteBuffer;
import android.support.v4.util.Pools$SynchronizedPool;
import com.facebook.imagepipeline.memory.BitmapPool;
import android.annotation.TargetApi;

@TargetApi(21)
public class ArtDecoder implements PlatformDecoder
{
    private static final byte[] EOI_TAIL;
    private final BitmapPool mBitmapPool;
    final Pools$SynchronizedPool<ByteBuffer> mDecodeBuffers;
    
    static {
        EOI_TAIL = new byte[] { -1, -39 };
    }
    
    public ArtDecoder(final BitmapPool mBitmapPool, final int n, final Pools$SynchronizedPool mDecodeBuffers) {
        this.mBitmapPool = mBitmapPool;
        this.mDecodeBuffers = (Pools$SynchronizedPool<ByteBuffer>)mDecodeBuffers;
        for (int i = 0; i < n; ++i) {
            this.mDecodeBuffers.release(ByteBuffer.allocate(16384));
        }
    }
    
    private static BitmapFactory$Options getDecodeOptionsForStream(final EncodedImage encodedImage, final Bitmap$Config inPreferredConfig) {
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inSampleSize = encodedImage.getSampleSize();
        bitmapFactory$Options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(encodedImage.getInputStream(), (Rect)null, bitmapFactory$Options);
        if (bitmapFactory$Options.outWidth == -1 || bitmapFactory$Options.outHeight == -1) {
            throw new IllegalArgumentException();
        }
        bitmapFactory$Options.inJustDecodeBounds = false;
        bitmapFactory$Options.inDither = true;
        bitmapFactory$Options.inPreferredConfig = inPreferredConfig;
        bitmapFactory$Options.inMutable = true;
        return bitmapFactory$Options;
    }
    
    @Override
    public CloseableReference<Bitmap> decodeFromEncodedImage(final EncodedImage encodedImage, final Bitmap$Config bitmap$Config) {
        final BitmapFactory$Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, bitmap$Config);
        Label_0030: {
            if (decodeOptionsForStream.inPreferredConfig == Bitmap$Config.ARGB_8888) {
                break Label_0030;
            }
            boolean b = true;
            try {
                return this.decodeStaticImageFromStream(encodedImage.getInputStream(), decodeOptionsForStream);
                b = false;
                return this.decodeStaticImageFromStream(encodedImage.getInputStream(), decodeOptionsForStream);
            }
            catch (RuntimeException ex) {
                if (b) {
                    return this.decodeFromEncodedImage(encodedImage, Bitmap$Config.ARGB_8888);
                }
                throw ex;
            }
        }
    }
    
    @Override
    public CloseableReference<Bitmap> decodeJPEGFromEncodedImage(final EncodedImage encodedImage, final Bitmap$Config bitmap$Config, int n) {
        final boolean complete = encodedImage.isCompleteAt(n);
        final BitmapFactory$Options decodeOptionsForStream = getDecodeOptionsForStream(encodedImage, bitmap$Config);
        InputStream inputStream = encodedImage.getInputStream();
        Preconditions.checkNotNull(inputStream);
        if (encodedImage.getSize() > n) {
            inputStream = new LimitedInputStream(inputStream, n);
        }
        if (!complete) {
            inputStream = new TailAppendingInputStream(inputStream, ArtDecoder.EOI_TAIL);
        }
        while (true) {
            Label_0082: {
                if (decodeOptionsForStream.inPreferredConfig == Bitmap$Config.ARGB_8888) {
                    break Label_0082;
                }
                n = 1;
                try {
                    return this.decodeStaticImageFromStream(inputStream, decodeOptionsForStream);
                    n = 0;
                    return this.decodeStaticImageFromStream(inputStream, decodeOptionsForStream);
                }
                catch (RuntimeException ex) {
                    if (n != 0) {
                        return this.decodeFromEncodedImage(encodedImage, Bitmap$Config.ARGB_8888);
                    }
                    throw ex;
                }
            }
            continue;
        }
    }
    
    protected CloseableReference<Bitmap> decodeStaticImageFromStream(final InputStream inputStream, final BitmapFactory$Options bitmapFactory$Options) {
        Preconditions.checkNotNull(inputStream);
        final Bitmap inBitmap = this.mBitmapPool.get(BitmapUtil.getSizeInByteForBitmap(bitmapFactory$Options.outWidth, bitmapFactory$Options.outHeight, bitmapFactory$Options.inPreferredConfig));
        if (inBitmap == null) {
            throw new NullPointerException("BitmapPool.get returned null");
        }
        bitmapFactory$Options.inBitmap = inBitmap;
        ByteBuffer allocate = this.mDecodeBuffers.acquire();
        if (allocate == null) {
            allocate = ByteBuffer.allocate(16384);
        }
        try {
            bitmapFactory$Options.inTempStorage = allocate.array();
            final Bitmap decodeStream = BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
            this.mDecodeBuffers.release(allocate);
            if (inBitmap != decodeStream) {
                this.mBitmapPool.release(inBitmap);
                decodeStream.recycle();
                throw new IllegalStateException();
            }
        }
        catch (RuntimeException ex) {
            this.mBitmapPool.release(inBitmap);
            throw ex;
        }
        finally {
            this.mDecodeBuffers.release(allocate);
        }
        final Bitmap bitmap;
        return CloseableReference.of(bitmap, this.mBitmapPool);
    }
}
