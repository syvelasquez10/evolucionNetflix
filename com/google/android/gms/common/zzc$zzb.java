// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.Arrays;

class zzc$zzb extends zzc$zza
{
    private final byte[] zzYq;
    
    zzc$zzb(final byte[] zzYq) {
        super(Arrays.copyOfRange(zzYq, 0, 25));
        this.zzYq = zzYq;
    }
    
    @Override
    byte[] getBytes() {
        return this.zzYq;
    }
}
