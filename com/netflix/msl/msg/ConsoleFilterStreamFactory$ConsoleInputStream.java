// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.InputStream;
import java.io.FilterInputStream;

class ConsoleFilterStreamFactory$ConsoleInputStream extends FilterInputStream
{
    protected ConsoleFilterStreamFactory$ConsoleInputStream(final InputStream inputStream) {
        super(inputStream);
    }
    
    @Override
    public void close() {
        System.out.println();
        System.out.flush();
        super.close();
    }
    
    @Override
    public int read() {
        final int read = super.read();
        System.out.write(read);
        System.out.flush();
        return read;
    }
    
    @Override
    public int read(final byte[] array, final int n, final int n2) {
        final int read = super.read(array, n, n2);
        System.out.write(array, n, n2);
        System.out.flush();
        return read;
    }
}
