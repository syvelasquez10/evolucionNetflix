// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

import java.io.IOException;
import java.io.EOFException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.io.Closeable;

class StrictLineReader implements Closeable
{
    private static final byte CR = 13;
    private static final byte LF = 10;
    private byte[] buf;
    private final Charset charset;
    private int end;
    private final InputStream in;
    private int pos;
    
    public StrictLineReader(final InputStream in, final int n, final Charset charset) {
        if (in == null || charset == null) {
            throw new NullPointerException();
        }
        if (n < 0) {
            throw new IllegalArgumentException("capacity <= 0");
        }
        if (!charset.equals(Util.US_ASCII)) {
            throw new IllegalArgumentException("Unsupported encoding");
        }
        this.in = in;
        this.charset = charset;
        this.buf = new byte[n];
    }
    
    public StrictLineReader(final InputStream inputStream, final Charset charset) {
        this(inputStream, 8192, charset);
    }
    
    private void fillBuf() {
        final int read = this.in.read(this.buf, 0, this.buf.length);
        if (read == -1) {
            throw new EOFException();
        }
        this.pos = 0;
        this.end = read;
    }
    
    @Override
    public void close() {
        synchronized (this.in) {
            if (this.buf != null) {
                this.buf = null;
                this.in.close();
            }
        }
    }
    
    public boolean hasUnterminatedLine() {
        return this.end == -1;
    }
    
    public String readLine() {
        synchronized (this.in) {
            if (this.buf == null) {
                throw new IOException("LineReader is closed");
            }
        }
        if (this.pos >= this.end) {
            this.fillBuf();
        }
    Label_0306_Outer:
        while (true) {
            for (int i = this.pos; i != this.end; ++i) {
                if (this.buf[i] == 10) {
                    int n;
                    if (i != this.pos && this.buf[i - 1] == 13) {
                        n = i - 1;
                    }
                    else {
                        n = i;
                    }
                    final String s = new String(this.buf, this.pos, n - this.pos, this.charset.name());
                    this.pos = i + 1;
                    // monitorexit(inputStream)
                    return s;
                }
            }
            final StrictLineReader$1 strictLineReader$1 = new StrictLineReader$1(this, this.end - this.pos + 80);
            final Throwable t = null;
            while (true) {
                try {
                    int j = 0;
                Block_12:
                    while (true) {
                        strictLineReader$1.write(this.buf, this.pos, this.end - this.pos);
                        this.end = -1;
                        this.fillBuf();
                        for (j = this.pos; j != this.end; ++j) {
                            if (this.buf[j] == 10) {
                                break Block_12;
                            }
                        }
                    }
                    if (j != this.pos) {
                        strictLineReader$1.write(this.buf, this.pos, j - this.pos);
                    }
                    this.pos = j + 1;
                    final String string = strictLineReader$1.toString();
                    while (true) {
                        if (strictLineReader$1 == null) {
                            break Label_0271;
                        }
                        Label_0285: {
                            if (!false) {
                                break Label_0285;
                            }
                            try {
                                strictLineReader$1.close();
                                // monitorexit(inputStream)
                                return string;
                            }
                            catch (Throwable t3) {
                                throw new NullPointerException();
                            }
                        }
                        strictLineReader$1.close();
                        continue Label_0306_Outer;
                    }
                }
                catch (Throwable t) {
                    try {
                        throw t;
                    }
                    finally {}
                    while (true) {
                        if (strictLineReader$1 == null) {
                            break Label_0321;
                        }
                        Label_0335: {
                            if (t == null) {
                                break Label_0335;
                            }
                            try {
                                strictLineReader$1.close();
                                throw;
                            }
                            catch (Throwable strictLineReader$1) {
                                t.addSuppressed((Throwable)strictLineReader$1);
                                throw;
                            }
                        }
                        strictLineReader$1.close();
                        continue;
                    }
                }
                finally {
                    continue;
                }
                break;
            }
            continue;
        }
    }
}
