// 
// Decompiled by Procyon v0.5.30
// 

package com.netflix.falkor.cache.lru;

import java.io.IOException;
import java.io.OutputStream;
import java.io.FilterOutputStream;

class DiskLruJournal$Editor$FaultHidingOutputStream extends FilterOutputStream
{
    final /* synthetic */ DiskLruJournal$Editor this$1;
    
    private DiskLruJournal$Editor$FaultHidingOutputStream(final DiskLruJournal$Editor this$1, final OutputStream outputStream) {
        this.this$1 = this$1;
        super(outputStream);
    }
    
    @Override
    public void close() {
        try {
            this.out.close();
        }
        catch (IOException ex) {
            this.this$1.hasErrors = true;
        }
    }
    
    @Override
    public void flush() {
        try {
            this.out.flush();
        }
        catch (IOException ex) {
            this.this$1.hasErrors = true;
        }
    }
    
    @Override
    public void write(final int n) {
        try {
            this.out.write(n);
        }
        catch (IOException ex) {
            this.this$1.hasErrors = true;
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        try {
            this.out.write(array, n, n2);
        }
        catch (IOException ex) {
            this.this$1.hasErrors = true;
        }
    }
}
