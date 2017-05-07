// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;

public class zzaa extends ByteArrayOutputStream
{
    private final zzu zzar;
    
    public zzaa(final zzu zzar, final int n) {
        this.zzar = zzar;
        this.buf = this.zzar.zzb(Math.max(n, 256));
    }
    
    private void zzd(final int n) {
        if (this.count + n <= this.buf.length) {
            return;
        }
        final byte[] zzb = this.zzar.zzb((this.count + n) * 2);
        System.arraycopy(this.buf, 0, zzb, 0, this.count);
        this.zzar.zza(this.buf);
        this.buf = zzb;
    }
    
    @Override
    public void close() {
        this.zzar.zza(this.buf);
        this.buf = null;
        super.close();
    }
    
    public void finalize() {
        this.zzar.zza(this.buf);
    }
    
    @Override
    public void write(final int n) {
        synchronized (this) {
            this.zzd(1);
            super.write(n);
        }
    }
    
    @Override
    public void write(final byte[] array, final int n, final int n2) {
        synchronized (this) {
            this.zzd(n2);
            super.write(array, n, n2);
        }
    }
}
