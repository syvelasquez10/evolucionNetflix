// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;

final class zzrz
{
    final int tag;
    final byte[] zzbcm;
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof zzrz)) {
                return false;
            }
            final zzrz zzrz = (zzrz)o;
            if (this.tag != zzrz.tag || !Arrays.equals(this.zzbcm, zzrz.zzbcm)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.zzbcm);
    }
    
    int zzB() {
        return 0 + zzrq.zzlx(this.tag) + this.zzbcm.length;
    }
    
    void zza(final zzrq zzrq) {
        zzrq.zzlw(this.tag);
        zzrq.zzD(this.zzbcm);
    }
}
