// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.InputStream;
import com.netflix.msl.io.Url$Connection;
import java.io.FilterInputStream;

class MslControl$RequestService$DelayedInputStream extends FilterInputStream
{
    private final Url$Connection conn;
    final /* synthetic */ MslControl$RequestService this$1;
    
    public MslControl$RequestService$DelayedInputStream(final MslControl$RequestService this$1, final Url$Connection conn) {
        this.this$1 = this$1;
        super(null);
        this.conn = conn;
    }
    
    @Override
    public int available() {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        return super.available();
    }
    
    @Override
    public void close() {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        super.close();
    }
    
    @Override
    public void mark(final int n) {
    }
    // monitorenter(this)
    // monitorexit(this)
    
    @Override
    public boolean markSupported() {
        return false;
    }
    
    @Override
    public int read() {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        return this.in.read();
    }
    
    @Override
    public int read(final byte[] array) {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        return super.read(array);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        return super.read(array, n, n2);
    }
    
    @Override
    public void reset() {
        synchronized (this) {
            if (this.in == null) {
                this.in = this.conn.getInputStream();
            }
            super.reset();
        }
    }
    
    @Override
    public long skip(final long n) {
        if (this.in == null) {
            this.in = this.conn.getInputStream();
        }
        return super.skip(n);
    }
}
