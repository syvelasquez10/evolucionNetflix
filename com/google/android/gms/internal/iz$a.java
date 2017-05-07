// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.m;

public final class iz$a
{
    public final int Li;
    public final int Lj;
    
    public iz$a(final int li, final int lj) {
        this.Li = li;
        this.Lj = lj;
    }
    
    @Override
    public boolean equals(final Object o) {
        final boolean b = true;
        boolean b2;
        if (!(o instanceof iz$a)) {
            b2 = false;
        }
        else {
            b2 = b;
            if (this != o) {
                final iz$a iz$a = (iz$a)o;
                if (iz$a.Li == this.Li) {
                    b2 = b;
                    if (iz$a.Lj == this.Lj) {
                        return b2;
                    }
                }
                return false;
            }
        }
        return b2;
    }
    
    @Override
    public int hashCode() {
        return m.hashCode(this.Li, this.Lj);
    }
}
