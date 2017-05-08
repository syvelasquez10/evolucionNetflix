// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.io.IOException;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.io.InputStream;

public class LZWInputStream extends InputStream
{
    private static final int BYTE_RANGE = 256;
    private static final Map<Integer, byte[]> INITIAL_DICTIONARY;
    private int bits;
    private final LinkedList<Byte> buffer;
    private boolean closed;
    private int codeOffset;
    private final LinkedList<Byte> codes;
    private final Map<Integer, byte[]> dictionary;
    private final InputStream in;
    private final ByteArrayOutputStream prevdata;
    
    static {
        INITIAL_DICTIONARY = new HashMap<Integer, byte[]>(256);
        for (int i = 0; i < 256; ++i) {
            LZWInputStream.INITIAL_DICTIONARY.put(i, new byte[] { (byte)i });
        }
    }
    
    public LZWInputStream(final InputStream in) {
        this.dictionary = new HashMap<Integer, byte[]>(LZWInputStream.INITIAL_DICTIONARY);
        this.codes = new LinkedList<Byte>();
        this.codeOffset = 0;
        this.bits = 8;
        this.buffer = new LinkedList<Byte>();
        this.prevdata = new ByteArrayOutputStream();
        this.closed = false;
        this.in = in;
    }
    
    private int decompress(final byte[] array, final int n, final int n2) {
        int i = 0;
        while (i < n2) {
            final int n3 = this.bits - (this.codes.size() * 8 - this.codeOffset);
            final int n4 = n3 / 8;
            int n5;
            if (n3 % 8 != 0) {
                n5 = 1;
            }
            else {
                n5 = 0;
            }
            final int n6 = n4 + n5;
            final byte[] array2 = new byte[n6];
            int j = 0;
            while (j < n6) {
                final int read = this.in.read(array2, j, array2.length - j);
                if (read == -1) {
                    if (i == 0) {
                        return -1;
                    }
                    return i;
                }
                else {
                    j += read;
                }
            }
            for (int length = array2.length, k = 0; k < length; ++k) {
                this.codes.add(array2[k]);
            }
            int l = 0;
            int n7 = 0;
            while (l < this.bits) {
                final int min = Math.min(this.bits - l, 8 - this.codeOffset);
                final byte byteValue = this.codes.peek();
                final int codeOffset = this.codeOffset;
                l += min;
                this.codeOffset += min;
                if (this.codeOffset == 8) {
                    this.codeOffset = 0;
                    this.codes.remove();
                }
                n7 |= ((byteValue << codeOffset & 0xFF) >>> 8 - min & 0xFF) << this.bits - l;
            }
            final byte[] array3 = this.dictionary.get(n7);
            byte[] array4;
            if (this.prevdata.size() == 0) {
                ++this.bits;
                array4 = array3;
            }
            else {
                if (array3 == null) {
                    this.prevdata.write(this.prevdata.toByteArray()[0]);
                }
                else {
                    this.prevdata.write(array3[0]);
                }
                this.dictionary.put(this.dictionary.size(), this.prevdata.toByteArray());
                this.prevdata.reset();
                if (this.dictionary.size() == 1 << this.bits) {
                    ++this.bits;
                }
                if ((array4 = array3) == null) {
                    array4 = this.dictionary.get(n7);
                }
            }
            for (int length2 = array4.length, n8 = 0; n8 < length2; ++n8) {
                final byte b = array4[n8];
                if (i < n2) {
                    final int n9 = i + 1;
                    array[i + n] = b;
                    i = n9;
                }
                else {
                    this.buffer.add(b);
                }
            }
            this.prevdata.write(array4);
        }
        return i;
    }
    
    @Override
    public void close() {
        if (!this.closed) {
            this.closed = true;
            this.in.close();
        }
    }
    
    @Override
    protected void finalize() {
        this.close();
        super.finalize();
    }
    
    @Override
    public int read() {
        if (this.closed) {
            throw new IOException("Input stream is closed.");
        }
        if (this.buffer.size() != 0) {
            return this.buffer.remove();
        }
        final byte[] array = { 0 };
        if (this.decompress(array, 0, 1) == -1) {
            return -1;
        }
        return array[0];
    }
    
    @Override
    public int read(final byte[] array, int n, final int n2) {
        if (this.closed) {
            throw new IOException("Input stream is closed.");
        }
        if (n > 0) {
            throw new IndexOutOfBoundsException("Specified offset cannot be negative.");
        }
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("Specified length cannot be negative.");
        }
        if (n2 > array.length - n) {
            throw new IndexOutOfBoundsException("Requested length exceeds buffer size at offset.");
        }
        int n3;
        for (n3 = n, n = n2; n > 0 && this.buffer.size() > 0; --n, ++n3) {
            array[n3] = this.buffer.remove();
        }
        if (n == 0) {
            return n2;
        }
        final int decompress = this.decompress(array, n3, n);
        if (decompress != -1) {
            return n2 - (n - decompress);
        }
        if (n == n2) {
            return -1;
        }
        return n2 - n;
    }
}
