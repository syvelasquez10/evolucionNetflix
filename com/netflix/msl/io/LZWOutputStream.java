// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.io;

import java.util.Arrays;
import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.io.ByteArrayOutputStream;
import java.util.LinkedList;
import java.util.Map;
import java.io.OutputStream;

public class LZWOutputStream extends OutputStream
{
    private static final int BYTE_RANGE = 256;
    private static final Map<LZWOutputStream$Key, Integer> INITIAL_DICTIONARY;
    private static final int MAX_BUFFER_SIZE = 100;
    private int bits;
    private final LinkedList<LZWOutputStream$Code> buffer;
    private boolean closed;
    private final Map<LZWOutputStream$Key, Integer> dictionary;
    private boolean finish;
    private final OutputStream out;
    private final ByteArrayOutputStream symbols;
    
    static {
        INITIAL_DICTIONARY = new HashMap<LZWOutputStream$Key, Integer>(256);
        for (int i = 0; i < 256; ++i) {
            LZWOutputStream.INITIAL_DICTIONARY.put(new LZWOutputStream$Key(new byte[] { (byte)i }), i);
        }
    }
    
    public LZWOutputStream(final OutputStream out) {
        this.dictionary = new HashMap<LZWOutputStream$Key, Integer>(LZWOutputStream.INITIAL_DICTIONARY);
        this.symbols = new ByteArrayOutputStream();
        this.bits = 8;
        this.buffer = new LinkedList<LZWOutputStream$Code>();
        this.finish = false;
        this.closed = false;
        this.out = out;
    }
    
    private static byte[] codesToBytes(final LinkedList<LZWOutputStream$Code> list) {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int n = 8;
        int n2 = 0;
        while (list.size() > 0) {
            final LZWOutputStream$Code lzwOutputStream$Code = list.remove();
            int bits = lzwOutputStream$Code.bits;
            int n3 = n2;
            int n4 = n;
            while (true) {
                n = n4;
                n2 = n3;
                if (bits <= 0) {
                    break;
                }
                if (bits > n4) {
                    byteArrayOutputStream.write((byte)(n3 | (lzwOutputStream$Code.value >>> bits - n4 & 0xFF)));
                    bits -= n4;
                    n3 = 0;
                    n4 = 8;
                }
                else {
                    if (bits > n4) {
                        continue;
                    }
                    final byte b = (byte)(n3 | ((lzwOutputStream$Code.value << n4 - bits & 0xFF) >>> 8 - n4 & 0xFF));
                    final int n5 = n4 -= bits;
                    n3 = b;
                    if (n5 == 0) {
                        byteArrayOutputStream.write(b);
                        n4 = 8;
                        n3 = 0;
                    }
                    bits = 0;
                }
            }
        }
        if (n < 8) {
            byteArrayOutputStream.write(n2);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    @Override
    public void close() {
        if (!this.closed) {
            this.finish();
            this.out.close();
            this.closed = true;
        }
    }
    
    @Override
    protected void finalize() {
        this.close();
        super.finalize();
    }
    
    public void finish() {
        if (!this.finish) {
            this.finish = true;
            if (this.symbols.size() > 0) {
                this.buffer.add(new LZWOutputStream$Code(this.dictionary.get(new LZWOutputStream$Key(this.symbols.toByteArray())), this.bits));
                this.flush();
            }
        }
    }
    
    @Override
    public void flush() {
        if (this.buffer.isEmpty()) {
            return;
        }
        final LinkedList<LZWOutputStream$Code> list = new LinkedList<LZWOutputStream$Code>();
        int n = 0;
        while (this.buffer.size() > 0) {
            final LZWOutputStream$Code lzwOutputStream$Code = this.buffer.remove();
            list.add(lzwOutputStream$Code);
            if ((n += lzwOutputStream$Code.bits) % 8 == 0) {
                this.out.write(codesToBytes(list));
                list.clear();
                n = 0;
            }
        }
        if (this.finish) {
            this.out.write(codesToBytes(list));
            return;
        }
        this.buffer.addAll(list);
    }
    
    @Override
    public void write(final int n) {
        this.write(new byte[] { (byte)(n & 0xFF) }, 0, 1);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        if (this.closed) {
            throw new IOException("Output stream is closed.");
        }
        if (n < 0) {
            throw new IndexOutOfBoundsException("Offset cannot be negative.");
        }
        if (n2 < 0) {
            throw new IndexOutOfBoundsException("Length cannot be negative.");
        }
        if (n + n2 > array.length) {
            throw new IndexOutOfBoundsException("Offset plus length cannot be greater than the array length.");
        }
        for (int i = n; i < n + n2; ++i) {
            final byte b = array[i];
            this.symbols.write(b);
            final byte[] byteArray = this.symbols.toByteArray();
            final LZWOutputStream$Key lzwOutputStream$Key = new LZWOutputStream$Key(byteArray);
            if (this.dictionary.get(lzwOutputStream$Key) == null) {
                this.buffer.add(new LZWOutputStream$Code(this.dictionary.get(new LZWOutputStream$Key(Arrays.copyOf(byteArray, byteArray.length - 1))), this.bits));
                final int size = this.dictionary.size();
                if (size >> this.bits != 0) {
                    ++this.bits;
                }
                this.dictionary.put(lzwOutputStream$Key, size);
                this.symbols.reset();
                this.symbols.write(b);
                if (this.buffer.size() > 100) {
                    this.flush();
                }
            }
        }
    }
}
