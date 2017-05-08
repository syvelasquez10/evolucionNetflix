// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import java.io.EOFException;
import java.util.Arrays;
import com.google.android.exoplayer.upstream.DataSource;

public final class DefaultExtractorInput implements ExtractorInput
{
    private static final byte[] SCRATCH_SPACE;
    private final DataSource dataSource;
    private byte[] peekBuffer;
    private int peekBufferLength;
    private int peekBufferPosition;
    private long position;
    private final long streamLength;
    
    static {
        SCRATCH_SPACE = new byte[4096];
    }
    
    public DefaultExtractorInput(final DataSource dataSource, final long position, final long streamLength) {
        this.dataSource = dataSource;
        this.position = position;
        this.streamLength = streamLength;
        this.peekBuffer = new byte[8192];
    }
    
    private void commitBytesRead(final int n) {
        if (n != -1) {
            this.position += n;
        }
    }
    
    private void ensureSpaceForPeek(int n) {
        n += this.peekBufferPosition;
        if (n > this.peekBuffer.length) {
            this.peekBuffer = Arrays.copyOf(this.peekBuffer, Math.max(this.peekBuffer.length * 2, n));
        }
    }
    
    private int readFromDataSource(final byte[] array, int read, final int n, final int n2, final boolean b) {
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        read = this.dataSource.read(array, read + n2, n - n2);
        if (read != -1) {
            return n2 + read;
        }
        if (n2 == 0 && b) {
            return -1;
        }
        throw new EOFException();
    }
    
    private int readFromPeekBuffer(final byte[] array, final int n, int min) {
        if (this.peekBufferLength == 0) {
            return 0;
        }
        min = Math.min(this.peekBufferLength, min);
        System.arraycopy(this.peekBuffer, 0, array, n, min);
        this.updatePeekBuffer(min);
        return min;
    }
    
    private int skipFromPeekBuffer(int min) {
        min = Math.min(this.peekBufferLength, min);
        this.updatePeekBuffer(min);
        return min;
    }
    
    private void updatePeekBuffer(final int n) {
        this.peekBufferLength -= n;
        this.peekBufferPosition = 0;
        System.arraycopy(this.peekBuffer, n, this.peekBuffer, 0, this.peekBufferLength);
    }
    
    public boolean advancePeekPosition(final int n, final boolean b) {
        this.ensureSpaceForPeek(n);
        int i = Math.min(this.peekBufferLength - this.peekBufferPosition, n);
        this.peekBufferLength += n - i;
        while (i < n) {
            if ((i = this.readFromDataSource(this.peekBuffer, this.peekBufferPosition, n, i, b)) == -1) {
                return false;
            }
        }
        this.peekBufferPosition += n;
        return true;
    }
    
    @Override
    public long getPosition() {
        return this.position;
    }
    
    @Override
    public void peekFully(final byte[] array, final int n, final int n2) {
        this.peekFully(array, n, n2, false);
    }
    
    public boolean peekFully(final byte[] array, final int n, final int n2, final boolean b) {
        if (!this.advancePeekPosition(n2, b)) {
            return false;
        }
        System.arraycopy(this.peekBuffer, this.peekBufferPosition - n2, array, n, n2);
        return true;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        int n3;
        if ((n3 = this.readFromPeekBuffer(array, n, n2)) == 0) {
            n3 = this.readFromDataSource(array, n, n2, 0, true);
        }
        this.commitBytesRead(n3);
        return n3;
    }
    
    @Override
    public void readFully(final byte[] array, final int n, final int n2) {
        this.readFully(array, n, n2, false);
    }
    
    @Override
    public boolean readFully(final byte[] array, final int n, final int n2, final boolean b) {
        int n3;
        for (n3 = this.readFromPeekBuffer(array, n, n2); n3 < n2 && n3 != -1; n3 = this.readFromDataSource(array, n, n2, n3, b)) {}
        this.commitBytesRead(n3);
        return n3 != -1;
    }
    
    @Override
    public void resetPeekPosition() {
        this.peekBufferPosition = 0;
    }
    
    @Override
    public void skipFully(final int n) {
        this.skipFully(n, false);
    }
    
    public boolean skipFully(final int n, final boolean b) {
        int n2;
        for (n2 = this.skipFromPeekBuffer(n); n2 < n && n2 != -1; n2 = this.readFromDataSource(DefaultExtractorInput.SCRATCH_SPACE, -n2, Math.min(n, DefaultExtractorInput.SCRATCH_SPACE.length + n2), n2, b)) {}
        this.commitBytesRead(n2);
        return n2 != -1;
    }
}
