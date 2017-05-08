// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

public final class ParsableBitArray
{
    private int bitOffset;
    private int byteLimit;
    private int byteOffset;
    public byte[] data;
    
    public ParsableBitArray() {
    }
    
    public ParsableBitArray(final byte[] array) {
        this(array, array.length);
    }
    
    public ParsableBitArray(final byte[] data, final int byteLimit) {
        this.data = data;
        this.byteLimit = byteLimit;
    }
    
    private void assertValidOffset() {
        Assertions.checkState(this.byteOffset >= 0 && this.bitOffset >= 0 && this.bitOffset < 8 && (this.byteOffset < this.byteLimit || (this.byteOffset == this.byteLimit && this.bitOffset == 0)));
    }
    
    private int readExpGolombCodeNum() {
        int bits = 0;
        int n = 0;
        while (!this.readBit()) {
            ++n;
        }
        if (n > 0) {
            bits = this.readBits(n);
        }
        return (1 << n) - 1 + bits;
    }
    
    public boolean readBit() {
        return this.readBits(1) == 1;
    }
    
    public int readBits(int n) {
        if (n == 0) {
            return 0;
        }
        final int n2 = n / 8;
        int i = 0;
        final int n3 = 0;
        int n4 = n;
        n = n3;
        while (i < n2) {
            int n5;
            if (this.bitOffset != 0) {
                n5 = ((this.data[this.byteOffset] & 0xFF) << this.bitOffset | (this.data[this.byteOffset + 1] & 0xFF) >>> 8 - this.bitOffset);
            }
            else {
                n5 = this.data[this.byteOffset];
            }
            n4 -= 8;
            n |= (n5 & 0xFF) << n4;
            ++this.byteOffset;
            ++i;
        }
        if (n4 > 0) {
            final int n6 = this.bitOffset + n4;
            final byte b = (byte)(255 >> 8 - n4);
            if (n6 > 8) {
                n |= (b & ((this.data[this.byteOffset] & 0xFF) << n6 - 8 | (this.data[this.byteOffset + 1] & 0xFF) >> 16 - n6));
                ++this.byteOffset;
            }
            else {
                final int n7 = n |= (b & (this.data[this.byteOffset] & 0xFF) >> 8 - n6);
                if (n6 == 8) {
                    ++this.byteOffset;
                    n = n7;
                }
            }
            this.bitOffset = n6 % 8;
        }
        this.assertValidOffset();
        return n;
    }
    
    public int readSignedExpGolombCodedInt() {
        final int expGolombCodeNum = this.readExpGolombCodeNum();
        int n;
        if (expGolombCodeNum % 2 == 0) {
            n = -1;
        }
        else {
            n = 1;
        }
        return n * ((expGolombCodeNum + 1) / 2);
    }
    
    public int readUnsignedExpGolombCodedInt() {
        return this.readExpGolombCodeNum();
    }
    
    public void setPosition(final int n) {
        this.byteOffset = n / 8;
        this.bitOffset = n - this.byteOffset * 8;
        this.assertValidOffset();
    }
    
    public void skipBits(final int n) {
        this.byteOffset += n / 8;
        this.bitOffset += n % 8;
        if (this.bitOffset > 7) {
            ++this.byteOffset;
            this.bitOffset -= 8;
        }
        this.assertValidOffset();
    }
}
