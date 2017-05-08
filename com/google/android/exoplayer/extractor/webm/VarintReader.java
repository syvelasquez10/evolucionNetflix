// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.extractor.webm;

import com.google.android.exoplayer.extractor.ExtractorInput;

final class VarintReader
{
    private static final long[] VARINT_LENGTH_MASKS;
    private int length;
    private final byte[] scratch;
    private int state;
    
    static {
        VARINT_LENGTH_MASKS = new long[] { 128L, 64L, 32L, 16L, 8L, 4L, 2L, 1L };
    }
    
    public VarintReader() {
        this.scratch = new byte[8];
    }
    
    public static long assembleVarint(final byte[] array, final int n, final boolean b) {
        long n3;
        final long n2 = n3 = (array[0] & 0xFFL);
        if (b) {
            n3 = (n2 & ~VarintReader.VARINT_LENGTH_MASKS[n - 1]);
        }
        for (int i = 1; i < n; ++i) {
            n3 = (n3 << 8 | (array[i] & 0xFFL));
        }
        return n3;
    }
    
    public static int parseUnsignedVarintLength(final int n) {
        for (int i = 0; i < VarintReader.VARINT_LENGTH_MASKS.length; ++i) {
            if ((VarintReader.VARINT_LENGTH_MASKS[i] & n) != 0x0L) {
                return i + 1;
            }
        }
        return -1;
    }
    
    public int getLastLength() {
        return this.length;
    }
    
    public long readUnsignedVarint(final ExtractorInput extractorInput, final boolean b, final boolean b2, final int n) {
        if (this.state == 0) {
            if (!extractorInput.readFully(this.scratch, 0, 1, b)) {
                return -1L;
            }
            this.length = parseUnsignedVarintLength(this.scratch[0] & 0xFF);
            if (this.length == -1) {
                throw new IllegalStateException("No valid varint length mask found");
            }
            this.state = 1;
        }
        if (this.length > n) {
            this.state = 0;
            return -2L;
        }
        if (this.length != 1) {
            extractorInput.readFully(this.scratch, 1, this.length - 1);
        }
        this.state = 0;
        return assembleVarint(this.scratch, this.length, b2);
    }
    
    public void reset() {
        this.state = 0;
        this.length = 0;
    }
}
