// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.SampleHolder;
import com.google.android.exoplayer.util.Assertions;

final class RollingSampleBuffer$InfoQueue
{
    private int absoluteReadIndex;
    private int capacity;
    private byte[][] encryptionKeys;
    private int[] flags;
    private long[] offsets;
    private int queueSize;
    private int relativeReadIndex;
    private int relativeWriteIndex;
    private int[] sizes;
    private long[] timesUs;
    
    public RollingSampleBuffer$InfoQueue() {
        this.capacity = 1000;
        this.offsets = new long[this.capacity];
        this.timesUs = new long[this.capacity];
        this.flags = new int[this.capacity];
        this.sizes = new int[this.capacity];
        this.encryptionKeys = new byte[this.capacity][];
    }
    
    public void clear() {
        this.absoluteReadIndex = 0;
        this.relativeReadIndex = 0;
        this.relativeWriteIndex = 0;
        this.queueSize = 0;
    }
    
    public void commitSample(final long n, int capacity, final long n2, int n3, final byte[] array) {
        synchronized (this) {
            this.timesUs[this.relativeWriteIndex] = n;
            this.offsets[this.relativeWriteIndex] = n2;
            this.sizes[this.relativeWriteIndex] = n3;
            this.flags[this.relativeWriteIndex] = capacity;
            this.encryptionKeys[this.relativeWriteIndex] = array;
            ++this.queueSize;
            if (this.queueSize == this.capacity) {
                capacity = this.capacity + 1000;
                final long[] offsets = new long[capacity];
                final long[] timesUs = new long[capacity];
                final int[] flags = new int[capacity];
                final int[] sizes = new int[capacity];
                final byte[][] encryptionKeys = new byte[capacity][];
                n3 = this.capacity - this.relativeReadIndex;
                System.arraycopy(this.offsets, this.relativeReadIndex, offsets, 0, n3);
                System.arraycopy(this.timesUs, this.relativeReadIndex, timesUs, 0, n3);
                System.arraycopy(this.flags, this.relativeReadIndex, flags, 0, n3);
                System.arraycopy(this.sizes, this.relativeReadIndex, sizes, 0, n3);
                System.arraycopy(this.encryptionKeys, this.relativeReadIndex, encryptionKeys, 0, n3);
                final int relativeReadIndex = this.relativeReadIndex;
                System.arraycopy(this.offsets, 0, offsets, n3, relativeReadIndex);
                System.arraycopy(this.timesUs, 0, timesUs, n3, relativeReadIndex);
                System.arraycopy(this.flags, 0, flags, n3, relativeReadIndex);
                System.arraycopy(this.sizes, 0, sizes, n3, relativeReadIndex);
                System.arraycopy(this.encryptionKeys, 0, encryptionKeys, n3, relativeReadIndex);
                this.offsets = offsets;
                this.timesUs = timesUs;
                this.flags = flags;
                this.sizes = sizes;
                this.encryptionKeys = encryptionKeys;
                this.relativeReadIndex = 0;
                this.relativeWriteIndex = this.capacity;
                this.queueSize = this.capacity;
                this.capacity = capacity;
            }
            else {
                ++this.relativeWriteIndex;
                if (this.relativeWriteIndex == this.capacity) {
                    this.relativeWriteIndex = 0;
                }
            }
        }
    }
    
    public long discardUpstreamSamples(int n) {
        n = this.getWriteIndex() - n;
        Assertions.checkArgument(n >= 0 && n <= this.queueSize);
        if (n != 0) {
            this.queueSize -= n;
            this.relativeWriteIndex = (this.relativeWriteIndex + this.capacity - n) % this.capacity;
            return this.offsets[this.relativeWriteIndex];
        }
        if (this.absoluteReadIndex == 0) {
            return 0L;
        }
        if (this.relativeWriteIndex == 0) {
            n = this.capacity;
        }
        else {
            n = this.relativeWriteIndex;
        }
        --n;
        return this.sizes[n] + this.offsets[n];
    }
    
    public int getReadIndex() {
        return this.absoluteReadIndex;
    }
    
    public int getWriteIndex() {
        return this.absoluteReadIndex + this.queueSize;
    }
    
    public long moveToNextSample() {
        synchronized (this) {
            --this.queueSize;
            final int n = this.relativeReadIndex++;
            ++this.absoluteReadIndex;
            if (this.relativeReadIndex == this.capacity) {
                this.relativeReadIndex = 0;
            }
            long n2;
            if (this.queueSize > 0) {
                n2 = this.offsets[this.relativeReadIndex];
            }
            else {
                n2 = this.offsets[n] + this.sizes[n];
            }
            return n2;
        }
    }
    
    public boolean peekSample(final SampleHolder sampleHolder, final RollingSampleBuffer$SampleExtrasHolder rollingSampleBuffer$SampleExtrasHolder) {
        synchronized (this) {
            boolean b;
            if (this.queueSize == 0) {
                b = false;
            }
            else {
                sampleHolder.timeUs = this.timesUs[this.relativeReadIndex];
                sampleHolder.size = this.sizes[this.relativeReadIndex];
                sampleHolder.flags = this.flags[this.relativeReadIndex];
                rollingSampleBuffer$SampleExtrasHolder.offset = this.offsets[this.relativeReadIndex];
                rollingSampleBuffer$SampleExtrasHolder.encryptionKeyId = this.encryptionKeys[this.relativeReadIndex];
                b = true;
            }
            return b;
        }
    }
    
    public long skipToKeyframeBefore(final long n) {
        final long n2 = -1L;
        // monitorenter(this)
        long n3 = n2;
        try {
            if (this.queueSize != 0) {
                if (n < this.timesUs[this.relativeReadIndex]) {
                    n3 = n2;
                }
                else {
                    int n4;
                    if (this.relativeWriteIndex == 0) {
                        n4 = this.capacity;
                    }
                    else {
                        n4 = this.relativeWriteIndex;
                    }
                    n3 = n2;
                    if (n <= this.timesUs[n4 - 1]) {
                        int n5 = 0;
                        int relativeReadIndex = this.relativeReadIndex;
                        int n6 = -1;
                        while (relativeReadIndex != this.relativeWriteIndex && this.timesUs[relativeReadIndex] <= n) {
                            if ((this.flags[relativeReadIndex] & 0x1) != 0x0) {
                                n6 = n5;
                            }
                            relativeReadIndex = (relativeReadIndex + 1) % this.capacity;
                            ++n5;
                        }
                        n3 = n2;
                        if (n6 != -1) {
                            this.queueSize -= n6;
                            this.relativeReadIndex = (this.relativeReadIndex + n6) % this.capacity;
                            this.absoluteReadIndex += n6;
                            n3 = this.offsets[this.relativeReadIndex];
                        }
                    }
                }
            }
            return n3;
        }
        finally {
        }
        // monitorexit(this)
    }
}
