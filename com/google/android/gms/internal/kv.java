// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;

public final class kv
{
    final byte[] adZ;
    final int tag;
    
    kv(final int tag, final byte[] adZ) {
        this.tag = tag;
        this.adZ = adZ;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof kv)) {
                return false;
            }
            final kv kv = (kv)o;
            if (this.tag != kv.tag || !Arrays.equals(this.adZ, kv.adZ)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.adZ);
    }
}
