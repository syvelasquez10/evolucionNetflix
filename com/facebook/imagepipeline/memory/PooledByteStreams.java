// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.io.OutputStream;
import java.io.InputStream;
import com.facebook.common.internal.Preconditions;

public class PooledByteStreams
{
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;
    
    public PooledByteStreams(final ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 16384);
    }
    
    PooledByteStreams(final ByteArrayPool mByteArrayPool, final int mTempBufSize) {
        Preconditions.checkArgument(mTempBufSize > 0);
        this.mTempBufSize = mTempBufSize;
        this.mByteArrayPool = mByteArrayPool;
    }
    
    public long copy(final InputStream inputStream, final OutputStream outputStream) {
        long n = 0L;
        final byte[] array = this.mByteArrayPool.get(this.mTempBufSize);
        try {
            while (true) {
                final int read = inputStream.read(array, 0, this.mTempBufSize);
                if (read == -1) {
                    break;
                }
                outputStream.write(array, 0, read);
                n += read;
            }
            return n;
        }
        finally {
            this.mByteArrayPool.release(array);
        }
    }
}
