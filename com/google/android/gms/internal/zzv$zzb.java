// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.InputStream;
import java.io.FilterInputStream;

class zzv$zzb extends FilterInputStream
{
    private int zzaC;
    
    private zzv$zzb(final InputStream inputStream) {
        super(inputStream);
        this.zzaC = 0;
    }
    
    @Override
    public int read() {
        final int read = super.read();
        if (read != -1) {
            ++this.zzaC;
        }
        return read;
    }
    
    @Override
    public int read(final byte[] array, int read, final int n) {
        read = super.read(array, read, n);
        if (read != -1) {
            this.zzaC += read;
        }
        return read;
    }
}
