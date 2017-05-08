// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.platform;

import android.graphics.Bitmap$Config;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.common.internal.Preconditions;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory$Options;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.FlexByteArrayPool;
import android.annotation.TargetApi;

@TargetApi(19)
public class KitKatPurgeableDecoder extends DalvikPurgeableDecoder
{
    private final FlexByteArrayPool mFlexByteArrayPool;
    
    public KitKatPurgeableDecoder(final FlexByteArrayPool mFlexByteArrayPool) {
        this.mFlexByteArrayPool = mFlexByteArrayPool;
    }
    
    private static void putEOI(final byte[] array, final int n) {
        array[n] = -1;
        array[n + 1] = -39;
    }
    
    protected Bitmap decodeByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> value, final BitmapFactory$Options bitmapFactory$Options) {
        final PooledByteBuffer pooledByteBuffer = (Object)value.get();
        final int size = pooledByteBuffer.size();
        value = this.mFlexByteArrayPool.get(size);
        try {
            final byte[] array = value.get();
            pooledByteBuffer.read(0, array, 0, size);
            return Preconditions.checkNotNull(BitmapFactory.decodeByteArray(array, 0, size, bitmapFactory$Options), "BitmapFactory returned null");
        }
        finally {
            CloseableReference.closeSafely(value);
        }
    }
    
    protected Bitmap decodeJPEGByteArrayAsPurgeable(CloseableReference<PooledByteBuffer> value, final int n, final BitmapFactory$Options bitmapFactory$Options) {
        boolean b = false;
        Label_0117: {
            if (!DalvikPurgeableDecoder.endsWithEOI((CloseableReference<PooledByteBuffer>)value, n)) {
                break Label_0117;
            }
            byte[] eoi = null;
            while (true) {
                final PooledByteBuffer pooledByteBuffer = (Object)value.get();
                if (n <= pooledByteBuffer.size()) {
                    b = true;
                }
                Preconditions.checkArgument(b);
                value = this.mFlexByteArrayPool.get(n + 2);
                try {
                    final byte[] array = value.get();
                    pooledByteBuffer.read(0, array, 0, n);
                    int n2 = n;
                    if (eoi != null) {
                        putEOI(array, n);
                        n2 = n + 2;
                    }
                    return Preconditions.checkNotNull(BitmapFactory.decodeByteArray(array, 0, n2, bitmapFactory$Options), "BitmapFactory returned null");
                    eoi = KitKatPurgeableDecoder.EOI;
                    continue;
                }
                finally {
                    CloseableReference.closeSafely(value);
                }
                break;
            }
        }
    }
}
