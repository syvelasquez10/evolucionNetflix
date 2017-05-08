// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import java.io.IOException;
import com.facebook.common.internal.Throwables;
import java.io.OutputStream;
import java.io.InputStream;

public class NativePooledByteBufferFactory implements PooledByteBufferFactory
{
    private final NativeMemoryChunkPool mPool;
    private final PooledByteStreams mPooledByteStreams;
    
    public NativePooledByteBufferFactory(final NativeMemoryChunkPool mPool, final PooledByteStreams mPooledByteStreams) {
        this.mPool = mPool;
        this.mPooledByteStreams = mPooledByteStreams;
    }
    
    NativePooledByteBuffer newByteBuf(final InputStream inputStream, final NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream) {
        this.mPooledByteStreams.copy(inputStream, nativePooledByteBufferOutputStream);
        return nativePooledByteBufferOutputStream.toByteBuffer();
    }
    
    @Override
    public NativePooledByteBuffer newByteBuffer(final InputStream inputStream) {
        final NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool);
        try {
            return this.newByteBuf(inputStream, nativePooledByteBufferOutputStream);
        }
        finally {
            nativePooledByteBufferOutputStream.close();
        }
    }
    
    @Override
    public NativePooledByteBuffer newByteBuffer(final InputStream inputStream, final int n) {
        final NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, n);
        try {
            return this.newByteBuf(inputStream, nativePooledByteBufferOutputStream);
        }
        finally {
            nativePooledByteBufferOutputStream.close();
        }
    }
    
    @Override
    public NativePooledByteBuffer newByteBuffer(final byte[] array) {
        final NativePooledByteBufferOutputStream nativePooledByteBufferOutputStream = new NativePooledByteBufferOutputStream(this.mPool, array.length);
        try {
            nativePooledByteBufferOutputStream.write(array, 0, array.length);
            return nativePooledByteBufferOutputStream.toByteBuffer();
        }
        catch (IOException ex) {
            throw Throwables.propagate(ex);
        }
        finally {
            nativePooledByteBufferOutputStream.close();
        }
    }
    
    @Override
    public NativePooledByteBufferOutputStream newOutputStream() {
        return new NativePooledByteBufferOutputStream(this.mPool);
    }
    
    @Override
    public NativePooledByteBufferOutputStream newOutputStream(final int n) {
        return new NativePooledByteBufferOutputStream(this.mPool, n);
    }
}
