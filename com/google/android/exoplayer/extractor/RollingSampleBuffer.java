// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor;

import com.google.android.exoplayer.upstream.DataSource;
import java.io.EOFException;
import com.google.android.exoplayer.SampleHolder;
import java.nio.ByteBuffer;
import com.google.android.exoplayer.util.ParsableByteArray;
import com.google.android.exoplayer.upstream.Allocation;
import java.util.concurrent.LinkedBlockingDeque;
import com.google.android.exoplayer.upstream.Allocator;

final class RollingSampleBuffer
{
    private final int allocationLength;
    private final Allocator allocator;
    private final LinkedBlockingDeque<Allocation> dataQueue;
    private final RollingSampleBuffer$SampleExtrasHolder extrasHolder;
    private final RollingSampleBuffer$InfoQueue infoQueue;
    private Allocation lastAllocation;
    private int lastAllocationOffset;
    private final ParsableByteArray scratch;
    private long totalBytesDropped;
    private long totalBytesWritten;
    
    public RollingSampleBuffer(final Allocator allocator) {
        this.allocator = allocator;
        this.allocationLength = allocator.getIndividualAllocationLength();
        this.infoQueue = new RollingSampleBuffer$InfoQueue();
        this.dataQueue = new LinkedBlockingDeque<Allocation>();
        this.extrasHolder = new RollingSampleBuffer$SampleExtrasHolder(null);
        this.scratch = new ParsableByteArray(32);
        this.lastAllocationOffset = this.allocationLength;
    }
    
    private void dropDownstreamTo(final long n) {
        for (int n2 = (int)(n - this.totalBytesDropped) / this.allocationLength, i = 0; i < n2; ++i) {
            this.allocator.release(this.dataQueue.remove());
            this.totalBytesDropped += this.allocationLength;
        }
    }
    
    private void dropUpstreamFrom(final long n) {
        final int n2 = (int)(n - this.totalBytesDropped);
        final int n3 = n2 / this.allocationLength;
        final int n4 = n2 % this.allocationLength;
        int n5 = this.dataQueue.size() - n3 - 1;
        if (n4 == 0) {
            ++n5;
        }
        for (int i = 0; i < n5; ++i) {
            this.allocator.release(this.dataQueue.removeLast());
        }
        this.lastAllocation = this.dataQueue.peekLast();
        int allocationLength;
        if (n4 == 0) {
            allocationLength = this.allocationLength;
        }
        else {
            allocationLength = n4;
        }
        this.lastAllocationOffset = allocationLength;
    }
    
    private static void ensureCapacity(final ParsableByteArray parsableByteArray, final int n) {
        if (parsableByteArray.limit() < n) {
            parsableByteArray.reset(new byte[n], n);
        }
    }
    
    private int prepareForAppend(final int n) {
        if (this.lastAllocationOffset == this.allocationLength) {
            this.lastAllocationOffset = 0;
            this.lastAllocation = this.allocator.allocate();
            this.dataQueue.add(this.lastAllocation);
        }
        return Math.min(n, this.allocationLength - this.lastAllocationOffset);
    }
    
    private void readData(long n, final ByteBuffer byteBuffer, int i) {
        while (i > 0) {
            this.dropDownstreamTo(n);
            final int n2 = (int)(n - this.totalBytesDropped);
            final int min = Math.min(i, this.allocationLength - n2);
            final Allocation allocation = this.dataQueue.peek();
            byteBuffer.put(allocation.data, allocation.translateOffset(n2), min);
            n += min;
            i -= min;
        }
    }
    
    private void readData(long n, final byte[] array, final int n2) {
        int min;
        for (int i = 0; i < n2; i += min) {
            this.dropDownstreamTo(n);
            final int n3 = (int)(n - this.totalBytesDropped);
            min = Math.min(n2 - i, this.allocationLength - n3);
            final Allocation allocation = this.dataQueue.peek();
            System.arraycopy(allocation.data, allocation.translateOffset(n3), array, i, min);
            n += min;
        }
    }
    
    private void readEncryptionData(final SampleHolder sampleHolder, final RollingSampleBuffer$SampleExtrasHolder rollingSampleBuffer$SampleExtrasHolder) {
        final int n = 0;
        final long offset = rollingSampleBuffer$SampleExtrasHolder.offset;
        this.readData(offset, this.scratch.data, 1);
        final long n2 = 1L + offset;
        final byte b = this.scratch.data[0];
        boolean b2;
        if ((b & 0x80) != 0x0) {
            b2 = true;
        }
        else {
            b2 = false;
        }
        final byte b3 = (byte)(b & 0x7F);
        if (sampleHolder.cryptoInfo.iv == null) {
            sampleHolder.cryptoInfo.iv = new byte[16];
        }
        this.readData(n2, sampleHolder.cryptoInfo.iv, b3);
        long n3 = n2 + b3;
        int unsignedShort;
        if (b2) {
            this.readData(n3, this.scratch.data, 2);
            this.scratch.setPosition(0);
            unsignedShort = this.scratch.readUnsignedShort();
            n3 += 2L;
        }
        else {
            unsignedShort = 1;
        }
        final int[] numBytesOfClearData = sampleHolder.cryptoInfo.numBytesOfClearData;
        int[] array = null;
        Label_0177: {
            if (numBytesOfClearData != null) {
                array = numBytesOfClearData;
                if (numBytesOfClearData.length >= unsignedShort) {
                    break Label_0177;
                }
            }
            array = new int[unsignedShort];
        }
        final int[] numBytesOfEncryptedData = sampleHolder.cryptoInfo.numBytesOfEncryptedData;
        int[] array2 = null;
        Label_0209: {
            if (numBytesOfEncryptedData != null) {
                array2 = numBytesOfEncryptedData;
                if (numBytesOfEncryptedData.length >= unsignedShort) {
                    break Label_0209;
                }
            }
            array2 = new int[unsignedShort];
        }
        if (b2) {
            final int n4 = unsignedShort * 6;
            ensureCapacity(this.scratch, n4);
            this.readData(n3, this.scratch.data, n4);
            final long n5 = n3 + n4;
            this.scratch.setPosition(0);
            int n6 = n;
            while (true) {
                n3 = n5;
                if (n6 >= unsignedShort) {
                    break;
                }
                array[n6] = this.scratch.readUnsignedShort();
                array2[n6] = this.scratch.readUnsignedIntToInt();
                ++n6;
            }
        }
        else {
            array2[array[0] = 0] = sampleHolder.size - (int)(n3 - rollingSampleBuffer$SampleExtrasHolder.offset);
        }
        sampleHolder.cryptoInfo.set(unsignedShort, array, array2, rollingSampleBuffer$SampleExtrasHolder.encryptionKeyId, sampleHolder.cryptoInfo.iv, 1);
        final int n7 = (int)(n3 - rollingSampleBuffer$SampleExtrasHolder.offset);
        rollingSampleBuffer$SampleExtrasHolder.offset += n7;
        sampleHolder.size -= n7;
    }
    
    public int appendData(final ExtractorInput extractorInput, int n, final boolean b) {
        n = this.prepareForAppend(n);
        n = extractorInput.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), n);
        if (n != -1) {
            this.lastAllocationOffset += n;
            this.totalBytesWritten += n;
            return n;
        }
        if (b) {
            return -1;
        }
        throw new EOFException();
    }
    
    public int appendData(final DataSource dataSource, int n, final boolean b) {
        n = this.prepareForAppend(n);
        n = dataSource.read(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), n);
        if (n != -1) {
            this.lastAllocationOffset += n;
            this.totalBytesWritten += n;
            return n;
        }
        if (b) {
            return -1;
        }
        throw new EOFException();
    }
    
    public void appendData(final ParsableByteArray parsableByteArray, int i) {
        while (i > 0) {
            final int prepareForAppend = this.prepareForAppend(i);
            parsableByteArray.readBytes(this.lastAllocation.data, this.lastAllocation.translateOffset(this.lastAllocationOffset), prepareForAppend);
            this.lastAllocationOffset += prepareForAppend;
            this.totalBytesWritten += prepareForAppend;
            i -= prepareForAppend;
        }
    }
    
    public void clear() {
        this.infoQueue.clear();
        this.allocator.release(this.dataQueue.toArray(new Allocation[this.dataQueue.size()]));
        this.dataQueue.clear();
        this.totalBytesDropped = 0L;
        this.totalBytesWritten = 0L;
        this.lastAllocation = null;
        this.lastAllocationOffset = this.allocationLength;
    }
    
    public void commitSample(final long n, final int n2, final long n3, final int n4, final byte[] array) {
        this.infoQueue.commitSample(n, n2, n3, n4, array);
    }
    
    public void discardUpstreamSamples(final int n) {
        this.dropUpstreamFrom(this.totalBytesWritten = this.infoQueue.discardUpstreamSamples(n));
    }
    
    public int getReadIndex() {
        return this.infoQueue.getReadIndex();
    }
    
    public int getWriteIndex() {
        return this.infoQueue.getWriteIndex();
    }
    
    public long getWritePosition() {
        return this.totalBytesWritten;
    }
    
    public boolean peekSample(final SampleHolder sampleHolder) {
        return this.infoQueue.peekSample(sampleHolder, this.extrasHolder);
    }
    
    public boolean readSample(final SampleHolder sampleHolder) {
        if (!this.infoQueue.peekSample(sampleHolder, this.extrasHolder)) {
            return false;
        }
        if (sampleHolder.isEncrypted()) {
            this.readEncryptionData(sampleHolder, this.extrasHolder);
        }
        sampleHolder.ensureSpaceForWrite(sampleHolder.size);
        this.readData(this.extrasHolder.offset, sampleHolder.data, sampleHolder.size);
        this.dropDownstreamTo(this.infoQueue.moveToNextSample());
        return true;
    }
    
    public void skipSample() {
        this.dropDownstreamTo(this.infoQueue.moveToNextSample());
    }
    
    public boolean skipToKeyframeBefore(long skipToKeyframeBefore) {
        skipToKeyframeBefore = this.infoQueue.skipToKeyframeBefore(skipToKeyframeBefore);
        if (skipToKeyframeBefore == -1L) {
            return false;
        }
        this.dropDownstreamTo(skipToKeyframeBefore);
        return true;
    }
}
