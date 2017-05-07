// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzc$zzc extends zzc$zza
{
    private static final WeakReference<byte[]> zzaaj;
    private WeakReference<byte[]> zzaai;
    
    static {
        zzaaj = new WeakReference<byte[]>(null);
    }
    
    zzc$zzc(final byte[] array) {
        super(array);
        this.zzaai = zzc$zzc.zzaaj;
    }
    
    @Override
    byte[] getBytes() {
        synchronized (this) {
            byte[] zznr;
            if ((zznr = this.zzaai.get()) == null) {
                zznr = this.zznr();
                this.zzaai = new WeakReference<byte[]>(zznr);
            }
            return zznr;
        }
    }
    
    protected abstract byte[] zznr();
}
