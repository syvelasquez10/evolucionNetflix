// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.imagepipeline.memory;

import com.facebook.common.logging.FLog;
import java.io.IOException;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.references.ResourceReleaser;
import java.io.InputStream;

public class PooledByteArrayBufferedInputStream extends InputStream
{
    private int mBufferOffset;
    private int mBufferedSize;
    private final byte[] mByteArray;
    private boolean mClosed;
    private final InputStream mInputStream;
    private final ResourceReleaser<byte[]> mResourceReleaser;
    
    public PooledByteArrayBufferedInputStream(final InputStream inputStream, final byte[] array, final ResourceReleaser<byte[]> resourceReleaser) {
        this.mInputStream = Preconditions.checkNotNull(inputStream);
        this.mByteArray = Preconditions.checkNotNull(array);
        this.mResourceReleaser = Preconditions.checkNotNull(resourceReleaser);
        this.mBufferedSize = 0;
        this.mBufferOffset = 0;
        this.mClosed = false;
    }
    
    private boolean ensureDataInBuffer() {
        if (this.mBufferOffset < this.mBufferedSize) {
            return true;
        }
        final int read = this.mInputStream.read(this.mByteArray);
        if (read <= 0) {
            return false;
        }
        this.mBufferedSize = read;
        this.mBufferOffset = 0;
        return true;
    }
    
    private void ensureNotClosed() {
        if (this.mClosed) {
            throw new IOException("stream already closed");
        }
    }
    
    @Override
    public int available() {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        this.ensureNotClosed();
        return this.mBufferedSize - this.mBufferOffset + this.mInputStream.available();
    }
    
    @Override
    public void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            this.mResourceReleaser.release(this.mByteArray);
            super.close();
        }
    }
    
    @Override
    protected void finalize() {
        if (!this.mClosed) {
            FLog.e("PooledByteInputStream", "Finalized without closing");
            this.close();
        }
        super.finalize();
    }
    
    @Override
    public int read() {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        this.ensureNotClosed();
        if (!this.ensureDataInBuffer()) {
            return -1;
        }
        return this.mByteArray[this.mBufferOffset++] & 0xFF;
    }
    
    @Override
    public int read(final byte[] array, final int n, int min) {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        this.ensureNotClosed();
        if (!this.ensureDataInBuffer()) {
            return -1;
        }
        min = Math.min(this.mBufferedSize - this.mBufferOffset, min);
        System.arraycopy(this.mByteArray, this.mBufferOffset, array, n, min);
        this.mBufferOffset += min;
        return min;
    }
    
    @Override
    public long skip(final long n) {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        this.ensureNotClosed();
        final int n2 = this.mBufferedSize - this.mBufferOffset;
        if (n2 >= n) {
            this.mBufferOffset += (int)n;
            return n;
        }
        this.mBufferOffset = this.mBufferedSize;
        return n2 + this.mInputStream.skip(n - n2);
    }
}
