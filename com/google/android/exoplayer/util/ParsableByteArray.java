// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.util;

public final class ParsableByteArray
{
    public byte[] data;
    private int limit;
    private int position;
    
    public ParsableByteArray() {
    }
    
    public ParsableByteArray(final int n) {
        this.data = new byte[n];
        this.limit = this.data.length;
    }
    
    public ParsableByteArray(final byte[] data) {
        this.data = data;
        this.limit = data.length;
    }
    
    public int bytesLeft() {
        return this.limit - this.position;
    }
    
    public int capacity() {
        if (this.data == null) {
            return 0;
        }
        return this.data.length;
    }
    
    public int getPosition() {
        return this.position;
    }
    
    public int limit() {
        return this.limit;
    }
    
    public void readBytes(final byte[] array, final int n, final int n2) {
        System.arraycopy(this.data, this.position, array, n, n2);
        this.position += n2;
    }
    
    public double readDouble() {
        return Double.longBitsToDouble(this.readLong());
    }
    
    public int readInt() {
        return (this.data[this.position++] & 0xFF) << 24 | (this.data[this.position++] & 0xFF) << 16 | (this.data[this.position++] & 0xFF) << 8 | (this.data[this.position++] & 0xFF);
    }
    
    public long readLittleEndianUnsignedInt() {
        return (this.data[this.position++] & 0xFFL) | (this.data[this.position++] & 0xFFL) << 8 | (this.data[this.position++] & 0xFFL) << 16 | (this.data[this.position++] & 0xFFL) << 24;
    }
    
    public int readLittleEndianUnsignedShort() {
        return (this.data[this.position++] & 0xFF) | (this.data[this.position++] & 0xFF) << 8;
    }
    
    public long readLong() {
        return (this.data[this.position++] & 0xFFL) << 56 | (this.data[this.position++] & 0xFFL) << 48 | (this.data[this.position++] & 0xFFL) << 40 | (this.data[this.position++] & 0xFFL) << 32 | (this.data[this.position++] & 0xFFL) << 24 | (this.data[this.position++] & 0xFFL) << 16 | (this.data[this.position++] & 0xFFL) << 8 | (this.data[this.position++] & 0xFFL);
    }
    
    public short readShort() {
        return (short)((this.data[this.position++] & 0xFF) << 8 | (this.data[this.position++] & 0xFF));
    }
    
    public int readUnsignedByte() {
        return this.data[this.position++] & 0xFF;
    }
    
    public int readUnsignedFixedPoint1616() {
        final byte b = this.data[this.position++];
        final byte b2 = this.data[this.position++];
        this.position += 2;
        return (b & 0xFF) << 8 | (b2 & 0xFF);
    }
    
    public long readUnsignedInt() {
        return (this.data[this.position++] & 0xFFL) << 24 | (this.data[this.position++] & 0xFFL) << 16 | (this.data[this.position++] & 0xFFL) << 8 | (this.data[this.position++] & 0xFFL);
    }
    
    public int readUnsignedIntToInt() {
        final int int1 = this.readInt();
        if (int1 < 0) {
            throw new IllegalStateException("Top bit not zero: " + int1);
        }
        return int1;
    }
    
    public long readUnsignedLongToLong() {
        final long long1 = this.readLong();
        if (long1 < 0L) {
            throw new IllegalStateException("Top bit not zero: " + long1);
        }
        return long1;
    }
    
    public int readUnsignedShort() {
        return (this.data[this.position++] & 0xFF) << 8 | (this.data[this.position++] & 0xFF);
    }
    
    public void reset() {
        this.position = 0;
        this.limit = 0;
    }
    
    public void reset(final byte[] data, final int limit) {
        this.data = data;
        this.limit = limit;
        this.position = 0;
    }
    
    public void setLimit(final int limit) {
        Assertions.checkArgument(limit >= 0 && limit <= this.data.length);
        this.limit = limit;
    }
    
    public void setPosition(final int position) {
        Assertions.checkArgument(position >= 0 && position <= this.limit);
        this.position = position;
    }
    
    public void skipBytes(final int n) {
        this.setPosition(this.position + n);
    }
}
