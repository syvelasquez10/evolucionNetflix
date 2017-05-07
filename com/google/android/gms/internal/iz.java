// 
// Decompiled by Procyon v0.5.30
// 

package com.google.android.gms.internal;

import java.io.IOException;

public abstract class iz
{
    protected int rw;
    
    public iz() {
        this.rw = -1;
    }
    
    public static final <T extends iz> T a(final T t, final byte[] array) throws iy {
        return b(t, array, 0, array.length);
    }
    
    public static final void a(final iz iz, final byte[] array, final int n, final int n2) {
        try {
            final ix b = ix.b(array, n, n2);
            iz.a(b);
            b.gf();
        }
        catch (IOException ex) {
            throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", ex);
        }
    }
    
    public static final byte[] a(final iz iz) {
        final byte[] array = new byte[iz.cP()];
        a(iz, array, 0, array.length);
        return array;
    }
    
    public static final <T extends iz> T b(final T t, final byte[] array, final int n, final int n2) throws iy {
        try {
            final iw a = iw.a(array, n, n2);
            t.b(a);
            a.bI(0);
            return t;
        }
        catch (iy iy) {
            throw iy;
        }
        catch (IOException ex) {
            throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).");
        }
    }
    
    public abstract void a(final ix p0) throws IOException;
    
    public abstract iz b(final iw p0) throws IOException;
    
    public int cP() {
        return this.rw = 0;
    }
    
    @Override
    public String toString() {
        return ja.b(this);
    }
}
