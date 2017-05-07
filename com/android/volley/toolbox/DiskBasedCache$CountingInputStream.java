// 
// Decompiled by Procyon v0.5.30
// 

package com.android.volley.toolbox;

import java.io.InputStream;
import java.io.FilterInputStream;

class DiskBasedCache$CountingInputStream extends FilterInputStream
{
    private int bytesRead;
    
    private DiskBasedCache$CountingInputStream(final InputStream inputStream) {
        super(inputStream);
        this.bytesRead = 0;
    }
    
    @Override
    public int read() {
        final int read = super.read();
        if (read != -1) {
            ++this.bytesRead;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, int read, final int n) {
        read = super.read(array, read, n);
        if (read != -1) {
            this.bytesRead += read;
        }
        return read;
    }
}
