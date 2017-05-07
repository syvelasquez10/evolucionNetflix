// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import android.graphics.drawable.Drawable;

public final class fa extends fu<a, Drawable>
{
    public fa() {
        super(10);
    }
    
    public static final class a
    {
        public final int CR;
        public final int CS;
        
        public a(final int cr, final int cs) {
            this.CR = cr;
            this.CS = cs;
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
                    if (a.CR == this.CR) {
                        b2 = b;
                        if (a.CS == this.CS) {
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
            return fo.hashCode(this.CR, this.CS);
        }
    }
}
