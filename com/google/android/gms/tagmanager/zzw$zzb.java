// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.tagmanager;

import java.util.Arrays;

class zzw$zzb
{
    final byte[] zzaPS;
    final String zztP;
    
    zzw$zzb(final String zztP, final byte[] zzaPS) {
        this.zztP = zztP;
        this.zzaPS = zzaPS;
    }
    
    @Override
    public String toString() {
        return "KeyAndSerialized: key = " + this.zztP + " serialized hash = " + Arrays.hashCode(this.zzaPS);
    }
}
