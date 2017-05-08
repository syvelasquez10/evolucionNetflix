// 
// Decompiled by Procyon v0.5.30
// 

package org.java_websocket.util;

import java.io.UnsupportedEncodingException;
import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

public class Base64$OutputStream extends FilterOutputStream
{
    private byte[] b4;
    private boolean breakLines;
    private byte[] buffer;
    private int bufferLength;
    private byte[] decodabet;
    private boolean encode;
    private int lineLength;
    private int options;
    private int position;
    private boolean suspendEncoding;
    
    public Base64$OutputStream(final OutputStream outputStream, final int options) {
        final boolean b = true;
        super(outputStream);
        this.breakLines = ((options & 0x8) != 0x0);
        this.encode = ((options & 0x1) != 0x0 && b);
        int bufferLength;
        if (this.encode) {
            bufferLength = 3;
        }
        else {
            bufferLength = 4;
        }
        this.bufferLength = bufferLength;
        this.buffer = new byte[this.bufferLength];
        this.position = 0;
        this.lineLength = 0;
        this.suspendEncoding = false;
        this.b4 = new byte[4];
        this.options = options;
        this.decodabet = getDecodabet(options);
    }
    
    @Override
    public void close() {
        this.flushBase64();
        super.close();
        this.buffer = null;
        this.out = null;
    }
    
    public void flushBase64() {
        if (this.position > 0) {
            if (!this.encode) {
                throw new IOException("Base64 input not properly padded.");
            }
            this.out.write(encode3to4(this.b4, this.buffer, this.position, this.options));
            this.position = 0;
        }
    }
    
    @Override
    public void write(int access$200) {
        if (this.suspendEncoding) {
            this.out.write(access$200);
        }
        else if (this.encode) {
            this.buffer[this.position++] = (byte)access$200;
            if (this.position >= this.bufferLength) {
                this.out.write(encode3to4(this.b4, this.buffer, this.bufferLength, this.options));
                this.lineLength += 4;
                if (this.breakLines && this.lineLength >= 76) {
                    this.out.write(10);
                    this.lineLength = 0;
                }
                this.position = 0;
            }
        }
        else if (this.decodabet[access$200 & 0x7F] > -5) {
            this.buffer[this.position++] = (byte)access$200;
            if (this.position >= this.bufferLength) {
                access$200 = decode4to3(this.buffer, 0, this.b4, 0, this.options);
                this.out.write(this.b4, 0, access$200);
                this.position = 0;
            }
        }
        else if (this.decodabet[access$200 & 0x7F] != -5) {
            throw new IOException("Invalid character in Base64 data.");
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        if (this.suspendEncoding) {
            this.out.write(array, n, n2);
        }
        else {
            for (int i = 0; i < n2; ++i) {
                this.write(array[n + i]);
            }
        }
    }
}
