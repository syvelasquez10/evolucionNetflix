// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.Arrays;

class zzc$zzb extends zzc$zza
{
    private final byte[] zzVN;
    
    zzc$zzb(final byte[] zzVN) {
        super(Arrays.copyOfRange(zzVN, 0, 25));
        this.zzVN = zzVN;
    }
    
    @Override
    byte[] getBytes() {
        return this.zzVN;
    }
}
