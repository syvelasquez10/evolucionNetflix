// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzc$zzc extends zzc$zza
{
    private static final WeakReference<byte[]> zzYs;
    private WeakReference<byte[]> zzYr;
    
    static {
        zzYs = new WeakReference<byte[]>(null);
    }
    
    zzc$zzc(final byte[] array) {
        super(array);
        this.zzYr = zzc$zzc.zzYs;
    }
    
    @Override
    byte[] getBytes() {
        synchronized (this) {
            byte[] zzmV;
            if ((zzmV = this.zzYr.get()) == null) {
                zzmV = this.zzmV();
                this.zzYr = new WeakReference<byte[]>(zzmV);
            }
            return zzmV;
        }
    }
    
    protected abstract byte[] zzmV();
}
