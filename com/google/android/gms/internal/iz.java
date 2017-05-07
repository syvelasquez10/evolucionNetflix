// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import com.google.android.gms.common.internal.m;
import android.graphics.drawable.Drawable;

public final class iz extends ja<a, Drawable>
{
    public iz() {
        super(10);
    }
    
    public static final class a
    {
        public final int Li;
        public final int Lj;
        
        public a(final int li, final int lj) {
            this.Li = li;
            this.Lj = lj;
        }
        
        @Override
        public boolean equals(final Object o) {
            final boolean b = true;
            boolean b2;
            if (!(o instanceof a)) {
                b2 = false;
            }
            else {
                b2 = b;
                if (this != o) {
                    final a a = (a)o;
                    if (a.Li == this.Li) {
                        b2 = b;
                        if (a.Lj == this.Lj) {
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
}
