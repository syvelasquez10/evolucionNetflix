// 
// Decompiled by Procyon v0.5.30
// 

package com.fasterxml.jackson.core.util;

public class BufferRecycler
{
    private static final int[] BYTE_BUFFER_LENGTHS;
    private static final int[] CHAR_BUFFER_LENGTHS;
    protected final byte[][] _byteBuffers;
    protected final char[][] _charBuffers;
    
    static {
        BYTE_BUFFER_LENGTHS = new int[] { 8000, 8000, 2000, 2000 };
        CHAR_BUFFER_LENGTHS = new int[] { 4000, 4000, 200, 200 };
    }
    
    public BufferRecycler() {
        this(4, 4);
    }
    
    protected BufferRecycler(final int n, final int n2) {
        this._byteBuffers = new byte[n][];
        this._charBuffers = new char[n2][];
    }
    
    public final char[] allocCharBuffer(final int n) {
        return this.allocCharBuffer(n, 0);
    }
    
    public char[] allocCharBuffer(final int n, final int n2) {
        final int charBufferLength = this.charBufferLength(n);
        int n3 = n2;
        if (n2 < charBufferLength) {
            n3 = charBufferLength;
        }
        final char[] array = this._charBuffers[n];
        if (array == null || array.length < n3) {
            return this.calloc(n3);
        }
        this._charBuffers[n] = null;
        return array;
    }
    
    protected char[] calloc(final int n) {
        return new char[n];
    }
    
    protected int charBufferLength(final int n) {
        return BufferRecycler.CHAR_BUFFER_LENGTHS[n];
    }
    
    public void releaseCharBuffer(final int n, final char[] array) {
        this._charBuffers[n] = array;
    }
}
