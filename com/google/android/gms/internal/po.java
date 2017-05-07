// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.util.Arrays;
import java.io.IOException;

final class po
{
    final byte[] awK;
    final int tag;
    
    po(final int tag, final byte[] awK) {
        this.tag = tag;
        this.awK = awK;
    }
    
    void a(final pf pf) throws IOException {
        pf.gz(this.tag);
        pf.t(this.awK);
    }
    
    int c() {
        return 0 + pf.gA(this.tag) + this.awK.length;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (o != this) {
            if (!(o instanceof po)) {
                return false;
            }
            final po po = (po)o;
            if (this.tag != po.tag || !Arrays.equals(this.awK, po.awK)) {
                return false;
            }
        }
        return true;
    }
    
    @Override
    public int hashCode() {
        return (this.tag + 527) * 31 + Arrays.hashCode(this.awK);
    }
}
