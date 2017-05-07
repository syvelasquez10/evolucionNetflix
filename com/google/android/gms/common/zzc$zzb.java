// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.common;

import java.util.Arrays;

class zzc$zzb extends zzc$zza
{
    private final byte[] zzaah;
    
    zzc$zzb(final byte[] zzaah) {
        super(Arrays.copyOfRange(zzaah, 0, 25));
        this.zzaah = zzaah;
    }
    
    @Override
    byte[] getBytes() {
        return this.zzaah;
    }
}
