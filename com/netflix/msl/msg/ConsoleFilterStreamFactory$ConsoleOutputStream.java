// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.msl.msg;

import java.io.OutputStream;
import java.io.FilterOutputStream;

class ConsoleFilterStreamFactory$ConsoleOutputStream extends FilterOutputStream
{
    public ConsoleFilterStreamFactory$ConsoleOutputStream(final OutputStream outputStream) {
        super(outputStream);
    }
    
    @Override
    public void close() {
        System.out.println();
        System.out.flush();
        super.close();
    }
    
    @Override
    public void write(final int n) {
        System.out.write(n);
        System.out.flush();
        super.write(n);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        System.out.write(array, n, n2);
        System.out.flush();
        super.write(array, n, n2);
    }
}
