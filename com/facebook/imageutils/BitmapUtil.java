// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imageutils;

import android.annotation.SuppressLint;
import android.os.Build$VERSION;
import android.graphics.Bitmap;
import android.graphics.Bitmap$Config;
import android.graphics.Rect;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory$Options;
import com.facebook.common.internal.Preconditions;
import android.util.Pair;
import java.io.InputStream;
import java.nio.ByteBuffer;
import android.support.v4.util.Pools$SynchronizedPool;

public final class BitmapUtil
{
    private static final Pools$SynchronizedPool<ByteBuffer> DECODE_BUFFERS;
    
    static {
        DECODE_BUFFERS = new Pools$SynchronizedPool<ByteBuffer>(12);
    }
    
    public static Pair<Integer, Integer> decodeDimensions(final InputStream inputStream) {
        final Pair<Integer, Integer> pair = null;
        Preconditions.checkNotNull(inputStream);
        ByteBuffer allocate;
        if ((allocate = BitmapUtil.DECODE_BUFFERS.acquire()) == null) {
            allocate = ByteBuffer.allocate(16384);
        }
        final BitmapFactory$Options bitmapFactory$Options = new BitmapFactory$Options();
        bitmapFactory$Options.inJustDecodeBounds = true;
        try {
            bitmapFactory$Options.inTempStorage = allocate.array();
            BitmapFactory.decodeStream(inputStream, (Rect)null, bitmapFactory$Options);
            Pair pair2 = pair;
            if (bitmapFactory$Options.outWidth != -1) {
                if (bitmapFactory$Options.outHeight == -1) {
                    pair2 = pair;
                }
                else {
                    pair2 = new Pair((Object)bitmapFactory$Options.outWidth, (Object)bitmapFactory$Options.outHeight);
                }
            }
            return (Pair<Integer, Integer>)pair2;
        }
        finally {
            BitmapUtil.DECODE_BUFFERS.release(allocate);
        }
    }
    
    public static int getPixelSizeForBitmapConfig(final Bitmap$Config bitmap$Config) {
        int n = 2;
        switch (BitmapUtil$1.$SwitchMap$android$graphics$Bitmap$Config[bitmap$Config.ordinal()]) {
            default: {
                throw new UnsupportedOperationException("The provided Bitmap.Config is not supported");
            }
            case 1: {
                n = 4;
            }
            case 3:
            case 4: {
                return n;
            }
            case 2: {
                return 1;
            }
        }
    }
    
    public static int getSizeInByteForBitmap(final int n, final int n2, final Bitmap$Config bitmap$Config) {
        return n * n2 * getPixelSizeForBitmapConfig(bitmap$Config);
    }
    
    @SuppressLint({ "NewApi" })
    public static int getSizeInBytes(final Bitmap bitmap) {
        if (bitmap == null) {
            return 0;
        }
        if (Build$VERSION.SDK_INT > 19) {
            try {
                return bitmap.getAllocationByteCount();
            }
            catch (NullPointerException ex) {}
        }
        if (Build$VERSION.SDK_INT >= 12) {
            return bitmap.getByteCount();
        }
        return bitmap.getWidth() * bitmap.getRowBytes();
    }
}
