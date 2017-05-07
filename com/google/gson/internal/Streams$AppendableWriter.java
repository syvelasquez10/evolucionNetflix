// 
// Decompiled by Procyon v0.5.30
// 

package com.google.gson.internal;

import java.io.Writer;

class Streams$AppendableWriter extends Writer
{
    private final Appendable appendable;
    private final Streams$AppendableWriter$CurrentWrite currentWrite;
    
    private Streams$AppendableWriter(final Appendable appendable) {
        this.currentWrite = new Streams$AppendableWriter$CurrentWrite();
        this.appendable = appendable;
    }
    
    @Override
    public void close() {
    }
    
    @Override
    public void flush() {
    }
    
    @Override
    public void write(final int n) {
        this.appendable.append((char)n);
    }
    
    @Override
    public void write(final char[] chars, final int n, final int n2) {
        this.currentWrite.chars = chars;
        this.appendable.append(this.currentWrite, n, n + n2);
    }
}
