// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzc$zzc extends zzc$zza
{
    private static final WeakReference<byte[]> zzVP;
    private WeakReference<byte[]> zzVO;
    
    static {
        zzVP = new WeakReference<byte[]>(null);
    }
    
    zzc$zzc(final byte[] array) {
        super(array);
        this.zzVO = zzc$zzc.zzVP;
    }
    
    @Override
    byte[] getBytes() {
        synchronized (this) {
            byte[] zzmi;
            if ((zzmi = this.zzVO.get()) == null) {
                zzmi = this.zzmi();
                this.zzVO = new WeakReference<byte[]>(zzmi);
            }
            return zzmi;
        }
    }
    
    protected abstract byte[] zzmi();
}
