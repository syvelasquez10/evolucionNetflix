// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer;

import java.nio.ByteBuffer;

public final class SampleHolder
{
    private final int bufferReplacementMode;
    public final CryptoInfo cryptoInfo;
    public ByteBuffer data;
    public int flags;
    public int size;
    public long timeUs;
    
    public SampleHolder(final int bufferReplacementMode) {
        this.cryptoInfo = new CryptoInfo();
        this.bufferReplacementMode = bufferReplacementMode;
    }
    
    private ByteBuffer createReplacementBuffer(final int n) {
        if (this.bufferReplacementMode == 1) {
            return ByteBuffer.allocate(n);
        }
        if (this.bufferReplacementMode == 2) {
            return ByteBuffer.allocateDirect(n);
        }
        int capacity;
        if (this.data == null) {
            capacity = 0;
        }
        else {
            capacity = this.data.capacity();
        }
        throw new IllegalStateException("Buffer too small (" + capacity + " < " + n + ")");
    }
    
    public void clearData() {
        if (this.data != null) {
            this.data.clear();
        }
    }
    
    public void ensureSpaceForWrite(int n) {
        if (this.data == null) {
            this.data = this.createReplacementBuffer(n);
        }
        else {
            final int capacity = this.data.capacity();
            final int position = this.data.position();
            n += position;
            if (capacity < n) {
                final ByteBuffer replacementBuffer = this.createReplacementBuffer(n);
                if (position > 0) {
                    this.data.position(0);
                    this.data.limit(position);
                    replacementBuffer.put(this.data);
                }
                this.data = replacementBuffer;
            }
        }
    }
    
    public boolean isDecodeOnly() {
        return (this.flags & 0x8000000) != 0x0;
    }
    
    public boolean isEncrypted() {
        return (this.flags & 0x2) != 0x0;
    }
    
    public boolean isSyncFrame() {
        return (this.flags & 0x1) != 0x0;
    }
}
