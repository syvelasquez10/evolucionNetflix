// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.bitmaps;

import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import java.io.IOException;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;

public class EmptyJpegGenerator
{
    private static final byte[] EMPTY_JPEG_PREFIX;
    private static final byte[] EMPTY_JPEG_SUFFIX;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    
    static {
        EMPTY_JPEG_PREFIX = new byte[] { -1, -40, -1, -37, 0, 67, 0, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -64, 0, 17, 8 };
        EMPTY_JPEG_SUFFIX = new byte[] { 3, 1, 34, 0, 2, 17, 0, 3, 17, 0, -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6, -1, -38, 0, 12, 3, 1, 0, 2, 17, 3, 17, 0, 63, 0, -114, -118, 40, -96, 15, -1, -39 };
    }
    
    public EmptyJpegGenerator(final PooledByteBufferFactory mPooledByteBufferFactory) {
        this.mPooledByteBufferFactory = mPooledByteBufferFactory;
    }
    
    public CloseableReference<PooledByteBuffer> generate(final short n, final short n2) {
        PooledByteBufferOutputStream pooledByteBufferOutputStream = null;
        PooledByteBufferOutputStream outputStream = null;
        try {
            final PooledByteBufferOutputStream pooledByteBufferOutputStream2 = pooledByteBufferOutputStream = (outputStream = this.mPooledByteBufferFactory.newOutputStream(EmptyJpegGenerator.EMPTY_JPEG_PREFIX.length + EmptyJpegGenerator.EMPTY_JPEG_SUFFIX.length + 4));
            pooledByteBufferOutputStream2.write(EmptyJpegGenerator.EMPTY_JPEG_PREFIX);
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream2.write((byte)(n2 >> 8));
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream2.write((byte)(n2 & 0xFF));
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream2.write((byte)(n >> 8));
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream2.write((byte)(n & 0xFF));
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream2.write(EmptyJpegGenerator.EMPTY_JPEG_SUFFIX);
            outputStream = pooledByteBufferOutputStream2;
            pooledByteBufferOutputStream = pooledByteBufferOutputStream2;
            return CloseableReference.of(pooledByteBufferOutputStream2.toByteBuffer());
        }
        catch (IOException ex) {
            pooledByteBufferOutputStream = outputStream;
            throw new RuntimeException(ex);
        }
        finally {
            if (pooledByteBufferOutputStream != null) {
                pooledByteBufferOutputStream.close();
            }
        }
    }
}
