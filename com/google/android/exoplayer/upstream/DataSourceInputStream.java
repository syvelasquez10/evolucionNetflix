// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.exoplayer.upstream;

import com.google.android.exoplayer.util.Assertions;
import java.io.InputStream;

public final class DataSourceInputStream extends InputStream
{
    private boolean closed;
    private final DataSource dataSource;
    private final DataSpec dataSpec;
    private boolean opened;
    private final byte[] singleByteArray;
    
    public DataSourceInputStream(final DataSource dataSource, final DataSpec dataSpec) {
        this.opened = false;
        this.closed = false;
        this.dataSource = dataSource;
        this.dataSpec = dataSpec;
        this.singleByteArray = new byte[1];
    }
    
    private void checkOpened() {
        if (!this.opened) {
            this.dataSource.open(this.dataSpec);
            this.opened = true;
        }
    }
    
    @Override
    public void close() {
        if (!this.closed) {
            this.dataSource.close();
            this.closed = true;
        }
    }
    
    public void open() {
        this.checkOpened();
    }
    
    @Override
    public int read() {
        if (this.read(this.singleByteArray) == -1) {
            return -1;
        }
        return this.singleByteArray[0] & 0xFF;
    }
    
    @Override
    public int read(final byte[] array) {
        return this.read(array, 0, array.length);
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        Assertions.checkState(!this.closed);
        this.checkOpened();
        return this.dataSource.read(array, n, n2);
    }
    
    @Override
    public long skip(final long n) {
        Assertions.checkState(!this.closed);
        this.checkOpened();
        return super.skip(n);
    }
}
