// 
// Decompiled by Procyon v0.5.30
// 

package com.facebook.internal;

import java.io.OutputStream;

class FileLruCache$CloseCallbackOutputStream extends OutputStream
{
    final FileLruCache$StreamCloseCallback callback;
    final OutputStream innerStream;
    
    FileLruCache$CloseCallbackOutputStream(final OutputStream innerStream, final FileLruCache$StreamCloseCallback callback) {
        this.innerStream = innerStream;
        this.callback = callback;
    }
    
    @Override
    public void close() {
        try {
            this.innerStream.close();
        }
        finally {
            this.callback.onClose();
        }
    }
    
    @Override
    public void flush() {
        this.innerStream.flush();
    }
    
    @Override
    public void write(final int n) {
        this.innerStream.write(n);
    }
    
    @Override
    public void write(final byte[] array) {
        this.innerStream.write(array);
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        this.innerStream.write(array, n, n2);
    }
}
