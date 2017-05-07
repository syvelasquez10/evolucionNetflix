// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.mediaclient.util;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.InputStream;
import java.io.FilterInputStream;

public class Base64$Base64InputStream extends FilterInputStream
{
    private boolean breakLines;
    private byte[] buffer;
    private int bufferLength;
    private byte[] decodabet;
    private boolean encode;
    private int lineLength;
    private int numSigBytes;
    private int options;
    private int position;
    
    public Base64$Base64InputStream(final InputStream inputStream) {
        this(inputStream, 0);
    }
    
    public Base64$Base64InputStream(final InputStream inputStream, final int options) {
        final boolean b = true;
        super(inputStream);
        this.options = options;
        this.breakLines = ((options & 0x8) > 0);
        this.encode = ((options & 0x1) > 0 && b);
        int bufferLength;
        if (this.encode) {
            bufferLength = 4;
        }
        else {
            bufferLength = 3;
        }
        this.bufferLength = bufferLength;
        this.buffer = new byte[this.bufferLength];
        this.position = -1;
        this.lineLength = 0;
        this.decodabet = getDecodabet(options);
    }
    
    @Override
    public int read() {
        if (this.position < 0) {
            if (this.encode) {
                final byte[] array = new byte[3];
                int i;
                int n;
                for (i = 0, n = 0; i < 3; ++i, ++n) {
                    final int read = this.in.read();
                    if (read < 0) {
                        break;
                    }
                    array[i] = (byte)read;
                }
                if (n <= 0) {
                    return -1;
                }
                encode3to4(array, 0, n, this.buffer, 0, this.options);
                this.position = 0;
                this.numSigBytes = 4;
            }
            else {
                final byte[] array2 = new byte[4];
                int j;
                for (j = 0; j < 4; ++j) {
                    int read2;
                    do {
                        read2 = this.in.read();
                    } while (read2 >= 0 && this.decodabet[read2 & 0x7F] <= -5);
                    if (read2 < 0) {
                        break;
                    }
                    array2[j] = (byte)read2;
                }
                if (j == 4) {
                    this.numSigBytes = decode4to3(array2, 0, this.buffer, 0, this.options);
                    this.position = 0;
                }
                else {
                    if (j == 0) {
                        return -1;
                    }
                    throw new IOException("Improperly padded Base64 input.");
                }
            }
        }
        if (this.position < 0) {
            throw new IOException("Error in Base64 code reading stream.");
        }
        if (this.position >= this.numSigBytes) {
            return -1;
        }
        if (this.encode && this.breakLines && this.lineLength >= 76) {
            this.lineLength = 0;
            return 10;
        }
        ++this.lineLength;
        final byte b = this.buffer[this.position++];
        if (this.position >= this.bufferLength) {
            this.position = -1;
        }
        return b & 0xFF;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        int n3 = 0;
        int n4;
        while (true) {
            n4 = n3;
            if (n3 >= n2) {
                break;
            }
            final int read = this.read();
            if (read >= 0) {
                array[n + n3] = (byte)read;
                ++n3;
            }
            else {
                if ((n4 = n3) == 0) {
                    n4 = -1;
                    break;
                }
                break;
            }
        }
        return n4;
    }
}
